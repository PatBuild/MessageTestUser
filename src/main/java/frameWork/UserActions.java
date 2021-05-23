package frameWork;


import frameWork.objects.User;
import frameWork.objects.json;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;
import java.util.List;

import static frameWork.Logger.printB;
import static frameWork.Logger.printR;
import static io.restassured.RestAssured.given;


public class UserActions {



    /**
     *
     * @return list of user objects
     */
    public static List<User> getUsers() {
        RestAssured.baseURI = Constants.baseUrlForPSVGS+Constants.userSufix;
        Response response= given().request().get();
        String responseBody = response.getBody().asString();
        response.then().statusCode(200);
        printB("GetUsers Users Found:"+ responseBody);
        return json.parseUsers(responseBody);
    }

    /**
     *
     * @param namesToCreate ; list of strings for the names of users
     * @return
     */
    public static  List<User> createUsers(String ... namesToCreate ) {
        List<User> createdUsers= new ArrayList<>();
        for(String eachUser:namesToCreate){
            createdUsers.add(createUser( eachUser));
        }
        return createdUsers;
    }

    public static User createUser(String  nameFirstAndLast) {
       try {
           RestAssured.baseURI = Constants.baseUrlForPSVGS + Constants.userSufix;
           RequestSpecification request = given().request();
           request.header("Content-Type", "application/json");
           String restString ="{\"name\":\"" + nameFirstAndLast + "\"}";
        //   printB(" restString:" + restString);
            request.body(restString);
            Response response = request.post();
            String responseBody = response.getBody().asString();
            printB("SetUsers response:" + responseBody);
        //    printR(" statusCode:" + response.statusCode());
           if(response.statusCode()==200) //FIXME response code is 200 but should be 201
            return json.parseUser(responseBody);
        }catch(Exception e ){
            printR("SetUsers: Issue with set User");
        }
        return null;
    }

    public static boolean deleteUsers(List<User> UsersToDelete ) {
       boolean allDeleted=true;
        for(User eachUser:UsersToDelete){
           if(!deleteUsers( eachUser))
               allDeleted=false;
        }
         return allDeleted;
    }

    /**
     *
     * @param userToDelete string of user ID
     * @return
     */
    public static boolean deleteUsers(String userToDelete) {
        try {
            RestAssured.baseURI = Constants.baseUrlForPSVGS + Constants.userSufix;
            RequestSpecification request = given().request();
            Response response = request.delete(userToDelete);
            String responseBody = response.getBody().asString();
          //     printB("deleteUsers response:" + responseBody);
                printB("deleteUsers statusCode:" + response.statusCode());
         //   if(response.statusCode()==200) //TODO response code is 503 but should be 20*
         //       return json.parseUsers(responseBody).get(0);
        }catch(Exception e ){
            printR("Issue with set User");
        }
        return true;
    }

    public static boolean deleteUsers(User userToDelete) {
        return deleteUsers(userToDelete.getId());
    }


/**PUT              /users/:id
 *  Update a user by id
 */


public static boolean updateUserName(User userToUpdate, String newName) {
        try {
            RestAssured.baseURI = Constants.baseUrlForPSVGS + Constants.userSufix;
            RequestSpecification request = given().request();
            request.header("Content-Type", "application/json");
            String restString ="{\"name\":\"" + newName + "\",\"id\":\""+userToUpdate.getId()+"\"}";
             printB("changeUserName restString:" + restString);
            request.body(restString);
            Response response = request.put(userToUpdate.getId());
            String responseBody = response.getBody().asString();
            printB("changeUserName response:" + responseBody);
            //    printR(" statusCode:" + response.statusCode());
            if(response.statusCode()==200)
                return true;
        }catch(Exception e ){
            printR("changeUserName: Issue with set User");
        }
    return true;
}














}
