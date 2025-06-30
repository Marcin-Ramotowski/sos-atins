package pl.atins.sos.data.dao.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import pl.atins.sos.data.dao.SubjectDao;
import pl.atins.sos.model.Subject;

@Repository
@Transactional
public class SubjectDaoImpl extends AbstractCrudDao<Subject> implements SubjectDao {

    @Override
    protected Class<Subject> getEntityClass() {
        return Subject.class;
    }
}
