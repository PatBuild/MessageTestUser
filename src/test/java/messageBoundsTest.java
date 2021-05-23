import frameWork.Constants;
import frameWork.MessageActions;
import frameWork.UserActions;
import frameWork.objects.Message;
import frameWork.objects.User;
import frameWork.objects.json;
import io.restassured.RestAssured;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static frameWork.Logger.printB;
import static frameWork.Logger.printR;

/**
 * Created PMG 05-2021
 *  Testing bounds/Fuzz test of the PSCamp message API
 */
public class messageBoundsTest extends BaseTest {

    String testId =Constants.testID+"mbt_";

    @Test
    public void _001_checkBoundsOfCreate() {

        int testNumber=1;
        String user1 = testId + "un1_" + testNumber, user2 = testId + "un2_" + testNumber;
        //check max userIDSize
        Message returnedMessage;
        for (int i =10; i<3000;i+=100 ){
            try {
                messagesCreated.add(MessageActions.sendMessage(StringUtils.repeat('a', i), user2, "test message"));

            }catch(AssertionError assertionError){
                        printR("Bounds of user name found "+i);     //~103010
                        break;
                }

        }
        //check max second user size etc
        //check max message size etc
    }


    /*
     *  function to make sure not allowed chars are handled correctly
     */



}
