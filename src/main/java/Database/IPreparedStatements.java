package Database;

import Models.User;

import java.util.List;

public interface IPreparedStatements {

    // Get list of users.
    List<User> getAllUsers();

    //Get user by id.
    User getUserByID(int id);

    // Add user.
    void addUser(String username, String password);

    boolean CheckUser(String username, String password);

    // Get points. (Not yet in db).
    void getPointsByUser(User user);




}
