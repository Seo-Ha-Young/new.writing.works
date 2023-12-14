package writing.board.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString(exclude = {"postWritten", "member"})
public class Preference extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.REFRESH})
    private PostWritten postWritten;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.REFRESH})
    @JoinColumn(name="member_no", referencedColumnName = "no") // 만들 컬럼 이름: nickname, 속성:varchar(255), 참조열 이름:nickname
    private Member member;
    private Long good;
    private Long bad;



}
