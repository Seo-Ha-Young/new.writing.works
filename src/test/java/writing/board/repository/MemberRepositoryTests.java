package writing.board.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import writing.board.entity.Member;
import writing.board.entity.MemberRole;
import writing.board.security.security.MemberUserDetailsService;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
public class MemberRepositoryTests {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberUserDetailsService memberService;
    @Test
    public void testEncode(){
        String password = "1111";
        String enPw = passwordEncoder.encode(password);
        System.out.println("enPw: "+enPw);
        boolean matchResult = passwordEncoder.matches(password, enPw);
        System.out.println("matchResult: "+matchResult);
    }


    @Test
    public void insertDummies(){
        IntStream.rangeClosed(1,10).forEach(i->{
            Member member = Member.builder()
                    .email("user"+i+"@email.com")
                    .password(passwordEncoder.encode("1111"))
                    .nickname("닉네임"+i)
                    .name("사용자"+i)
                    .birth("2000-11-11")
                    .address("주소"+i)
                    .build();
            member.addMemberRole(MemberRole.USER);
            if(i == 1)
                member.addMemberRole(MemberRole.ADMIN);
            memberRepository.save(member);
        });
    }

    @Test
    public void testRead(){
        Optional<Member> result = memberRepository.findByEmail("user1@email.com");

        Member member = result.orElseThrow();

        System.out.println(member);
        System.out.println(member.getRoleSet());

        member.getRoleSet().forEach(memberRole -> System.out.println(memberRole.name()));
    }


}
