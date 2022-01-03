package levanhieu.lvh.ungdungtratudien;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DiscoveryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiscoveryFragment extends Fragment {

    //ConstraintSet.Constraint constraint;
    ConstraintLayout learnVocabulary;
    ConstraintLayout History;
    ConstraintLayout QuicklyVocabulary;
    ConstraintLayout LearnSentences;

    //BottomNavigationView bottomNavigationView;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DiscoveryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DiscoveryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DiscoveryFragment newInstance(String param1, String param2) {
        DiscoveryFragment fragment = new DiscoveryFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_discovery, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        learnVocabulary = view.findViewById(R.id.LearnVocabulary);
        History = view.findViewById(R.id.History);
        QuicklyVocabulary = view.findViewById(R.id.QuicklyVocabulary);
        LearnSentences = view.findViewById(R.id.LearnSentences);
        //bottomNavigationView = view.findViewById(R.id.bottomNavigationView);
        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottomNavigationView);
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        learnVocabulary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                bottomNavigationView.getMenu().findItem(R.id.mnuVocabulary).setChecked(true);
//                Toast.makeText(getContext(), "ok", Toast.LENGTH_SHORT).show();

                Fragment fragment = new VocabularyFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content, fragment);
                MenuItem item = bottomNavigationView.getMenu().findItem(R.id.mnuVocabulary);
                item.setChecked(true);
                toolbar.setTitle("Vocabulary");
                //bottomNavigationView.getMenu().findItem(R.id.mnuVocabulary).setChecked(true);
                ft.commit();
            }
        });
        History.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                bottomNavigationView.getMenu().findItem(R.id.mnuVocabulary).setChecked(true);
//                Toast.makeText(getContext(), "ok", Toast.LENGTH_SHORT).show();

                Fragment fragment = new HistoryFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content, fragment);
                bottomNavigationView.getMenu().findItem(R.id.mnuHistory).setChecked(true);
                toolbar.setTitle("");
                //bottomNavigationView.getMenu().findItem(R.id.mnuHistory).setChecked(true);
                ft.commit();
            }
        });
        QuicklyVocabulary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                bottomNavigationView.getMenu().findItem(R.id.mnuVocabulary).setChecked(true);
//                Toast.makeText(getContext(), "ok", Toast.LENGTH_SHORT).show();

                Intent intent =  new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
            }
        });
        LearnSentences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                bottomNavigationView.getMenu().findItem(R.id.mnuVocabulary).setChecked(true);
//                Toast.makeText(getContext(), "ok", Toast.LENGTH_SHORT).show();

                Fragment fragment = new SentencesFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content, fragment);
                //bottomNavigationView.getMenu().findItem(R.id.mnuHistory).setChecked(true);
                ft.commit();
            }
        });
    }
}