package levanhieu.lvh.ungdungtratudien;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.Toolbar;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistoryFragment extends Fragment implements HistoryAdapter.Listener, FavouriteAdapter.Listener {

    RecyclerView rcHistory;
    ArrayList<Vocabulary> arrayList;
    ArrayList<Vocabulary> arrayListFavourite;
    HistoryAdapter historyAdapter ;
    FavouriteAdapter favouriteAdapter ;
    DBHelper dbHelper;
    Context context;

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

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rcHistory= view.findViewById(R.id.rcHistory);
        arrayList = dbHelper.getVocabularyHis();
        historyAdapter = new HistoryAdapter(arrayList,this);
        rcHistory.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rcHistory.addItemDecoration(new DividerItemDecoration(getContext(),LinearLayoutManager.VERTICAL ));
        rcHistory.setAdapter(historyAdapter);
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
            Toast.makeText(getContext(), "Thêm vào mục yêu thích thành ", Toast.LENGTH_SHORT).show();
            SharedPreferences preferences = getContext().getSharedPreferences("prefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("check", true);
            editor.apply();

        }
        else {
            dbHelper.deleteVocabularyFavourite(vocabulary.IdVocabulary);
            Toast.makeText(getContext(), " Xóa thành công", Toast.LENGTH_SHORT).show();
        }

        //Toast.makeText(getContext(), "Them Vao muc yeu thich Thanh cong", Toast.LENGTH_SHORT).show();
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
    public void onClickStar1(Vocabulary vocabulary) {
        dbHelper.setVocabularyFavourite(vocabulary.IdVocabulary);
        Toast.makeText(getContext(), "Them Vao muc yeu thic Thanh cong", Toast.LENGTH_SHORT).show();
    }





}