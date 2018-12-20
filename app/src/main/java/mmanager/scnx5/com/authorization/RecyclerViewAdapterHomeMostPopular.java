package mmanager.scnx5.com.authorization;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import mmanager.scnx5.com.mitvmanager.Exoplayer.exoplayer_layar;
import mmanager.scnx5.com.mitvmanager.R;
import mmanager.scnx5.com.mitvmanager.getURL;

import static android.content.Context.MODE_PRIVATE;

public class RecyclerViewAdapterHomeMostPopular extends RecyclerView.Adapter<RecyclerViewAdapterHomeMostPopular.MyViewHolderRB> {

    private Context mContext ;
    private List<HomeTrendBook> mData ;
    private String playURL,channelName,imgUrl;
    private getURL wget =new getURL();
    private SharedPreferences pref ;
    private SharedPreferences.Editor editor ;
    private String server,tk,Json;

    public RecyclerViewAdapterHomeMostPopular(Context mContext, List<HomeTrendBook> mData,String server,String tk,String Json) {
        this.mContext = mContext;
        this.mData = mData;
        this.server=server;
        this.tk=tk;
        this.Json=Json;
    }

    @Override
    public RecyclerViewAdapterHomeMostPopular.MyViewHolderRB onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=null;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
       /* double width = Resources.getSystem().getDisplayMetrics().widthPixels;
         if (width > 2200 && width < 3000){
            view = mInflater.inflate(R.layout.exoplayer_channel_switch_1440, parent, false);

        }else if (width > 1280 && width < 2200){
            view = mInflater.inflate(R.layout.exoplayer_channel_switch_1080, parent, false);

        }else {
            view = mInflater.inflate(R.layout.exoplayer_channel_switch_720, parent, false);
        }*/

        view = mInflater.inflate(R.layout.home_most_popular, parent, false);


        return new RecyclerViewAdapterHomeMostPopular.MyViewHolderRB(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapterHomeMostPopular.MyViewHolderRB holder, final int position) {

        pref = mContext.getSharedPreferences("HomeUI", MODE_PRIVATE);
        editor = pref.edit();

        holder.channelname.setText(mData.get(position).getTitle().toString());
        holder.channelcategory.setText(mData.get(position).getCategory().toString());
        String picURL = mData.get(position).getThumbnail().toString();
        Glide.with(mContext).load(picURL).into(holder.img_book_thumbnail);
        String focus_last = pref.getString("most_popular", null);



        if (focus_last != null) {


            if (focus_last.equalsIgnoreCase("true")) {
                holder.cardView.setFocusable(true);
                if(position==0) {
                    holder.cardView.requestFocus();
                }


            } else {
                holder.cardView.setFocusable(false);


            }
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, exoplayer_layar.class);

                // passing data to the book activity


                intent.putExtra("cid", mData.get(position).getId());
                intent.putExtra("Title", mData.get(position).getTitle());
                intent.putExtra("Url", mData.get(position).getUrl());
                intent.putExtra("Thumbnail", mData.get(position).getThumbnail());
                intent.putExtra("Sypnopsis", mData.get(position).getSysnopsis());
                intent.putExtra("Category", mData.get(position).getCategory());
                intent.putExtra("liveTV", "rb");
                intent.putExtra("tk", tk);
                intent.putExtra("server", server);
                intent.putExtra("json", Json);
                intent.putExtra("channelPos", 0);
                intent.putExtra("premium", mData.get(position).getpremium());

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



    public static class MyViewHolderRB extends RecyclerView.ViewHolder {

        TextView channelname,channelcategory;
        ImageView img_book_thumbnail;


        //CardView cardView ;
        FrameLayout cardView ;
        public MyViewHolderRB(final View itemView) {
            super(itemView);

            channelname = (TextView) itemView.findViewById(R.id.lastwatching_channelname) ;
            channelcategory= (TextView) itemView.findViewById(R.id.lastwatching_channelcategory) ;
            img_book_thumbnail = (ImageView) itemView.findViewById(R.id.lastwatching_logo);
            cardView =(FrameLayout) itemView.findViewById(R.id.home_last_watching);
         /*   Animation anim = AnimationUtils.loadAnimation(itemView.getContext(),R.anim.scale_out_tv);
            itemView.startAnimation(anim);
            anim.setFillAfter(true);*/



            itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {



                    if(b){
                        Animation anim = AnimationUtils.loadAnimation(itemView.getContext(),R.anim.scale_in_tv);
                        itemView.startAnimation(anim);
                        anim.setFillAfter(true);
                     //   Log.d("Onfocus","focused");
                    }else {
                        Animation anim = AnimationUtils.loadAnimation(itemView.getContext(),R.anim.scale_out_tv);
                        itemView.startAnimation(anim);
                        anim.setFillAfter(true);
                    }

                }
            });


        }

        public ImageView getImgView(){
            return img_book_thumbnail;
        }
    }


}
