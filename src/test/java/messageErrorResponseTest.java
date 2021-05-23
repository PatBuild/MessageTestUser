import frameWork.Constants;
import frameWork.MessageActions;
import frameWork.objects.Message;
import frameWork.objects.json;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import  frameWork.Constants;

import static frameWork.Constants.*;
import static frameWork.Logger.printB;
import static frameWork.Logger.printR;
import static io.restassured.RestAssured.given;

/**
 * Created PMG 05-2021
 *  General error responses of the PSCamp message API
 */
public class messageErrorResponseTest extends  BaseTest{

    String  classTestId =testID+"mert_";

    /**
     * Test the response to a bad POST
     */
    @Test
    public void _001_checkErrorResponsesOfCreate_SendMessage() {

        int testNumber=1;
        String user1 = classTestId +"un1_" + testNumber, user2 = classTestId +"un2_" + testNumber;
        //Wrong Json sent in post
        RequestSpecification request = given().request();
        request.header("Content-Type", "application/json");
        String restString = MessageActions.getCreateMessageParameters(user1 , user2 , "messageBody");
        request.body(restString+"}");
        Response response = request.post();
        String responseBody = response.getBody().asString();
        //   printB("sendMessage response:" + responseBody);
        //  printR(" statusCode:" + response.statusCode());
        response.then().statusCode(400);   //FIXME response is 500 should be 400 bad request
        Assert.assertTrue(responseBody.contains("entity.parse.failed"));
    }

    /**
     * Test response to a bad GET
     */
    @Test()
    public void _002_checkGetMessageErrorResponsesOf() {

        int testNumber = 2;
        String user1 = classTestId +"un1_" + testNumber, user2 = classTestId +"un2_" + testNumber;
        Message sentMessage = MessageActions.sendMessage(user1, user2, "test message");
        messagesCreated.add(sentMessage);
        printB("message created " + sentMessage.toString());
        Response response= given().request().get("This is not a Corrrect message ID");
        String responseBody = response.getBody().asString();
        response.then().statusCode(404);
    }


}
