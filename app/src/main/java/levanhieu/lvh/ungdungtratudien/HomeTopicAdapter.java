package levanhieu.lvh.ungdungtratudien;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class HomeTopicAdapter extends RecyclerView.Adapter<HomeTopicAdapter.HomeTopicVH> {
    ArrayList<Topics> topics;
    HomeTopicAdapter.Listener listener;
    public HomeTopicAdapter(ArrayList<Topics> topics, Listener listener) {
        this.topics = topics;
        this.listener = listener;
    }

    @NonNull
    @Override
    public HomeTopicVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemhomevocabulary,parent,false);
        return new HomeTopicVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeTopicVH holder, int position) {
        Topics topic = topics.get(position);
        holder.txtWord.setText(topic.name);
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

    class HomeTopicVH extends RecyclerView.ViewHolder{

        TextView txtWord;
        TextView txtMean;


        public HomeTopicVH(@NonNull View itemView) {
            super(itemView);
            txtWord = itemView.findViewById(R.id.txtHistoryWord);
//            txtMean = itemView.findViewById(R.id.txtMean);

        }
    }
}
