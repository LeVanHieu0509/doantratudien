package levanhieu.lvh.ungdungtratudien;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class SentencesDetailActivity extends AppCompatActivity {
    RecyclerView rcTopicDetailActivity;
    ListVocabularyAdapter listVocabularyAdapter;
    Toolbar toolbar;
    ArrayList<DetailSentences> arrayList;
    DBHelper dbHelper;
    TextView txtDesSentenceDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sentences_detail);
        Intent intent = getIntent();
        Sentences sentences = (Sentences) intent.getSerializableExtra("sentencesDetail");
        txtDesSentenceDetail = findViewById(R.id.txtDesSentenceDetail);
        txtDesSentenceDetail.setText(sentences.desSentence);
    }
}