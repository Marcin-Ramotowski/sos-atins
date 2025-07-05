package pl.atins.sos.data.dao;

import pl.atins.sos.model.UniversityClass;

import java.util.List;

public interface ClassDao extends CrudDao<UniversityClass> {

    List<UniversityClass> findBySubjectId(long subjectId);
}
