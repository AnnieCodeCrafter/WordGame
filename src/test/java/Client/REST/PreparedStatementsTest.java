package Client.REST;

import Database.PreparedStatements;
import Models.User;
import org.junit.Assert;
import org.junit.Test;

public class PreparedStatementsTest {
    PreparedStatements statements = new PreparedStatements();


    @Test
    public void GetPlayer() {
        User user = statements.getUserByID(1);
        Assert.assertNotNull(user);
    }
}
