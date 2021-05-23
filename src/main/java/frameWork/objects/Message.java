package frameWork.objects;

import java.util.Date;

import static frameWork.Logger.printR;

public class Message implements Comparable<Message>{
    public From from;
    public To to;
    public String message;
    public String id;
    public Date time;

    public class From{
        public String id;
    }

    public class To{
        public String id;
    }

    public String getID(){
       return this.id;
    }

    @Override
    public String toString() {
        return "Message from:"+from.id+" to:"+to.id+" Body:"+ message +"\n ID:"+id;
    }

    public boolean equalsValues(Message messageSent) {
        if (this == messageSent) return true;
        if (messageSent == null ) return false;
        String a =this.from.id+this.to.id+this.message+this.id+this.time.toString();
        String b =messageSent.from.id+messageSent.to.id+messageSent.message+messageSent.id+messageSent.time.toString();
         if(a.equals(b)) return true;
         printR( "String a:"+a+"\nString a:"+b);
    return false;
    }

    @Override
    public int compareTo(Message message) {
        return this.time.compareTo(message.time);
    }


}
