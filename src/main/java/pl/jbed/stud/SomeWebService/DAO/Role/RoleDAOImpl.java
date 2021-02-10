package pl.jbed.stud.SomeWebService.DAO.Role;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.jbed.stud.SomeWebService.Entity.Role;
import javax.persistence.EntityManager;

@Repository
public class RoleDAOImpl implements RoleRepo {

    private final EntityManager entityManager;

    @Autowired
    public RoleDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public Role findRoleByName(String roleName) {

        Session currentSession = entityManager.unwrap(Session.class);

        NativeQuery<Role> query = currentSession.createNativeQuery(
                "SELECT * FROM roles WHERE name=:theRoleName",Role.class);
        query.setParameter("theRoleName",roleName);

        Role theRole;
        try{
            theRole = query.getSingleResult();
        }catch (Exception e) {
            theRole = null;
        }

        return theRole;
    }

}
