package me.mikasa.popupwindow;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by mikasa on 2018/12/13.
 */
public class CustomAdapter extends RecyclerView.Adapter {
    private List<String>mData;
    public void setData(List<String>data){
        mData=data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder=(ViewHolder)holder;
        viewHolder.mTextView.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData==null?0:mData.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView;
        public ViewHolder(View itemView){
            super(itemView);
            mTextView=itemView.findViewById(R.id.text_content);
        }
    }
}
