package levanhieu.lvh.ungdungtratudien;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class VocabularyAdapter extends RecyclerView.Adapter<VocabularyAdapter.VocabularyVH> {
    ArrayList<Topics> topics;
    VocabularyAdapter.Listener listener;
    public VocabularyAdapter(ArrayList<Topics> topics, Listener listener) {
        this.topics = topics;
        this.listener = listener;
    }

    @NonNull
    @Override
    public VocabularyVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_vocabulary,parent,false);
        return new VocabularyVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VocabularyVH holder, int position) {
        Topics topic = topics.get(position);
        holder.txtTopicVocabulary.setText(topic.name);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(topic);
            }
        });
//        holder.txtMean.setText(vocabulary.word)s

    }
    interface  Listener{
        void onClick(Topics topics);
    }

    @Override
    public int getItemCount() {
        return topics.size();
    }

    class VocabularyVH extends RecyclerView.ViewHolder{

        TextView txtTopicVocabulary;
        TextView txtMean;


        public VocabularyVH(@NonNull View itemView) {
            super(itemView);
            txtTopicVocabulary = itemView.findViewById(R.id.txtTopicVocabulary);
//            txtMean = itemView.findViewById(R.id.txtMean);

        }
    }
}
