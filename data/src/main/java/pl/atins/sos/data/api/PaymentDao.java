package pl.atins.sos.data.api;

import pl.atins.sos.model.external.Payment;

import java.util.List;

public interface PaymentDao {
    List<Payment> findAll();

    List<Payment> findAllByStudentId(long studentId);
}
