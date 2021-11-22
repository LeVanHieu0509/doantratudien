package levanhieu.lvh.ungdungtratudien;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class HomeVocabularyAdapter extends RecyclerView.Adapter<HomeVocabularyAdapter.HomeVocabularyVH> {
    ArrayList<Vocabulary> vocabularies;
    public HomeVocabularyAdapter(ArrayList<Vocabulary> vocabularies) {
        this.vocabularies = vocabularies;
    }

    @NonNull
    @Override
    public HomeVocabularyVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemhomevocabulary,parent,false);
        return new HomeVocabularyVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeVocabularyVH holder, int position) {
        Vocabulary vocabulary = vocabularies.get(position);
        holder.txtWord.setText(vocabulary.word);
//        holder.txtMean.setText(vocabulary.word);

    }

    @Override
    public int getItemCount() {
        return vocabularies.size();
    }

    class HomeVocabularyVH extends RecyclerView.ViewHolder{

        TextView txtWord;
        TextView txtMean;


        public HomeVocabularyVH(@NonNull View itemView) {
            super(itemView);
            txtWord = itemView.findViewById(R.id.txtWord);
//            txtMean = itemView.findViewById(R.id.txtMean);

        }
    }
}