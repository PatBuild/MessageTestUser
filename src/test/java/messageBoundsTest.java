import frameWork.Constants;
import frameWork.MessageActions;
import frameWork.UserActions;
import frameWork.objects.Message;
import frameWork.objects.User;
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
 *  General functionality of the PSCamp message API
 */
public class messageBoundsTest extends BaseTest {



    @Test
    public void _001_checkBoundsOfCreate() {

        int testNumber=1;
        String user1="username1 "+testNumber,user2="username2 "+testNumber;
        //check max userIDSize
        Message returnedMessage;
        for (int i =10; i<3000000;i+=1000 ){
            try {
                 returnedMessage = MessageActions.sendMessage(StringUtils.repeat('a', i), user2, "test message");
            }catch(AssertionError assertionError){
                        printR("Bounds of user name found "+i);     //103010
                        break;
                }

        }
        //check max second user size etc
        //check max message size etc
    }


    /**
     *  function to make sure not allowed chars are handled correctly
     */



}
