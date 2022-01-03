package levanhieu.lvh.ungdungtratudien;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class SentencesDetailActivity extends AppCompatActivity {
    RecyclerView rcTopicDetailActivity;
    ListVocabularyAdapter listVocabularyAdapter;
    Toolbar toolbar;
    ArrayList<DetailSentences> arrayList;
    DBHelper dbHelper;
    TextView txtDesSentenceDetail;
    TextView txtSentenceDetail;
    ImageView imageSentenceDetail;
    LinearLayout toolbarSentenceDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sentences_detail);
        Intent intent = getIntent();
        Sentences sentences = (Sentences) intent.getSerializableExtra("sentencesDetail");
        txtDesSentenceDetail = findViewById(R.id.txtDesSentenceDetail);
        txtSentenceDetail = findViewById(R.id.txtSentenceDetail);
        imageSentenceDetail = findViewById(R.id.imageSentenceDetail);
        txtDesSentenceDetail.setText(sentences.desSentence);
        txtSentenceDetail.setText(sentences.sentence);
        toolbarSentenceDetail = findViewById(R.id.toolbarSentenceDetail);
        imageSentenceDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new SentencesFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.toolbarSentenceDetail, fragment);
                toolbarSentenceDetail.setEnabled(true);
                ft.commit();
            }
        });

    }
}