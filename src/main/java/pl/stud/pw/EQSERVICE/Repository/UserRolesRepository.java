package pl.stud.pw.EQSERVICE.Repository;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@Repository
public class UserRolesRepository {

    private final EntityManager entityManager;

    public UserRolesRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void deleteUserRoles(Long theId){
        Session currentSession = entityManager.unwrap(Session.class);
        Query query = currentSession.createNativeQuery(
                "DELETE FROM USER_ROLE WHERE USER_ID=:userId");
        query.setParameter("userId",theId);
        query.executeUpdate();
    }
}
