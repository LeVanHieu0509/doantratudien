
package levanhieu.lvh.ungdungtratudien;

import java.io.Serializable;

public class Vocabulary implements Serializable {
    String word;
    String mean;
    int IdTopic;
    int IdVocabulary;



    public Vocabulary(String word, String mean, int IdTopic) {
        this.word = word;
        this.mean = mean;
        this.IdTopic = IdTopic;
    }
    public Vocabulary(String word, String mean, int IdTopic, int IdVocabulary) {
        this.word = word;
        this.mean = mean;
        this.IdTopic = IdTopic;
        this.IdVocabulary = IdVocabulary;
    }

}
