package writing.board.repository;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import writing.board.entity.PostWritten;

import java.util.List;


public interface PostWrittenRepository extends JpaRepository<PostWritten, Long>, QuerydslPredicateExecutor<PostWritten> {

    @Query("select p, count(r.bad), count(r.good) from PostWritten p "
            +"left outer join Tests r on r.postWritten = p "
            +"where p.no = :no group by p")
    List<Object[]> getPostWithAll(Long no);

    @Query(value = "select p.no, p.post_name, p.post_content, p.writer, p.image_no, p.regDate, p.count(r.bad), p.count(r.good) from Post_Written p "
            +"left join Tests r on r.post_written = p", nativeQuery = true)
    List<Object[]> getPostWithAll();

    @Query("select p.no, p.post_name, p.post_content, p.writer, p.image_no, p.regDate, i.img_name from PostWritten p "
            +"left join Image i on i.no = p.image_no "
 //           +"left outer join Essay e.postWritten = p"
            +"where p.no = :no ")
    List<Object[]> getPostWritten_no(long no);

    @Query(value = "select p, count(r.bad) as badCnt, count(r.good) as goodCnt from PostWritten p "
            +"left outer join Tests r on r.postWritten = p "
            +"group by p")
    Page<Object[]> getListPage(Pageable pageable);
}
