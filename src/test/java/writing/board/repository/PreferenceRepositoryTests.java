package writing.board.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import writing.board.entity.PostWritten;
import writing.board.entity.Preference;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class PreferenceRepositoryTests {
    @Autowired
    private PreferenceRepository preferenceRepository;
    @Commit
    @Transactional
    @Test
    public void insertRecommend() {
        IntStream.rangeClosed(1,5).forEach(i ->{
            Preference preference = Preference.builder()
                    .postWritten((PostWritten.builder().no((long)i)).build())
                    .bad((long)1)
                    .build();
            System.out.println("=======================================================");
            preferenceRepository.save(preference);
            System.out.println("--------------------------------------------------------");
        });
    }

    @Test
    public void testGetRecommendationWithAll() {
        List<Object[]> result = preferenceRepository.getTestWithAll(4L);
        System.out.println("-----------------------------------------------------");
        System.out.println(result);
        for(Object[] arr : result){
            System.out.println(Arrays.toString(arr));
            System.out.println("-----------------------------------------------------");
        }
    }

    @org.junit.jupiter.api.Test
    public void testGetRecommendationWithAll2() {
        List<Object[]> result = preferenceRepository.getTestWithAll();
        System.out.println("-----------------------------------------------------");
        System.out.println(result);
        for(Object[] arr : result){
            System.out.println(Arrays.toString(arr));
            System.out.println("-----------------------------------------------------");
        }
    }
    @Test
    public void testGetNicknameWithAll() {
        Preference result = preferenceRepository.getNicknameWithAll((long) 8, (long) 3);
        System.out.println("-----------------------------------------------------");
        System.out.println(result);

    }
}
