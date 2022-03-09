package com.example.islamicapp.ui.azkar.azkarHome;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.islamicapp.R;
import com.example.islamicapp.data.pojo.azkar.ZekrTypes;

import java.util.ArrayList;
import java.util.HashSet;

public class AzkarTypesAdepter extends RecyclerView.Adapter<AzkarTypesAdepter.ViewHolder> {
  ArrayList<ZekrTypes> azkarTypes;
private Fragment fragment;

    public AzkarTypesAdepter(Fragment fragment) {
        this.fragment = fragment;
    }

    public void setAzkarTypes(HashSet<ZekrTypes> azkarTypes) {
        this.azkarTypes = new ArrayList<>(azkarTypes);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_zekr,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      holder.bind(azkarTypes.get(position));
    }

    @Override
    public int getItemCount() {
        return azkarTypes==null ? 0:azkarTypes.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView zekrName;
        ImageView zekrImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            zekrName = itemView.findViewById(R.id.zekr_name);
            zekrImage = itemView.findViewById(R.id.zekr_image);
        }
        public void bind(ZekrTypes zekr){
zekrName.setText(zekr.getZekrName());
if (zekr.getZekrImageId() != -1){
    zekrImage.setImageResource(zekr.getZekrImageId());
}
itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        NavHostFragment.findNavController(fragment)
                .navigate(AzkarHomeFragmentDirections.actionAzkarHomeFragment2ToAzkarListFragment(zekr.getZekrName()));
    }
});
        }

    }



}
