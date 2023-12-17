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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.RequestContextUtils;
import writing.board.dto.MemberDTO;
import writing.board.security.dto.AuthMemberDTO;
import writing.board.security.security.MemberUserDetailsService;
import writing.board.validator.CheckEmailValidator;

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
    final private CheckEmailValidator checkEmailValidator;
    @InitBinder
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(checkEmailValidator);
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request, Model model) {
        log.info("login page.............");
        /* 로그인 성공 시 이전 페이지로 이동 */
        String uri = request.getHeader("Referer");

        // 이전 uri가 null이다 -> 배포 서버에서 나타나는 오류?
        if (uri==null) {
            // null일시 이전 페이지에서 addFlashAttribute로 보내준 uri을 저장
            Map<String, ?> paramMap = RequestContextUtils.getInputFlashMap(request);
            uri = (String) paramMap.get("referer");

            // 이전 url 정보 담기
            request.getSession().setAttribute("prevPage", uri);

        }else {
            // 이전 url 정보 담기
            request.getSession().setAttribute("prevPage", uri);
        }
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

    @GetMapping("/member/{no}")
    public String memberInfo(@PathVariable("no") Long no, Model model) {
        log.info("member page..............");
        MemberDTO memberDTO = memberService.findById(no);
        model.addAttribute("member", memberDTO);
        return "/html/member";
    }

    @GetMapping("/delete")
    public String deleteGet() {
        return "/html/delete";
    }

    @PostMapping("/delete")
    public String deletePost(@RequestParam String password, Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        boolean result = memberService.deleteMember(userDetails.getUsername(), password);
        log.info("비밀번호 일치 확인 : "+result);
        if (result) {
            return "redirect:/html/logout";
        } else {
            model.addAttribute("wrongPassword", "비밀번호가 맞지 않습니다.");
            return "/html/delete";
        }
    }

    @GetMapping("/update")
    public String updateGet(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        MemberDTO memberDTO = memberService.findMember(userDetails.getUsername());
        model.addAttribute("memberDTO", memberDTO);
        return "/html/update";
    }

    @PostMapping("/update")
    public String updatePost(@Valid MemberDTO memberDTO, Errors errors, Model model, Authentication authentication) {
        log.info("update post..................");

        if(memberDTO.getNickname().equals("")) {
            model.addAttribute("wrongNickname", "닉네임을 다시 입력해주세요.");
            return "html/update";
        }

        memberService.updateMember(memberDTO);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        MemberDTO member = memberService.findMember(userDetails.getUsername());

        return "redirect:/html/member/"+member.getNo();
    }

}
