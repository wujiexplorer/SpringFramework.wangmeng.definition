import java.util.ArrayList;
import java.util.Date;

public class Test002 {
    @Override
    public String toString() {
        return super.toString();
    }

//    @Override
//    public void add(){ 报错
//
//    }

    @Deprecated
    public static void add(){
        new Date().parse("");
    }

    //@SuppressWarnings("rawTypes")
    public static void main(String[] args){
        new ArrayList();
        add();
    }
}
