package frameWork.objects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import frameWork.objects.Message;
import frameWork.objects.User;

import java.util.List;

import static frameWork.Logger.printR;

public class json {


    private static ObjectMapper objectmapper =getObjectMapper();

    private static ObjectMapper getObjectMapper() {
        ObjectMapper standardObjectMapper = new ObjectMapper();
        return standardObjectMapper;
    }

    public static JsonNode parse(String inputString){
        try {
            return objectmapper.readTree(inputString);
        } catch (JsonProcessingException e) {
            System.err.println("Issue parsing JSON input string");
            e.printStackTrace();
        }
        return null;
    }

    public static String parseToJsonString(String ... inputArray){
        if(inputArray.length%2!=0)
            printR("Issue with passed string, pair values only");
        String jsonString ="";
        for(int i=0;i<inputArray.length;i+=2 ){
            jsonString += "[{\""+inputArray[i]+"\":\""+inputArray[i+1]+"\"},";
        }
        jsonString=jsonString.substring(0,jsonString.length()-1);//cut final comma off string
        jsonString=jsonString+"]";
        printR("parseToJsonString :"+jsonString );
        return jsonString;
    }




    public static List<User> parseUsers(String inputString){
        try {
          return  getObjectMapper().readValue(inputString,  new TypeReference<List<User>>(){});
        } catch (JsonProcessingException e) {
            System.err.println("Issue parsing Users");
            e.printStackTrace();
        }
        return null;
    }
    public static User parseUser(String inputString){
        try {
            return  getObjectMapper().readValue(inputString,User.class);
        } catch (JsonProcessingException e) {
            System.err.println("Issue parsing Users");
            e.printStackTrace();
        }
        return null;
    }

    public static List<Message> parseMessages(String inputString){
        try {
            return  getObjectMapper().readValue(inputString,  new TypeReference<List<Message>>(){});
        } catch (JsonProcessingException e) {
            System.err.println("Issue parsing Messages");
            e.printStackTrace();
        }
        return null;
    }
    public static Message parseMessage(String inputString){
        try {
            return  getObjectMapper().readValue(inputString,  Message.class);
        } catch (JsonProcessingException e) {
            System.err.println("Issue parsing Messages");
            e.printStackTrace();
        }
        return null;
    }




}
