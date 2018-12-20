package mmanager.scnx5.com.mitvmanager.RedBoxGrid;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

import mmanager.scnx5.com.abcsesscxz;
import mmanager.scnx5.com.abcyxoorp;
import mmanager.scnx5.com.mitvmanager.Exoplayer.exoplayer_layar;
import mmanager.scnx5.com.mitvmanager.Exoplayer.vlc_player_livetv;
import mmanager.scnx5.com.mitvmanager.R;

import mmanager.scnx5.com.mitvmanager.VODGrid.Book;
import mmanager.scnx5.com.mitvmanager.getURL;

public class RecyclerViewAdapterRB extends RecyclerView.Adapter<mmanager.scnx5.com.mitvmanager.RedBoxGrid.RecyclerViewAdapterRB.MyViewHolderRB> {

    private Context mContext ;
    private List<LiveBook> mData ;
    private String token;
    private String server;
    private String Json;


    public RecyclerViewAdapterRB(Context mContext, List<LiveBook> mData,String token,String server,String json) {
        this.mContext = mContext;
        this.mData = mData;
        this.token = token;
        this.server=server;
        this.Json=json;
    }

    @Override
    public mmanager.scnx5.com.mitvmanager.RedBoxGrid.RecyclerViewAdapterRB.MyViewHolderRB onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        double width= Resources.getSystem().getDisplayMetrics().widthPixels;
        if (width < 1920){
            view = mInflater.inflate(R.layout.cardveiw_item_book_720p, parent, false);

        }else {
            view = mInflater.inflate(R.layout.cardveiw_item_book, parent, false);
        }
        return new mmanager.scnx5.com.mitvmanager.RedBoxGrid.RecyclerViewAdapterRB.MyViewHolderRB(view);
    }

    @Override
    public void onBindViewHolder(mmanager.scnx5.com.mitvmanager.RedBoxGrid.RecyclerViewAdapterRB.MyViewHolderRB holder, final int position) {

        holder.tv_book_title.setText(mData.get(position).getTitle());


        //Integer picURL=mData.get(position).getThumbnail();
        // Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(picURL).getContent());
        String picURL=mData.get(position).getThumbnail();
        //  holder.img_book_thumbnail.setImage(picURL);
        Glide.with(mContext).load(picURL).into(holder.img_book_thumbnail);


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

           //     String sypnopsis = mData.get(position).getSysnopsis();
             //   String Url=mData.get(position).getUrl();

               // new StartChannel().execute(sypnopsis,Url);

                String Sypnopsis=mData.get(position).getSysnopsis();

                if (Sypnopsis.equalsIgnoreCase("REDBOX") ||Sypnopsis.equalsIgnoreCase("M4K") || Sypnopsis.equalsIgnoreCase("LIVENET")
                        || Sypnopsis.equalsIgnoreCase("HLS")) {

                    abcyxoorp a =new abcyxoorp();
                    abcsesscxz b=new abcsesscxz();


                        Intent intent = new Intent(mContext, exoplayer_layar.class);

                        // passing data to the book activity

                        intent.putExtra("cid", mData.get(position).getId());
                        intent.putExtra("Title", mData.get(position).getTitle());
                        intent.putExtra("Url", mData.get(position).getUrl());
                        intent.putExtra("Thumbnail", mData.get(position).getThumbnail());
                        intent.putExtra("Sypnopsis", mData.get(position).getSysnopsis());
                        intent.putExtra("Category", mData.get(position).getCategory());
                        intent.putExtra("liveTV", "rb");
                        intent.putExtra("tk", token);
                        intent.putExtra("server", server);
                        intent.putExtra("json", Json);
                        intent.putExtra("channelPos", position);
                        intent.putExtra("premium", mData.get(position).getPremium());

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
