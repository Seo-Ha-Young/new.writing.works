package writing.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import writing.board.entity.Essay;
import writing.board.entity.Image;
import writing.board.entity.PostWritten;

import java.util.List;

public interface PostWrittenRepository extends JpaRepository<PostWritten, Long>, QuerydslPredicateExecutor<PostWritten> {

    @Query("select no, regDate, post_content, writer, image_no from PostWritten where no = :no")
    List<Object[]> getPostWithAll(Long no);

    @Query("select no, regDate, post_content, writer, image_no from PostWritten")
    List<Object[]> getPostWithAll();

//    @Query("select p, i.img_name from PostWritten p "
//            +"left join Image i on i.no = p.image_no "
//            +"group by p ")
//    Page<PostWritten> getListPage(Pageable pageable);
}
