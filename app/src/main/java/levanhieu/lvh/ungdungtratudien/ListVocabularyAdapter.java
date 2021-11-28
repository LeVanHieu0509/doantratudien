package levanhieu.lvh.ungdungtratudien;

        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Filter;
        import android.widget.Filterable;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

        import java.util.ArrayList;

class ListVocabularyAdapter extends RecyclerView.Adapter<ListVocabularyAdapter.ListVocabularyVH> implements Filterable {

    ArrayList<Vocabulary> arrayList;
    ArrayList<Vocabulary> arrayListFilter;
    boolean flag = false;
    public ListVocabularyAdapter(ArrayList<Vocabulary> arrayList) {
        this.arrayList=arrayList;
        this.arrayListFilter = arrayList;
    }

    @NonNull
    @Override
    public ListVocabularyVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_vocabulary,parent,false);
        return new ListVocabularyVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListVocabularyVH holder, int position) {
        Vocabulary vocabulary = arrayListFilter.get(position);
        holder.txtWord.setText(vocabulary.word);
        holder.txtMean.setText(vocabulary.mean);

    }

    @Override
    public int getItemCount() {
        return arrayListFilter.size();
    }

    @Override
    public Filter getFilter() {
        return new FurnitureFilter();
    }

    class ListVocabularyVH extends RecyclerView.ViewHolder{

        TextView txtWord;
        TextView txtMean;
        public ListVocabularyVH(@NonNull View itemView) {
            super(itemView);
            txtWord = itemView.findViewById(R.id.txtWord);
            txtMean = itemView.findViewById(R.id.txtMean);

        }
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
}
