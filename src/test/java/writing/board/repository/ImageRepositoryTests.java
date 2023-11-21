package writing.board.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import writing.board.entity.Essay;
import writing.board.entity.Image;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class ImageRepositoryTests {
    @Autowired
    private ImageRepository imageRepository;
    @Commit
    @Transactional
    @Test
    public void insertImage() {
        IntStream.rangeClosed(1,10).forEach(i ->{
            Image image = Image.builder()
                    .img_name("image_name"+i)
                    .build();
            System.out.println("=======================================================");
            imageRepository.save(image);
            System.out.println("--------------------------------------------------------");
        });
    }

    @Test
    public void testGetImageWithAll() {
        List<Object[]> result = imageRepository.getImageWithAll(4L);
        System.out.println("-----------------------------------------------------");
        System.out.println(result);
        for(Object[] arr : result){
            System.out.println(Arrays.toString(arr));
            System.out.println("-----------------------------------------------------");
        }
    }

    @Test
    public void testGetImageWithAll2() {
        List<Object[]> result = imageRepository.getImageWithAll();
        System.out.println("-----------------------------------------------------");
        System.out.println(result);
        for(Object[] arr : result){
            System.out.println(Arrays.toString(arr));
            System.out.println("-----------------------------------------------------");
        }
    }
}
