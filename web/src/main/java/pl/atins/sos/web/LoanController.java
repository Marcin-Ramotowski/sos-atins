package pl.atins.sos.web;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.atins.sos.data.api.LoanDao;
import pl.atins.sos.model.external.Loan;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
public class LoanController {

    private final LoanDao loanDao;

    public LoanController(LoanDao loanDao) {
        this.loanDao = Objects.requireNonNull(loanDao);
    }

    @GetMapping(path = "/students/{studentId}/loans")
    public List<Loan> findAllByStudentId(@PathVariable("studentId") long studentId) {
        return loanDao.findAllByStudentId(studentId);
    }
}
