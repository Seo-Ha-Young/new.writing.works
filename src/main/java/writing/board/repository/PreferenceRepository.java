package writing.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import writing.board.entity.Member;
import writing.board.entity.PostWritten;
import writing.board.entity.Preference;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface PreferenceRepository extends JpaRepository<Preference, Long> {

    @Query(value = "select no, reg_date, member_no, good, bad from Preference where no = :no", nativeQuery = true)
    List<Object[]> getTestWithAll(Long no);

    @Query(value = "select no, reg_date, member_no,  good, bad from Preference", nativeQuery = true)
    List<Object[]> getTestWithAll();

    @Query(value = "select p.no, p.reg_date, p.good, p.bad, p.post_written_no, p.member_no from Preference p "
            +"where p.post_written_no = :no", nativeQuery = true)
    List<Preference> getTestWithPost_No(Long no);



    @Query(value = "select p.no, p.reg_date, p.good, p.bad, p.post_written_no, p.member_no from Preference p "
            +"where p.member_no = :member_no and p.post_written_no = :no", nativeQuery = true)
    Preference getNicknameWithAll(Long member_no, Long no);

    @Modifying
    @Transactional
    @Query(value = "delete from Preference p where p.member_no = :member_no and p.post_written_no = :post_no ", nativeQuery = true)
    void deleteByUserId(Long member_no, Long post_no);

}
