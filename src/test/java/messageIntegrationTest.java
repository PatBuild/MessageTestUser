import frameWork.Constants;
import frameWork.MessageActions;
import frameWork.UserActions;
import frameWork.objects.Message;
import frameWork.objects.User;
import frameWork.objects.json;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static frameWork.Logger.printB;
import static io.restassured.RestAssured.given;

/**
 * Created PMG 05-2021
 *  General integration testing  of the PSCamp message API
 */
public class messageIntegrationTest extends  BaseTest{

    String testId =Constants.testID+"mft_";

    @Test
    public void _001_checkFunctionOfCreate_SendMessageWithUsers() {

        //create users
        int testNumber = 1, loopCountLocal = 100, totalTimeA = 0, totalTimeB = 0;
        String user1a = testId + "un1_" + testNumber, user2a = testId + "un2_" + testNumber;
        String user1b = testId + "un1_" + testNumber, user2b = testId + "un2_" + testNumber;
        List<User> userCreated = UserActions.createUsers(user1a, user2a);
        String restPostStringA = MessageActions.getCreateMessageParameters(userCreated.get(0).getId(), userCreated.get(1).getId(), "test message");
        String restPostStringB = MessageActions.getCreateMessageParameters(user1b, user2b, "test message");

        //set request
        RequestSpecification request = given().request();
        request.header("Content-Type", "application/json");

        //Loop with real users
        request.body(restPostStringA);
        for (int i = 0; i < loopCountLocal; i++) {
            Response response = request.post();
            response.then().statusCode(200);
            String responseBody = response.getBody().asString();
            messagesCreated.add(json.parseMessage(responseBody));
            totalTimeA += response.getTime();
        }

        //Loop with fake users
        request.body(restPostStringB);
        for (int i = 0; i < loopCountLocal; i++) {
            Response response = request.post();
            response.then().statusCode(200);
            String responseBody = response.getBody().asString();
            messagesCreated.add(json.parseMessage(responseBody));
            totalTimeB += response.getTime();
        }

        printB("Messages Created realUsers:" + totalTimeA);
        printB("Messages Created fakeUsers:" + totalTimeB);
    }


}
