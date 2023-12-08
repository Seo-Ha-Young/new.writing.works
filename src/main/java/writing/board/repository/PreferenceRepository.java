package writing.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import writing.board.entity.Preference;

import javax.transaction.Transactional;
import java.util.List;

public interface PreferenceRepository extends JpaRepository<Preference, Long> {

    @Query("select no, regDate, id, good, bad from Preference where no = :no")
    List<Object[]> getTestWithAll(Long no);

    @Query("select no, regDate, id,  good, bad from Preference")
    List<Object[]> getTestWithAll();

    @Query(value = "select no, reg_date, id, good, bad, post_written_no from Preference  "
            +"where post_written_no = :no", nativeQuery = true)
    List<Preference> getTestWithPost_No(Long no);
    @Modifying
    @Transactional
    @Query("delete from Preference p where p.id = :id ")
    void deleteByUserId(String id);
}
