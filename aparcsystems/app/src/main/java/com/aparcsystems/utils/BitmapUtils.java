package com.aparcsystems.utils;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.aparcsystems.model.Photo;
import com.stripe.android.compat.AsyncTask;

import java.lang.ref.WeakReference;

import static android.graphics.BitmapFactory.Options;
import static android.graphics.BitmapFactory.decodeFile;

/**
 * Created by Emiliano on 16/02/2015.
 */
public class BitmapUtils {

    public static Bitmap decodeSampledBitmapFromGallery(String path, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final Options options = new Options();
        options.inJustDecodeBounds = true;
        decodeFile(path, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return decodeFile(path, options);
    }

    public static int calculateInSampleSize(
            Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static void showOnParent(Context context,View parent,String path,ImageView imageView,Uri imageUri){
        showImage(context,parent.getWidth(),parent.getHeight(),path,imageView,imageUri);
    }

    public static void showImage(Context context,int width,int height,String path,ImageView imageView,Uri imageUri){
        Bitmap bitmap = getBitmapInSpecificDimens(context, width, height, path, imageView.getId(), imageUri);
        imageView.setImageBitmap(bitmap);
    }

    public static Bitmap getBitmapInSpecificDimens(Context context, int width, int height, String path, int imageViewId, Uri imageUri) {
        Bitmap bitmap= BitmapUtils.decodeSampledBitmapFromGallery(path, imageViewId, width, height);
        if(imageUri!=null){
            if (getOrientation(context.getApplicationContext(), imageUri) != 0) {
                Matrix matrix = new Matrix();
                matrix.postRotate(getOrientation(context.getApplicationContext(), imageUri));
                bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            }
        }
        return bitmap;
    }

    public static int getOrientation(Context context, Uri photoUri) {
        Cursor cursor = context.getContentResolver().query(photoUri,
                new String[] { MediaStore.Images.ImageColumns.ORIENTATION },null, null, null);

        if (cursor.getCount() != 1) {
            return -1;
        }
        cursor.moveToFirst();
        return cursor.getInt(0);
    }


    private static class AsyncDrawable extends BitmapDrawable {
        private final WeakReference<BitmapWorkerTask> bitmapWorkerTaskReference;

        public AsyncDrawable(Resources res, Bitmap bitmap,
                             BitmapWorkerTask bitmapWorkerTask) {
            super(res, bitmap);
            bitmapWorkerTaskReference =
                    new WeakReference<BitmapWorkerTask>(bitmapWorkerTask);
        }

        public BitmapWorkerTask getBitmapWorkerTask() {
            return bitmapWorkerTaskReference.get();
        }
    }
    private static class BitmapWorkerTask extends AsyncTask<Integer, Void, Bitmap> {
        private final WeakReference<ImageView> imageViewReference;
        private int data = 0;
        private Context context;
        private int width;
        private int height;
        private Photo photo;
        private int newBackground;

        public BitmapWorkerTask(ImageView imageView, Context context, int width, int height, Photo photo, int newBackground) {
            this.context = context;
            this.width = width;
            this.height = height;
            this.photo = photo;
            this.newBackground = newBackground;
            imageViewReference = new WeakReference<ImageView>(imageView);
        }

        // Decode image in background.
        @Override
        protected Bitmap doInBackground(Integer... params) {
            data = params[0];
            return BitmapUtils.getBitmapInSpecificDimens(context,width,height,photo.getPath(),imageViewReference.get().getId(),photo.getUri());
        }

        // Once complete, see if ImageView is still around and set bitmap.
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (imageViewReference != null && bitmap != null) {
                final ImageView imageView = imageViewReference.get();
                if (imageView != null) {
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    imageView.setImageBitmap(bitmap);
//                    imageView.setBackgroundColor(newBackground);
                }
            }
        }
    }


    public static void loadBitmapInListBackground(Context context,int resId, ImageView imageView, int width, int height,Photo photo,int placeHolderResId,int placeHolderBackground,int newBackground) {
        if (cancelPotentialWork(resId, imageView)) {
            BitmapWorkerTask task = new BitmapWorkerTask(imageView, context, width, height, photo, newBackground);
            final AsyncDrawable asyncDrawable =
                    new AsyncDrawable(context.getResources(), BitmapFactory.decodeResource(context.getResources(), placeHolderResId), task);

            imageView.setImageDrawable(asyncDrawable);
            task.execute(resId);
        }
    }

    public static boolean cancelPotentialWork(int data, ImageView imageView) {
        final BitmapWorkerTask bitmapWorkerTask = getBitmapWorkerTask(imageView);

        if (bitmapWorkerTask != null) {
            final int bitmapData = bitmapWorkerTask.data;
            if (bitmapData != data) {
                // Cancel previous task
                bitmapWorkerTask.cancel(true);
            } else {
                // The same work is already in progress
                return false;
            }
        }
        // No task associated with the ImageView, or an existing task was cancelled
        return true;
    }
    private static BitmapWorkerTask getBitmapWorkerTask(ImageView imageView) {
        if (imageView != null) {
            final Drawable drawable = imageView.getDrawable();
            if (drawable instanceof AsyncDrawable) {
                final AsyncDrawable asyncDrawable = (AsyncDrawable) drawable;
                return asyncDrawable.getBitmapWorkerTask();
            }
        }
        return null;
    }


}
