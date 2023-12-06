package writing.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import writing.board.entity.Recommendation;
import writing.board.entity.Tests;

import java.util.List;

public interface TestsRepository extends JpaRepository<Tests, Long> {

    @Query("select no, regDate, id, good, bad from Tests where no = :no")
    List<Object[]> getTestWithAll(Long no);

    @Query("select no, regDate, id,  good, bad from Tests")
    List<Object[]> getTestWithAll();

}
