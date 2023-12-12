package writing.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import writing.board.dto.MemberDTO;
import writing.board.handler.MessageHandler;
import writing.board.security.dto.AuthMemberDTO;
import writing.board.security.security.MemberUserDetailsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/member")
public class TestMemberController {
    private final MemberUserDetailsService memberService;
    private final MessageHandler messageHandler;

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
    public String joinPost(@Valid MemberDTO memberDTO, Errors errors, Model model) {
        log.info("join post..................");
        if (errors.hasErrors()) {
            model.addAttribute("memberDTO", memberDTO);

            Map<String, String> validatorResult = memberService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                log.info(validatorResult.get(key));
                model.addAttribute(key, validatorResult.get(key));

            }

            return "member/join";
        }

        memberService.joinMember(memberDTO);

        return "redirect:/member/login";
    }

    @GetMapping("/memberinfo/{no}")
    public String memberInfo(@PathVariable("no") Long no, Model model) {
        log.info("memberinfo page..............");
        MemberDTO memberDTO = memberService.findById(no);
        model.addAttribute("member", memberDTO);
        return "/member/memberinfo";
    }

    @GetMapping("/delete")
    public String deleteGet() {
        return "/member/delete";
    }

    @PostMapping("/delete")
    public String deletePost(@RequestParam String password, Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        boolean result = memberService.deleteMember(userDetails.getUsername(), password);

        if (result) {
            return "redirect:/member/logout";
        } else {
            model.addAttribute("wrongPassword", "비밀번호가 맞지 않습니다.");
            return "/member/delete";
        }
    }

    @GetMapping("/update/email")
    public String updateUsernameForm(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        MemberDTO memberDTO = memberService.findMember(userDetails.getUsername());
        model.addAttribute("member", memberDTO);

        return "/member/updateemail";
    }

    @PostMapping("/update/email")
    public String updateUsername(@Valid MemberDTO memberDTO, Errors errors, Model model, Authentication authentication) {
        if (errors.hasErrors()) {
            model.addAttribute("member", memberDTO);
            messageHandler.messageHandling(errors, model);
            return "/member/updateemail";
        }

        memberService.updateMember(memberDTO);

        return "redirect:/member/memberinfo";
    }

    @GetMapping("/all")
    public void exAll(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, Model model) {
        log.info("exAll................");
        model.addAttribute("member", authMemberDTO);
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
