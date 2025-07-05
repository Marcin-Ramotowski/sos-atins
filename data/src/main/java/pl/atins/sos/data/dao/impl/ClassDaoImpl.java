package pl.atins.sos.data.dao.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import pl.atins.sos.data.dao.ClassDao;
import pl.atins.sos.model.UniversityClass;

import java.util.LinkedList;
import java.util.List;

@Repository
@Transactional
public class ClassDaoImpl extends AbstractCrudDao<UniversityClass> implements ClassDao {

    @Override
    public List<UniversityClass> findBySubjectId(long subjectId) {
        return new LinkedList<>(); // TODO
    }

    @Override
    protected Class<UniversityClass> getEntityClass() {
        return UniversityClass.class;
    }
}
