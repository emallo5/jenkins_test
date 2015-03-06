package com.aparcsystems.utils;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileManager {

    private static final String CACHE_DIR = "android_authority";


	public static File getImagesDir(){
		return createDir(Environment.DIRECTORY_PICTURES);
	}

	private static File createDir(String directory) {
		boolean mExternalStorageAvailable = false;
		boolean mExternalStorageWriteable = false;
		String state = Environment.getExternalStorageState();

		if (Environment.MEDIA_MOUNTED.equals(state)) {
			mExternalStorageAvailable = mExternalStorageWriteable = true;
		} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			mExternalStorageAvailable = true;
			mExternalStorageWriteable = false;
		} else {
			mExternalStorageAvailable = mExternalStorageWriteable = false;
		}

		if (mExternalStorageAvailable && mExternalStorageWriteable){

			File dir = Environment.getExternalStoragePublicDirectory(directory);
			File cacheDir = new File(dir.getAbsolutePath() + "/" + CACHE_DIR);

			if (!cacheDir.exists()){
                cacheDir.mkdirs();
			}

			return cacheDir;
		}else{
			return null;
		}
	}


	public static File getFilename(File dir, String imageUrl){
		return getFilename(dir, imageUrl, 0);
	}


	public static File getFilename(File dir, String imageUrl, int recursivity){
		File videoFile;
		if (recursivity > 0){
			videoFile = new File(dir.getAbsolutePath() + "/" + imageUrl + "_" + recursivity + ".mp4");
		}else{
			videoFile = new File(dir.getAbsolutePath() + "/" + imageUrl + ".mp4");
		}

		if (videoFile.exists()){
			return getFilename(dir, imageUrl, recursivity + 1);
		}else{
			return videoFile;
		}
	}

	public static File createFile(String path, String filename) {
		File file = new File(path + "/" + filename);
		return file;
	}

	public static File createFile(String path, String folderName, String filename) {
		File file = new File(path + folderName, filename);

		return file;
	}

	public static String[] getAllFiles(String path, String folderName) {
		File file = new File(path + "/" + folderName);
		return file.list();
	}

    public static File saveFile(Context context, String destination, Uri source){
        InputStream input = null;
        OutputStream output = null;
        File file = null;

        try{
            file = new File(destination);
            input = context.getContentResolver().openInputStream(source);
            output = new FileOutputStream(file);

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = input.read(bytes)) != -1) {
                output.write(bytes, 0, read);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                if (output!=null){
                    output.close();
                }

                if (output!=null){
                    input.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return file;
    }

	public static void saveImageFile(String path, String fileName, Context context, Uri uri) {
		OutputStream fOut = null;
		Bitmap bitmap = null;
		InputStream input = null;
		File file = new File(path + "/" + fileName);
		try {
//			bitmap = ImageUtils.getThumbnail(context, uri, 500);

			BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
			bitmapOptions.inDither = true;// optional
			bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;// optional

			input = context.getContentResolver().openInputStream(uri);
			bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);

			fOut = new FileOutputStream(file);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fOut);
			fOut.flush();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(fOut != null) {
					fOut.close();
				}
				if(bitmap != null) {
					bitmap.recycle(); bitmap = null;
				}
				if(input != null) {
					input.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

    public static void saveImageFile(String fileName, Context context, Uri uri) {
        saveImageFile(getImagesDir().getAbsolutePath(), fileName, context, uri);
    }

    public static void saveImageFile(String fileName, Context context, Bitmap bitmap) {
        OutputStream fOut = null;
        InputStream input = null;
        File file = new File(getImagesDir().getAbsolutePath() + "/" + fileName.hashCode());
        try {

            fOut = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fOut);
            fOut.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(fOut != null) {
                    fOut.close();
                }
                if(bitmap != null) {
                    bitmap = null;
                }
                if(input != null) {
                    input.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static File saveFile(File from, String to) {
        InputStream is = null;
        OutputStream os = null;
        File output = new File(to);
        try {
            is = new FileInputStream(from);
            os = new FileOutputStream(output);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is!=null){
                    is.close();
                }
                if (os!=null){
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return output;
    }

    public static String getRealPath(Activity activity, Uri uri) {
        String res = null;
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = activity.getContentResolver().query(uri, proj, null, null, null);
        if(cursor != null && cursor.moveToFirst()){;
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
	    if(cursor != null) {
		    cursor.close();
	    }
        return res;
    }
}
