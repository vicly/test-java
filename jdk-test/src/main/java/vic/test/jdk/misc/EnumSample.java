package vic.test.jdk.misc;


import java.io.Closeable;
import java.io.IOException;

public class EnumSample {

}

enum ThreadStatesEnum implements Closeable{
    START(1){
        @Override
        public String toString(){
            return "START implementation. Priority="+getPriority();
        }
 
        @Override
        public String getDetail() {
            return "START";
        }
    },
    RUNNING(2){
        @Override
        public String getDetail() {
            return "RUNNING";
        }
    };
     
    private int priority;
    // abstract method
    public abstract String getDetail();
    //Enum constructors should always be private.
    private ThreadStatesEnum(int i){
        priority = i;
    }
     
    //Enum can have methods
    public int getPriority(){
        return this.priority;
    }
     
    public void setPriority(int p){
        this.priority = p;
    }
     
    //Enum can override functions
    @Override
    public String toString(){
        return "Default ThreadStatesConstructors implementation. Priority="+getPriority();
    }
 
    @Override
    public void close() throws IOException {
        System.out.println("Close of Enum");
    }
}