package pl.atins.sos.data.dao.impl;

import org.springframework.stereotype.Component;
import pl.atins.sos.data.dao.EnrollmentDao;
import pl.atins.sos.model.Enrollment;

@Component
public class EnrollmentDaoImpl extends AbstractCrudDao<Enrollment> implements EnrollmentDao {

    @Override
    protected Class<Enrollment> getEntityClass() {
        return Enrollment.class;
    }
}
