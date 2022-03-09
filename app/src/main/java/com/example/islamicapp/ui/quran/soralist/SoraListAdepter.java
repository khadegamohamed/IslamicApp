package com.example.islamicapp.ui.quran.soralist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.islamicapp.R;
import com.example.islamicapp.data.pojo.quran.Jozz;
import com.example.islamicapp.data.pojo.quran.Sora;
import com.example.islamicapp.ui.quran.quranIndex.QuranIndexFragmentDirections;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class SoraListAdepter extends RecyclerView.Adapter<SoraListAdepter.ViewHolder> {
    List<?> index;
   Fragment fragment;
    public SoraListAdepter(List<?> index,Fragment fragment) {
        this.index = index;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_sora_list,null,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
if (index.get(position) instanceof Sora){
    holder.bind((Sora) index.get(position));
}else if (index.get(position) instanceof Jozz){
    holder.bind((Jozz) index.get(position));
}else if(index.get(position) instanceof Integer){
holder.bind((Integer) index.get(position));
}
    }

    @Override
    public int getItemCount() {
        return index.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView soraName,from,to,soraNumber,wordTo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            soraName = itemView.findViewById(R.id.sora_name);
            from = itemView.findViewById(R.id.sora_start);
            to = itemView.findViewById(R.id.sora_end);
            soraNumber = itemView.findViewById(R.id.sora_number);
            wordTo = itemView.findViewById(R.id.wordTo);

        }
        public void bind(Sora sora){
            NumberFormat numberFormat = NumberFormat.getInstance(new Locale("ar","EG"));
       soraNumber.setText(numberFormat.format(sora.getSoraNumber()));
       wordTo.setVisibility(View.VISIBLE);
       soraName.setText(sora.getArabicName());
       from.setText(numberFormat.format(sora.getStartPage()));
       to.setText(numberFormat.format(sora.getEndPage()));
       itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               NavHostFragment.findNavController(fragment)
                       .navigate(QuranIndexFragmentDirections
                               .actionQuranIndexFragmentToQuranContainerFragment(sora.getStartPage()));
           }
       });
        }
public void bind(Jozz jozz){
    NumberFormat numberFormat = NumberFormat.getInstance(new Locale("ar","EG"));
    wordTo.setVisibility(View.VISIBLE);
    soraName.setVisibility(View.GONE);
    soraNumber.setText(numberFormat.format(jozz.getJozzNumber()));
from.setText(numberFormat.format(jozz.getStartPage()));
to.setText(numberFormat.format(jozz.getEndPage()));
    itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            NavHostFragment.findNavController(fragment)
                    .navigate(QuranIndexFragmentDirections
                            .actionQuranIndexFragmentToQuranContainerFragment(jozz.getStartPage()));
        }
    });
}
public void bind(Integer page){
    NumberFormat numberFormat = NumberFormat.getInstance(new Locale("ar","EG"));
    wordTo.setVisibility(View.VISIBLE);
    soraName.setVisibility(View.GONE);
    from.setVisibility(View.GONE);
    to.setVisibility(View.GONE);
    soraNumber.setText(numberFormat.format(page));
    itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            NavHostFragment.findNavController(fragment)
                    .navigate(QuranIndexFragmentDirections.actionQuranIndexFragmentToQuranContainerFragment(page));
        }
    });
}

    }

}
