package mmanager.scnx5.com.mitvmanager.RedBoxGrid;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import mmanager.scnx5.com.mitvmanager.Exoplayer.exoplayer_layar;
import mmanager.scnx5.com.mitvmanager.R;
import mmanager.scnx5.com.mitvmanager.VODGrid.Book;
import mmanager.scnx5.com.mitvmanager.VODGrid.Book_Activity;
import mmanager.scnx5.com.mitvmanager.getURL;
import mmanager.scnx5.com.mitvmanager.redbox_grid_activity;

public class RecyclerViewAdapterRB extends RecyclerView.Adapter<mmanager.scnx5.com.mitvmanager.RedBoxGrid.RecyclerViewAdapterRB.MyViewHolderRB> {

    private Context mContext ;
    private List<Book> mData ;



    public RecyclerViewAdapterRB(Context mContext, List<Book> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public mmanager.scnx5.com.mitvmanager.RedBoxGrid.RecyclerViewAdapterRB.MyViewHolderRB onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardveiw_item_book,parent,false);
        return new mmanager.scnx5.com.mitvmanager.RedBoxGrid.RecyclerViewAdapterRB.MyViewHolderRB(view);
    }

    @Override
    public void onBindViewHolder(mmanager.scnx5.com.mitvmanager.RedBoxGrid.RecyclerViewAdapterRB.MyViewHolderRB holder, final int position) {

        holder.tv_book_title.setText(mData.get(position).getTitle());


        //Integer picURL=mData.get(position).getThumbnail();
        // Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(picURL).getContent());
        String picURL=mData.get(position).getThumbnail();
        //  holder.img_book_thumbnail.setImage(picURL);
        Picasso.with(mContext).load(picURL).into(holder.img_book_thumbnail);


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

           //     String sypnopsis = mData.get(position).getSysnopsis();
             //   String Url=mData.get(position).getUrl();

               // new StartChannel().execute(sypnopsis,Url);

                String Sypnopsis=mData.get(position).getSysnopsis();

                if (Sypnopsis.equalsIgnoreCase("REDBOX") ||Sypnopsis.equalsIgnoreCase("M4K") || Sypnopsis.equalsIgnoreCase("LIVENET")
                        || Sypnopsis.equalsIgnoreCase("HLS")) {

                    Intent intent = new Intent(mContext, exoplayer_layar.class);

                    // passing data to the book activity
                    intent.putExtra("Title", mData.get(position).getTitle());
                    intent.putExtra("Url", mData.get(position).getUrl());
                    intent.putExtra("Thumbnail", mData.get(position).getThumbnail());
                    intent.putExtra("Sypnopsis", mData.get(position).getSysnopsis());
                    intent.putExtra("Category", mData.get(position).getCategory());
                    intent.putExtra("liveTV", "rb");
                    //  Toast.makeText(mContext,"test",Toast.LENGTH_LONG).show();
                    // start the activity

                    mContext.startActivity(intent);
                }else {

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setPackage("com.mxtech.videoplayer.ad");
                    intent.setDataAndType(Uri.parse(mData.get(position).getUrl()), "video/*");
                    //Toast.makeText(Book_Activity.this,"Movie is starting...",Toast.LENGTH_LONG).show();
                    mContext.startActivity(intent);

                }



            }
        });
   }




    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolderRB extends RecyclerView.ViewHolder {

        TextView tv_book_title;
        ImageView img_book_thumbnail;
        //CardView cardView ;
        LinearLayout cardView ;
        public MyViewHolderRB(View itemView) {
            super(itemView);

            tv_book_title = (TextView) itemView.findViewById(R.id.book_title_id) ;
            img_book_thumbnail = (ImageView) itemView.findViewById(R.id.book_img_id);
//            cardView = (CardView) itemView.findViewById(R.id.cardview_id);
            cardView = (LinearLayout) itemView.findViewById(R.id.cardview_id);


        }
    }


}
