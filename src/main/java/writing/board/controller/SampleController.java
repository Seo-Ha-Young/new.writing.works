package writing.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import writing.board.security.dto.AuthMemberDTO;

@Controller
@RequestMapping("/html")
@RequiredArgsConstructor
@Log4j2
public class SampleController {
    @GetMapping("/hello")
    public String[] test() {
        return new String[]{"Hello, World"};
    }

    @GetMapping("/header")
    public String header(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, Model model) {
        log.info("header................");
        model.addAttribute("member", authMemberDTO);
        return "/html/header";
    }
}
