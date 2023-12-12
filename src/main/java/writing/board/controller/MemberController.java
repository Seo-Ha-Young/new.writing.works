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
import writing.board.security.dto.AuthMemberDTO;
import writing.board.security.security.MemberUserDetailsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/html")
public class MemberController {
    final private MemberUserDetailsService memberService;

    @GetMapping("/login")
    public String login() {
        log.info("login page.............");
        return "html/login";
    }

    @GetMapping("/error")
    public String loginError(Model model) {
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요.");
        return "html/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/html/login";
    }

    @GetMapping("/register")
    public String joinGet() {
        log.info("register page.............");
        return "html/register";
    }

    @PostMapping("/register")
    public String joinPost(@Valid MemberDTO memberDTO, Errors errors, Model model) {
        log.info("register post..................");
        if (errors.hasErrors()) {
            model.addAttribute("memberDTO", memberDTO);

            Map<String, String> validatorResult = memberService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                log.info(validatorResult.get(key));
                model.addAttribute(key, validatorResult.get(key));

            }

            return "html/register";
        }

        memberService.joinMember(memberDTO);

        return "redirect:/html/login";
    }

    @GetMapping("/memberinfo/{no}")
    public String memberInfo(@PathVariable("no") Long no, Model model) {
        log.info("memberinfo page..............");
        MemberDTO memberDTO = memberService.findById(no);
        model.addAttribute("member", memberDTO);
        return "/html/memberinfo";
    }

    @GetMapping("/delete")
    public String deleteGet() {
        return "/html/delete";
    }

    @PostMapping("/delete")
    public String deletePost(@RequestParam String password, Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        boolean result = memberService.deleteMember(userDetails.getUsername(), password);

        if (result) {
            return "redirect:/html/logout";
        } else {
            model.addAttribute("wrongPassword", "비밀번호가 맞지 않습니다.");
            return "/html/delete";
        }
    }




}
