package levanhieu.lvh.ungdungtratudien;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.util.Printer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;


public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryVH> implements Filterable {
    ArrayList<Vocabulary> arrayList;
    HistoryAdapter.Listener listener;
    DBHelper dbHelper;
    boolean flag = false;
    ArrayList<Vocabulary> arrayListFilter;


    public HistoryAdapter(ArrayList<Vocabulary> arrayList , Listener listener) {
        this.arrayList = arrayList;
        this.listener = listener;
        this.arrayListFilter = arrayList;
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
        Vocabulary vocabulary = arrayListFilter.get(position);
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
        holder.toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean ischecked) {
                listener.onClickStar(vocabulary,ischecked);
            }
        });

    }


    @Override
    public int getItemCount() {
        return arrayListFilter.size();
    }

    @Override
    public Filter getFilter() {
        return new FurnitureFilter();
    }

    interface  Listener{
        void onClick(Vocabulary vocabulary);
        void onClickClose(Vocabulary vocabulary);
        void onClickStar(Vocabulary vocabulary, boolean isChecked);


    }
    class FurnitureFilter extends Filter{

        //loc du lieu
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String charString = constraint.toString();
            if(charString.isEmpty()){
                arrayListFilter = arrayList;
            } else {
                ArrayList<Vocabulary> filteredList = new ArrayList<>();
                for (Vocabulary row: arrayList){
                    //loc dua tren ten va dua tren mota
                    if( row.word.contains(charString) ){
                        filteredList.add(row);
                    }
                }
                arrayListFilter = filteredList;
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values =arrayListFilter;
            return filterResults;
        }

        //sau khi loc xong lay ket qua va hien thi ra ngoai
        @Override
        protected void publishResults(CharSequence constraint, FilterResults filterResults) {
            //gans lai bo du lieu loc
            arrayListFilter = (ArrayList<Vocabulary>) filterResults.values;
            notifyDataSetChanged();
        }
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

