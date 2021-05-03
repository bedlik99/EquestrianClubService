package pl.jbed.stud.SomeWebService.Repositories.UserCode;

import pl.jbed.stud.SomeWebService.Entity.User;
import pl.jbed.stud.SomeWebService.Entity.UserCode;

public interface UserCodeRepository {

    public void addFieldForUserCode(User user);
    public void updateUserCode(UserCode userCode);
    public UserCode findById(int id);
    public void clearUserCodeAfterLogout(UserCode code);

}
