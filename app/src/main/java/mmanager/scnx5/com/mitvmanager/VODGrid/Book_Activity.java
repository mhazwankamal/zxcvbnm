package mmanager.scnx5.com.mitvmanager.VODGrid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


import mmanager.scnx5.com.mitvmanager.Exoplayer.exoplayer_layar_vod_new_code;
import mmanager.scnx5.com.mitvmanager.R;
import mmanager.scnx5.com.mitvmanager.getURL;
import mmanager.scnx5.com.mitvmanager.setViewSizeByReso;

import static org.jsoup.nodes.Document.OutputSettings.Syntax.html;

public class Book_Activity extends AppCompatActivity {


    private TextView tvtitle,tvdescription,tvcategory;
    private ImageView img;
    private FrameLayout bookbackground;
    private String movieId,Url,category,sypnopsis, Title,tk,backdrop,server;
    private setViewSizeByReso setView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE); // for hiding title

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //   setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

     //   View decorView = getWindow().getDecorView();
    //    int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
    //    decorView.setSystemUiVisibility(uiOptions);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.vod_preview_page);

        setView=new setViewSizeByReso();

        tvtitle = (TextView) findViewById(R.id.txttitle);
        tvdescription = (TextView) findViewById(R.id.txtDesc);
        tvcategory = (TextView) findViewById(R.id.txtCat);
     //   img = (ImageView) findViewById(R.id.bookthumbnail);
        bookbackground=(FrameLayout)findViewById(R.id.backgroundMovie);

        // Recieve data
        Intent intent = getIntent();
        movieId=intent.getExtras().getString("movieId");
         Title = intent.getExtras().getString("Title");
         Url = intent.getExtras().getString("Url");
         sypnopsis=intent.getExtras().getString("Sypnopsis");
        category=intent.getExtras().getString("Category");
        tk=intent.getExtras().getString("tk");
        backdrop=intent.getExtras().getString("backdrop");
        // Bitmap image =(Bitmap) intent.getParcelableExtra("Thumbnail") ;
        String Picurl=intent.getExtras().getString("Thumbnail");
        server=intent.getExtras().getString("server");
        final String liveTV=intent.getExtras().getString("liveTV");
        // Setting values



        sypnopsis= String.valueOf(Html.fromHtml(sypnopsis));
        if(sypnopsis.length() > 250){

            sypnopsis=sypnopsis.substring(0,250);

            tvdescription.setText(sypnopsis + " ...."); //url
        }else {
            tvdescription.setText(sypnopsis); //url
        }//   Glide.with(this).load(Picurl).into(img);
        Glide.with(this).load(backdrop).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    bookbackground.setBackground(resource);
                }
            }
        });

        // img.setImageBitmap(image);
        tvtitle.setText(Title);
        tvcategory.setText(category);
        tvtitle.setTextSize(TypedValue.COMPLEX_UNIT_PX,setView.getPixelByReso(0.06));
        tvcategory.setTextSize(TypedValue.COMPLEX_UNIT_PX,setView.getPixelByReso(0.03));
        tvdescription.setTextSize(TypedValue.COMPLEX_UNIT_PX,setView.getPixelByReso(0.035));


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
        final ImageButton play=(ImageButton)findViewById(R.id.playvodbttn);


        if(liveTV.equalsIgnoreCase("rb")){

            new Book_Activity.startRedBoxChannel().execute("");
        }




        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(liveTV.equalsIgnoreCase("vod")) {
               /*     String getLiveYT=sypnopsis.substring(0,6);

                  //Toast.makeText(Book_Activity.this,getLiveYT,Toast.LENGTH_SHORT).show();
                 Intent intent = new Intent(Intent.ACTION_VIEW);

                 if(getLiveYT.equalsIgnoreCase("LiveYT"))
                     intent.setPackage("org.videolan.vlc");
             else {
                     intent.setPackage("com.mxtech.videoplayer.ad");

                 }

                 intent.setDataAndType(Uri.parse(Url), "video/*");
                    Toast.makeText(Book_Activity.this,"Movie is starting...",Toast.LENGTH_LONG).show();





                    startActivity(intent);
                    //startActivity(Intent.createChooser(intent, "Complete action using"));
*/

                    Intent intent = new Intent(Book_Activity.this, exoplayer_layar_vod_new_code.class);

                    // passing data to the book activity

                    intent.putExtra("movieId",movieId);
                    intent.putExtra("Url", Url);
                    intent.putExtra("Channel", Title);
                    intent.putExtra("tk",tk);
                    intent.putExtra("category",category);
                    intent.putExtra("server",server);
                    intent.putExtra("thumbnail",Picurl);

                    //  Toast.makeText(mContext,"test",Toast.LENGTH_LONG).show();
                    // start the activity

                     startActivity(intent);


                }

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
           /*
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



            } else
            */
           if (sypnopsis.equalsIgnoreCase("REDBOX")) {
               try {
                   json = wget.getURL("http://163.172.181.152:8030/rbtv/token21.php");
               } catch (IOException e) {
                   e.printStackTrace();
               }
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
