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
            for (Sentences sentences :initSentences()){
                dbHelper.insertSentences(sentences);
            }
            for (DetailSentences detailSentences :initDetailSentences()){
                dbHelper.insertDetailSentences(detailSentences);
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

    public static ArrayList<Sentences> initSentences(){
        ArrayList<Sentences> tmp = new ArrayList<>();
       tmp.add(new Sentences(1, "Một số mẫu câu bày tỏ sự tức giận","-Shut up (Câm miệng) \n" +
               "- Get lost (Cút đi)"));
        tmp.add(new Sentences(2, "Một số mẫu câu chúc mừng bằng tiếng anh","-Shut up (Câm miệng1)"));
        tmp.add(new Sentences(3, "Động viên người khác bằng tiếng anh","-Shut up (Câm miệng2)"));
        tmp.add(new Sentences(4, "Từ vựng về các giác ","-Shut up (Câm miệng3)"));
        tmp.add(new Sentences(5, "Động viên người khác bằng tiếng anh","-Shut up (Câm miệng4)"));
        return tmp;
    }

    public static ArrayList<DetailSentences> initDetailSentences(){
        ArrayList<DetailSentences> tmp = new ArrayList<>();
        tmp.add(new DetailSentences(1,"-Shut up (Câm miệng)1",1));
        tmp.add(new DetailSentences(2,"-Shut up (Câm miệng)2",2));
        tmp.add(new DetailSentences(3,"-Shut up (Câm miệng)3",2));
        tmp.add(new DetailSentences(4,"-Shut up (Câm miệng)4",4));
        tmp.add(new DetailSentences(5,"-Shut up (Câm miệng5)",3));
        tmp.add(new DetailSentences(6,"-Shut up (Câm miệng6)",5));
        tmp.add(new DetailSentences(7,"-Shut up (Câm miệng7)",4));


        return tmp;
    }
}
