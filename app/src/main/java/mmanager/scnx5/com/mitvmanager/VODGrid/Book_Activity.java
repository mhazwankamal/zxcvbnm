package mmanager.scnx5.com.mitvmanager.VODGrid;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;

import mmanager.scnx5.com.mitvmanager.Exoplayer.exoplayer_layar;
import mmanager.scnx5.com.mitvmanager.R;
import mmanager.scnx5.com.mitvmanager.getURL;
import mmanager.scnx5.com.mitvmanager.livetv_menu;
import mmanager.scnx5.com.mitvmanager.vod_grid_activity;

public class Book_Activity extends AppCompatActivity {


    private TextView tvtitle,tvdescription,tvcategory;
    private ImageView img;
    private LinearLayout bookbackground;
    private String Url,category,sypnopsis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE); // for hiding title

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //   setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

     //   View decorView = getWindow().getDecorView();
    //    int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
    //    decorView.setSystemUiVisibility(uiOptions);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_);

        tvtitle = (TextView) findViewById(R.id.txttitle);
        tvdescription = (TextView) findViewById(R.id.txtDesc);
        tvcategory = (TextView) findViewById(R.id.txtCat);
        img = (ImageView) findViewById(R.id.bookthumbnail);

        // Recieve data
        Intent intent = getIntent();
        String Title = intent.getExtras().getString("Title");
         Url = intent.getExtras().getString("Url");
         sypnopsis=intent.getExtras().getString("Sypnopsis");
        category=intent.getExtras().getString("Category");
       // Bitmap image =(Bitmap) intent.getParcelableExtra("Thumbnail") ;
        String Picurl=intent.getExtras().getString("Thumbnail");
        final String liveTV=intent.getExtras().getString("liveTV");
        // Setting values

        tvtitle.setText(Title);
        tvdescription.setText(sypnopsis); //url
        Picasso.with(this).load(Picurl).into(img);
       // img.setImageBitmap(image);
        tvcategory.setText(category);

/*
        final ImageView img = new ImageView(this);
        Picasso.with(img.getContext())
                .load(Picurl)
                .into(img, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        bookbackground.setBackground(img.getDrawable());
                    }

                    @Override
                    public void onError() {
                    }
                });

      //  img.setImageResource(image);
*/
        final Button play=(Button)findViewById(R.id.playvodbttn);
        Button back=(Button)findViewById(R.id.backbttn);
        if(liveTV.equalsIgnoreCase("vod")) {
            play.setText("Play Movie");
        }

        if(liveTV.equalsIgnoreCase("rb")){

            new Book_Activity.startRedBoxChannel().execute("");
        }




        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(liveTV.equalsIgnoreCase("vod")) {
                  Intent intent = new Intent(Intent.ACTION_VIEW);
                  intent.setPackage("com.mxtech.videoplayer.ad");
                  intent.setDataAndType(Uri.parse(Url), "video/*");
                   Toast.makeText(Book_Activity.this,"Movie is starting...",Toast.LENGTH_LONG).show();



               startActivity(intent);
                    //startActivity(Intent.createChooser(intent, "Complete action using"));

                }

            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });


    }



    private class startRedBoxChannel extends AsyncTask<String,String,String>
    {
        ProgressDialog pdLoading = new ProgressDialog(Book_Activity.this);
        String json;
        String playUrlToken;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();

        }
        @Override
        protected String doInBackground(String... params) {


            getURL wget=new getURL();
            if (sypnopsis.equalsIgnoreCase("M4K")){
                String myiptv4kurl ="https://layar3.com/apps/livetv/getmypadtvusertoken1.php";
                String UserToken = wget.getURL(myiptv4kurl);
               // String playlisttokenurl ="https://mypadtv.com/api/v1/lives?token=" + UserToken;
                String playlisttokenurl ="https://www.mypadtvcom.com/api/v1/lives?token=" + UserToken;

                String playlisttoken = wget.getURL(playlisttokenurl);

                JSONObject objectPremium = null;
                try {
                    objectPremium = new JSONObject(String.valueOf(playlisttoken));
                    JSONArray VodData = (JSONArray) objectPremium.getJSONArray("data");
                   for(int i=0;i<VodData.length();i++) {
                       JSONObject VODLive = VodData.getJSONObject(i).getJSONObject("live");
                       String playUrl = VODLive.getString("url");
                       String containsURL = Url.substring(8);
                       if (playUrl.contains(containsURL)) {

                           String playUrlToken = playUrl.substring(playUrl.indexOf("?"));
                                json=playUrlToken;
                          // Log.d("ContainsURL", playUrlToken);
                         //  Log.d("playUrl",playUrl);
                           break;
                       }
                   }

                    // Toast.makeText(Book_Activity.this,playUrl,Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }



            } else if (sypnopsis.equalsIgnoreCase("REDBOX")) {
                json = wget.getURL("http://163.172.181.152:8030/rbtv/token21.php");
            }else{
                json="";
            }
            return "";

        }

        @Override
        protected void onPostExecute(String result) {

            //this method will be running on UI thread
            pdLoading.dismiss();

           Intent intent = new Intent(Intent.ACTION_VIEW);
         //  Intent i=new Intent(Book_Activity.this,exoplayer_layar.class );
          //  i.putExtra("playurl",Url+json);
           //startActivity(i);
            // intent.setPackage("com.mxtech.videoplayer.ad");
          //  intent.setDataAndType(Uri.parse(Url+json), "video/*");

           // startActivity(intent);
            Toast.makeText(Book_Activity.this,"Channel is starting...",Toast.LENGTH_LONG).show();
            //Toast.makeText(Book_Activity.this,category ,Toast.LENGTH_LONG).show();

        }

    }




}
