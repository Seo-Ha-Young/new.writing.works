package writing.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import writing.board.dto.PageRequestDTO;
import writing.board.dto.PageResultDTO;
import writing.board.dto.PostWrittenDTO;
import writing.board.entity.Image;
import writing.board.entity.PostWritten;
import writing.board.repository.PostWrittenRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final PostWrittenRepository postRepository;

    @Override
    public PageResultDTO<PostWrittenDTO, PostWritten> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("no").descending());
        Page<PostWritten> result = postRepository.findAll(pageable);
        Function<PostWritten, PostWrittenDTO> fn = (entity -> entitiesToDTO(entity));
        return new PageResultDTO<>(result, fn);
    }

}
