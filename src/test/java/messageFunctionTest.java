import frameWork.Constants;
import frameWork.MessageActions;
import frameWork.objects.Message;
import frameWork.objects.User;
import io.restassured.RestAssured;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static frameWork.Logger.printB;
import static frameWork.Logger.printR;
import static io.restassured.RestAssured.given;

/**
 * Created PMG 05-2021
 *  General functionality of the PSCamp message API
 */
public class messageFunctionTest extends  BaseTest{

    String testId =Constants.testID+"mft_";

    /**
     * Test of create user function
     */
    @Test
    public void _001_checkFunctionOfCreate_SendMessage() {
        //TODO users should be locked down to UUIDS not allow any names.

        int testNumber=1;
        String user1 = testId +"un1_" + testNumber, user2 = testId +"un2_" + testNumber;
        Message sentMessage=  MessageActions.sendMessage(user1,user2,"test message");
        messagesCreated.add(sentMessage);
        Assert.assertNotNull(sentMessage);
        printB("message created "+sentMessage.toString());

    }

    /**
     * Test of GET specific message function
     */
    @Test()
    public void _002_checkGetMessageFunction() {

        int testNumber = 2;
        String user1 = testId +"un1_" + testNumber, user2 = testId +"un2_" + testNumber;
        Message sentMessage = MessageActions.sendMessage(user1, user2, "test message");
        messagesCreated.add(sentMessage);
        printB("message created " + sentMessage.toString());

        Message retrievedMessage= MessageActions.getMessage(sentMessage.getID());
        Assert.assertTrue(retrievedMessage.equalsValues(sentMessage) );

    }

    /**
     * Test of GET messages between users function
     * Create 10 messages and assure they are all returned by the get function
     */
    @Test()
    public void _003_checkGetMessagesFunction() {
        List<Message>  createdMessagedLocal= new ArrayList<Message>();
        int testNumber = 3;
        String user1 = testId +"un1_" + testNumber, user2 = testId +"un2_" + testNumber;
        for(int i=0; i <10; i++){
            createdMessagedLocal.add(MessageActions.sendMessage(user1, user2, StringUtils.repeat('A', i)));
        }
        messagesCreated.addAll(createdMessagedLocal);

        List<Message> retrievedMessage= MessageActions.getMessages(user1,user2);
        //check correct number of messages are returned
        Assert.assertEquals(createdMessagedLocal.size(),retrievedMessage.size());

        Collections.sort(createdMessagedLocal);     //Sort Messages
        Collections.sort(retrievedMessage);
        int i = 0;
        while (i < createdMessagedLocal.size()) {
            Assert.assertTrue(createdMessagedLocal.get(i).equalsValues(retrievedMessage.get(i)) );
            i++;
        }
    }

    /**
     * Test of DELETE of specific message
     */
    @Test
    public void _004_checkFunctionOfDeleteMessage() {
        int testNumber=4;
        String user1 = testId +"un1_" + testNumber, user2 = testId +"un2_" + testNumber;
        Message sentMessage=  MessageActions.sendMessage(user1,user2,"test message");
        messagesCreated.add(sentMessage);
        Assert.assertNotNull(sentMessage);
        printB("Message created "+sentMessage.toString());

        //Get number of messages before delete
        int messagesBeforeDelete=MessageActions.getMessages(user1,user2).size();
        Assert.assertEquals(messagesBeforeDelete,1);
        Assert.assertTrue( MessageActions.deleteMessage(sentMessage)); //Check delete supposed sucessful
        int messagesAfterDelete=MessageActions.getMessages(user1,user2).size();
        Assert.assertEquals(messagesAfterDelete,0);

    }

    /**
     * Test of update Message function
     */
    @Test()
    public void _005_checkFunctionOfPut() {
        Assert.assertTrue(false);
        printR("PUT not functioning");
        /*
        int testNumber=5;
        String user1 = testId +"un1_" + testNumber, user2 = testId +"un2_" + testNumber;
        Message sentMessage=  MessageActions.sendMessage(user1,user2,"test message");
        messagesCreated.add(sentMessage);
        Assert.assertNotNull(sentMessage);
        printB("Message created "+sentMessage.toString());

        given().log().all().request().put(sentMessage.getID());

       //     Message retrievedMessage= MessageActions.getMessage(sentMessage.getID());
       //     printB("Message post edited "+retrievedMessage.toString());
       //     Assert.assertTrue(retrievedMessage.equalsValues(sentMessage) );
    /* possible body
            {
                "message": "updated message",
                    "id": "message ID"
            }
    */

    }



}
