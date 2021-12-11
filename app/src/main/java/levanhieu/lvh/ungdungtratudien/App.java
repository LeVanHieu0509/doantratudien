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
        tmp.add(new Vocabulary("Hello", "Xin chào",1));
        tmp.add(new Vocabulary("Goodbye", "Tạm biệt",2));
        tmp.add(new Vocabulary("See", "Nhìn",3));
        tmp.add(new Vocabulary("Love", "Yêu",4));
        tmp.add(new Vocabulary("Word", "Từ Mới",3));
        tmp.add(new Vocabulary("Mean", "Nghĩa",2));
        tmp.add(new Vocabulary("Vision", "Tầm nhìn",1));
        tmp.add(new Vocabulary("Cake", "bánh kẹo",2));
        tmp.add(new Vocabulary("Chocalate", "kẹo sô cô la",3));
        tmp.add(new Vocabulary("Hate", "Ghét",4));
        tmp.add(new Vocabulary("Something", "1 vài cái gì đó",3));
        tmp.add(new Vocabulary("Do", "Làm gì",2));
        tmp.add(new Vocabulary("Ghét", "Hate",4));
        tmp.add(new Vocabulary("1 vài cái gì đó", "Something",3));
        tmp.add(new Vocabulary("Làm gì", "Do",2));

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
