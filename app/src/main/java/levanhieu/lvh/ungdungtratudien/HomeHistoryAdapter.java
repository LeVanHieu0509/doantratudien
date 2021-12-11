package levanhieu.lvh.ungdungtratudien;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class HomeHistoryAdapter extends RecyclerView.Adapter<HomeHistoryAdapter.HomeHistoryVH> {
    ArrayList<Vocabulary> vocabularies;
    HomeHistoryAdapter.Listener listener;
    boolean flag = false;

    public HomeHistoryAdapter(ArrayList<Vocabulary> vocabularies, Listener listener) {
        this.vocabularies = vocabularies;
        this.listener = listener;
    }

    @NonNull
    @Override
    public HomeHistoryVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemhomehistory,parent,false);
        return new HomeHistoryVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeHistoryVH holder, int position) {
        Vocabulary vocabulary = vocabularies.get(position);
        holder.txtWord.setText(vocabulary.word);
        //holder.txtMean.setText(vocabulary.mean);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(vocabulary);
            }
        });

    }
    interface  Listener{
        void onClick(Vocabulary vocabulary);
    }

    @Override
    public int getItemCount() {
        return vocabularies.size();
    }

    class HomeHistoryVH extends RecyclerView.ViewHolder{
        TextView txtWord;
        TextView txtMean;
        public HomeHistoryVH(@NonNull View itemView) {
            super(itemView);
            txtWord = itemView.findViewById(R.id.txtHistoryWord);
            txtMean = itemView.findViewById(R.id.txtHistoryMean);

        }
    }
}
