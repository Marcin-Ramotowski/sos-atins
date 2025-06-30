package pl.atins.sos.data.dao.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import pl.atins.sos.data.dao.ClassDao;
import pl.atins.sos.model.UniversityClass;

@Repository
@Transactional
public class ClassDaoImpl extends AbstractCrudDao<UniversityClass> implements ClassDao {

    @Override
    protected Class<UniversityClass> getEntityClass() {
        return UniversityClass.class;
    }
}
