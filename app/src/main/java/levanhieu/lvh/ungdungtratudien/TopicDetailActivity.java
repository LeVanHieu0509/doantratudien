package levanhieu.lvh.ungdungtratudien;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class TopicDetailActivity extends AppCompatActivity implements ListVocabularyAdapter.Listener  {

    RecyclerView rcTopicDetailActivity;
    ListVocabularyAdapter listVocabularyAdapter;
    Toolbar toolbar;
    ArrayList<Vocabulary> arrayList;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_topic);
        dbHelper = new DBHelper(this);
        toolbar = findViewById(R.id.toolbarTopicDetailActivity);
        rcTopicDetailActivity = findViewById(R.id.rcTopicDetailActivity);
        arrayList = Utilities.dataCateHome;
        listVocabularyAdapter = new ListVocabularyAdapter(arrayList, this);
        rcTopicDetailActivity.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rcTopicDetailActivity.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL ));
        rcTopicDetailActivity.setAdapter(listVocabularyAdapter);


    }


    @Override
    public void onClick(Vocabulary vocabulary) {
        dbHelper.setVocabularyHis(vocabulary.IdVocabulary);
        //dbHelper.getFurnitureDetail(furniture.idFurniture);
        Intent intent = new Intent(this,VocabularyDetailActivity.class);
        intent.putExtra("furniture", vocabulary);
        startActivity(intent);
        Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();
    }
}