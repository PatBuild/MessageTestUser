package frameWork.objects;

public class User {

    public String name;
    public String  id;

    @Override
    public String toString() {
            return "User, Name:"+name+ " id:"+id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

}
