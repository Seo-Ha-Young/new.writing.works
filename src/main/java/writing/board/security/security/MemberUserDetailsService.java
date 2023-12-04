package writing.board.security.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import writing.board.dto.MemberDTO;
import writing.board.entity.Member;
import writing.board.repository.MemberRepository;
import writing.board.security.dto.AuthMemberDTO;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;
    @Transactional
    public Long joinMember(MemberDTO memberDTO) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        memberDTO.setPassword(bCryptPasswordEncoder.encode(memberDTO.getPassword()));
        return memberRepository.save(memberDTO.toEntity()).getNo();
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
