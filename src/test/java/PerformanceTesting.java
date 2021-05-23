import frameWork.MessageActions;
import frameWork.objects.Message;
import frameWork.objects.json;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Random;

import static frameWork.Constants.testID;
import static frameWork.Logger.printB;
import static frameWork.Logger.printR;
import static io.restassured.RestAssured.given;

/**
 * Simple stress test
 */

public class PerformanceTesting extends BaseTest{

/*
    Stress testing
    Soak/Load testing.
    Peak/Spike testing.

 */

    String  classTestId =testID+"pt_";
    final int threadPoolCountLocal=1;    final int invocationCountLocal=10;     int loopCountLocal=100;


    @Test(threadPoolSize = threadPoolCountLocal, invocationCount = invocationCountLocal,  timeOut = 10000)
    @Parameters("LoopCountParameter")
    public void _001_checkStressOfPostsOnMessage(String LoopCountParameter ) {

        //set loop parameter
        try {loopCountLocal = LoopCountParameter == null ? loopCountLocal : Integer.valueOf(LoopCountParameter);
        }catch(Exception e){printR("Please only input numbers for loop count");e.printStackTrace();};

        int testNumber = 1 ,totalTime=0;
        String user1 = classTestId + "un1_" + testNumber, user2 = classTestId + "un2_" + testNumber;
        String restPostString = MessageActions.getCreateMessageParameters(user1, user2, StringUtils.repeat('a', 500));

        RequestSpecification request = given().request();
        request.header("Content-Type", "application/json");
        request.body(restPostString);
        for(int i =0 ;i<loopCountLocal;i++){
            Response response = request.post();
            response.then().statusCode(200);
            String responseBody = response.getBody().asString();
            messagesCreated.add(json.parseMessage(responseBody));
            totalTime+=response.getTime();
        }

        printB("threadPoolCount: "+threadPoolCountLocal+" invocationCountLocal"+invocationCountLocal + " loopCountLocal:"+loopCountLocal);
        printB("Response Time : " + totalTime);

    }

    public void _002_checkStressOfPostsOnMessageLength() {

    }




  }
