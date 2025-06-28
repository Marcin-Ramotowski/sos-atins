package pl.atins.sos.data.dao;

import pl.atins.sos.model.external.Book;

import java.util.List;

public interface BookDao {
    List<Book> findAll();

    Book findById(long id);
}
