package writing.board.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import writing.board.entity.Image;
import writing.board.entity.Member;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTests {
    @Autowired
    private MemberRepository memberRepository;
    @Commit
    @Transactional
    @Test
    public void insertMember() {
        IntStream.rangeClosed(1,10).forEach(i ->{
            Member member = Member.builder()
                    .address("address"+i)
                    .name("name"+i)
                    .id("id"+i)
                    .password("password"+i)
                    .dateOfBirth(new Date())
                    .email("email"+i+"@email.com")
                    .nickName("nickName"+i)
                    .build();
            System.out.println("=======================================================");
            memberRepository.save(member);
            System.out.println("--------------------------------------------------------");
        });
    }

    @Test
    public void testGetMemberWithAll() {
        List<Object[]> result = memberRepository.getMemberWithAll(4L);
        System.out.println("-----------------------------------------------------");
        System.out.println(result);
        for(Object[] arr : result){
            System.out.println(Arrays.toString(arr));
            System.out.println("-----------------------------------------------------");
        }
    }

    @Test
    public void testGetMemberWithAll2() {
        List<Object[]> result = memberRepository.getMemberWithAll();
        System.out.println("-----------------------------------------------------");
        System.out.println(result);
        for(Object[] arr : result){
            System.out.println(Arrays.toString(arr));
            System.out.println("-----------------------------------------------------");
        }
    }
}
