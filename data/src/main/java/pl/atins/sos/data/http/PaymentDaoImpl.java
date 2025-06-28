package pl.atins.sos.data.http;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import pl.atins.sos.data.api.PaymentDao;
import pl.atins.sos.model.external.Payment;

import java.util.List;
import java.util.Objects;

@Repository
public class PaymentDaoImpl implements PaymentDao {

    private final WebClient webClient;

    public PaymentDaoImpl(@Qualifier("paymentServiceWebClient") WebClient webClient) {
        this.webClient = Objects.requireNonNull(webClient);
    }

    @Override
    public List<Payment> findAll() {
        return webClient.get()
                .uri("/payments")
                .exchangeToFlux(resp -> resp.bodyToFlux(Payment.class))
                .collectList()
                .block();
    }

    @Override
    public List<Payment> findAllByStudentId(long studentId) {
        return webClient.get()
                .uri("/students/{studentId}/payments", studentId)
                .exchangeToFlux(resp -> resp.bodyToFlux(Payment.class))
                .collectList()
                .block();
    }
}
