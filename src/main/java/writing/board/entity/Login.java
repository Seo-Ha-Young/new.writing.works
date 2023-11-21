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
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mid;
    private String id;
    private String pw;
    private String nickname;

    private String name;
    private String birth;
    private String address;
    private String email;

}
