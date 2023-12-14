package writing.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import writing.board.dto.PostWrittenDTO;
import writing.board.entity.Image;
import writing.board.entity.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query(value = "select no, reg_date, review_content, member_no, post_no from Review where no = :no", nativeQuery = true)
    List<Object[]> getReviewWithAll(Long no);

    @Query(value = "select no, reg_date, review_content, member_no, post_no from Review", nativeQuery = true)
    List<Object[]> getReviewWithAll();

    @Query(value = "select r.no, r.reg_date, r.review_content, r.member_no, r.post_no from Review r "
            +"left join post_written p on p.no = r.post_no "
            +"where post_no = :no", nativeQuery = true)
    List<Review> findByPostWritten(Long no);
}
