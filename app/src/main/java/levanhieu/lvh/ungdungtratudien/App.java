package levanhieu.lvh.ungdungtratudien;

import android.app.Application;
import android.content.Context;

import java.util.ArrayList;

public class App extends Application {

    public static ArrayList<Vocabulary> init(Context context) {

        ArrayList<Vocabulary> tmp = new ArrayList<>();
        tmp.add(new Vocabulary("hello", "xin chao"));
        tmp.add(new Vocabulary("goodbye", "tam biet"));
        tmp.add(new Vocabulary("see", "nhin"));
        tmp.add(new Vocabulary("love", "yeu"));
        return tmp;
    }
}
