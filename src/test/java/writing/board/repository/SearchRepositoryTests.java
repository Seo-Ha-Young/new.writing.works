package writing.board.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SearchRepositoryTests {
    @Autowired
    SearchRepository repository;

    @Test
    public void testSearch1() {
        repository.search1();
    }
}
