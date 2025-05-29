package idv.ykx.cja10138webapp.user.dao;

import idv.ykx.cja10138webapp.user.model.Users;
import java.util.List;
public interface UsersDao {

    public Users findByEmail(String email);
    public void updateByUser(Users user);
    public void updateByAdm(Users user);
    public List<Users> getAllUsers();
    public Users getUserById(Integer id);
    public void addUser(Users user);

}
