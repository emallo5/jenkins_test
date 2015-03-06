package com.aparcsystems.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.aparcsystems.R;
import com.aparcsystems.model.Photo;
import com.aparcsystems.utils.AndroidScreenUtils;
import com.aparcsystems.utils.BitmapUtils;
import com.stripe.android.compat.AsyncTask;

import java.lang.ref.WeakReference;
import java.util.List;

import static android.view.LayoutInflater.*;

/**
 * Created by Emiliano on 16/02/2015.
 */
public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {
    private List<Photo> photos;
    private Activity context;
    private OnItemClickListener onItemClickListener;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView imageView;
        public OnViewHolderClickListener onClickListener;

        public void setOnClickListener(OnViewHolderClickListener onClickListener) {
            this.onClickListener = onClickListener;
        }

        public ViewHolder(ImageView v) {
            super(v);
            imageView = v;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.onViwHolderClickListener(ViewHolder.this);
                }
            });
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public PhotoAdapter(List<Photo> photos, Activity context) {
        this.photos = photos;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public PhotoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        ImageView imageView = (ImageView) from(parent.getContext())
                .inflate(R.layout.gallery_item, parent, false);

        ViewHolder vh = new ViewHolder(imageView);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.setOnClickListener(new OnViewHolderClickListener() {
            @Override
            public void onViwHolderClickListener(ViewHolder holder) {
                if(onItemClickListener!=null){
                    onItemClickListener.onItemClickListener(photos.get(holder.getPosition()),holder.getPosition());
                }
            }
        });
        int width = AndroidScreenUtils.getDisplayScreen(context).x;
        holder.imageView.getLayoutParams().height=((width/2)*4)/6;
        holder.imageView.getLayoutParams().width=width/2;
        //TODO to change placeholder
        BitmapUtils.loadBitmapInListBackground(context,holder.imageView.getId(),holder.imageView,width,((width/2)*4)/6,photos.get(position),R.drawable.placeholder,R.color.place_holder_background,R.color.background_gallery);
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        public void onItemClickListener(Photo photo,int position);
    }

    public interface OnViewHolderClickListener{
        public void onViwHolderClickListener(ViewHolder holder);
    }





}
