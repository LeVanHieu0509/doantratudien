package levanhieu.lvh.ungdungtratudien;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements ListVocabularyAdapter.Listener {

    Toolbar toolbar;
    SearchView searchView;
    ListVocabularyAdapter listVocabularyAdapter;
    ArrayList<Vocabulary> arrayList;
    LinearLayout linearEmpty;
    RecyclerView rcFurnitureFilter;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DBHelper(this);
        setContentView(R.layout.activity_search);
        toolbar = findViewById(R.id.toolbar);
        searchView = findViewById(R.id.searchView);
        linearEmpty = findViewById(R.id.linearEmpty);
        rcFurnitureFilter = findViewById(R.id.rcFurnitureFilter);

        arrayList = App.init(this);
        listVocabularyAdapter = new ListVocabularyAdapter(arrayList, this);
        rcFurnitureFilter.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rcFurnitureFilter.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL ));
        rcFurnitureFilter.setAdapter(listVocabularyAdapter);
        setVisible(false);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            //An vao nut search thi moi tra cuu
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            //Nhap tu la tra cuu luon
            @Override
            public boolean onQueryTextChange(String newText) {
                listVocabularyAdapter.getFilter().filter(newText);
                if(listVocabularyAdapter.arrayListFilter.size() == 0 || newText.isEmpty())
                {
                    setVisible(false);
                }
                else{
                    setVisible(true);
                }
                return false;
            }
        });
    }

    public void setVisible(boolean flag)
    {
        if(flag == false){
            linearEmpty.setVisibility(View.VISIBLE);
            rcFurnitureFilter.setVisibility(View.GONE);
        }
        else{
            linearEmpty.setVisibility(View.GONE);
            rcFurnitureFilter.setVisibility(View.VISIBLE);
        }
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

//    @Override
//    public void onClick(Furniture furniture) {
//        Utilities.data.add(furniture);
//        Intent intent = new Intent(this,HomeDetailActivity.class);
//        intent.putExtra("furniture", furniture);
//        startActivity(intent);
//    }
}