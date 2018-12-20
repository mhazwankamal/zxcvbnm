package mmanager.scnx5.com.authorization;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import mmanager.scnx5.com.mitvmanager.Exoplayer.exoplayer_layar;
import mmanager.scnx5.com.mitvmanager.R;
import mmanager.scnx5.com.mitvmanager.getURL;

public class RecyclerViewVerticalAdapterHome extends RecyclerView.Adapter<RecyclerViewVerticalAdapterHome.MyViewHolderRB> {

    private Context mContext ;
    private List<AllBook> mData ;
    private String playURL,channelName,imgUrl;
    private getURL wget =new getURL();
    private Integer scrollPos=0;
    private LinearLayoutManager   HorizontalChannel;
    private String server,tk,Json;
    public RecyclerViewVerticalAdapterHome(Context mContext, List<AllBook> mData,String server,String tk,String Json) {
        this.mContext = mContext;
        this.mData = mData;
        this.server=server;
        this.tk=tk;
        this.Json=Json;
    }

    @Override
    public RecyclerViewVerticalAdapterHome.MyViewHolderRB onCreateViewHolder(ViewGroup parent, int viewType) {

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
    //need to create view for vertical listview layout
        view = mInflater.inflate(R.layout.home_channelcategory_list, parent, false);


        return new RecyclerViewVerticalAdapterHome.MyViewHolderRB(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewVerticalAdapterHome.MyViewHolderRB holder, final int position) {


      holder.headerTitle.setText(mData.get(position).getHeaderTitle().toString());

     /* RecyclerViewAdapterHomeTrend myAdapterHomeTrend=new RecyclerViewAdapterHomeTrend(mContext,mData.get(position).getAllBooks());
      HorizontalChannel=new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,true);
      holder.channelContentListing.setLayoutManager(HorizontalChannel);
      holder.channelContentListing.setAdapter(myAdapterHomeTrend);*/

        holder.channelContentListing.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                Log.d("OnScrolled","Check on scroll");
            }
        });

    }





    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolderRB extends RecyclerView.ViewHolder {

        TextView headerTitle;
        ImageView img_book_thumbnail;
        RecyclerView channelContentListing;

        //CardView cardView ;
      //  FrameLayout cardView ;
        public MyViewHolderRB(final View itemView) {
            super(itemView);

          //put receyvlerview horizontal here
            headerTitle=(TextView) itemView.findViewById(R.id.HeaderListTitle);
            channelContentListing=(RecyclerView)itemView.findViewById(R.id.home_listing_channel_based_category);

           /* Animation anim = AnimationUtils.loadAnimation(itemView.getContext(),R.anim.scale_in_tv);
            itemView.startAnimation(anim);
            anim.setFillAfter(true);*/

            itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {

                     if(b){
                  /*      Animation anim = AnimationUtils.loadAnimation(itemView.getContext(),R.anim.scale_in_tv);
                        itemView.startAnimation(anim);
                        anim.setFillAfter(true);*/


                         //    Log.d("Onfocus","focused");
                    }else {
                        //    Log.d("Outfocus","Outfocused");

                    }

                }
            });

        }
    }


}
