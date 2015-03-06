package com.aparcsystems.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aparcsystems.R;
import com.aparcsystems.model.Photo;
import com.aparcsystems.ui.activity.BigImageActivity;
import com.aparcsystems.ui.adapter.PhotoAdapter;
import com.aparcsystems.utils.ExtraConstants;
import com.aparcsystems.utils.Lists;

import java.util.List;

/**
 * Created by Emiliano on 16/02/2015.
 */
public class GalleryFragment extends BaseFragment {
    private List<Photo> photos;
    private RecyclerView recyclerView;
    private PhotoAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    public static final int SHOW_PHOTO_REQUEST_CODE=0;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.gallery_fragment,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        photos= (List<Photo>) getArguments().getSerializable(ExtraConstants.PHOTOS_EXTRA);

        recyclerView = (RecyclerView) getView().findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        adapter = new PhotoAdapter(photos,getActivity());
        adapter.setOnItemClickListener(new PhotoAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(Photo photo,int position) {
                BigImageActivity.startActivityForResult(getActivity(),photo,position,SHOW_PHOTO_REQUEST_CODE);
            }
        });
        recyclerView.setAdapter(adapter);

    }

    public void returnValue() {
        Intent intent=new Intent();
        Bundle bundle=new Bundle();
        bundle.putSerializable(ExtraConstants.PHOTOS_EXTRA, (java.io.Serializable) photos);
        intent.putExtras(bundle);
        getActivity().setResult(Activity.RESULT_OK,intent);
        getActivity().finish();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Activity.RESULT_OK && requestCode==SHOW_PHOTO_REQUEST_CODE){
            Photo photo= (Photo) data.getSerializableExtra(ExtraConstants.PHOTO_EXTRA);
            int position=data.getIntExtra(ExtraConstants.PHOTO_POSITION_EXTRA,0);
            photos.remove(photo);
            if(Lists.isNullOrEmpty(photos)){
                returnValue();
            }else{
                adapter.notifyItemRemoved(position);
            }
        }
    }
}
