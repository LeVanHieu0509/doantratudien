package levanhieu.lvh.ungdungtratudien;

import android.app.Application;
import android.content.Context;

import java.util.ArrayList;

public class App extends Application {

    DBHelper dbHelper;
    @Override
    public void onCreate(){
        super.onCreate();
        dbHelper = new DBHelper(this);
        dbHelper.createTable();
        if (dbHelper.countFurniture() == 0 ){
            for(Vocabulary vocabulary :init(this)){
                dbHelper.insertFurniture(vocabulary);
            }
            for(Topics topics :init()){
                dbHelper.insertCategories(topics);
            }
        }
    }
    public static ArrayList<Vocabulary> init(Context context) {
        ArrayList<Vocabulary> tmp = new ArrayList<>();
        tmp.add(new Vocabulary("hello", "xin chao",1));
        tmp.add(new Vocabulary("goodbye", "tam biet",2));
        tmp.add(new Vocabulary("see", "nhin",3));
        tmp.add(new Vocabulary("love", "yeu",4));
        tmp.add(new Vocabulary("word", "tu moi",3));
        tmp.add(new Vocabulary("mean", "nghia",2));
        return tmp;
    }

    public static ArrayList<Topics> init(){
        ArrayList<Topics> tmp = new ArrayList<>();
        tmp.add(new Topics(1,"TOEIC",1));
        tmp.add(new Topics(2,"TOEFL",2));
        tmp.add(new Topics(3,"IELTS",3));
        tmp.add(new Topics(4,"TRAVEL",4));
        tmp.add(new Topics(5,"BASSIC",5));

        return tmp;
    }
}
