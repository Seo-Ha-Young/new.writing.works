package writing.board.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Getter
@ToString
public class PostWritten extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;
    private String post_name;
    private String post_content;
    private String writer;
    private Long image_no;

    @ElementCollection(fetch = FetchType.LAZY)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_written_no", referencedColumnName = "no")
    private Set<Preference> preferences;

    @ElementCollection(fetch = FetchType.LAZY)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_no", referencedColumnName = "no")
    private Set<Review>  reviews;
}
