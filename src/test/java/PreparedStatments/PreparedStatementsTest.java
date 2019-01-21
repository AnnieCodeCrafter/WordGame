package PreparedStatments;

import Database.PreparedStatements;
import Models.User;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PreparedStatementsTest {
    PreparedStatements statements = new PreparedStatements();

    @Test
    public void getAllUsers() {
        List<User> users = statements.getAllUsers();
        Assert.assertNotNull(users);

    }

    @Test
    public void GetPlayer() {
        User user = statements.getUserByID(1);
        Assert.assertNotNull(user);
    }

    @Test
    public void checkPlayer() {
       boolean check =  statements.CheckUser("tom", "mot");

       Assert.assertTrue(check);
    }

    @Test
    public void addUser() {
        statements.addUser("me", "neverguess");

        boolean check = statements.CheckUser("me", "neverguess");
        Assert.assertTrue(check);
    }
}
