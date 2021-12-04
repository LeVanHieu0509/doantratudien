package levanhieu.lvh.ungdungtratudien;

import java.io.Serializable;

public class Topics implements Serializable {

    String name;
    int idTopic;
    int image;

    public Topics( int idTopic,String name) {
        this.name = name;
        this.idTopic = idTopic;
    }

    public Topics(int image, String name,int idTopic) {
        this.image = image;
        this.name = name;
        this.idTopic = idTopic;
    }
}


