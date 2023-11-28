package writing.board.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import writing.board.security.dto.AuthMemberDTO;

@Controller
@Log4j2
@RequestMapping("/sample")
public class MemberController {
    @GetMapping("/all")
    public void exAll() {
        log.info("exAll................");
    }

    @GetMapping("/member")
    public void exMember(@AuthenticationPrincipal AuthMemberDTO authMemberDTO) {
        log.info("exMember................");
        log.info(authMemberDTO);
    }

    @GetMapping("/admin")
    public void exAdmin() {
        log.info("exAdmin................");
    }
}
