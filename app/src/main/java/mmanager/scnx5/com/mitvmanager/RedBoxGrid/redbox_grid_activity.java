package mmanager.scnx5.com.mitvmanager.RedBoxGrid;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import mmanager.scnx5.com.abcyxoorp;
import mmanager.scnx5.com.abdyxoorp;
import mmanager.scnx5.com.mitvmanager.R;
import mmanager.scnx5.com.mitvmanager.VODGrid.Book;
import mmanager.scnx5.com.mitvmanager.getURL;
import mmanager.scnx5.com.mitvmanager.log_;
import mmanager.scnx5.com.mitvmanager.old_getURL;
import mmanager.scnx5.com.vd452ax3;

public class redbox_grid_activity extends AppCompatActivity {
    public List<LiveBook> lstBook;
    public List<LiveBook> VOD,AllChannel;
    public List<String> VODJsonId;
    public List<String> VODJsonName;
    public List<String> VODJsonLogoPath;
    public List<String> VODJsonUrl;
    public List<String> VODJsonCategory;
    public List<String> VODJsonsypnopsis;
    public List<String> VODJsonpremium;
    public String tk="",server;
    public String json;
    public RecyclerViewAdapterRB myAdapterRB,myAdapterRBAllChannel;
    public RecyclerView myrvRB;
    public ListView listView;
    public ProgressDialog pdLoading;
   // public String[] VODCat;
    public ArrayList<String> VODCat;
    public LinkedHashSet<String> SETVODJsonCategory;
    private vd452ax3 b=new vd452ax3();
    private abcyxoorp a=new abcyxoorp();
    private log_ dlog=new log_();
    private Boolean debug=false;
    private getURL wget=new getURL();
    private old_getURL old_wget=new old_getURL();
    Double width;
    private Boolean newGetUrl;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE); // for hiding title

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
      //  java.lang.System.setProperty("java.net.preferIPv4Stack", "true");
      //  java.lang.System.setProperty("java.net.preferIPv6Addresses", "false");
         // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        //  ActionBar actionBar = getActionBar();
        // actionBar.hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redbox_grid_activity);

        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
            tk = extras.getString("tk");
            server = extras.getString("server");

            // Toast.makeText(getApplicationContext(),json,Toast.LENGTH_LONG).show();
        }


      //  Log.d("tk","token" + tk);


        //set correct width based on resolution
        Double ratio;
        width= redbox_grid_activity.getScreenWidth();
        ratio = width * 0.28;
        //Toast.makeText(this,swidth,Toast.LENGTH_LONG).show();

        int correctWidth;

        correctWidth = Integer.valueOf(ratio.intValue());
        LinearLayout categoryLI =(LinearLayout)findViewById(R.id.categoryvod);
        categoryLI.getLayoutParams().width=correctWidth;

        ratio = width * 0.7;
      //  ratio = width * 0.28;
        correctWidth = Integer.valueOf(ratio.intValue());

        LinearLayout vodlistingLI =(LinearLayout)findViewById(R.id.vodlisting);
        vodlistingLI.getLayoutParams().width=correctWidth;

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        System.setProperty("java.net.preferIPv4Addresses", "true");
        System.setProperty("java.net.preferIPv6Addresses", "false");
        System.setProperty("validated.ipv6", "false");




        new CountDownTimer(10, 10) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {

            new redbox_grid_activity.LoadVODInBookList().execute("");

            }

        }.start();

        listView = (ListView) findViewById(R.id.redboxvodcategorlist);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

              new redbox_grid_activity.FilterContent().execute(i);
            }
        });

        // Toast.makeText(getApplicationContext(),"Height screen : " + widths,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
      //  Log.d("tghnakstart","Starting");
    }

    @Override
    protected void onResume() {
        super.onResume();
/*
        checkFileExist cf=new checkFileExist();
        if(cf.checkFileExist("activityStateRedBoxGrid.txt",this)){

            ReadFromFile rff=new ReadFromFile();
            String filecontent=rff.ReadFromFile("activityStateRedBoxGrid.txt",this);
            filecontent=filecontent.trim();
            Log.d("Onresume",filecontent);
            if(filecontent.equalsIgnoreCase("started")){

                WriteToFile wtf=new WriteToFile();
                wtf.WriteToFile("activityStateRedBoxGrid.txt","stop",this);
                Intent i = new Intent(getBaseContext(), logout_main.class);
                startActivity(i);
                Log.d("Onresume","file dok ada");
            }




        } else {
            Log.d("Onresume","file x dak");

        }
*/
     //   Log.d("Onresume","asdasd");

    }





    public static double getScreenWidth() {

        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }


    private class FilterContent extends AsyncTask<Integer, Integer, String>
    {
        ProgressDialog pdLoading = new ProgressDialog(redbox_grid_activity.this);
        ArrayList NewVOD;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();

        }
        @Override
        protected String doInBackground(Integer... pos) {



            NewVOD=new ArrayList<>();
            AllChannel=new ArrayList<>();
            String selectedCategory=listView.getItemAtPosition(pos[0]).toString();

            for(int j=0;j<VODJsonCategory.size();j++){

                AllChannel.add(new LiveBook(VODJsonName.get(j).toString(),VODJsonCategory.get(j).toString(),VODJsonUrl.get(j).toString(),VODJsonLogoPath.get(j).toString(),VODJsonsypnopsis.get(j).toString(),VODJsonId.get(j).toString(),VODJsonpremium.get(j).toString()));


                if (selectedCategory.equalsIgnoreCase(VODJsonCategory.get(j).toString())){
                    NewVOD.add(new LiveBook(VODJsonName.get(j).toString(),VODJsonCategory.get(j).toString(),VODJsonUrl.get(j).toString(),VODJsonLogoPath.get(j).toString(),VODJsonsypnopsis.get(j).toString(),VODJsonId.get(j).toString(),VODJsonpremium.get(j).toString()));
                }


            }



            return "";
        }

        @Override
        protected void onPostExecute(String result) {

            //this method will be running on UI thread
            pdLoading.dismiss();


            myAdapterRBAllChannel=new RecyclerViewAdapterRB(redbox_grid_activity.this,AllChannel,tk,server,json);
            myAdapterRB = new RecyclerViewAdapterRB(redbox_grid_activity.this,NewVOD,tk,server,json);
            myrvRB.setAdapter(myAdapterRB);


        }

    }
    @Override
    public void onPause(){
        super.onPause();
     //   WriteToFile wtf=new WriteToFile();
      //  wtf.WriteToFile("activityStateRedBoxGrid.txt","started",this);



        if(pdLoading!=null)
            pdLoading.dismiss();

    }

    private class LoadVODInBookList extends AsyncTask<String,String,String>
    {
        ProgressDialog pdLoading = new ProgressDialog(redbox_grid_activity.this);
        Boolean expiry=false;
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

            String id, name, logoPath, url, category, sypnopsis, firstCat, premium;


            //
            newGetUrl=true;
            if (newGetUrl) {


            try {
                json = wget.getURL(server + b.rvd452ax3() + ".php?tk=" + tk);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else

        {
              json= old_wget.getURL(server+b.rvd452ax3()+".php?tk="+tk);
        }


        dlog.log_d(debug,"json",json);
          //  String json="expired";

            if (json.equalsIgnoreCase("expired")) {
                expiry=true;

            } else {
                try {
                    JSONObject objectPremium = new JSONObject(String.valueOf(json));
                    JSONArray VodData = (JSONArray) objectPremium.getJSONArray("data");

                    SETVODJsonCategory = new LinkedHashSet<String>();
                    VODJsonId = new ArrayList<String>();
                    VODJsonName = new ArrayList<String>();
                    VODJsonLogoPath = new ArrayList<String>();
                    VODJsonUrl = new ArrayList<String>();
                    VODJsonCategory = new ArrayList<String>();
                    VODJsonsypnopsis = new ArrayList<String>();
                    VODJsonpremium = new ArrayList<String>();
                    //Toast.makeText(getApplicationContext(),VODname,Toast.LENGTH_LONG).show();
                    String VODId,VODname, VODlogo, VODurl, VODcategory, VODsypnopsis,VODPremium;

                    for (int i = 0; i < VodData.length(); i++) {
                        VODId = VodData.getJSONObject(i).getString("id");
                        VODname = VodData.getJSONObject(i).getString("name");
                        VODlogo = VodData.getJSONObject(i).getString("logoPath");
                        VODurl = VodData.getJSONObject(i).getString("playUrl");
                        VODcategory = VodData.getJSONObject(i).getString("genre");
                        VODsypnopsis = VodData.getJSONObject(i).getString("sypnopsis");
                        VODPremium = VodData.getJSONObject(i).getString("premium");

                        VODJsonId.add(VODId);
                        VODJsonName.add(VODname);
                        VODJsonLogoPath.add(VODlogo);
                        VODJsonUrl.add(VODurl);
                        VODJsonCategory.add(VODcategory);
                        VODJsonsypnopsis.add(VODsypnopsis);
                        VODJsonpremium.add(VODPremium);

                        SETVODJsonCategory.add(VODcategory);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


                // Toast.makeText(getApplicationContext(),objectPremium.getBoolean("state"),Toast.LENGTH_LONG).show();

                VODCat = new ArrayList<String>();

                for (String s : SETVODJsonCategory) {
               //     Log.d("SETVODJsonCategory", s);

                    VODCat.add(s);


                }


                firstCat = "";
                for (int i = 0; i < VODCat.size(); i++) {
                    firstCat = VODCat.get(i).toString();
                    break;
                }


                VOD = new ArrayList<>();
                for (int i = 0; i < VODJsonName.size(); i++) {
                    id=VODJsonId.get(i).toString();
                    name = VODJsonName.get(i).toString();
                    category = VODJsonCategory.get(i).toString();
                    logoPath = VODJsonLogoPath.get(i).toString();
                    url = VODJsonUrl.get(i).toString();
                    sypnopsis = VODJsonsypnopsis.get(i).toString();
                    premium =VODJsonpremium.get(i).toString();
                    // try {
                    // bitmap = BitmapFactory.decodeStream((InputStream)new URL(logoPath).getContent());
                    //} catch (IOException e) {
                    //      e.printStackTrace();
                    //  }
                    if (firstCat.equalsIgnoreCase(category)) {
                        VOD.add(new LiveBook(name, category, url, logoPath, sypnopsis,id,premium));
                    }
                }


                myrvRB = (RecyclerView) findViewById(R.id.recyclerview_id);
                myAdapterRB = new RecyclerViewAdapterRB(redbox_grid_activity.this, VOD,tk,server,json);

            }

            return "";

        }

        @Override
        protected void onPostExecute(String result) {

            if(redbox_grid_activity.this.isDestroyed()){
                finish();
            }

            //this method will be running on UI thread
            pdLoading.dismiss();

            if (expiry){
                finish();
            }

            ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.listview,VODCat);
            listView.setAdapter(adapter);


            CardView frameCardView=(CardView)findViewById(R.id.cardidgrid);
            ImageView imageGrid=(ImageView)findViewById(R.id.book_img_id);
            TextView bookTitle=(TextView)findViewById(R.id.book_title_id);

            myrvRB.setLayoutManager(new GridLayoutManager(getApplicationContext(),4));
            myrvRB.setAdapter(myAdapterRB);

            SearchView SearchLiveTv=(SearchView)findViewById(R.id.SearchChannel);

            SearchLiveTv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                ArrayList NewVOD=new ArrayList<>();
                @Override
                public boolean onQueryTextSubmit(String s) {
                //    Log.d("QueryTextSubmit",s);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
               //     Log.d("QueryTextChanged", s);

                    String inputUser = s.toLowerCase();
                    ArrayList FilterChannel = new ArrayList<>();
                    int j = 0;
                    for (String channel : VODJsonName) {

                        if (channel.toLowerCase().contains(inputUser)) {

                            FilterChannel.add(new Book(VODJsonName.get(j).toString(), VODJsonCategory.get(j).toString(), VODJsonUrl.get(j).toString(), VODJsonLogoPath.get(j).toString(), VODJsonsypnopsis.get(j).toString(),VODJsonId.get(j).toString()));
                        }
                        j++;
                    }

                    myAdapterRB = new RecyclerViewAdapterRB(redbox_grid_activity.this,FilterChannel,tk,server,json);
                    myrvRB.setAdapter(myAdapterRB);
                    return true;
                }
            });

        }

    }
}
