package writing.board.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import writing.board.entity.PostWritten;
import writing.board.entity.Recommendation;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class RecommendationRepositoryTests {
    @Autowired
    private RecommendationRepository recommendationRepository;
    @Commit
    @Transactional
    @Test
    public void insertRecommend() {
        IntStream.rangeClosed(1,10).forEach(i ->{
            Recommendation recommendation = Recommendation.builder()
                    .id("id"+i)
                    .post_no((long) i)
                    .push(true)
                    .build();
            System.out.println("=======================================================");
            recommendationRepository.save(recommendation);
            System.out.println("--------------------------------------------------------");
        });
    }

    @Test
    public void testGetRecommendationWithAll() {
        List<Object[]> result = recommendationRepository.getRecommendationWithAll(4L);
        System.out.println("-----------------------------------------------------");
        System.out.println(result);
        for(Object[] arr : result){
            System.out.println(Arrays.toString(arr));
            System.out.println("-----------------------------------------------------");
        }
    }

    @Test
    public void testGetRecommendationWithAll2() {
        List<Object[]> result = recommendationRepository.getRecommendationWithAll();
        System.out.println("-----------------------------------------------------");
        System.out.println(result);
        for(Object[] arr : result){
            System.out.println(Arrays.toString(arr));
            System.out.println("-----------------------------------------------------");
        }
    }
}
