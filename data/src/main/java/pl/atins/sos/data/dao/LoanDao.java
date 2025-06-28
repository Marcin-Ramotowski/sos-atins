package pl.atins.sos.data.dao;

import pl.atins.sos.model.external.Loan;

import java.util.List;

public interface LoanDao {
    List<Loan> findAllByStudentId(long studentId);
}
