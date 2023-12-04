package writing.board.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;


@Getter
@Setter
@ToString
public class AuthMemberDTO extends User {
    private Long no; // 계정번호
    private String email; // 이메일
    private String password; // 비밀번호
    private String nickname; // 닉네임
    private String name; // 이름
    private LocalDate birth;
    private String address;

    public AuthMemberDTO(Long no, String username, String password, String nickname, String name, LocalDate birth, String address, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.no = no;
        this.email = username;
        this.password = password;
        this.nickname = nickname;
        this.name = name;
        this.birth = birth;
        this.address = address;
    }

}
