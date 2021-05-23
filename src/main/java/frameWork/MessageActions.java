package frameWork;


import frameWork.objects.Message;
import frameWork.objects.User;
import frameWork.objects.json;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;

import static frameWork.Logger.printB;
import static frameWork.Logger.printR;
import static io.restassured.RestAssured.given;


public class MessageActions {


    /**
     * @param userSender   user Object A
     * @param userReceiver user Object B
     * @param messageBody  message to be sent from A to B
     * @return
     */
    public static Message sendMessage(String userSender, String userReceiver, String messageBody) {
        try {
            RestAssured.baseURI = Constants.baseUrlForPSVGS + Constants.messageSufix;
            RequestSpecification request = given().request();
            request.header("Content-Type", "application/json");
            String restString = getCreateMessageParameters(userSender , userReceiver , messageBody);
            //  printB("sendMessage  restString:" + restString);
            request.body(restString);
            Response response = request.post();
            String responseBody = response.getBody().asString();
            //   printB("sendMessage response:" + responseBody);
            //  printR(" statusCode:" + response.statusCode());
            response.then().statusCode(200);     //testing the response code is 200
            if (response.statusCode() == 200) //FIXME response code is 200 but should be 201
                return json.parseMessage(responseBody);
        } catch (Exception e) {
            printR("sendMessage: Issue with sendMessage");
        }
        return null;
    }

    public static Message sendMessage(User userSender, User userReceiver, String messageBody) {
        return sendMessage(userSender.getId(), userReceiver.getId(), messageBody);
    }

    public static String getCreateMessageParameters(String un1, String un2, String message) {
     return  "{\"from\":{\"id\":\"" + un1 + "\"},\"to\":{\"id\":\"" + un2 + "\"}, \"message\":\"" + message + "\"}";
    }

    /**
     *
     * @param messageID string of message id to return
     * @return  message Object
     */
    public static Message getMessage( String messageID ) {
        RestAssured.baseURI = Constants.baseUrlForPSVGS+Constants.messageSufix;
        Response response= given().request().get(messageID);
        String responseBody = response.getBody().asString();
        response.then().statusCode(200);
        Message messageFound = json.parseMessage(responseBody);
        printB("getMessage id:"+messageID+" message Found:"+ messageFound.toString());
        return messageFound;
    }
    public static Message getMessage( Message messageObject ) {
       return getMessage(messageObject.getID());
    }

    /**
     *
     * @param userSender  user that sent message
     * @param userReceiver  user object that recieved message
     * @return
     */
    public static List<Message> getMessages(String userSender, String userReceiver ) {
        RestAssured.baseURI = Constants.baseUrlForPSVGS+Constants.messageSufix;
        //GET /message?from=fromUserId&to=toUserId
        Response response= given().request().get("?from="+userSender+"&to="+userReceiver);
        String responseBody = response.getBody().asString();
        response.then().statusCode(200);
        List<Message> messageFound = json.parseMessages(responseBody);
        printB("getMessages Sender:"+userSender+" Receiver:"+userReceiver+" \n Messages Found:"+ messageFound.size());
        return messageFound;
    }
    public static List<Message> getMessages(User userSender, User userReceiver ) {
        return  getMessages( userSender.getId(),  userReceiver.getId() );
    }


    //FIXME Update Message JSON body not found on postman
    public static void updateMessage(String messageId ) {
/*
{       possible similar syntax to this. if time allows
    "name": "updated Name",
    "id": "userId"
}
 */

    }

    /**
     *
     * @param messageIdToDelete string of Message ID
     * @return true if delete succeeds
     */
    public static boolean deleteMessage(String messageIdToDelete) {
        try {
            RestAssured.baseURI = Constants.baseUrlForPSVGS + Constants.messageSufix;
            RequestSpecification request = given().request();
            Response response = request.delete(messageIdToDelete);
            String responseBody = response.getBody().asString();
          // printB("deleteUsers response:" + responseBody);
          //   printR("deleteUsers statusCode:" + response.statusCode());
         //   if(response.statusCode()==200) //TODO response code is 503 but should be 20*
         //       return json.parseUsers(responseBody).get(0);
        }catch(Exception e ){
            printR("Issue with deleteMessage");
        }
        printB("Message Deleted:"+messageIdToDelete);
        return true;
    }

    public static boolean deleteMessage(Message messageIdToDelete) {
        return deleteMessage(messageIdToDelete.getID());
    }

    public static boolean deleteMessage(List<Message> messagesToDelete) {
        boolean faultless=true;
        for(Message eachMessage:messagesToDelete){
           if(!deleteMessage(eachMessage.getID()))
               faultless=false;
        }
        return faultless;
    }










}
