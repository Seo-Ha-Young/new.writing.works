package writing.board.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import writing.board.entity.MemberRole;

import java.time.LocalDateTime;

import java.util.Set;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    private Long no;
    private String email; // 이메일
    private String password; // 비밀번호
    private String nickname; // 닉네임
    private String name; // 이름
    private boolean fromSocial; // 소셜로그인 여부
    private Set<MemberRole> roleSet; // 권한
    private LocalDateTime modDate;
    private LocalDateTime regDate;
}
