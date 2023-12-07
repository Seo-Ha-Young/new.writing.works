package writing.board.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import writing.board.dto.PreferenceDTO;
import writing.board.service.PreferenceService;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("/like")
@RequiredArgsConstructor
public class PreferenceController {
    private final PreferenceService preferenceService;

    @GetMapping("/{post_no}/all")
    public List<PreferenceDTO> getLikeList(@PathVariable("post_no")  Long post_no) {
        List<PreferenceDTO> preferenceDTOList = preferenceService.find_post_no(post_no);
        return preferenceDTOList;
    }

    @PostMapping("/{post_no}/good")
    public void addGoodCnt(@RequestBody PreferenceDTO preferenceDTO) {
        log.info("add Preference");
        log.info("추천 정보:"+preferenceDTO);
        Long preference_no = preferenceService.register(preferenceDTO);
        log.info("저장된 추천 번호 : "+preference_no);
    }

}
