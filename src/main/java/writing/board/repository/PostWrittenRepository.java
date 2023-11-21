package writing.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import writing.board.entity.Essay;
import writing.board.entity.PostWritten;

import java.util.List;

public interface PostWrittenRepository extends JpaRepository<PostWritten, Long> {

    @Query("select no, regDate, post_content, writer, image_no from PostWritten where no = :no")
    List<Object[]> getPostWithAll(Long no);

    @Query("select no, regDate, post_content, writer, image_no from PostWritten")
    List<Object[]> getPostWithAll();
}
