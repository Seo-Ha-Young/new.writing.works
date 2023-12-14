package writing.board.entity;

import lombok.*;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

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
    private String birth; // 생년월일
    private String mainaddress;
    private String subaddress;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>(); // 권한

    @ElementCollection(fetch = FetchType.LAZY)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "member_no", referencedColumnName = "no")
    private Set<Preference>  preferences;

    @ElementCollection(fetch = FetchType.LAZY)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "member_no", referencedColumnName = "no")
    private Set<Review>  reviews;
    public void addMemberRole(MemberRole memberRole){
        roleSet.add(memberRole);
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

}
