package writing.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import writing.board.repository.PostWrittenRepository;

@Service
public class PostService {
    private final PostWrittenRepository postWrittenRepository;

    @Autowired
    public PostService(PostWrittenRepository postWrittenRepository) {
        this.postWrittenRepository = postWrittenRepository;
    }

/*
       public Post savePost(Post post) {
        return PostWrittenRepository.save(post);*//*

    }
*/

}
