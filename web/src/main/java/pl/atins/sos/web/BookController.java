package pl.atins.sos.web;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.atins.sos.data.api.BookDao;
import pl.atins.sos.model.external.Book;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/books", produces = {MediaType.APPLICATION_JSON_VALUE})
public class BookController {

    private final BookDao bookDao;

    public BookController(BookDao bookDao) {
        this.bookDao = Objects.requireNonNull(bookDao);
    }

    @GetMapping
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @GetMapping(path = "/{id}")
    public Book findById(@PathVariable("id") long id) {
        return bookDao.findById(id);
    }
}
