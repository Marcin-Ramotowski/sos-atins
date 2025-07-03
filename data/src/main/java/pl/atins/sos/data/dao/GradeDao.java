package pl.atins.sos.data.dao;

import pl.atins.sos.model.Grade;

import java.util.List;

public interface GradeDao extends CrudDao<Grade> {

    List<Grade> findByStudentId(Long studentId);
}
