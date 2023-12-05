package writing.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import writing.board.dto.PostWrittenDTO;
import writing.board.entity.PostWritten;
import writing.board.repository.PostWrittenRepository;

import java.util.List;

@Service
public class PostService {
    private final PostWrittenRepository postWrittenRepository;

    @Autowired
    public PostService(PostWrittenRepository postWrittenRepository) {
        this.postWrittenRepository = postWrittenRepository;
    }

    public void savePost(PostWrittenDTO postWrittenDTO) {
        PostWritten postWritten = new PostWritten();
        postWrittenRepository.getPostWithAll();
        postWrittenRepository.save(postWritten);
    }

    public List<PostWritten> getAllPosts() {
        return postWrittenRepository.findAll();
    }

}
