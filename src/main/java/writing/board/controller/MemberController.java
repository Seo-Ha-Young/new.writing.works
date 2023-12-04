package writing.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import writing.board.dto.MemberDTO;
import writing.board.security.dto.AuthMemberDTO;
import writing.board.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/login")
    public String login() {
        log.info("login page.............");
        return "member/login";
    }

    @GetMapping("/error")
    public String loginError(Model model) {
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요.");
        return "member/login";
    }

    @GetMapping("/join")
    public String join() {
        log.info("join page.............");
        return "member/join";
    }

    @PostMapping("/member/joinProc")
    public String joinProc(@Valid MemberDTO memberDTO) {
        memberService.saveMember(memberDTO);
        return "redirect:/member/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/member/login";
    }


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
