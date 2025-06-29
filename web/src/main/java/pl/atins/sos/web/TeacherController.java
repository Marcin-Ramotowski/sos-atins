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
import pl.atins.sos.data.dao.TeacherDao;
import pl.atins.sos.model.Teacher;

import java.util.Objects;

@RestController
@RequestMapping(path = "/teachers", produces = {MediaType.APPLICATION_JSON_VALUE})
public class TeacherController {

    private final TeacherDao teacherDao;

    public TeacherController(TeacherDao teacherDao) {
        this.teacherDao = Objects.requireNonNull(teacherDao);
    }

    @PostMapping
    public void create(@RequestBody Teacher teacher) {
        teacherDao.create(teacher);
    }

    @GetMapping("/{userId}")
    public Teacher findById(@PathVariable("userId") long userId) {
        return teacherDao.findById(userId).get();
    }

    @PutMapping("/{userId}")
    @Transactional
    public Teacher update(@PathVariable("userId") long userId, @RequestBody Teacher updates) {
        Teacher teacher = teacherDao.findById(userId).get();
        teacher.setMfaEnabled(updates.isMfaEnabled());
        teacher.setEmail(updates.getEmail());
        return teacherDao.update(teacher).get();
    }

    @DeleteMapping("/{userId}")
    @Transactional
    public void delete(@PathVariable("userId") long userId) {
        Teacher teacher = teacherDao.findById(userId).get();
        teacherDao.delete(teacher);
    }
}
