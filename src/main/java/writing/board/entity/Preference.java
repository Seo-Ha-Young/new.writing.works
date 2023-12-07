package writing.board.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString(exclude = "postWritten")
public class Preference extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;
    private String id;
    @ManyToOne(fetch = FetchType.LAZY)
    private PostWritten postWritten;
    private Long good;
    @Column()
    private Long bad;



}
