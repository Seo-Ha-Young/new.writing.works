package writing.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import writing.board.entity.Essay;
import writing.board.entity.Member;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select no, name, dateOfBirth, address, id, password, nickName, email, regDate, modDate  from Member where no = :no")
    List<Object[]> getMemberWithAll(Long no);

    @Query("select no, name, dateOfBirth, address, id, password, nickName, email, regDate, modDate from Member")
    List<Object[]> getMemberWithAll();
}
