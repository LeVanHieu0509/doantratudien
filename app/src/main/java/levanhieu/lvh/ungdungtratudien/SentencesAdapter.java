package levanhieu.lvh.ungdungtratudien;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class SentencesAdapter extends RecyclerView.Adapter<SentencesAdapter.SentencesVH> {
    ArrayList<Sentences> sentences;
    SentencesAdapter.Listener listener;
    public SentencesAdapter(ArrayList<Sentences> sentences, Listener listener) {
        this.sentences = sentences;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SentencesVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sentences,parent,false);
        return new SentencesVH(view);
    }
    @Override
    public void onBindViewHolder(@NonNull SentencesVH holder, int position) {
        Sentences sentence = sentences.get(position);
        holder.txtSentence.setText(sentence.sentence);
        holder.txtDesSentence.setText(sentence.desSentence);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(sentence);
            }
        });
//        holder.txtMean.setText(vocabulary.word)s

    }
    interface  Listener{
        void onClick(Sentences sentences);
    }

    @Override
    public int getItemCount() {
        return sentences.size();
    }

    class SentencesVH extends RecyclerView.ViewHolder{

        TextView txtSentence;
        TextView txtDesSentence;


        public SentencesVH(@NonNull View itemView) {
            super(itemView);
            txtSentence = itemView.findViewById(R.id.txtSentence);
            txtDesSentence = itemView.findViewById(R.id.txtDesSentence);

        }
    }
}
