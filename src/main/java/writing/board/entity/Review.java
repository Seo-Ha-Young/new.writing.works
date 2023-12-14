package writing.board.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Review extends BaseEntity {

    private String review_content;
    private Long post_no;
    private Long member_no;

    public void changeContent(String review_content) {this.review_content = review_content;}

}
