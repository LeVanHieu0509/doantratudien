package levanhieu.lvh.ungdungtratudien;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryVH> {
    ArrayList<Vocabulary> arrayList;
    HistoryAdapter.Listener listener;


    public HistoryAdapter(ArrayList<Vocabulary> arrayList , Listener listener) {
        this.arrayList = arrayList;
        this.listener = listener;

    }

    @NonNull
    @Override
    public HistoryAdapter.HistoryVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_history,viewGroup ,false);
        return new HistoryVH(view);
    }
    @Override
    public void onBindViewHolder(@NonNull HistoryVH holder, int position) {
        Vocabulary vocabulary = arrayList.get(position);
        holder.txtHistoryWord.setText(vocabulary.word);
        holder.txtHistoryMean.setText(vocabulary.mean);
//        holder.imgFavourite.setImageResource(furniture.image);
//        holder.imgClose.setImageResource(furniture.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(vocabulary);
            }
        });
        holder.imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClickClose(vocabulary);
            }
        });
        //
        holder.imgFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClickStar(vocabulary);
            }
        });



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    interface  Listener{
        void onClick(Vocabulary vocabulary);
        void onClickClose(Vocabulary vocabulary);
        void onClickStar(Vocabulary vocabulary);
    }

    class HistoryVH extends RecyclerView.ViewHolder {
        TextView txtHistoryWord, txtHistoryMean;
        ImageView imgFavourite;
        ImageView imgClose;

        public HistoryVH(@NonNull View itemView){
            super(itemView);
            txtHistoryWord = itemView.findViewById(R.id.txtHistoryWord);
            txtHistoryMean = itemView.findViewById(R.id.txtHistoryMean);
            imgFavourite = itemView.findViewById(R.id.iconStar);
            imgClose = itemView.findViewById(R.id.iconClose);
        }
    }
}

