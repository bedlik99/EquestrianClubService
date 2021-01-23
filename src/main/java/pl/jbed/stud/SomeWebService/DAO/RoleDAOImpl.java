package pl.jbed.stud.SomeWebService.DAO;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.jbed.stud.SomeWebService.Entity.Role;

import javax.persistence.EntityManager;

@Repository
public class RoleDAOImpl implements RoleRepo {

    @Autowired
    private EntityManager entityManager;


    @Override
    public Role findRoleByName(String roleName) {

        Session currentSession = entityManager.unwrap(Session.class);

        Query<Role> theQuery = currentSession.createQuery("FROM Role WHERE name=:theRoleName",Role.class);
        theQuery.setParameter("theRoleName", roleName);

        Role theRole = null;

        try{
            theRole = theQuery.getSingleResult();
        }catch (Exception e) {
            theRole = null;

        }


        return theRole;
    }
}
