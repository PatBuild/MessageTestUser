
import frameWork.MessageActions;
import frameWork.UserActions;
import frameWork.objects.json;
import frameWork.objects.Message;
import frameWork.objects.User;
import io.restassured.RestAssured;
import org.apache.commons.lang3.StringUtils;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static frameWork.Constants.baseUrlForPSVGS;
import static frameWork.Constants.messageSufix;
import static frameWork.Logger.*;
import static io.restassured.RestAssured.given;

public class BaseTest {

    List<User> usersCreated= new ArrayList<>();
    List<Message> messagesCreated= new ArrayList<>();

    @BeforeClass
    public void beforeClass(){
        RestAssured.baseURI = baseUrlForPSVGS + messageSufix;
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        String resultString =result.getStatus()==ITestResult.SUCCESS? "Pass": "FAIL";
       printR("Method name:" + result.getMethod().getMethodName() + "   Result :"+resultString);
       printB( StringUtils.repeat('-', 35));
    }

    @AfterClass
    public void afterClass(){
        //Clear all data created by this test
        printR("All messages Deleted:" +MessageActions.deleteMessage(messagesCreated));
        printR("All Users Deleted:" +UserActions.deleteUsers(usersCreated));

    }






}
