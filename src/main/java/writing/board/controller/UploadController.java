package writing.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import writing.board.dto.EssayDTO;
import writing.board.service.EssayService;

@Controller
@RequestMapping("/upload")
@Log4j2
@RequiredArgsConstructor
public class UploadController {

    private final EssayService essayService;
    @GetMapping("/essay")
    public void essay() { };

    @PostMapping("/essay")
    public String addEssay(EssayDTO essayDTO, RedirectAttributes redirectAttributes) {
        log.info("essay "+essayDTO);
        Long no = essayService.register(essayDTO);
        redirectAttributes.addFlashAttribute("msg", no+"번 에세이가 등록됨");
        return "redirect:/upload/essay";
    }


}
