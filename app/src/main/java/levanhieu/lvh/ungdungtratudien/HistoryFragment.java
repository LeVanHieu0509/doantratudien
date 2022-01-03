package levanhieu.lvh.ungdungtratudien;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistoryFragment extends Fragment implements HistoryAdapter.Listener, FavouriteAdapter.Listener {

    RecyclerView  rcHistory;
    ArrayList<Vocabulary> arrayList;
    ArrayList<Vocabulary> arrayListFavourite;
    HistoryAdapter historyAdapter ;
    FavouriteAdapter favouriteAdapter ;
    DBHelper dbHelper;
    Context context;
    Toolbar toolbarHistory;
    SearchView searchHistory;
    ListVocabularyAdapter listVocabularyAdapter;
    LinearLayout linearEmpty;
    RecyclerView rcHistoryFilter;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public HistoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HistoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HistoryFragment newInstance(String param1, String param2){
        HistoryFragment fragment = new HistoryFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_history, container, false);
        // Inflate the layout for this fragment
//        rcHistory = v.findViewById(R.id.rcHistory);
//        rcHistory.setHasFixedSize(true);
//        rcHistory.setLayoutManager(new LinearLayoutManager(getActivity()));
        return v;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbarHistory = view.findViewById(R.id.toolbar);
        searchHistory = view.findViewById(R.id.searchHistory);
        linearEmpty = view.findViewById(R.id.linearEmpty);
        rcHistoryFilter = view.findViewById(R.id.rcHistory);


        rcHistory= view.findViewById(R.id.rcHistory);
        arrayList = dbHelper.getVocabularyHis();
        historyAdapter = new HistoryAdapter(arrayList,this);
        rcHistoryFilter.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rcHistoryFilter.addItemDecoration(new DividerItemDecoration(getContext(),LinearLayoutManager.VERTICAL ));
        rcHistoryFilter.setAdapter(historyAdapter);
        //setVisible(false);

        searchHistory.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            //An vao nut search thi moi tra cuu
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            //Nhap tu la tra cuu luon
            @Override
            public boolean onQueryTextChange(String newText) {
                historyAdapter.getFilter().filter(newText);
//                if(historyAdapter.arrayListFilter.size() == 0 || newText.isEmpty())
//                {
//                    rcHistoryFilter.setVisibility(View.GONE);
//                }
//                else{
//                    Toast.makeText(getContext(), newText, Toast.LENGTH_SHORT).show();
//                    rcHistoryFilter.setVisibility(View.GONE);
//                }
                return false;
            }
        });

    }
    public void setVisible(boolean flag)
    {
        if(flag == false){
            //linearEmpty.setVisibility(View.VISIBLE);
            rcHistoryFilter.setVisibility(View.GONE);
        }
        else{
            //linearEmpty.setVisibility(View.GONE);
            rcHistoryFilter.setVisibility(View.GONE);
        }
    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.history_search, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.mnuHistory){
            Toast.makeText(getContext(), "Bạn đang xem lịch sử", Toast.LENGTH_SHORT).show();
            Fragment fragment = new HistoryFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.content, fragment);
            ft.commit();
        }
        if(item.getItemId() == R.id.mnuFavourite){
            Toast.makeText(getContext(), "Bạn đang xem mục yêu thích", Toast.LENGTH_SHORT).show();
            arrayListFavourite = dbHelper.getVocabularyFavourite();
            favouriteAdapter = new FavouriteAdapter(arrayListFavourite,this);
            rcHistory.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            rcHistory.addItemDecoration(new DividerItemDecoration(getContext(),LinearLayoutManager.VERTICAL ));
            rcHistory.setAdapter(favouriteAdapter);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(Vocabulary vocabulary) {
        dbHelper.setVocabularyHis(vocabulary.IdVocabulary);
        Intent intent = new Intent(getActivity(),VocabularyDetailActivity.class);
        intent.putExtra("furniture", vocabulary);
        startActivity(intent);
    }
    @Override
    public void onClickClose(Vocabulary vocabulary) {
        dbHelper.deleteVocabularyHis(vocabulary.IdVocabulary);
        arrayList = dbHelper.getVocabularyHis();
        historyAdapter = new HistoryAdapter(arrayList,this);
        rcHistory.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rcHistory.addItemDecoration(new DividerItemDecoration(getContext(),LinearLayoutManager.VERTICAL ));
        rcHistory.setAdapter(historyAdapter);
        Toast.makeText(getContext(), "Xoa Thanh cong", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onClickStar(Vocabulary vocabulary,boolean isChecked) {
        String a = String.valueOf(vocabulary.IdVocabulary);
        int b = Integer.parseInt(a);
        if (isChecked){
            dbHelper.setVocabularyFavourite(b);
            Toast.makeText(getContext(), "Thêm vào mục yêu thích thành công  ", Toast.LENGTH_SHORT).show();
        }
        else {
            dbHelper.deleteVocabularyFavourite(vocabulary.IdVocabulary);
            Toast.makeText(getContext(), " Xóa thành công", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClickClose1(Vocabulary vocabulary) {
        dbHelper.deleteVocabularyFavourite(vocabulary.IdVocabulary);
        arrayList = dbHelper.getVocabularyFavourite();
        historyAdapter = new HistoryAdapter(arrayList,this);
        rcHistory.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rcHistory.addItemDecoration(new DividerItemDecoration(getContext(),LinearLayoutManager.VERTICAL ));
        rcHistory.setAdapter(historyAdapter);
        Toast.makeText(getContext(), "Xoa Thanh cong", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickStar1(Vocabulary vocabulary,boolean isChecked) {
        String a = String.valueOf(vocabulary.IdVocabulary);
        int b = Integer.parseInt(a);
        if (isChecked){
            dbHelper.setVocabularyFavourite(b);
            Toast.makeText(getContext(), "Thêm vào mục yêu thích thành ", Toast.LENGTH_SHORT).show();
        }
        else {
            dbHelper.deleteVocabularyFavourite(vocabulary.IdVocabulary);
            Toast.makeText(getContext(), " Xóa thành công", Toast.LENGTH_SHORT).show();
        }
    }





}