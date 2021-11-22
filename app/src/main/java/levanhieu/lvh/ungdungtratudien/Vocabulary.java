
package levanhieu.lvh.ungdungtratudien;

public class Vocabulary {
    String word;
    String mean;
    public Vocabulary(String word, String mean) {
        this.word = word;
        this.mean = mean;
    }

    @Override
    public String toString() {
        return "Vocabulary{" +
                "word='" + word + '\'' +
                ", mean='" + mean + '\'' +
                '}';
    }
}
