package pl.atins.sos.data.dao.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import pl.atins.sos.data.dao.BookDao;
import pl.atins.sos.model.external.Book;

import java.util.List;
import java.util.Objects;

@Repository
public class BookDaoImpl implements BookDao {

    private final WebClient webClient;

    public BookDaoImpl(@Qualifier("libraryServiceWebClient") WebClient webClient) {
        this.webClient = Objects.requireNonNull(webClient);
    }

    @Override
    public List<Book> findAll() {
        return webClient.get()
                .uri("/books")
                .exchangeToFlux(resp -> resp.bodyToFlux(Book.class))
                .collectList()
                .block();
    }

    @Override
    public Book findById(long id) {
        return webClient.get()
                .uri("/books/{id}", id)
                .exchangeToMono(resp -> resp.bodyToMono(Book.class))
                .block();
    }
}
