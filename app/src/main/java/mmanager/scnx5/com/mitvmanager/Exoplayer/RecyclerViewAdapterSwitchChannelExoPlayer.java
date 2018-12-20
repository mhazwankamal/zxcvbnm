package mmanager.scnx5.com.mitvmanager.Exoplayer;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import mmanager.scnx5.com.mitvmanager.R;
import mmanager.scnx5.com.mitvmanager.RedBoxGrid.LiveBook;
import mmanager.scnx5.com.mitvmanager.getURL;

import static android.content.Context.MODE_PRIVATE;

public class RecyclerViewAdapterSwitchChannelExoPlayer extends RecyclerView.Adapter<RecyclerViewAdapterSwitchChannelExoPlayer.MyViewHolderRB> {

    private Context mContext ;
    private List<ExoBook> mData ;
    private String playURL,channelName,imgUrl;
    private getURL wget =new getURL();

    public RecyclerViewAdapterSwitchChannelExoPlayer(Context mContext, List<ExoBook> mData) {
        this.mContext = mContext;
        this.mData = mData;

    }

    @Override
    public RecyclerViewAdapterSwitchChannelExoPlayer.MyViewHolderRB onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        double width = Resources.getSystem().getDisplayMetrics().widthPixels;
         if (width > 2200 && width < 3000){
            view = mInflater.inflate(R.layout.exoplayer_channel_switch_1440, parent, false);

        }else if (width > 1280 && width < 2200){
            view = mInflater.inflate(R.layout.exoplayer_channel_switch_1080, parent, false);

        }else {
            view = mInflater.inflate(R.layout.exoplayer_channel_switch_720, parent, false);
        }
        return new RecyclerViewAdapterSwitchChannelExoPlayer.MyViewHolderRB(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapterSwitchChannelExoPlayer.MyViewHolderRB holder, final int position) {

      holder.tv_book_title.setText(mData.get(position).getTitle());


        //Integer picURL=mData.get(position).getThumbnail();
        // Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(picURL).getContent());
        String picURL=mData.get(position).getThumbnail();
        String[] proginfo1=mData.get(position).getProgInfo1().split("hazwan");
        String[] proginfo2=mData.get(position).getProgInfo2().split("hazwan");

       SharedPreferences pref = mContext.getSharedPreferences("MyPref", MODE_PRIVATE);
        int i=0;
        Integer selectChannelPosition=pref.getInt("channelPos",i);

       if((selectChannelPosition - position) == 0){
           holder.cardView.requestFocus();
       }

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

        holder.programnow.setText(proginfo1[0]);
        holder.timenow.setText(playStart + "-" + playEnd);
        holder.programnext.setText(proginfo2[0]);
        holder.timenext.setText(playStart2 + "-" + playEnd2);

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

           //     String sypnopsis = mData.get(position).getSysnopsis();
             //   String Url=mData.get(position).getUrl();

               // new StartChannel().execute(sypnopsis,Url);

            /*    String Sypnopsis=mData.get(position).getSysnopsis();

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


                    //  Toast.makeText(mContext,"test",Toast.LENGTH_LONG).show();
                        // start the activity

                        mContext.startActivity(intent);

                }

*/

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
        TextView programnow;
        TextView timenow;
        TextView programnext;
        TextView timenext;

        //CardView cardView ;
        LinearLayout cardView ;
        public MyViewHolderRB(View itemView) {
            super(itemView);

            tv_book_title = (TextView) itemView.findViewById(R.id.channelname_switch) ;

            img_book_thumbnail = (ImageView) itemView.findViewById(R.id.exo_channel_image);
            cardView = (LinearLayout)itemView.findViewById(R.id.top_layout);
            programnow=(TextView)itemView.findViewById(R.id.programnow);
            programnext=(TextView)itemView.findViewById(R.id.programnext);
            timenow=(TextView)itemView.findViewById(R.id.timestapnow);
            timenext=(TextView)itemView.findViewById(R.id.timestapnext);
//            cardView = (CardView) itemView.findViewById(R.id.cardview_id);
         //   cardView = (LinearLayout) itemView.findViewById(R.id.cardview_id);


        }
    }


}
