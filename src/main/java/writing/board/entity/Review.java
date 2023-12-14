package writing.board.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString(exclude = {"postWritten", "member"})
public class Review extends BaseEntity {

    private String review_content;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.REFRESH})
    @JoinColumn(name = "post_no", referencedColumnName = "no")
    private PostWritten postWritten;
    @ManyToOne(fetch = FetchType.LAZY, cascade =  {CascadeType.REFRESH, CascadeType.REMOVE})
    @JoinColumn(name = "member_no", referencedColumnName = "no")
    private Member member;

    public void changeContent(String review_content) {this.review_content = review_content;}

}
