package mmanager.scnx5.com.authorization;

import android.content.Context;
import android.content.res.Resources;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import mmanager.scnx5.com.mitvmanager.Exoplayer.ExoBook;
import mmanager.scnx5.com.mitvmanager.Exoplayer.exoplayer_layar;
import mmanager.scnx5.com.mitvmanager.R;
import mmanager.scnx5.com.mitvmanager.getURL;

public class RecyclerViewAdapterHome extends RecyclerView.Adapter<RecyclerViewAdapterHome.MyViewHolderRB> {

    private Context mContext ;
    private List<HomeBook> mData ;
    private String playURL,channelName,imgUrl;
    private getURL wget =new getURL();

    public RecyclerViewAdapterHome(Context mContext, List<HomeBook> mData) {
        this.mContext = mContext;
        this.mData = mData;

    }

    @Override
    public RecyclerViewAdapterHome.MyViewHolderRB onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
       /* double width = Resources.getSystem().getDisplayMetrics().widthPixels;
         if (width > 2200 && width < 3000){
            view = mInflater.inflate(R.layout.exoplayer_channel_switch_1440, parent, false);

        }else if (width > 1280 && width < 2200){
            view = mInflater.inflate(R.layout.exoplayer_channel_switch_1080, parent, false);

        }else {
            view = mInflater.inflate(R.layout.exoplayer_channel_switch_720, parent, false);
        }*/
        view = mInflater.inflate(R.layout.home_last_watching_channel, parent, false);


        return new RecyclerViewAdapterHome.MyViewHolderRB(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapterHome.MyViewHolderRB holder, final int position) {


        holder.channelname.setText(mData.get(position).getTitle().toString());
        holder.channelcategory.setText(mData.get(position).getCategory().toString());
        String picURL=mData.get(position).getThumbnail().toString();
        Glide.with(mContext).load(picURL).into(holder.img_book_thumbnail);



    }




    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolderRB extends RecyclerView.ViewHolder {

        TextView channelname,channelcategory;
        ImageView img_book_thumbnail;


        //CardView cardView ;
      //  FrameLayout cardView ;
        public MyViewHolderRB(final View itemView) {
            super(itemView);

            channelname = (TextView) itemView.findViewById(R.id.lastwatching_channelname) ;
            channelcategory= (TextView) itemView.findViewById(R.id.lastwatching_channelcategory) ;
            img_book_thumbnail = (ImageView) itemView.findViewById(R.id.lastwatching_logo);
         //   cardView =(FrameLayout)itemView.findViewById(R.id.li_lastwatching_channel);
            Animation anim = AnimationUtils.loadAnimation(itemView.getContext(),R.anim.scale_out_tv);
            itemView.startAnimation(anim);
            anim.setFillAfter(true);




            itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if(b){

                        Animation anim = AnimationUtils.loadAnimation(itemView.getContext(),R.anim.scale_in_tv);
                        itemView.startAnimation(anim);
                        anim.setFillAfter(true);

                    //    Log.d("Onfocus","focused");
                    }else {
                        Animation anim = AnimationUtils.loadAnimation(itemView.getContext(),R.anim.scale_out_tv);
                        itemView.startAnimation(anim);
                        anim.setFillAfter(true);
                    //    Log.d("Outfocus","Outfocused");
                    }

                }
            });

        }
    }


}
