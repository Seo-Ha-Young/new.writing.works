package writing.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import writing.board.entity.Essay;
import writing.board.entity.Image;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {

    @Query("select no, regDate, img_name from Image where no = :no")
    List<Object[]> getImageWithAll(Long no);

    @Query("select no, regDate, img_name from Image")
    List<Object[]> getImageWithAll();
}
