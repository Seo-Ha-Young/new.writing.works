package writing.board.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import writing.board.entity.Member;
import writing.board.entity.PostWritten;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class PostWrittenRepositoryTests {
    @Autowired
    private PostWrittenRepository postWrittenRepository;
    @Commit
    @Transactional
    @Test
    public void insertPost() {
        IntStream.rangeClosed(51,60).forEach(i ->{
            PostWritten postWritten = PostWritten.builder()
                    .post_content("작성내용"+i)
                    .writer("닉네임"+(i-50))
                    .image_no((long) (i-48))
                    .build();
            System.out.println("=======================================================");
            postWrittenRepository.save(postWritten);
            System.out.println("--------------------------------------------------------");
        });
    }

    @Test
    public void testGetPostWithAll() {
        List<Object[]> result = postWrittenRepository.getPostWithAll(4L);
        System.out.println("-----------------------------------------------------");
        System.out.println(result);
        for(Object[] arr : result){
            System.out.println(Arrays.toString(arr));
            System.out.println("-----------------------------------------------------");
        }
    }

    @Test
    public void testGetPostWithAll2() {
        List<Object[]> result = postWrittenRepository.getPostWithAll();
        System.out.println("-----------------------------------------------------");
        System.out.println(result);
        for(Object[] arr : result){
            System.out.println(Arrays.toString(arr));
            System.out.println("-----------------------------------------------------");
        }
    }
}
