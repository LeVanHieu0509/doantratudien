package levanhieu.lvh.ungdungtratudien;

import java.io.Serializable;

public class Sentences implements Serializable {

    String sentence;
    int idSentence;
    String desSentence;

    public Sentences( int idSentence,String sentence, String desSentence) {
        this.idSentence = idSentence;
        this.sentence = sentence;
        this.desSentence = desSentence;
    }


}



