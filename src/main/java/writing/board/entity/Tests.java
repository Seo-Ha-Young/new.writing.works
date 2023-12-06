package writing.board.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString(exclude = "postWritten")
public class Tests extends BaseEntity {

    private String id;
    @ManyToOne(fetch = FetchType.LAZY)
    private PostWritten postWritten;
    private boolean good;
    private boolean bad;

}
