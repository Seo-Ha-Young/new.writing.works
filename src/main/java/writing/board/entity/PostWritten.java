package writing.board.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PostWritten extends BaseEntity {

    private String post_content;
    private String writer;
    private Long image_no;

}
