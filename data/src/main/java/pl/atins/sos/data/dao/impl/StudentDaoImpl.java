package pl.atins.sos.data.dao.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import pl.atins.sos.data.dao.StudentDao;
import pl.atins.sos.model.Student;

@Repository
@Transactional
public class StudentDaoImpl extends AbstractCrudDao<Student> implements StudentDao {

    @Override
    protected Class<Student> getEntityClass() {
        return Student.class;
    }
}
