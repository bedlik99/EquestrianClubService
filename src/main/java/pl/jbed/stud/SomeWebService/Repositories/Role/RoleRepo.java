package pl.jbed.stud.SomeWebService.Repositories.Role;

import pl.jbed.stud.SomeWebService.Entity.Role;

public interface RoleRepo  {
    public Role findRoleByName(String roleName);
}
