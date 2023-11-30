package writing.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import writing.board.entity.PostWritten;

import java.util.List;

public interface PostWrittenRepository extends JpaRepository<PostWritten, Long>, QuerydslPredicateExecutor<PostWritten> {

    @Query("select no, post_name, regDate, post_content, writer, image_no from PostWritten where no = :no")
    List<Object[]> getPostWithAll(Long no);

    @Query("select no, post_name, regDate, post_content, writer, image_no from PostWritten")
    List<Object[]> getPostWithAll();

    @Query("select p, i.img_name from PostWritten p "
            +"left join Image i on i.no = p.image_no "
 //           +"left outer join Essay e.postWritten = p"
            +"where p.no = :no ")
    List<Object[]> getPostWritten_no(long no);
}
