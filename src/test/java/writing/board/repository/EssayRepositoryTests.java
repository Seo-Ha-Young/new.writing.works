package writing.board.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import writing.board.entity.Essay;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

@SpringBootTest
public class EssayRepositoryTests {
    @Autowired
    private EssayRepository essayRepository;
    @Commit
    @Transactional
    @Test
    public void insertEassy() {
        IntStream.rangeClosed(1,10).forEach(i ->{
            Essay essay = Essay.builder()
                    .essay_content("content number "+i).build();
            System.out.println("=======================================================");
            essayRepository.save(essay);
            System.out.println("--------------------------------------------------------");
        });
    }

    @Test
    public void testGetEssayWithAll() {
        List<Object[]> result = essayRepository.getEssayWithAll(4L);
        System.out.println("-----------------------------------------------------");
        System.out.println(result);
        for(Object[] arr : result){
            System.out.println(Arrays.toString(arr));
            System.out.println("-----------------------------------------------------");
        }
    }

    @Test
    public void testGetEssayWithAll2() {
        List<Object[]> result = essayRepository.getEssayWithAll();
        System.out.println("-----------------------------------------------------");
        System.out.println(result);
        for(Object[] arr : result){
            System.out.println(Arrays.toString(arr));
            System.out.println("-----------------------------------------------------");
        }
    }
}
