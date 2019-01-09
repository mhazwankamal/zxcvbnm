package mmanager.scnx5.com.mitvmanager.Exoplayer;

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

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import mmanager.scnx5.com.abcsesscxz;
import mmanager.scnx5.com.abcyxoorp;
import mmanager.scnx5.com.mitvmanager.R;
import mmanager.scnx5.com.mitvmanager.RedBoxGrid.LiveBook;

public class RecyclerViewAdapterRBExo extends RecyclerView.Adapter<RecyclerViewAdapterRBExo.MyViewHolderRB> {

    private Context mContext ;
    private List<ExoBook> mData ;
    private String playURL,channelName,imgUrl;


    public RecyclerViewAdapterRBExo(Context mContext, List<ExoBook> mData) {
        this.mContext = mContext;
        this.mData = mData;

    }

    @Override
    public RecyclerViewAdapterRBExo.MyViewHolderRB onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        double width= Resources.getSystem().getDisplayMetrics().widthPixels;

        if (width > 2200 && width < 3000){
            view = mInflater.inflate(R.layout.cardveiw_item_book_1440, parent, false);

        }else if (width > 1600 && width < 2200){
            view = mInflater.inflate(R.layout.cardveiw_item_book, parent, false);

        }else {
            view = mInflater.inflate(R.layout.cardveiw_item_book_720p, parent, false);
        }


        return new RecyclerViewAdapterRBExo.MyViewHolderRB(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapterRBExo.MyViewHolderRB holder, final int position) {

        holder.tv_book_title.setText(mData.get(position).getTitle());


        //Integer picURL=mData.get(position).getThumbnail();
        // Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(picURL).getContent());
        String picURL=mData.get(position).getThumbnail();
        //  holder.img_book_thumbnail.setImage(picURL);
        Glide.with(mContext).load(picURL).into(holder.img_book_thumbnail);


         holder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        String[] proginfo1=mData.get(position).getProgInfo1().split("hazwan");
                        String[] proginfo2=mData.get(position).getProgInfo2().split("hazwan");

                        String playStart,playEnd,playStart2,playEnd2;
                        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
                        formatter.setTimeZone(TimeZone.getTimeZone("Asia/Kuala_Lumpur"));
                        Log.d("TimeString",proginfo2[2]);

                        if(proginfo1[0].equalsIgnoreCase("No working EPG") || proginfo2[0].equalsIgnoreCase("No working EPG")){

                            playStart="-";
                            playEnd="-";
                            playStart2="-";
                            playEnd2="-";

                        } else {
                            playStart = formatter.format(new Date(Long.parseLong(proginfo1[1]) * 1000));
                            playEnd = formatter.format(new Date(Long.parseLong(proginfo1[2]) * 1000));
                            playStart2 = formatter.format(new Date(Long.parseLong(proginfo2[1]) * 1000));
                            playEnd2 = formatter.format(new Date(Long.parseLong(proginfo2[2]) * 1000));

                        }



                        playURL=mData.get(position).getUrl();
                        channelName=mData.get(position).getTitle();
                        imgUrl=mData.get(position).getThumbnail();



                        ((exoplayer_layar)v.getContext()).switchChannel(playURL,channelName,imgUrl,mData.get(position).getpremium()
                                ,mData.get(position).getId(),proginfo1[0],playStart,playEnd,position,mData.get(position).getCategory(),mData.get(position).getSysnopsis());



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
