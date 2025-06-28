package pl.atins.sos.data.dao.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import pl.atins.sos.data.dao.TeacherDao;
import pl.atins.sos.model.Teacher;

@Repository
@Transactional
public class TeacherDaoImpl extends AbstractCrudDao<Teacher> implements TeacherDao {

    @Override
    protected Class<Teacher> getEntityClass() {
        return Teacher.class;
    }
}
