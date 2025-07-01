package pl.atins.sos.data.dao;

import pl.atins.sos.model.Department;

import java.util.List;

public interface DepartmentDao extends CrudDao<Department> {

    List<Department> findAll();
    List<Department> searchByName(String name);

}
