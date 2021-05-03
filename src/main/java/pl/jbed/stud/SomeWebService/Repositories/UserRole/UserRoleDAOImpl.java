package pl.jbed.stud.SomeWebService.Repositories.UserRole;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Repository
public class UserRoleDAOImpl implements UserRoleRepo{
    private final EntityManager entityManager;

    @Autowired
    public UserRoleDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void deleteUserRole(int theId) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query query = currentSession.createNativeQuery(
                "DELETE FROM user_role WHERE user_id=:userId");
        query.setParameter("userId",theId);
        query.executeUpdate();

    }

}
