package levanhieu.lvh.ungdungtratudien;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements HomeTopicAdapter.Listener, HomeHistoryAdapter.Listener, NavigationView.OnNavigationItemSelectedListener {
    View mView;
    RecyclerView rcHomeHistory;
    RecyclerView rcHomeVocabulary;
    TextView txtSeeAllHistory;
    TextView txtSeeAllSentences;
    TextView txtSeeAllTopics;
    ArrayList<Vocabulary> vocabularies;
    ArrayList<Topics> topics;
    ArrayList<Vocabulary> ListItemCate;
    ArrayList<Vocabulary> ListItemHis;

    BottomNavigationView bottomNavigationView;
    HomeHistoryAdapter homeHistoryAdapter;
    HomeTopicAdapter homeTopicAdapter;
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
        txtSeeAllHistory = view.findViewById(R.id.txtSeeAllHistory);
        txtSeeAllSentences = view.findViewById(R.id.txtSeeAllSentences);
        txtSeeAllTopics = view.findViewById(R.id.txtSeeAllTopic);

        bottomNavigationView = view.findViewById(R.id.bottomNavigationView);
        vocabularies = dbHelper.getALLVocabulary();
        topics = dbHelper.getALLTopics();
        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottomNavigationView);
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        txtSeeAllHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fragment = new HistoryFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content, fragment);
                bottomNavigationView.getMenu().findItem(R.id.mnuHistory).setChecked(true);
                toolbar.setTitle("");
                ft.commit();
            }
        });
        txtSeeAllSentences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new SentencesFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content, fragment);
                ft.commit();
            }
        });
        txtSeeAllTopics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new VocabularyFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content, fragment);
                bottomNavigationView.getMenu().findItem(R.id.mnuVocabulary).setChecked(true);
                toolbar.setTitle("Vocabulary");
                ft.commit();
            }
        });
        ListItemHis = dbHelper.getVocabularyHis();
        homeHistoryAdapter = new HomeHistoryAdapter(ListItemHis, this);
        rcHomeHistory.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        rcHomeHistory.addItemDecoration(new DividerItemDecoration(getContext(),LinearLayoutManager.HORIZONTAL));
        rcHomeHistory.setAdapter(homeHistoryAdapter);

        homeTopicAdapter = new HomeTopicAdapter(topics,this);
        rcHomeVocabulary.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        rcHomeVocabulary.addItemDecoration(new DividerItemDecoration(getContext(),LinearLayoutManager.HORIZONTAL));
        rcHomeVocabulary.setAdapter(homeTopicAdapter);
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
        mView = inflater.inflate(R.layout.fragment_home, container, false);
        mView.findViewById(R.id.txtSeeAllHistory).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomNavigationView.getMenu().findItem(R.id.mnuHistory).setChecked(true);
            }
        });
        return mView;
    }

    @Override
    public void onClick(Vocabulary vocabulary) {
        dbHelper.setVocabularyHis(vocabulary.IdVocabulary);
        Intent intent = new Intent(getActivity(),VocabularyDetailActivity.class);
        intent.putExtra("furniture", vocabulary);
        startActivity(intent);
    }


    @Override
    public void onClick(Topics topics) {
        if (Utilities.dataCateHome.size() != 0) {
            Utilities.dataCateHome.clear();
        }
        ListItemCate = dbHelper.getTopicsDetail(topics.idTopic);
        Utilities.dataCateHome.addAll(ListItemCate);
        Intent intent = new Intent(getActivity(),TopicDetailActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}