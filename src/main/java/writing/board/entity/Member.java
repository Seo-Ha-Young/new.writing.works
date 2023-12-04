package writing.board.entity;

import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import writing.board.dto.MemberDTO;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Member extends BaseEntity {

    @Id
    private Long no; // 계정번호
    private String email; // 이메일
    private String password; // 비밀번호
    private String nickname; // 닉네임
    private String name; // 이름
    private LocalDate birth; // 생년월일
    private String address; // 주소


    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>(); // 권한

    public void addMemberRole(MemberRole memberRole){
        roleSet.add(memberRole);
    }

}
