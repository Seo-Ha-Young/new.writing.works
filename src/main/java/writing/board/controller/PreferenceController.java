package writing.board.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import writing.board.dto.PreferenceDTO;
import writing.board.service.PreferenceService;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@Log4j2
@RequestMapping("/like")
@RequiredArgsConstructor
public class PreferenceController {
    private final PreferenceService preferenceService;

    @GetMapping("/{post_no}/all")
    public ResponseEntity<List<PreferenceDTO>> getLikeList(@PathVariable("post_no")  Long post_no) {
        log.info("추천번호정보"+post_no);
        List<PreferenceDTO> preferenceDTOList = preferenceService.find_post_no(post_no);
        return new ResponseEntity<>(preferenceDTOList, HttpStatus.OK);
    }

    @PostMapping("/{post_no}/good")
    public void addGoodCnt(@RequestBody PreferenceDTO preferenceDTO) {
        log.info("add Preference");
        log.info("추천 정보:"+preferenceDTO);
        Long preference_no = preferenceService.register(preferenceDTO);
        log.info("저장된 추천 번호 : "+preference_no);
    }

    @DeleteMapping("/{id}")
    public void deleteGood(@PathVariable String id) {
        log.info("추천취소");
        log.info("취소 아이디:"+id);
        preferenceService.remove(id);
    }

}
