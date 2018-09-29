package mmanager.scnx5.com.mitvmanager;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import mmanager.scnx5.com.mitvmanager.RedBoxGrid.RecyclerViewAdapterRB;
import mmanager.scnx5.com.mitvmanager.VODGrid.Book;
import mmanager.scnx5.com.mitvmanager.VODGrid.RecyclerViewAdapter;

public class redbox_grid_activity extends AppCompatActivity {
    public List<Book> lstBook;
    public List<Book> VOD,AllChannel;
    public List<String> VODJsonName;
    public List<String> VODJsonLogoPath;
    public List<String> VODJsonUrl;
    public List<String> VODJsonCategory;
    public List<String> VODJsonsypnopsis;
    public String personID;
    public String json;
    public RecyclerViewAdapterRB myAdapterRB,myAdapterRBAllChannel;
    public RecyclerView myrvRB;
    public ListView listView;
    public ProgressDialog pdLoading;
   // public String[] VODCat;
    public ArrayList<String> VODCat;
    public LinkedHashSet<String> SETVODJsonCategory;
    Double width;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE); // for hiding title

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

         // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        //  ActionBar actionBar = getActionBar();
        // actionBar.hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redbox_grid_activity);

     /*   Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
            personID = extras.getString("user");

            // Toast.makeText(getApplicationContext(),json,Toast.LENGTH_LONG).show();
        }
*/




        //set correct width based on resolution
        Double ratio;
        width= redbox_grid_activity.getScreenWidth();
        ratio = width * 0.28;

        int correctWidth;

        correctWidth = Integer.valueOf(ratio.intValue());
        LinearLayout categoryLI =(LinearLayout)findViewById(R.id.categoryvod);
        categoryLI.getLayoutParams().width=correctWidth;

        ratio = width * 0.7;

        correctWidth = Integer.valueOf(ratio.intValue());

        LinearLayout vodlistingLI =(LinearLayout)findViewById(R.id.vodlisting);
        vodlistingLI.getLayoutParams().width=correctWidth;

        /*
        getURL wget=new getURL();
        String json= wget.getURL("http://scnx5.sytes.net/mitv/apps/vod/getvodlist.php");

        try {
            JSONObject objectPremium = new JSONObject(String.valueOf(json));
            JSONArray VodData = (JSONArray) objectPremium.getJSONArray("data");

            SETVODJsonCategory = new HashSet<String>();
            VODJsonName = new ArrayList<String>();
            VODJsonLogoPath = new ArrayList<String>();
            VODJsonUrl = new ArrayList<String>();
            VODJsonCategory = new ArrayList<String>();
            VODJsonsypnopsis = new ArrayList<String>();
            //Toast.makeText(getApplicationContext(),VODname,Toast.LENGTH_LONG).show();
            String VODname,VODlogo,VODurl,VODcategory,VODsypnopsis;

            for (int i = 0; i < VodData.length(); i++) {
                VODname = VodData.getJSONObject(i).getString("name");
                VODlogo = VodData.getJSONObject(i).getString("logoPath");
                VODurl = VodData.getJSONObject(i).getString("playUrl");
                VODcategory = VodData.getJSONObject(i).getString("genre");
                VODsypnopsis = VodData.getJSONObject(i).getString("sypnopsis");


                VODJsonName.add(VODname);
                VODJsonLogoPath.add(VODlogo);
                VODJsonUrl.add(VODurl);
                VODJsonCategory.add(VODcategory);
                VODJsonsypnopsis.add(VODsypnopsis);

                SETVODJsonCategory.add(VODcategory);
            }


           // list = new ArrayList<String>();


            for (String s : VODJsonCategory) {
                Log.d("VODJsonCategory",s);

            }

            for (String s : VODJsonName) {
                Log.d("VODJsonName",s);

            }
            for (String s : VODJsonLogoPath) {
                Log.d("VODJsonLogoPath",s);

            }
            for (String s : VODJsonUrl) {
                Log.d("VODJsonUrl",s);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


            // Toast.makeText(getApplicationContext(),objectPremium.getBoolean("state"),Toast.LENGTH_LONG).show();

        VODCat=new ArrayList<String>();

        for (String s : SETVODJsonCategory) {
            Log.d("SETVODJsonCategory",s);

            VODCat.add(s);


        }

      //  String arraycontent;

        //arraycontent=getURL.getURL("http://scnx5.sytes.net/mitv/apps/vod/getvodlist.php");

      //  VODCat=arraycontent.split(",");




      //  String[] mobileArray = {"Android","IPhone","WindowsMobile","Blackberry","WebOS","Ubuntu","Windows7","Max OS X"};
        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.listview,VODCat);

        listView = (ListView) findViewById(R.id.vodcategorlist);
        listView.setAdapter(adapter);
*/

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        System.setProperty("java.net.preferIPv4Addresses", "true");
        System.setProperty("java.net.preferIPv6Addresses", "true");
        System.setProperty("validated.ipv6", "true");

        SearchView SearchLiveTv=(SearchView)findViewById(R.id.SearchChannel);

        SearchLiveTv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            ArrayList NewVOD=new ArrayList<>();
            @Override
            public boolean onQueryTextSubmit(String s) {
                Log.d("QueryTextSubmit",s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Log.d("QueryTextChanged", s);

                String inputUser = s.toLowerCase();
                ArrayList FilterChannel = new ArrayList<>();
                int j = 0;
                for (String channel : VODJsonName) {

                    if (channel.toLowerCase().contains(inputUser)) {

                        FilterChannel.add(new Book(VODJsonName.get(j).toString(), VODJsonCategory.get(j).toString(), VODJsonUrl.get(j).toString(), VODJsonLogoPath.get(j).toString(), VODJsonsypnopsis.get(j).toString()));
                    }
                    j++;
                }

                myAdapterRB = new RecyclerViewAdapterRB(redbox_grid_activity.this,FilterChannel);
                myrvRB.setAdapter(myAdapterRB);
                return true;
            }
        });

        new CountDownTimer(10, 10) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {

            new redbox_grid_activity.LoadVODInBookList().execute("");

            }

        }.start();


/*


        myrv = (RecyclerView) findViewById(R.id.recyclerview_id);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this,lstBook);
        myAdapter2 = new RecyclerViewAdapter(this,MalayVOD);
        myrv.setLayoutManager(new GridLayoutManager(this,4));
        myrv.setAdapter(myAdapter);
*/
        listView = (ListView) findViewById(R.id.redboxvodcategorlist);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

              new redbox_grid_activity.FilterContent().execute(i);
            }
        });

        // Toast.makeText(getApplicationContext(),"Height screen : " + widths,Toast.LENGTH_SHORT).show();
    }

    public String getPersonID(){

        return personID;
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



            //  String arraycontent;

            //arraycontent=getURL.getURL("http://scnx5.sytes.net/mitv/apps/vod/getvodlist.php");

            //  VODCat=arraycontent.split(",");




            //  String[] mobileArray = {"Android","IPhone","WindowsMobile","Blackberry","WebOS","Ubuntu","Windows7","Max OS X"};







            NewVOD=new ArrayList<>();
            AllChannel=new ArrayList<>();
            String selectedCategory=listView.getItemAtPosition(pos[0]).toString();

            for(int j=0;j<VODJsonCategory.size();j++){

                AllChannel.add(new Book(VODJsonName.get(j).toString(),VODJsonCategory.get(j).toString(),VODJsonUrl.get(j).toString(),VODJsonLogoPath.get(j).toString(),VODJsonsypnopsis.get(j).toString()));


                if (selectedCategory.equalsIgnoreCase(VODJsonCategory.get(j).toString())){
                    NewVOD.add(new Book(VODJsonName.get(j).toString(),VODJsonCategory.get(j).toString(),VODJsonUrl.get(j).toString(),VODJsonLogoPath.get(j).toString(),VODJsonsypnopsis.get(j).toString()));
                }


            }



            return "";
        }

        @Override
        protected void onPostExecute(String result) {

            //this method will be running on UI thread
            pdLoading.dismiss();


            myAdapterRBAllChannel=new RecyclerViewAdapterRB(redbox_grid_activity.this,AllChannel);
            myAdapterRB = new RecyclerViewAdapterRB(redbox_grid_activity.this,NewVOD);
            myrvRB.setAdapter(myAdapterRB);


        }

    }
    @Override
    public void onPause(){
        super.onPause();
        if(pdLoading!=null)
            pdLoading.dismiss();
    }

    private class LoadVODInBookList extends AsyncTask<String,String,String>
    {
        ProgressDialog pdLoading = new ProgressDialog(redbox_grid_activity.this);

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

            String name,logoPath,url,category,sypnopsis,firstCat;

            getURL wget=new getURL();
            String json= wget.getURL("https://layar3.com/apps/livetv/getmyiptv4k.php");

            try {
                JSONObject objectPremium = new JSONObject(String.valueOf(json));
                JSONArray VodData = (JSONArray) objectPremium.getJSONArray("data");

                SETVODJsonCategory = new LinkedHashSet<String>();
                VODJsonName = new ArrayList<String>();
                VODJsonLogoPath = new ArrayList<String>();
                VODJsonUrl = new ArrayList<String>();
                VODJsonCategory = new ArrayList<String>();
                VODJsonsypnopsis = new ArrayList<String>();
                //Toast.makeText(getApplicationContext(),VODname,Toast.LENGTH_LONG).show();
                String VODname,VODlogo,VODurl,VODcategory,VODsypnopsis;

                for (int i = 0; i < VodData.length(); i++) {
                    VODname = VodData.getJSONObject(i).getString("name");
                    VODlogo = VodData.getJSONObject(i).getString("logoPath");
                    VODurl = VodData.getJSONObject(i).getString("playUrl");
                    VODcategory = VodData.getJSONObject(i).getString("genre");
                    VODsypnopsis = VodData.getJSONObject(i).getString("sypnopsis");


                    VODJsonName.add(VODname);
                    VODJsonLogoPath.add(VODlogo);
                    VODJsonUrl.add(VODurl);
                    VODJsonCategory.add(VODcategory);
                    VODJsonsypnopsis.add(VODsypnopsis);

                    SETVODJsonCategory.add(VODcategory);
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }


            // Toast.makeText(getApplicationContext(),objectPremium.getBoolean("state"),Toast.LENGTH_LONG).show();

            VODCat=new ArrayList<String>();

            for (String s : SETVODJsonCategory) {
                Log.d("SETVODJsonCategory",s);

                VODCat.add(s);


            }



            firstCat="";
            for(int i=0;i<VODCat.size();i++){
                firstCat=VODCat.get(i).toString();
                break;
            }



            VOD=new ArrayList<>();
            for (int i=0;i<VODJsonName.size();i++){
                name=VODJsonName.get(i).toString();
                category=VODJsonCategory.get(i).toString();
                logoPath=VODJsonLogoPath.get(i).toString();
                url=VODJsonUrl.get(i).toString();
                sypnopsis=VODJsonsypnopsis.get(i).toString();

               // try {
                   // bitmap = BitmapFactory.decodeStream((InputStream)new URL(logoPath).getContent());
                //} catch (IOException e) {
              //      e.printStackTrace();
              //  }
               if(firstCat.equalsIgnoreCase(category)){
                   VOD.add(new Book(name, category, url, logoPath, sypnopsis));
               }
            }





            myrvRB = (RecyclerView) findViewById(R.id.recyclerview_id);
            myAdapterRB = new RecyclerViewAdapterRB(redbox_grid_activity.this,VOD);


            return "";

        }

        @Override
        protected void onPostExecute(String result) {

            //this method will be running on UI thread
            pdLoading.dismiss();

            ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.listview,VODCat);
            listView.setAdapter(adapter);

            myrvRB.setLayoutManager(new GridLayoutManager(getApplicationContext(),4));
            myrvRB.setAdapter(myAdapterRB);


        }

    }
}
