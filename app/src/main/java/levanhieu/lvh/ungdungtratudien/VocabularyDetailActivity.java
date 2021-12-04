package levanhieu.lvh.ungdungtratudien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

public class VocabularyDetailActivity extends AppCompatActivity {

    TextView txtDesDetail;
    ImageView imgHomeDetail;
    Toolbar toolbar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary_detail);

        Intent intent = getIntent();
        Vocabulary vocabulary = (Vocabulary) intent.getSerializableExtra("furniture");

        toolbar2 = findViewById(R.id.toolbardetail);

        setSupportActionBar(toolbar2);
        toolbar2.setTitle("Vocabulary Detail");
        txtDesDetail = findViewById(R.id.txtHomeDetail);
//        imgHomeDetail = findViewById(R.id.imgHomeDetail);
        txtDesDetail.setText(vocabulary.word);
//        imgHomeDetail.setImageResource(furniture.image);
    }
}