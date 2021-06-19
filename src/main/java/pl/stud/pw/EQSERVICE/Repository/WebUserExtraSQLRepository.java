package pl.stud.pw.EQSERVICE.Repository;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;
import pl.stud.pw.EQSERVICE.Entity.WebUser;

import javax.persistence.EntityManager;


@Repository
public class WebUserExtraSQLRepository {

    private final EntityManager entityManager;

    public WebUserExtraSQLRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void updateUserInformation(WebUser user){

        Session currentSession = entityManager.unwrap(Session.class);
        NativeQuery<WebUser> query = currentSession.createNativeQuery(
                "UPDATE WEB_USER SET first_name=:newFirstName, last_name=:newLastName," +
                        "email=:newEmail,password=:newPassword,username=:newUsername" +
                        " WHERE id=:userId",WebUser.class);

        query.setParameter("newFirstName",user.getFirstName());
        query.setParameter("newLastName", user.getLastName());
        query.setParameter("newEmail", user.getEmail());
        query.setParameter("newPassword", user.getPassword());
        query.setParameter("newUsername", user.getUsername());
        query.setParameter("userId", user.getId());
        query.executeUpdate();

    }

}
