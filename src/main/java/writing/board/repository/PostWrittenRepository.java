package writing.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import writing.board.entity.PostWritten;

import java.util.List;

public interface PostWrittenRepository extends JpaRepository<PostWritten, Long>, QuerydslPredicateExecutor<PostWritten> {

    @Query(value = "select p.no, p.post_name, p.post_content, p.writer, p.image_no, p.regDate, r.push from PostWritten p "
            +"left join Recommendation r on p.no = r.post_no"
            +"where p.no = :no", nativeQuery = true)
    List<Object[]> getPostWithAll(Long no);

    @Query(value = "select p.no, p.post_name, p.post_content, p.writer, p.image_no, p.regDate, r.push from PostWritten p "
            +"left join Recommendation r on p.no = r.post_no", nativeQuery = true)
    List<Object[]> getPostWithAll();

    @Query("select p.no, p.post_name, p.post_content, p.writer, p.image_no, p.regDate, i.img_name from PostWritten p "
            +"left join Image i on i.no = p.image_no "
 //           +"left outer join Essay e.postWritten = p"
            +"where p.no = :no ")
    List<Object[]> getPostWritten_no(long no);
}
