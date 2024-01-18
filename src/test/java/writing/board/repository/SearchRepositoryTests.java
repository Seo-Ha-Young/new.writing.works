package writing.board.repository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


@SpringBootTest
public class SearchRepositoryTests {
    @Autowired
    SearchRepository repository;

    @Test
    public void testSearch1() {
        repository.search1();
    }

    @Test
    public void testSearchPage() {
        Pageable pageable = PageRequest.of(0,5, Sort.by("no").descending());
        Page<Object[]> result = repository.searchPage("t","이",pageable);
    }
}
