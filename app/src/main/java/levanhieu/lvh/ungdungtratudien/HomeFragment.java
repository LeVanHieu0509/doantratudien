package levanhieu.lvh.ungdungtratudien;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements HomeHistoryAdapter.Listener {

    RecyclerView rcHomeHistory;
    RecyclerView rcHomeVocabulary;

    ArrayList<Vocabulary> vocabularies;
    ArrayList<Topics> topics;

    HomeHistoryAdapter homeHistoryAdapter;
    HomeVocabularyAdapter homeVocabularyAdapter;
    DBHelper dbHelper;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        setHasOptionsMenu(true);
        dbHelper = new DBHelper(getContext());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcHomeHistory = view.findViewById(R.id.rcHomeHistory);
        rcHomeVocabulary = view.findViewById(R.id.rcHomeVocabulary);

        vocabularies = dbHelper.getALLVocabulary();
        topics = dbHelper.getALLTopics();

        homeHistoryAdapter = new HomeHistoryAdapter(vocabularies, this);
        rcHomeHistory.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        rcHomeHistory.addItemDecoration(new DividerItemDecoration(getContext(),LinearLayoutManager.HORIZONTAL));
        rcHomeHistory.setAdapter(homeHistoryAdapter);

        homeVocabularyAdapter = new HomeVocabularyAdapter(topics);
        rcHomeVocabulary.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        rcHomeVocabulary.addItemDecoration(new DividerItemDecoration(getContext(),LinearLayoutManager.HORIZONTAL));
        rcHomeVocabulary.setAdapter(homeVocabularyAdapter);



    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.mnuearch){
            Intent intent =  new Intent(getContext(), SearchActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onClick(Vocabulary vocabulary) {
        //add funrniture vao data
//        Log.d("abc", String.valueOf(dbHelper.getFurnitureDetail(furniture.idFurniture)));
        //ListItemHis = dbHelper.getFurnitureDetail(furniture.idFurniture);
        //Utilities.data.addAll(ListItemHis);
//        Log.d("abc", String.valueOf(dbHelper.getFurnitureDetail(furniture.idFurniture)));
        //dbHelper.setFurnitureHis(furniture.idFurniture);

        //dbHelper.getFurnitureDetail(furniture.idFurniture);
        Intent intent = new Intent(getActivity(),VocabularyDetailActivity.class);
        intent.putExtra("furniture", vocabulary);
        startActivity(intent);

        Toast.makeText(getContext(), "OK", Toast.LENGTH_SHORT).show();

    }

//    public  ArrayList<Vocabulary> init(){
//        Context context = getContext();
//        ArrayList<Vocabulary> tmp = new ArrayList<>();
//            tmp.add(new Vocabulary("hello","xinchao"));
//            tmp.add(new Vocabulary("hello","xinchao"));
//            tmp.add(new Vocabulary("hello","xinchao"));
//            tmp.add(new Vocabulary("hello","xinchao"));
//            tmp.add(new Vocabulary("hello","xinchao"));
//            tmp.add(new Vocabulary("hello","xinchao"));
//            tmp.add(new Vocabulary("hello","xinchao"));
//            tmp.add(new Vocabulary("hello","xinchao"));
//            tmp.add(new Vocabulary("hello","xinchao"));
//            tmp.add(new Vocabulary("hello","xinchao"));
//            tmp.add(new Vocabulary("hello","xinchao"));
//            tmp.add(new Vocabulary("hello","xinchao"));
//
//        return tmp;
//    }
}