package mmanager.scnx5.com.mitvmanager.VODGrid;


import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import mmanager.scnx5.com.mitvmanager.R;

/**
 * Created by Aws on 28/01/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext ;
    private List<VODBook> mData ;
    private String token;
    private String server;

    public RecyclerViewAdapter(Context mContext, List<VODBook> mData,String token,String server) {
        this.mContext = mContext;
        this.mData = mData;
        this.token=token;
        this.server=server;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        double width= Resources.getSystem().getDisplayMetrics().widthPixels;
        if (width < 1920){
            view = mInflater.inflate(R.layout.cardveiw_item_book_720p_vod, parent, false);

        }else {
            view = mInflater.inflate(R.layout.cardveiw_item_book_vod, parent, false);
        }
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

      //  holder.tv_book_title.setText(mData.get(position).getTitle());


            //Integer picURL=mData.get(position).getThumbnail();
           // Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(picURL).getContent());
        String picURL=mData.get(position).getThumbnail();
          //  holder.img_book_thumbnail.setImage(picURL);
        Glide
                .with(mContext)
                .load(picURL)
                .into(holder.img_book_thumbnail);
      //  Picasso.with(mContext).load(picURL).into(holder.img_book_thumbnail);


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext,Book_Activity.class);

                // passing data to the book activity
                intent.putExtra("Title",mData.get(position).getTitle());
                intent.putExtra("Url",mData.get(position).getUrl());
                intent.putExtra("Thumbnail",mData.get(position).getThumbnail());
                intent.putExtra("Sypnopsis",mData.get(position).getSysnopsis());
                intent.putExtra("Category",mData.get(position).getCategory());
                intent.putExtra("backdrop",mData.get(position).getBackdrop());

                intent.putExtra("liveTV","vod");
                intent.putExtra("tk",token);
                intent.putExtra("server",server);
                //  Toast.makeText(mContext,"test",Toast.LENGTH_LONG).show();
                // start the activity
                mContext.startActivity(intent);


            }
        });



    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        //TextView tv_book_title;
        ImageView img_book_thumbnail;
        //CardView cardView ;
        LinearLayout cardView ;
        public MyViewHolder(View itemView) {
            super(itemView);

          //  tv_book_title = (TextView) itemView.findViewById(R.id.book_title_id) ;
            img_book_thumbnail = (ImageView) itemView.findViewById(R.id.book_img_id);
//            cardView = (CardView) itemView.findViewById(R.id.cardview_id);
            cardView = (LinearLayout) itemView.findViewById(R.id.cardview_id);


        }
    }


}
