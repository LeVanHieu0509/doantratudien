package levanhieu.lvh.ungdungtratudien;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.util.Printer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;


public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryVH> {
    ArrayList<Vocabulary> arrayList;
    HistoryAdapter.Listener listener;
    boolean ischeck ;
    Context context;

    public HistoryAdapter(ArrayList<Vocabulary> arrayList , Listener listener) {
        this.arrayList = arrayList;
        this.listener = listener;

    }

    @NonNull
    @Override
    public HistoryAdapter.HistoryVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_history,viewGroup ,false);
//        SharedPreferences preferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
//         ischeck = preferences.getBoolean("check",false);
//         Log.d("aqq", String.valueOf(ischeck));
        return new HistoryVH(view);
    }
    @Override
    public void onBindViewHolder(@NonNull HistoryVH holder, int position) {
        Vocabulary vocabulary = arrayList.get(position);
        holder.txtHistoryWord.setText(vocabulary.word);
        holder.txtHistoryMean.setText(vocabulary.mean);

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
//        holder.imgFavourite.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                listener.onClickStar(vocabulary);
//            }
//        });
//        holder.toggleButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                listener.onClickStar(vocabulary);
//            }
//        });
        holder.toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean ischecked) {
                listener.onClickStar(vocabulary,ischecked);


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
        void onClickStar(Vocabulary vocabulary, boolean isChecked);


    }

    class HistoryVH extends RecyclerView.ViewHolder {
        TextView txtHistoryWord, txtHistoryMean;

        ImageView imgClose;
        ToggleButton toggleButton;

        public HistoryVH(@NonNull View itemView){
            super(itemView);
            txtHistoryWord = itemView.findViewById(R.id.txtHistoryWord);
            txtHistoryMean = itemView.findViewById(R.id.txtHistoryMean);
            imgClose = itemView.findViewById(R.id.iconClose);
            toggleButton = itemView.findViewById(R.id.toggleButton);
        }
    }
}

