import frameWork.MessageActions;
import frameWork.UserActions;
import frameWork.objects.Message;
import frameWork.objects.User;
import frameWork.objects.json;

import java.util.List;

import static frameWork.Logger.printR;

/**
 * Class to populate the server
 */
public class TestSetup {


    //check server for user


    //create and provision users and messages for futher testing

    public void UserTestsSetup() {

        printR(" 0  "+ json.parseUser("{ \"name\": \" test RestAssured\", \"id\": \"d18bb6f2-feaa-434c-a594-e198cbee6e13\"}"));


        printR(" 1  "+ UserActions.getUsers());

        printR(" Number of users  "+UserActions.getUsers().size());

        User recentUser =UserActions.createUser(" test RestAssured 23");

        printR(" 2   udpate over");

        UserActions.updateUserName(recentUser,"updated");


        printR(" Number of users  "+UserActions.getUsers().size());

        printR(" 4  "+UserActions.deleteUsers(UserActions.getUsers()));

        printR(" Number of users  "+UserActions.getUsers().size());

        //return json.parseUsers(responseBody);
    }


    public void MessageTestSetup() {
        Message sentMessage0=  MessageActions.sendMessage("UserA","UserB","test message");



        List<User> users=UserActions.createUsers("one","two");
        printR(" Number of users  "+UserActions.getUsers().size());

        Message sentMessage=  MessageActions.sendMessage(users.get(0),users.get(1),"test message");

        printR(" sentMessage " + MessageActions.getMessage(sentMessage));

        Message sentMessage2=  MessageActions.sendMessage(users.get(0),users.get(1),"test 2");

        printR(" number of messages " + MessageActions.getMessages(users.get(0),users.get(1)));

        Message sentMessage3=  MessageActions.sendMessage(users.get(0),users.get(1),"test 3");

        printR(" number of messages " + MessageActions.getMessages(users.get(0),users.get(1)));

        printR(" number of messages "+ MessageActions.deleteMessage(sentMessage3));

        printR(" number of messages " + MessageActions.getMessages(users.get(0),users.get(1)));


    }

}
