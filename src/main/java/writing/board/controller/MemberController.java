package writing.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import writing.board.dto.MemberDTO;
import writing.board.security.dto.AuthMemberDTO;
import writing.board.security.security.MemberUserDetailsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberUserDetailsService memberService;

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

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/member/login";
    }

    @GetMapping("/join")
    public String joinGet() {
        log.info("join page.............");
        return "member/join";
    }

    @PostMapping("/join")
    public String joinPost(@Valid MemberDTO memberDTO, BindingResult bindingResult, Model model) {
        log.info("join post..................");
        if (bindingResult.hasErrors()) {
            model.addAttribute("memberDTO", memberDTO);
            return "member/join";
        }

        memberService.joinMember(memberDTO);

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
