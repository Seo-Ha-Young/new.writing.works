package writing.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import writing.board.entity.Preference;

import java.util.List;

public interface PreferenceRepository extends JpaRepository<Preference, Long> {

    @Query("select no, regDate, id, good, bad from Preference where no = :no")
    List<Object[]> getTestWithAll(Long no);

    @Query("select no, regDate, id,  good, bad from Preference")
    List<Object[]> getTestWithAll();

}
