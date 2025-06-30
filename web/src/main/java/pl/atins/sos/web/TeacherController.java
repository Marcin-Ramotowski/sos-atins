package pl.atins.sos.web;

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
    public Teacher update(@PathVariable("userId") long userId, @RequestBody Teacher updates) {
        return teacherDao.updateById(userId, teacher -> {
            teacher.setMfaEnabled(updates.isMfaEnabled());
            teacher.setEmail(updates.getEmail());
        }).get();
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable("userId") long userId) {
        teacherDao.deleteById(userId);
    }
}
