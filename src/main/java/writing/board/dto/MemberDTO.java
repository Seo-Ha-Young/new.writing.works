package writing.board.dto;


import lombok.Data;
import writing.board.entity.Member;
import writing.board.entity.MemberRole;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class MemberDTO {
    private String email; // 이메일
    private String password; // 비밀번호
    private String nickname; // 닉네임
    private String name; // 이름
    private String birth; // 생년월일
    private String address; // 주소

    /* DTO -> Entity */
    public Member toEntity() {
        Member member = Member.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .name(name)
                .birth(birth)
                .address(address)
                .build();
        member.addMemberRole(MemberRole.USER);
        return member;
    }
}
