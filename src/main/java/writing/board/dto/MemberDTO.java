package writing.board.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import writing.board.entity.Member;
import writing.board.entity.MemberRole;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.Set;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    @NotBlank(message = "이메일을 입력해주세요.")
    private String email; // 이메일
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password; // 비밀번호
    @NotBlank(message = "닉네임을 입력해주세요.")
    private String nickname; // 닉네임
    @NotBlank(message = "이름을 입력해주세요.")
    private String name; // 이름
    @NotBlank(message = "생년월일을 입력해주세요.")
    private LocalDate birth; // 생년월일
    @NotBlank(message = "주소를 입력해주세요.")
    private String address; // 주소

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .name(name)
                .birth(birth)
                .address(address)
                .build();
    }
}
