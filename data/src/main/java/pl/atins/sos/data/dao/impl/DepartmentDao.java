package pl.atins.sos.data.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import pl.atins.sos.model.Department;

import java.util.List;

@Repository
public class DepartmentDao {

    @PersistenceContext
    private EntityManager em;

    public List<Department> findAll() {
        Query query = em.createQuery("from Department");
        return (List<Department>) query.getResultList();
    }
}
