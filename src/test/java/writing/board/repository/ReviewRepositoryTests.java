package writing.board.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import writing.board.entity.Recommendation;
import writing.board.entity.Review;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class ReviewRepositoryTests {
    @Autowired
    private ReviewRepository reviewRepository;
    @Commit
    @Transactional
    @Test
    public void insertReview() {
        IntStream.rangeClosed(1,10).forEach(i ->{
            Review review = Review.builder()
                    .id("id"+i)
                    .post_no((long) i)
                    .review_content("review_content"+i)
                    .build();
            System.out.println("=======================================================");
            reviewRepository.save(review);
            System.out.println("--------------------------------------------------------");
        });
    }

    @Test
    public void testGetReviewWithAll() {
        List<Object[]> result = reviewRepository.getReviewWithAll(4L);
        System.out.println("-----------------------------------------------------");
        System.out.println(result);
        for(Object[] arr : result){
            System.out.println(Arrays.toString(arr));
            System.out.println("-----------------------------------------------------");
        }
    }

    @Test
    public void testGetReviewWithAll2() {
        List<Object[]> result = reviewRepository.getReviewWithAll();
        System.out.println("-----------------------------------------------------");
        System.out.println(result);
        for(Object[] arr : result){
            System.out.println(Arrays.toString(arr));
            System.out.println("-----------------------------------------------------");
        }
    }
}
