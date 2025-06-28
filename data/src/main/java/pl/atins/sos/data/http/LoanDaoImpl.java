package pl.atins.sos.data.http;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import pl.atins.sos.data.api.LoanDao;
import pl.atins.sos.model.external.Loan;

import java.util.List;
import java.util.Objects;

@Repository
public class LoanDaoImpl implements LoanDao {

    private final WebClient webClient;

    public LoanDaoImpl(@Qualifier("libraryServiceWebClient") WebClient webClient) {
        this.webClient = Objects.requireNonNull(webClient);
    }

    @Override
    public List<Loan> findAllByStudentId(long studentId) {
        return webClient.get()
                .uri("/students/{studentId}/loans", studentId)
                .exchangeToFlux(resp -> resp.bodyToFlux(Loan.class))
                .collectList()
                .block();
    }
}
