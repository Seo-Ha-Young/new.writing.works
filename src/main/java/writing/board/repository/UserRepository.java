package writing.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import writing.board.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByLoginId(String login_id);
    boolean existsByNickname(String nickname);
    boolean existsByEmail(String email);
    Optional<User> findByLoginId(String login_id);


}
