package writing.board.dto;

import lombok.*;
import writing.board.entity.BaseEntity;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecommendationDTO extends BaseEntity {

    private Long no;
    private String id;
    private Long post_no;
    private boolean push;
    private LocalDateTime regDate;

}
