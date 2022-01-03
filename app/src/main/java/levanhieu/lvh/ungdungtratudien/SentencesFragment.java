package levanhieu.lvh.ungdungtratudien;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SentencesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SentencesFragment extends Fragment  implements SentencesAdapter.Listener {

    ArrayList<Sentences> sentences;
    RecyclerView rcSentences;
    SentencesAdapter sentencesAdapter;
    DBHelper dbHelper;
    ArrayList<DetailSentences> dataSentences;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SentencesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SentencesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SentencesFragment newInstance(String param1, String param2) {
        SentencesFragment fragment = new SentencesFragment();
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
        dbHelper = new DBHelper(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sentences, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcSentences = view.findViewById(R.id.rcSentences);
        sentences = dbHelper.getALLSentences();
        sentencesAdapter = new SentencesAdapter(sentences,this);
        rcSentences.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        rcSentences.addItemDecoration(new DividerItemDecoration(getContext(),LinearLayoutManager.VERTICAL));
        rcSentences.setAdapter(sentencesAdapter);
    }
    @Override
    public void onClick(Sentences sentences) {
            Intent intent = new Intent(getActivity(),SentencesDetailActivity.class);
            intent.putExtra("sentencesDetail", sentences);
            startActivity(intent);
    }


}