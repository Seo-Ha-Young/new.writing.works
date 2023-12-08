package writing.board.security.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ResponseStatus;
import writing.board.dto.MemberDTO;
import writing.board.entity.Member;
import writing.board.repository.MemberRepository;
import writing.board.security.dto.AuthMemberDTO;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        /* 유효성 및 중복 검사에 실패한 필드 목록을 받음 */
        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }

        return validatorResult;
    }

    public MemberDTO findMember(String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("이메일이 존재하지 않습니다."));

        MemberDTO memberDTO = MemberDTO.toDTO(member);

        return memberDTO;
    }

    public Long updateMember(MemberDTO memberDTO) {
        Member member = memberRepository.findByEmail(memberDTO.getEmail()).orElseThrow(() -> new UsernameNotFoundException("이메일이 존재하지 않습니다."));

        member.updateEmail(memberDTO.getEmail());
        memberRepository.save(member);

        return member.getNo();
    }

    @Transactional
    public Long joinMember(MemberDTO memberDTO) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        memberDTO.setPassword(bCryptPasswordEncoder.encode(memberDTO.getPassword()));
        return memberRepository.save(memberDTO.toEntity()).getNo();
    }

    public MemberDTO findById(Long no) {
        Optional<Member> result = memberRepository.findById(no);
        if(result.isPresent()) {
            return MemberDTO.toDTO(result.get());
        }
        else {
            return null;
        }
    }

    public boolean deleteMember(String email, String password) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("이메일이 존재하지 않습니다."));

        if (bCryptPasswordEncoder.matches(password, member.getPassword())) {
            memberRepository.delete(member);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername: " + username);
        Optional<Member> result = memberRepository.findByEmail(username);

        if(result.isEmpty()) {
            throw new UsernameNotFoundException("사용자가 없습니다.");
        }

        Member member = result.get();

        AuthMemberDTO authMemberDTO = new AuthMemberDTO(
                member.getNo(),
                member.getEmail(),
                member.getPassword(),
                member.getNickname(),
                member.getName(),
                member.getBirth(),
                member.getAddress(),
                member.getRoleSet().stream().map(memberRole -> new SimpleGrantedAuthority("ROLE_" + memberRole.name())).collect(Collectors.toList())
        );
        log.info("AuthMemberDTO: " + authMemberDTO);

        return authMemberDTO;
    }
}
