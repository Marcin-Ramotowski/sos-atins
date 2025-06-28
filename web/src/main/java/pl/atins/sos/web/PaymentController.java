package pl.atins.sos.web;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.atins.sos.data.dao.PaymentDao;
import pl.atins.sos.model.external.Payment;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
public class PaymentController {

    private final PaymentDao paymentDao;

    public PaymentController(PaymentDao paymentDao) {
        this.paymentDao = Objects.requireNonNull(paymentDao);
    }

    @GetMapping(path = "/payments")
    public List<Payment> findAll() {
        return paymentDao.findAll();
    }

    @GetMapping(path = "/students/{studentId}/payments")
    public List<Payment> findAllByStudentId(@PathVariable("studentId") long studentId) {
        return paymentDao.findAllByStudentId(studentId);
    }
}
