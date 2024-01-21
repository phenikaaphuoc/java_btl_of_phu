package dao;


import entity.User;

public class UserDao {
    public boolean checkUser(User user) {
        if (user != null) {
            if ("admin".equals(user.getUserName())
                    && "1".equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }
}
