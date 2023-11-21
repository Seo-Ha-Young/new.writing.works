package writing.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import writing.board.entity.Essay;

import java.util.List;
import java.util.Objects;

public interface EssayRepository extends JpaRepository<Essay, Long> {

    @Query("select no, regDate, essay_content from Essay where no = :no")
    List<Object[]> getEssayWithAll(Long no);

    @Query("select no, regDate, essay_content from Essay")
    List<Object[]> getEssayWithAll();
}
