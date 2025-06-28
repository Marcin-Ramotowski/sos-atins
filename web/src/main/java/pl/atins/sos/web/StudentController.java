package pl.atins.sos.web;

import jakarta.transaction.Transactional;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.atins.sos.data.dao.StudentDao;
import pl.atins.sos.model.Student;

import java.util.Objects;

@RestController
@RequestMapping(path = "/students", produces = {MediaType.APPLICATION_JSON_VALUE})
public class StudentController {

    private final StudentDao studentDao;

    public StudentController(StudentDao studentDao) {
        this.studentDao = Objects.requireNonNull(studentDao);
    }

    @PostMapping
    public void create(@RequestBody Student student) {
        student.setAdmin(false);
        studentDao.create(student);
    }

    @GetMapping("/{userId}")
    public Student findById(@PathVariable("userId") long userId) {
        return studentDao.findById(userId).get();
    }

    @PutMapping("/{userId}")
    @Transactional
    public Student update(@PathVariable("userId") long userId, @RequestBody Student updates) {
        Student student = studentDao.findById(userId).get();
        student.setMfaEnabled(updates.isMfaEnabled());
        student.setEmail(updates.getEmail());
        return studentDao.update(student).get();
    }

    @DeleteMapping("/{userId}")
    @Transactional
    public void delete(@PathVariable("userId") long userId) {
        Student student = studentDao.findById(userId).get();
        studentDao.delete(student);
    }
}
