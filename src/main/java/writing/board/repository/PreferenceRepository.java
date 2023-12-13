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

    @Query("select no, regDate, id, good, bad from Preference where no = :no")
    List<Object[]> getTestWithAll(Long no);

    @Query("select no, regDate, id,  good, bad from Preference")
    List<Object[]> getTestWithAll();

    @Query(value = "select no, reg_date, id, good, bad, post_written_no, nickname from Preference  "
            +"where post_written_no = :no", nativeQuery = true)
    List<Preference> getTestWithPost_No(Long no);

    @Query(value = "select no, reg_date, id, good, bad, post_written_no, nickname from Preference "+
            "where id = :nickname", nativeQuery = true) // 나중에 이 닉네임을 저 닉네임과 연결 필요
    List<Preference> getNicknameWithAll(String nickname);

    @Modifying
    @Transactional
    @Query("delete from Preference p where p.id = :id ")
    void deleteByUserId(String id);

}
