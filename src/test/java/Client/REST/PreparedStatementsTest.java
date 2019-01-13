package Client.REST;

import Database.PreparedStatements;
import Models.User;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.junit.Assert;
import org.junit.Test;

public class PreparedStatementsTest {
    PreparedStatements statements = new PreparedStatements();


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
}
