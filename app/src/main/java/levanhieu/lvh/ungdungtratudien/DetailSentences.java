package levanhieu.lvh.ungdungtratudien;

import java.io.Serializable;

public class DetailSentences implements Serializable {

    String sentence;
    int idSentence;
    int idDetailSentence;

    public DetailSentences(  int idDetailSentence,String sentence,int idSentence) {
        this.idSentence = idSentence;
        this.sentence = sentence;
        this.idDetailSentence = idDetailSentence;
    }

}
