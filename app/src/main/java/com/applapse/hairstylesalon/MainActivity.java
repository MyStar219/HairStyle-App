package com.applapse.hairstylesalon;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

@SuppressWarnings("deprecation")
public class MainActivity extends AppCompatActivity {

    private LinearLayout pixel,textphoto,mImageViewPhotos;
    private TextView text,more,name,textname;
    ImageView ivSave;
    boolean back= true;
    private static final String MARKET_CONSTANT = "market://details?id=";
    private static final String GOOGLE_PLAY_CONSTANT = "http://play.google.com/store/apps/details?id=";
    Typeface typeface;
    TextView textView;
    boolean range = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        getSupportActionBar().hide();
        methodtwo();

        rate.showRate(this);




        more=(TextView)findViewById(R.id.more);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        name=(TextView)findViewById(R.id.name);
        textname=(TextView)findViewById(R.id.textname);
        mImageViewPhotos = (LinearLayout) findViewById(R.id.img_photos);
       typeface= Typeface.createFromAsset(getAssets(),"open-sans.semibold.ttf");
        textView=(TextView)findViewById(R.id.enter);
        textView.setTypeface(typeface);
        ivSave=(ImageView)findViewById(R.id.ivSaveone);
        more.setTypeface(typeface);
        name.setTypeface(typeface);
        textname.setTypeface(typeface);
        ivSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().setBackgroundDrawable(
                        new ColorDrawable(Color.TRANSPARENT));
                dialog.setContentView(R.layout.menu_main);
                TextView tvShare = (TextView) dialog.findViewById(R.id.tvShare);
                TextView tvRate=(TextView)dialog.findViewById(R.id.tvRate);
                tvShare.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent kl = new Intent(android.content.Intent.ACTION_SEND);
                        kl.setType("text/plain");
                        kl.putExtra(android.content.Intent.EXTRA_SUBJECT, "Mustify");
                        kl.putExtra(android.content.Intent.EXTRA_TEXT, "One of the best Hair and Mustache style app now available on Google Play - https://play.google.com/store/apps/details?id="+getPackageName());
                        startActivity(Intent.createChooser(kl, "Share via"));
                        dialog.dismiss();
                    }
                });
                tvRate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                          //  startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(MARKET_CONSTANT + getPackageName())));
                        } catch (android.content.ActivityNotFoundException anfe) {
                       //     startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(GOOGLE_PLAY_CONSTANT + getPackageName())));
                        }
                        dialog.dismiss();
                    }
                });
                dialog.show();

            }
        });
        mImageViewPhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(galleryIntent, 1);
            }
        });
        pixel=(LinearLayout)findViewById(R.id.pixel);
        pixel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                //    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(MARKET_CONSTANT + "com.photoeditor.photo_editor.pixel")));
                } catch (android.content.ActivityNotFoundException anfe) {
                //    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(GOOGLE_PLAY_CONSTANT + "com.photoeditor.photo_editor.pixel")));
                }
            }
        });
        textphoto=(LinearLayout)findViewById(R.id.textphoto);
        textphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                //    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(MARKET_CONSTANT + "com.writeonpictures.photo_caption_maker")));
                } catch (android.content.ActivityNotFoundException anfe) {
                   // startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(GOOGLE_PLAY_CONSTANT + "com.writeonpictures.photo_caption_maker")));
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == 1)&& data!=null) {
            Uri imageUri = data.getData();
            Intent mIntent = new Intent(this, HairActivity.class);
            AppConstant.IMAGE_EDIT_PATH = getPath(MainActivity.this,imageUri);
             mIntent.putExtra("yo",imageUri);
            startActivity(mIntent);


        }
    }
    public static String getPath(final Context context, final Uri uri)
    {
        final boolean isKitKatOrAbove = Build.VERSION.SDK_INT >=  Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKatOrAbove && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[] {
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }
    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }
    private void leavesure(){

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.cross_promote);
        TextView name=(TextView)dialog.findViewById(R.id.name);
        TextView free=(TextView)dialog.findViewById(R.id.freeapp);
        free.setTypeface(typeface);
        name.setTypeface(typeface);
        Window window = dialog.getWindow();
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int screenWidth = (int) (metrics.widthPixels * 0.90);
        int screenHeight=(int)(metrics.heightPixels*0.40);

        window.setLayout(screenWidth, LinearLayout.LayoutParams.WRAP_CONTENT);
        Button post=(Button)dialog.findViewById(R.id.submitpost);
        post.setTypeface(typeface);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(MARKET_CONSTANT + "com.writeonpictures.photo_caption_maker")));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(GOOGLE_PLAY_CONSTANT + "com.writeonpictures.photo_caption_maker")));
                }
                dialog.dismiss();
                MainActivity.super.onBackPressed();

            }
        });

        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        /*if(range){

            leavesure();
            range=false;

        }else{
            super.onBackPressed();
        } */
        super.onBackPressed();
    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @AfterPermissionGranted(11)
    private  void methodtwo(){
        String[] perms = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.INTERNET};
        if (EasyPermissions.hasPermissions(this, perms)) {
            // Already have permission, do the thing
            // ...


        } else {
            EasyPermissions.requestPermissions(this, "This app requires permission to store and read saved images",
                    11, perms);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

}
