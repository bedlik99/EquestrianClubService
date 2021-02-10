package pl.jbed.stud.SomeWebService.DAO.UserCode;

import pl.jbed.stud.SomeWebService.Entity.User;
import pl.jbed.stud.SomeWebService.Entity.UserCode;

public interface UserCodeRepository {

    public void addFieldForUserCode();
    public void updateUserCode(UserCode userCode);
    public UserCode findById(int id);

}
