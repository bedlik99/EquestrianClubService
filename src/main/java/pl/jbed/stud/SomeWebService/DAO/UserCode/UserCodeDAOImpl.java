package pl.jbed.stud.SomeWebService.DAO.UserCode;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.jbed.stud.SomeWebService.Entity.UserCode;

import javax.persistence.EntityManager;

@Repository
public class UserCodeDAOImpl implements UserCodeRepository{

    private final EntityManager entityManager;

    @Autowired
    public UserCodeDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public void addFieldForUserCode() {
        Session currentSession = entityManager.unwrap(Session.class);
        UserCode userCode = new UserCode(null);
        currentSession.save(userCode);
    }

    @Override
    public void updateUserCode(UserCode userCode) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.update(userCode);
    }

    @Override
    public UserCode findById(int givenId) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<UserCode> theQuery = currentSession.
                createQuery("FROM UserCode WHERE id=:theId", UserCode.class);

        theQuery.setParameter("theId",givenId);
        UserCode userCode;

        try {
            userCode = theQuery.getSingleResult();
        }catch (Exception e){
            userCode = null;
        }

        return userCode;
    }

}
