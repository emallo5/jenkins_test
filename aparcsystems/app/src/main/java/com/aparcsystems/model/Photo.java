package com.aparcsystems.model;

import android.net.Uri;

import java.io.Serializable;

/**
 * Created by Emiliano on 16/02/2015.
 */
public class Photo implements Serializable {
    private String path;
    private String uri;

    public Photo() {
    }

    public Photo(String path, Uri uri) {
        this.path = path;
        this.uri = uri!=null?uri.toString():null;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Uri getUri() {
        if(uri!=null){
            return Uri.parse(uri);
        }else{
            return null;
        }

    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public boolean equals(Object o) {
        if(o!=null && path!=null && !path.isEmpty() && ((Photo)o).getPath()!=null && !((Photo)o).getPath().isEmpty() ){
            return ((Photo)o).getPath().equalsIgnoreCase(path);
        }
        return false;
    }
}
