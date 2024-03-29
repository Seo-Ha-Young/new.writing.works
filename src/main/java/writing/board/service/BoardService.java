package writing.board.service;

import writing.board.dto.PageRequestDTO;
import writing.board.dto.PageResultDTO;
import writing.board.dto.PostWrittenDTO;
import writing.board.entity.PostWritten;


public interface BoardService {
    PageResultDTO<PostWrittenDTO, Object[]> getList(PageRequestDTO requestDTO);

    PostWrittenDTO read(Long no);

    void remove(Long postNo);
    default PostWrittenDTO entitiesToDTO(PostWritten postWritten, Long goodCnt, Long badCnt){
        PostWrittenDTO postWrittenDTO = PostWrittenDTO.builder()
                .no(postWritten.getNo())
                .post_name(postWritten.getPost_name())
                .post_content(postWritten.getPost_content())
                .regDate(postWritten.getRegDate())
                .writer(postWritten.getWriter())
                .image_no((postWritten.getImage_no()))
                .build();
        postWrittenDTO.setGoodCnt(goodCnt.intValue());
        postWrittenDTO.setBadCnt(badCnt.intValue());

        return postWrittenDTO;
    }

    default PostWrittenDTO entitiesToDTO_Search(PostWritten postWritten){
        PostWrittenDTO postWrittenDTO = PostWrittenDTO.builder()
                .no(postWritten.getNo())
                .post_name(postWritten.getPost_name())
                .post_content(postWritten.getPost_content())
                .regDate(postWritten.getRegDate())
                .writer(postWritten.getWriter())
                .image_no((postWritten.getImage_no()))
                .build();
        return postWrittenDTO;
    }

    default PostWritten dtoToEntity(PostWrittenDTO postWrittenDTO) {
        PostWritten postWritten = PostWritten.builder()
                .post_content(postWrittenDTO.getPost_content())
                .no(postWrittenDTO.getNo())
                .post_name(postWrittenDTO.getPost_name())
                .image_no(postWrittenDTO.getImage_no())
                .build();
        return postWritten;
    }



}

