package writing.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import writing.board.dto.MemberDTO;
import writing.board.entity.Member;
import writing.board.entity.MemberRole;
import writing.board.repository.MemberRepository;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Long saveMember(MemberDTO memberDTO) {
        memberDTO.setPassword(bCryptPasswordEncoder.encode(memberDTO.getPassword()));
        memberRepository.save(memberDTO.toEntity());
        return memberDTO.toEntity().getNo();
    }

//    @Override
//    public void saveMember(MemberDTO memberDTO) {
//        Member member = Member.builder()
//                .email(memberDTO.getEmail())
//                .password(bCryptPasswordEncoder.encode(memberDTO.getPassword()))
//                .nickname(memberDTO.getNickname())
//                .name(memberDTO.getName())
//                .birth(memberDTO.getBirth())
//                .address(memberDTO.getAddress())
//                .build();
//        member.addMemberRole(MemberRole.USER);
//        memberRepository.save(member);
//    }
}
