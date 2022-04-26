package ru.kata.spring.boot_security.demo.dao.impl;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {
    @PersistenceContext
    private EntityManager entityManager;

    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

    @Override
    public Role getByRole(String role) {
        Role entityRole = null;
        List<Role> roles = getAll();
        for (Role r : roles) {
            if (r.getRole().equals(role)) entityRole = r;
        }
        return entityRole;
    }

    @Override
    public List<Role> getAll() {
        return entityManager.createQuery("from Role").getResultList();
    }
}
