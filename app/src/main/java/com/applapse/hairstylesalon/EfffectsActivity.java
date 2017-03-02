package com.applapse.hairstylesalon;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.applapse.hairstylesalon.horizontal_listview.HorizontalItemAdapter;
import com.meetme.android.horizontallistview.HorizontalListView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.zomato.photofilters.geometry.Point;
import com.zomato.photofilters.imageprocessors.Filter;
import com.zomato.photofilters.imageprocessors.subfilters.BrightnessSubfilter;
import com.zomato.photofilters.imageprocessors.subfilters.ColorOverlaySubfilter;
import com.zomato.photofilters.imageprocessors.subfilters.ContrastSubfilter;
import com.zomato.photofilters.imageprocessors.subfilters.SaturationSubfilter;
import com.zomato.photofilters.imageprocessors.subfilters.ToneCurveSubfilter;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 04/01/2017.
 */
public class EfffectsActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ImageView ivback,ivsave,imageView;
    private TextView textView6;
    private String path="",name;
    private HorizontalListView hListSticker;
    private String[] strArrayAssetSticker;
    Filter myFilter;
    Bitmap inputImage;
    FrameLayout relativeLayout;
    Typeface typeface;
    static
    {
        System.loadLibrary("NativeImageProcessor");
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.effects);
        initview();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        typeface= Typeface.createFromAsset(getAssets(),"open-sans.semibold.ttf");
        path = getIntent().getStringExtra(Utils.path);
        name=getIntent().getStringExtra(Utils.name);
        ImageLoader.getInstance().displayImage("file:///" + path, imageView);
        hListSticker = (HorizontalListView) findViewById(R.id.hlvCustomList);
        hListSticker.setOnItemClickListener(this);

        getSupportActionBar().hide();
        strArrayAssetSticker = loadImagesFromAssets(Utils.filters);

        hListSticker.setAdapter(new HorizontalItemAdapter(
                EfffectsActivity.this, strArrayAssetSticker));
    }
    private void initview(){
        ivback=(ImageView)findViewById(R.id.ivBack);
        ivsave=(ImageView)findViewById(R.id.ivSave);
        textView6=(TextView)findViewById(R.id.textView6);
        imageView=(ImageView)findViewById(R.id.imageview);
        relativeLayout= (FrameLayout) findViewById(R.id.middleview);
     ivback.setOnClickListener(this);
        ivsave.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
switch (view.getId()){
    case R.id.ivSave:
        final Dialog dialog = new Dialog(EfffectsActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.next);
        TextView tvSave = (TextView) dialog.findViewById(R.id.tvSave);

        tvSave.setTypeface(typeface);


        tvSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                dialog.dismiss();
                new Storeimage().execute();
            }
        });
        dialog.show();
        break;
    case R.id.ivBack:
        dia();
        break;

}
    }
    public void GetOrizinalImage() {

       // ImageLoader.getInstance().displayImage("file:///" + path, imageView);
        imageView.setImageURI(Uri.parse("file://"+path));
        Drawable d = imageView.getDrawable();
        inputImage = Bitmap.createBitmap(d.getIntrinsicWidth(), d.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        d.draw(new Canvas(inputImage));
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

switch (i){
    case 0:

        //    ImageLoader.getInstance().displayImage("file:///" + path, imageView);
            GetOrizinalImage();
            myFilter = new Filter();
            myFilter.addSubFilter(new SaturationSubfilter(2.3f));
            imageView.setImageBitmap(myFilter.processFilter(inputImage));

        break;
    case 1:

          //  ImageLoader.getInstance().displayImage("file:///" + path, imageView);
            GetOrizinalImage();
            myFilter = new Filter();
            myFilter.addSubFilter(new ColorOverlaySubfilter(100, 0.7f, 0.0f, 1.0f));
            this.imageView.setImageBitmap(myFilter.processFilter(this.inputImage));
break;
    case 2:


            GetOrizinalImage();
            myFilter = new Filter();
            myFilter.addSubFilter(new ColorOverlaySubfilter(100, 0.2f, 0.2f, 0.0f));
            this.imageView.setImageBitmap(myFilter.processFilter(this.inputImage));


        break;
    case 3:
        GetOrizinalImage();
        myFilter = new Filter();
        myFilter.addSubFilter(new ContrastSubfilter(1.2f));
        this.imageView.setImageBitmap(myFilter.processFilter(this.inputImage));

        break;
    case 4:
        GetOrizinalImage();
        myFilter = new Filter();
        myFilter.addSubFilter(new BrightnessSubfilter(30));
        this.imageView.setImageBitmap(myFilter.processFilter(this.inputImage));

        break;
    case 5:
        GetOrizinalImage();
        myFilter = new Filter();
        myFilter.addSubFilter(new ColorOverlaySubfilter(100, 0.0f, 0.4f, 1.0f));
        this.imageView.setImageBitmap(myFilter.processFilter(this.inputImage));

        break;
    case 6:
        GetOrizinalImage();
        myFilter = new Filter();
        myFilter.addSubFilter(new ColorOverlaySubfilter(100, 0.5f, 0.5f, 0.5f));
        this.imageView.setImageBitmap(myFilter.processFilter(this.inputImage));

        break;
    case 7:
        GetOrizinalImage();
        myFilter = new Filter();
        myFilter.addSubFilter(new ColorOverlaySubfilter(100, 0.1f, 1.0f, 0.8f));
        this.imageView.setImageBitmap(myFilter.processFilter(this.inputImage));

        break;
    case 8:
        GetOrizinalImage();
        myFilter = new Filter();
        myFilter.addSubFilter(new ColorOverlaySubfilter(100, 0.3f, 0.5f, 0.0f));
        this.imageView.setImageBitmap(myFilter.processFilter(this.inputImage));

        break;
    case 9:
        GetOrizinalImage();
        myFilter = new Filter();
        myFilter.addSubFilter(new ColorOverlaySubfilter(100, 0.8f, 0.0f, 0.5f));
        this.imageView.setImageBitmap(myFilter.processFilter(this.inputImage));

        break;
    case 10:
        GetOrizinalImage();
        myFilter = new Filter();
        myFilter.addSubFilter(new ColorOverlaySubfilter(100, 1.0f, 0.5f, 0.0f));
        this.imageView.setImageBitmap(myFilter.processFilter(this.inputImage));

        break;
    case 11:
        GetOrizinalImage();
        myFilter = new Filter();
        myFilter.addSubFilter(new ColorOverlaySubfilter(100, 0.0f, 0.0f, 1.0f));
        this.imageView.setImageBitmap(myFilter.processFilter(this.inputImage));

        break;
    case 12:
        GetOrizinalImage();
        myFilter = new Filter();
        myFilter.addSubFilter(new ColorOverlaySubfilter(100, 1.0f, 0.5f, 0.0f));
        this.imageView.setImageBitmap(myFilter.processFilter(this.inputImage));

        break;
    case 13:
        GetOrizinalImage();
        myFilter = new Filter();
        myFilter.addSubFilter(new ColorOverlaySubfilter(100, 0.3f, 0.0f, 0.8f));
        this.imageView.setImageBitmap(myFilter.processFilter(this.inputImage));

        break;

    case 14:
        GetOrizinalImage();
        myFilter = new Filter();
        myFilter.addSubFilter(new ToneCurveSubfilter(new Point[]{new Point(0.0f, 0.0f), new Point(100.0f, 159.0f), new Point(255.0f, 255.0f)}, null, null, null));
        this.imageView.setImageBitmap(myFilter.processFilter(this.inputImage));

        break;
    case 15:
        GetOrizinalImage();
        myFilter = new Filter();
        myFilter.addSubFilter(new ColorOverlaySubfilter(100, 0.8f, 0.0f, 0.0f));
        this.imageView.setImageBitmap(myFilter.processFilter(this.inputImage));

        break;
    case 16:
        GetOrizinalImage();
        myFilter = new Filter();
        myFilter.addSubFilter(new ColorOverlaySubfilter(100, 0.0f, 0.6f, 0.0f));
        this.imageView.setImageBitmap(myFilter.processFilter(this.inputImage));

        break;
    case 17:
        GetOrizinalImage();
        myFilter = new Filter();
        myFilter.addSubFilter(new ToneCurveSubfilter(new Point[]{new Point(0.0f, 0.0f), new Point(100.0f, 200.0f), new Point(255.0f, 255.0f)}, null, null, null));
        this.imageView.setImageBitmap(myFilter.processFilter(this.inputImage));

        break;
    case 18:
        GetOrizinalImage();
        myFilter = new Filter();
        myFilter.addSubFilter(new SaturationSubfilter(4.3f));
        this.imageView.setImageBitmap(myFilter.processFilter(this.inputImage));

        break;


}
    }
    private String[] loadImagesFromAssets(String path) {
        try {
            Resources res = getResources(); // if you are in an activity
            AssetManager am = res.getAssets();
            String fileList[] = am.list(path);

            String[] gridViewItems = new String[fileList.length];
            if (fileList != null) {
                for (int i = 0; i < fileList.length; i++) {
                    if (fileList[i].contains(".png")
                            || fileList[i].contains(".jpg")) {
                        gridViewItems[i] = path + File.separator + fileList[i];
                    }

                }
            }
            return gridViewItems;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    private void removeTempImage() {
       try {
            if (!path.equals("")) {
                new File(path).delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void dia(){
        final Dialog dialog = new Dialog(EfffectsActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.leave_sure);
        TextView tvyes = (TextView) dialog.findViewById(R.id.tvyes);
        TextView dail=(TextView)dialog.findViewById(R.id.dial);
        dail.setTypeface(typeface);
        TextView tvno=(TextView)dialog.findViewById(R.id.tvno);
        tvno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        tvno.setTypeface(typeface);
        tvyes.setTypeface(typeface);

        tvyes.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                removeTempImage();
                sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + Environment.getExternalStorageDirectory())));
                dialog.dismiss();
               EfffectsActivity.this.finish();

            }
        });
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        dia();
    }

    class Storeimage extends AsyncTask<Void,Void,Void> {
        String fileSavedPath;
        ProgressDialog pDialog;
        @Override
        protected void onPostExecute(Void aVoid) {
            pDialog.dismiss();


       final Dialog dialog = new Dialog(EfffectsActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.saved);
        TextView tvdone = (TextView) dialog.findViewById(R.id.tvdone);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                // Prevent dialog close on back press button
                return keyCode == KeyEvent.KEYCODE_BACK;
            }});
        tvdone.setTypeface(typeface);
        tvdone.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();

                EfffectsActivity.this.finish();
HairActivity.getInstance().finish();
              /*  Intent sl=new Intent(EfffectsActivity.this,exitone.class);
                startActivity(sl);
                */


            }
        });
        dialog.show();
     //       removeTempImage();
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onPreExecute() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                pDialog = new ProgressDialog(
                        new ContextThemeWrapper(EfffectsActivity.this,
                                android.R.style.Theme_Holo_Light_Dialog));
            } else {
                pDialog = new ProgressDialog(EfffectsActivity.this);
            }
            pDialog.setTitle("Saving Image");
            pDialog.setMessage("Please wait while saving image");
            pDialog.setCancelable(false);
            pDialog.show();
            super.onPreExecute();
        }


        @Override
        protected Void doInBackground(Void... voids) {
            String root = Environment.getExternalStorageDirectory().toString();
            File myDir = new File(root + File.separator
                    + getResources().getString(R.string.app_name));
            myDir.mkdirs();

            SimpleDateFormat dateFormat = new SimpleDateFormat(
                    "yyyyMMdd_HH_mm_ss");
            String currentTimeStamp = dateFormat.format(new Date());

            String fname = name;
            File file = new File(myDir, fname);
            fileSavedPath = file.getAbsolutePath();
            if (file.exists())
                file.delete();
            try {
                FileOutputStream out = new FileOutputStream(file);

                getBitmapFromView(relativeLayout).compress(
                        Bitmap.CompressFormat.PNG, 100, out);
                out.flush();
                out.close();
                MediaScannerConnection.scanFile(EfffectsActivity.this,
                        new String[] { fileSavedPath },
                        new String[] { "image/png" }, null);
            } catch (Exception e) {
                e.printStackTrace();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(EfffectsActivity.this,
                                "Some thing wrong happen", Toast.LENGTH_SHORT)
                                .show();
                        pDialog.dismiss();
                    }
                });
            }
            return null;

        }
    }
    public Bitmap getBitmapFromView(View view) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(),
                view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null)
            bgDrawable.draw(canvas);
        else
            canvas.drawColor(Color.TRANSPARENT);
        view.draw(canvas);
        return returnedBitmap;
    }


}
