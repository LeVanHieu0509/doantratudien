package levanhieu.lvh.ungdungtratudien;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.HistoryVH> {
    ArrayList<Vocabulary> arrayList;
    FavouriteAdapter.Listener listener;


    public FavouriteAdapter(ArrayList<Vocabulary> arrayList , Listener listener) {
        this.arrayList = arrayList;
        this.listener = listener;

    }

    @NonNull
    @Override
    public FavouriteAdapter.HistoryVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_history,viewGroup ,false);
        return new HistoryVH(view);
    }
    @Override
    public void onBindViewHolder(@NonNull HistoryVH holder, int position) {
        Vocabulary vocabulary = arrayList.get(position);
        holder.txtHistoryWord.setText(vocabulary.word);
        holder.txtHistoryMean.setText(vocabulary.mean);
        holder.imgFavourite.setChecked(true);
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
                listener.onClickClose1(vocabulary);
            }
        });
        //
//        holder.imgFavourite.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                listener.onClickStar1(vocabulary);
//            }
//        });



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    interface  Listener{
        void onClick(Vocabulary vocabulary);
        void onClickClose1(Vocabulary vocabulary);
        void onClickStar1(Vocabulary vocabulary);
    }

    class HistoryVH extends RecyclerView.ViewHolder {
        TextView txtHistoryWord, txtHistoryMean;
        ToggleButton imgFavourite;
        ImageView imgClose;

        public HistoryVH(@NonNull View itemView){
            super(itemView);
            txtHistoryWord = itemView.findViewById(R.id.txtHistoryWord);
            txtHistoryMean = itemView.findViewById(R.id.txtHistoryMean);
            imgClose = itemView.findViewById(R.id.iconClose);
            imgFavourite = itemView.findViewById(R.id.toggleButton);
        }
    }
}


