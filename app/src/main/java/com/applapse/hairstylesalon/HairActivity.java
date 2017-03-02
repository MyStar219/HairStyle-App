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
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.applapse.hairstylesalon.horizontal_listview.HorizontalItemAdapter;
import com.applapse.hairstylesalon.stickerwala.StickerView;
import com.meetme.android.horizontallistview.HorizontalListView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
/**
 * Created by Administrator on 09/11/2016.
 */

@SuppressWarnings("deprecation")
public class HairActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener, SeekBar.OnSeekBarChangeListener {
    private LinearLayout bottombar,tvstick,tvreset,tvbg,tvRotate;
    private HorizontalListView horizontalListView;
    boolean isHair=false,isMustache=false;
    private ImageView imageview,imagehair,ivback,ivsave;

   static HairActivity hairActivity;
    private String[] strArrayAssetHair,strArrayMustache;
  //  private StickerView imagemustache,stickerview;
  final int mus=2,hair=3;
    private String path;
    private Typeface typeface;
    private FrameLayout relativeLayout;
    private TextView titles,hairs,mustache,rotate,reset;
    private ProgressDialog pdial;
  //  private com.applapse.hairstylesalon.stickerwala.StickerView mCurrentView;
private StickerView mCurrentView;
    private ArrayList<View> mViews;
    StickerView stickerView,stickerViewone;
    private LinearLayout color_picker,color_pickertwo;
    private ImageButton picker_close,picker_closeone;

    boolean hairone=false,mustacheone=false;
    private SeekBar seekBarRotate, seekBar1, seekBar2, seekBar3,seekBarRed, seekBarBlue,seekBarLastModify;
    int brightnessvalue=50;
    private Bitmap currentBitmap, originalImage,tempImage;
    private Button resets;
    private TextView txtcolor,textviewbright;


    private SeekBar seekBarRotateone, seekBar1one, seekBar2one, seekBar3one,seekBarRedone, seekBarBlueone,seekBarLastModifyone;
    int brightnessvalueone=50;
    private Bitmap currentBitmapone, originalImageone,tempImageone;
    private Button resetsone;
    private TextView txtcolorone,textviewbrightone;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hairview);
        initialview();
        getSupportActionBar().hide();
        hairActivity=this;
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mViews=new ArrayList<>();
       stickerView = new StickerView(this);
       stickerViewone = new StickerView(this);
        if(AppConstant.IMAGE_EDIT_PATH!=null){
            if(AppConstant.IMAGE_EDIT_PATH.length()>5){
path=AppConstant.IMAGE_EDIT_PATH;
if(new File(path).exists()){
  ImageLoader.getInstance().displayImage("file://"+path,imageview);
}
            }
        }
        relativeLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                stickerView.setInEdit(false);
                stickerViewone.setInEdit(false);
                return false;
            }
        });
        picker_close=(ImageButton)findViewById(R.id.btnImgCloseColorLayout);
        picker_closeone=(ImageButton)findViewById(R.id.btnImgCloseColorLayoutone);
        picker_closeone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color_pickertwo.setVisibility(View.GONE);

            }
        });
        picker_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    color_picker.setVisibility(View.GONE);

            }
        });
    }
    public void initialview(){
        bottombar=(LinearLayout)findViewById(R.id.bottom_bar);
        tvstick = (LinearLayout)findViewById(R.id.tvStick);
        tvreset = (LinearLayout)findViewById(R.id.tvReset);
        tvbg = (LinearLayout)findViewById(R.id.tvBG);
        color_picker=(LinearLayout)findViewById(R.id.color_pickerone);
        color_pickertwo=(LinearLayout)findViewById(R.id.color_pickertwo);
        imageview =(ImageView)findViewById(R.id.imageview);
     //   imagemustache =(StickerView) findViewById(R.id.imagemustache);
        horizontalListView=(HorizontalListView)findViewById(R.id.hlvCustomList);
      //  stickerview =(StickerView) findViewById(R.id.sticker_view);
        tvRotate=(LinearLayout)findViewById(R.id.tvRotate);
        ivback=(ImageView)findViewById(R.id.ivBack);
        ivsave=(ImageView)findViewById(R.id.ivSave);
        typeface=Typeface.createFromAsset(getAssets(),"open-sans.semibold.ttf");
        relativeLayout= (FrameLayout) findViewById(R.id.middleview);
        titles=(TextView)findViewById(R.id.textView6);
        hairs=(TextView)findViewById(R.id.gridtext2);
        mustache=(TextView)findViewById(R.id.sticktext);
        rotate=(TextView)findViewById(R.id.rotatetext);
        reset=(TextView)findViewById(R.id.resettext);
        titles.setTypeface(typeface);
        hairs.setTypeface(typeface);
        mustache.setTypeface(typeface);
        rotate.setTypeface(typeface);
        reset.setTypeface(typeface);

        txtcolor=(TextView)findViewById(R.id.txtColor);
        textviewbright =(TextView)findViewById(R.id.textviewBrightness);
        txtcolor.setTypeface(typeface);
        textviewbright.setTypeface(typeface);
        picker_close=(ImageButton)findViewById(R.id.btnImgCloseColorLayout);
        picker_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    color_picker.setVisibility(View.GONE);


            }
        });

        txtcolorone=(TextView)findViewById(R.id.txtColorone);
        textviewbrightone =(TextView)findViewById(R.id.textviewBrightnessone);
        txtcolorone.setTypeface(typeface);
        textviewbrightone.setTypeface(typeface);
        picker_closeone=(ImageButton)findViewById(R.id.btnImgCloseColorLayoutone);
        picker_closeone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    color_pickertwo.setVisibility(View.GONE);

            }
        });


        resets=(Button)findViewById(R.id.btnReset);
        resets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                currentBitmap = originalImage;
                seekBar1.setProgress(128);
                seekBar2.setProgress(128);
                seekBar3.setProgress(128);
                seekBarRed.setProgress(seekBarRed.getMax() / 2);
                seekBarBlue.setProgress(seekBarBlue.getMax() / 2);
                   addStickerView(currentBitmap);


            }
        });
        resets.setTypeface(typeface);

        resetsone=(Button)findViewById(R.id.btnResetone);
        resetsone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                currentBitmapone = originalImageone;
                seekBar1one.setProgress(128);
                seekBar2one.setProgress(128);
                seekBar3one.setProgress(128);
                seekBarRedone.setProgress(seekBarRedone.getMax() / 2);
                seekBarBlueone.setProgress(seekBarBlueone.getMax() / 2);
                    addStickerViewone(currentBitmapone);

            }
        });
        resetsone.setTypeface(typeface);


        seekBarRed = (SeekBar) findViewById(R.id.seekBarRed);
        seekBarBlue = (SeekBar) findViewById(R.id.seekBarBlue);
        seekBar1 = (SeekBar) findViewById(R.id.seekBar1);
        seekBar2 = (SeekBar) findViewById(R.id.seekBar2);
        seekBar3 = (SeekBar) findViewById(R.id.seekBar3);
        if(seekBarRed!=null){
            seekBarRed.setOnSeekBarChangeListener(this);
        }
        if(seekBarBlue!=null){
            seekBarBlue.setOnSeekBarChangeListener(this);
        }
        if(seekBar1!=null){
            seekBar1.setOnSeekBarChangeListener(this);
        }if(seekBar2!=null){
            seekBar2.setOnSeekBarChangeListener(this);
        }if(seekBar3!=null){
            seekBar3.setOnSeekBarChangeListener(this);
        }

        seekBarRedone = (SeekBar) findViewById(R.id.seekBarRedone);
        seekBarBlueone = (SeekBar) findViewById(R.id.seekBarBlueone);
        seekBar1one = (SeekBar) findViewById(R.id.seekBar1one);
        seekBar2one = (SeekBar) findViewById(R.id.seekBar2one);
        seekBar3one = (SeekBar) findViewById(R.id.seekBar3one);
        if(seekBarRedone!=null){
            seekBarRedone.setOnSeekBarChangeListener(this);
        }
        if(seekBarBlueone!=null){
            seekBarBlueone.setOnSeekBarChangeListener(this);
        }
        if(seekBar1!=null){
            seekBar1one.setOnSeekBarChangeListener(this);
        }if(seekBar2one!=null){
            seekBar2one.setOnSeekBarChangeListener(this);
        }if(seekBar3one!=null){
            seekBar3one.setOnSeekBarChangeListener(this);
        }



        if(ivback!=null){
            ivback.setOnClickListener(this);
        }
        if(ivsave!=null){
            ivsave.setOnClickListener(this);
        }
        if(tvbg!=null){
            tvbg.setOnClickListener(this);
        }
        if(horizontalListView!=null){
            horizontalListView.setOnItemClickListener(this);
        }
if(tvreset!=null){
    tvreset.setOnClickListener(this);
}
        if(tvstick!=null){
            tvstick.setOnClickListener(this);
        }
        if(tvRotate!=null){
            tvRotate.setOnClickListener(this);
        }

    }
    private void resetFlag() {
     isHair = false;
        isMustache = false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvBG:
                setTextOk();
                resetFlag();
                isHair = true;
                loadBG_OR_STICKER_OR_Frame(Utils.ASSET_HAIR);
                bottombar.setVisibility(View.GONE);
                break;
            case R.id.tvStick:
                setTextOk();
                resetFlag();
                isMustache = true;
                loadBG_OR_STICKER_OR_Frame(Utils.ASSET_MUSTACHE);
                bottombar.setVisibility(View.GONE);
                break;
            case R.id.tvRotate:

                imageview.setRotation(imageview.getRotation() + 90);

                break;
            case R.id.tvReset:
                break;
            case R.id.ivBack:
                dia();
                break;
            case R.id.ivSave:
ivsave.setImageResource(R.drawable.menu_two);
                stickerView.setInEdit(false);
                stickerViewone.setInEdit(false);
                color_picker.setVisibility(View.GONE);
                color_pickertwo.setVisibility(View.GONE);
                horizontalListView.setVisibility(View.INVISIBLE);
                bottombar.setVisibility(View.VISIBLE);
if(!isHair && !isMustache){

resetFlag();
    new Storeimage().execute();
}
                resetFlag();
                break;
        }

    }
    private void loadBG_OR_STICKER_OR_Frame(String path) {
        horizontalListView.setVisibility(View.VISIBLE);
         if (isHair) {
            if (strArrayAssetHair == null) {
                strArrayAssetHair = loadImagesFromAssets(path);
            }

            horizontalListView.setAdapter(new HorizontalItemAdapter(
                    HairActivity.this, strArrayAssetHair));

        }else if(isMustache){
             if (strArrayMustache == null) {
                 strArrayMustache = loadImagesFromAssets(path);
             }

             horizontalListView.setAdapter(new HorizontalItemAdapter(
                     HairActivity.this, strArrayMustache));

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

    @Override
    public void onBackPressed() {
        if (isHair || isMustache ) {
            ivsave.setImageResource(R.drawable.menu_two);
            horizontalListView.setVisibility(View.INVISIBLE);
            resetFlag();
            stickerView.setInEdit(false);
            stickerViewone.setInEdit(false);
            color_picker.setVisibility(View.GONE);
            color_pickertwo.setVisibility(View.GONE);
            bottombar.setVisibility(View.VISIBLE);
        }else{
dia();

        }

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              if(isHair){

                  addStickerView(ImageLoader.getInstance().loadImageSync("assets://"+strArrayAssetHair[i]));

              } else if(isMustache){
                  addStickerViewone(ImageLoader.getInstance().loadImageSync("assets://"+strArrayMustache[i]));

              }
    }
    public void dia(){
        final Dialog dialog = new Dialog(HairActivity.this);
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
                dialog.dismiss();
                HairActivity.this.finish();

            }
        });
        dialog.show();
    }
    private void setTextOk() {
        ivsave.setImageResource(R.drawable.ok);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (seekBar == seekBarRed) {

            Bitmap b = null;
            float val = 0;
            if (seekBarRed.getProgress() != brightnessvalue) {

                val = seekBarRed.getProgress() - seekBarRed.getMax() / 2f;

                if (seekBarLastModify != null
                        && seekBarLastModify != seekBarRed) {
                    currentBitmap = tempImage;
                } else {
                    seekBarLastModify = seekBarRed;
                }
                b = ImageProcessing.brightness(currentBitmap, val);
                    addStickerView(b);
}
            tempImage = b;

        } else if (seekBar == seekBarBlue) {
            float value = seekBarBlue.getProgress() / 5f;

            if (seekBarLastModify != null && seekBarLastModify != seekBarBlue) {
                currentBitmap = tempImage;
            } else {
                seekBarLastModify = seekBarBlue;
            }
            Bitmap b = ImageProcessing.contrast(currentBitmap, value);
                addStickerView(b);

            tempImage = b;
        } else if (seekBar == seekBar1 || seekBar == seekBar2
                || seekBar == seekBar3) {

            if (seekBarLastModify != null && seekBarLastModify != seekBar1
                    && seekBarLastModify != seekBar2
                    && seekBarLastModify != seekBar3) {
                currentBitmap = tempImage;
            } else {
                seekBarLastModify = seekBar1;
            }
            Bitmap b = ImageProcessing.rgbChange(currentBitmap,
                    seekBar1.getProgress() / 128f,
                    seekBar2.getProgress() / 128f,
                    seekBar3.getProgress() / 128f);
                addStickerView(b);

            tempImage = b;
        }

        if (seekBar == seekBarRedone) {

            Bitmap b = null;
            float val = 0;
            if (seekBarRedone.getProgress() != brightnessvalueone) {

                val = seekBarRedone.getProgress() - seekBarRedone.getMax() / 2f;

                if (seekBarLastModifyone != null
                        && seekBarLastModifyone != seekBarRedone) {
                    currentBitmapone = tempImageone;
                } else {
                    seekBarLastModifyone = seekBarRedone;
                }
                b = ImageProcessing.brightness(currentBitmapone, val);
                    addStickerViewone(b);

            }
            tempImageone = b;

        } else if (seekBar == seekBarBlueone) {
            float value = seekBarBlueone.getProgress() / 5f;

            if (seekBarLastModifyone != null && seekBarLastModifyone != seekBarBlueone) {
                currentBitmapone = tempImageone;
            } else {
                seekBarLastModifyone = seekBarBlueone;
            }
            Bitmap b = ImageProcessing.contrast(currentBitmapone, value);
                addStickerViewone(b);

            tempImageone = b;
        } else if (seekBar == seekBar1one || seekBar == seekBar2one
                || seekBar == seekBar3one) {

            if (seekBarLastModifyone != null && seekBarLastModifyone != seekBar1one
                    && seekBarLastModifyone != seekBar2one
                    && seekBarLastModifyone != seekBar3one) {
                currentBitmapone = tempImageone;
            } else {
                seekBarLastModifyone = seekBar1one;
            }
            Bitmap b = ImageProcessing.rgbChange(currentBitmapone,
                    seekBar1one.getProgress() / 128f,
                    seekBar2one.getProgress() / 128f,
                    seekBar3one.getProgress() / 128f);
                addStickerViewone(b);

            tempImageone = b;
        }

    }

    class Storeimage extends AsyncTask<Void,Void,Void>{
    String fileSavedPath;
    ProgressDialog pDialog;
String fname;
    @Override
    protected void onPostExecute(Void aVoid) {
        pDialog.dismiss();
        final Dialog dialog = new Dialog(HairActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.save_share_add_picture);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                // Prevent dialog close on back press button
                return keyCode == KeyEvent.KEYCODE_BACK;
            }});
        TextView tvSave = (TextView) dialog.findViewById(R.id.tvSave);

        tvSave.setTypeface(typeface);


        tvSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent sl=new Intent(HairActivity.this,EfffectsActivity.class);
                sl.putExtra(Utils.path,
                        fileSavedPath);
                sl.putExtra(Utils.name,fname);
                startActivity(sl);
                dialog.dismiss();

            }
        });
        dialog.show();


        super.onPostExecute(aVoid);
    }

    @Override
    protected void onPreExecute() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            pDialog = new ProgressDialog(
                    new ContextThemeWrapper(HairActivity.this,
                            android.R.style.Theme_Holo_Light_Dialog));
        } else {
            pDialog = new ProgressDialog(HairActivity.this);
        }
        pDialog.setTitle("Next");
        pDialog.setMessage("Please wait");
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

        fname = "Img_" + currentTimeStamp + "" + ".png";
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
            MediaScannerConnection.scanFile(HairActivity.this,
                    new String[] { fileSavedPath },
                    new String[] { "image/png" }, null);
        } catch (Exception e) {
            e.printStackTrace();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(HairActivity.this,
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

    private void addStickerView(final Bitmap bp) {

        stickerView.setI(bp);


        stickerView.setOperationListener(new StickerView.OperationListener() {
            @Override
            public void onDeleteClick() {
                mViews.remove(stickerView);
                relativeLayout.removeView(stickerView);

                color_picker.setVisibility(View.GONE);
            }

            @Override
            public void onEdit(StickerView stickerView) {
                /*if (mCurrentEditTextView != null) {
                    mCurrentEditTextView.setInEdit(false);
                }*/
                mCurrentView.setInEdit(false);
                mCurrentView = stickerView;
                mCurrentView.setInEdit(true);
            }

            @Override
            public void onTop(StickerView stickerView) {
         /*       int position = mViews.indexOf(stickerView);
                if (position == mViews.size() - 1) {
                    return;
                }
                StickerView stickerTemp = (StickerView) mViews.remove(position);
                mViews.add(mViews.size(), stickerTemp);
           */
    currentBitmap=bp;
    color_picker.setVisibility(View.VISIBLE);
                color_pickertwo.setVisibility(View.GONE);


            }
        });

       FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
relativeLayout.removeView(stickerView);
        relativeLayout.addView(stickerView,lp);
       /* if(mViews.size()==1){
mViews.remove(0);
            mViews.add(0,stickerView);

        }else{
            mViews.add(0,stickerView);

        } */
        setCurrentEdit(stickerView);
    }

    private void setCurrentEdit(StickerView stickerView) {
        if (mCurrentView != null) {
            mCurrentView.setInEdit(false);
        }
     /*   if (mCurrentEditTextView != null) {
            mCurrentEditTextView.setInEdit(false);
        } */
        mCurrentView = stickerView;
        stickerView.setInEdit(true);
    }
    private void addStickerViewone(final Bitmap bp) {

        stickerViewone.setI(bp);


        stickerViewone.setOperationListener(new StickerView.OperationListener() {
            @Override
            public void onDeleteClick() {
                relativeLayout.removeView(stickerViewone);
                color_pickertwo.setVisibility(View.GONE);
            }

            @Override
            public void onEdit(StickerView stickerView) {
                /*if (mCurrentEditTextView != null) {
                    mCurrentEditTextView.setInEdit(false);
                }*/
                mCurrentView.setInEdit(false);
                mCurrentView = stickerViewone;
                mCurrentView.setInEdit(true);
            }

            @Override
            public void onTop(StickerView stickerView) {
             /*   int position = mViews.indexOf(stickerView);
                if (position == mViews.size() - 1) {
                    return;
                }
                StickerView stickerTemp = (StickerView) mViews.remove(position);
                mViews.add(mViews.size(), stickerTemp);
            */
                currentBitmapone=bp;
                color_picker.setVisibility(View.GONE);
                color_pickertwo.setVisibility(View.VISIBLE);
            }
        });
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        relativeLayout.removeView(stickerViewone);
        relativeLayout.addView(stickerViewone, lp);
        setCurrentEditone(stickerViewone);
    }

    private void setCurrentEditone(StickerView stickerView) {
        if (mCurrentView != null) {
            mCurrentView.setInEdit(false);
        }
     /*   if (mCurrentEditTextView != null) {
            mCurrentEditTextView.setInEdit(false);
        } */
        mCurrentView = stickerView;
        stickerView.setInEdit(true);
    }


    public static HairActivity getInstance(){
        return hairActivity;
    }
}
