package writing.board.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.sql.Blob;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Image extends BaseEntity {

    private String img_name;


}
