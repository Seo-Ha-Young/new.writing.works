package writing.board.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Table(name = "login")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mid;
    private String login_id;
    private String password;
    private String nickname;

    private String name;
    private String birth;
    private String address;
    private String email;

    private UserRole role;

}
