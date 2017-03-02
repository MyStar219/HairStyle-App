package com.applapse.hairstylesalon;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.applapse.hairstylesalon.horizontal_listview.HorizontalItemAdapterone;
import com.meetme.android.horizontallistview.HorizontalListView;

import java.io.File;

/**
 * Created by Administrator on 03/09/2016.
 */
public class exitone extends Activity implements AdapterView.OnItemClickListener {
    public static int [] cover = {R.drawable.pc,R.drawable.wop,R.drawable.br};
    private String[] strHl;
    private HorizontalListView horizontalListView;
    private String[] title={"Text Over Pic","Collage","Pic Editor","Photo Replace","PIP"};
    public static String[] appname={"Photo Collage","wop","br"};

    private static final String MARKET_CONSTANT = "market://details?id=";
    private static final String GOOGLE_PLAY_CONSTANT = "http://play.google.com/store/apps/details?id=";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exitone);
horizontalListView=(HorizontalListView)findViewById(R.id.hlvCustomList);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ListView listview=(ListView)findViewById(R.id.listview);

        Adapter adapter=new Adapter(this,appname);
        listview.setAdapter(adapter);
        strHl=loadImagesFromAssets(Utils.ASSET_CROSS);
        horizontalListView.setAdapter(new HorizontalItemAdapterone(
                exitone.this, strHl,title,1));
        horizontalListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
switch (i){
    case 0:
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(MARKET_CONSTANT + "com.writeonpictures.photo_caption_maker")));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(GOOGLE_PLAY_CONSTANT + "com.writeonpictures.photo_caption_maker")));
        }
        break;
    case 1:

        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(MARKET_CONSTANT + "com.photocollage.photo_collage_maker_editor")));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(GOOGLE_PLAY_CONSTANT + "com.photocollage.photo_collage_maker_editor")));
        }
        break;
    case 2:
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(MARKET_CONSTANT + "com.photoeditor.photo_editor.pixel")));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(GOOGLE_PLAY_CONSTANT + "com.photoeditor.photo_editor.pixel")));
        }
        break;
    case 3:

        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(MARKET_CONSTANT + "com.backgrounderaser.replace_photo_background")));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(GOOGLE_PLAY_CONSTANT + "com.backgrounderaser.replace_photo_background")));
        }


        break;
    case 4:
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(MARKET_CONSTANT + "com.pip_in_pic.pipcamera")));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(GOOGLE_PLAY_CONSTANT + "com.pip_in_pic.pipcamera")));
        }
        break;
}
    }

    class ViewHolder{

        ImageView one;
    }
    class Adapter extends BaseAdapter {
        Context context;

        String[] appname;
        public Adapter() {

        }
        public Adapter(Context context, String[] c){
            this.context=context;

        appname=c;

        }




        @Override
        public int getCount() {
            return appname.length;
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            ViewHolder holder=new ViewHolder();
            View v= LayoutInflater.from(context).inflate(R.layout.exittwo,null);

            holder.one=(ImageView)v.findViewById(R.id.appcover);

            holder.one.setImageResource(cover[i]);
            holder.one.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch(i){
                        case 0:
                            try {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(MARKET_CONSTANT + "com.pip_in_pic.pipcamera")));
                            } catch (android.content.ActivityNotFoundException anfe) {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(GOOGLE_PLAY_CONSTANT + "com.pip_in_pic.pipcamera")));
                            }
                            break;
                        case 1:
                            try {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(MARKET_CONSTANT + "com.tattoo_photo_editor.stickertattoo")));
                            } catch (android.content.ActivityNotFoundException anfe) {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(GOOGLE_PLAY_CONSTANT + "com.tattoo_photo_editor.stickertattoo")));
                            }


                            break;
                        case 2:

                            try {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(MARKET_CONSTANT + "com.hairstyles_for_women.trendyhairstyles")));
                            } catch (android.content.ActivityNotFoundException anfe) {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(GOOGLE_PLAY_CONSTANT + "com.hairstyles_for_women.trendyhairstyles")));
                            }

                            break;
                    }
                }
            });

            return v;
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
}
