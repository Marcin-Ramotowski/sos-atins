package pl.atins.sos.data.dao.impl;

import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import pl.atins.sos.data.dao.DepartmentDao;
import pl.atins.sos.model.Department;

import java.util.List;

@Repository
public class DepartmentDaoImpl extends AbstractCrudDao<Department> implements DepartmentDao {


    @Override
    protected Class<Department> getEntityClass() {
        return Department.class;
    }

    @Override
    public List<Department> findAll() {
        Query query = em.createQuery("FROM Department");
        return query.getResultList();
    }

    @Override
    public List<Department> searchByName(String name) {
        String searchName = "%" + name + "%";
        Query query = em.createQuery("FROM Department d where d.name like :name");
        query.setParameter("name", searchName);
        return query.getResultList();
    }
}
