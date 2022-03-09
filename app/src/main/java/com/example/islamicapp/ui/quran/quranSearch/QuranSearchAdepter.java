package com.example.islamicapp.ui.quran.quranSearch;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.islamicapp.R;
import com.example.islamicapp.data.pojo.quran.Aya;

import java.util.List;

public class QuranSearchAdepter extends RecyclerView.Adapter<QuranSearchAdepter.ViewHolder> {
List<Aya> ayat;
private Fragment fragment;

    public QuranSearchAdepter(Fragment fragment) {
        this.fragment = fragment;
    }

    public void setAyas(List<Aya> ayat) {
        this.ayat  =ayat;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_quran_search,null,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
holder.bind(ayat.get(position));
    }

    @Override
    public int getItemCount() {
        return ayat==null ?0 : ayat.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ayaNumber,soraNumber,soraName,ayaContent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ayaNumber = itemView.findViewById(R.id.aya_no);
            soraName = itemView.findViewById(R.id.sora_name);
            soraNumber = itemView.findViewById(R.id.sora_number);
            ayaContent = itemView.findViewById(R.id.aya_content);
        }
        public void bind(Aya aya){
soraNumber.setText(String.valueOf(aya.getSora()));
soraName.setText((aya.getSora_name_ar()));
ayaNumber.setText(String.valueOf(aya.getAya_no()));
ayaContent.setText(aya.getAya_text());
itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        NavHostFragment.findNavController(fragment).navigate(QuranSearchFragmentDirections.
                actionQuranSearchFragmentToQuranContainerFragment(aya.getPage()));
    }
});
        }
    }

}
