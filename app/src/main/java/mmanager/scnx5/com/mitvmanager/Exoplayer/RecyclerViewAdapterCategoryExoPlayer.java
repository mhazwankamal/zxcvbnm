package mmanager.scnx5.com.mitvmanager.Exoplayer;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import mmanager.scnx5.com.mitvmanager.R;
import mmanager.scnx5.com.mitvmanager.log_;

public class RecyclerViewAdapterCategoryExoPlayer extends RecyclerView.Adapter<mmanager.scnx5.com.mitvmanager.Exoplayer.RecyclerViewAdapterCategoryExoPlayer.MyViewHolderExoPlayer> {

    private Context mContext ;
   // private List<ExoBook> mData ;
    private ArrayList <String> Category;
    private log_ dlog;


    public RecyclerViewAdapterCategoryExoPlayer(Context mContext,ArrayList<String> category) {
        this.mContext = mContext;
        this.Category = category;

    }

    @Override
    public mmanager.scnx5.com.mitvmanager.Exoplayer.RecyclerViewAdapterCategoryExoPlayer.MyViewHolderExoPlayer onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        double width= Resources.getSystem().getDisplayMetrics().widthPixels;
        if (width < 1920){
            view = mInflater.inflate(R.layout.exoplayer_channel_category, parent, false);

        }else {
            view = mInflater.inflate(R.layout.exoplayer_channel_category, parent, false);
        }
        return new mmanager.scnx5.com.mitvmanager.Exoplayer.RecyclerViewAdapterCategoryExoPlayer.MyViewHolderExoPlayer(view);
    }

    @Override
    public void onBindViewHolder(final mmanager.scnx5.com.mitvmanager.Exoplayer.RecyclerViewAdapterCategoryExoPlayer.MyViewHolderExoPlayer holder, final int position) {

        holder.tv_book_title.setText(Category.get(position));
   //     String picURL =Category.get(position);
 //       Glide.with(mContext).load(picURL).into(holder.img_book_thumbnail);
        //Integer picURL=mData.get(position).getThumbnail();
        // Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(picURL).getContent());
//        String picURL=mData.get(position).getThumbnail();
        //  holder.img_book_thumbnail.setImage(picURL);
  //      Glide.with(mContext).load(picURL).into(holder.img_book_thumbnail);

      holder.cardView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              List<String> myVODName=((exoplayer_layar)view.getContext()).VODJsonName;
              Toast.makeText(mContext,String.valueOf(myVODName.size()),Toast.LENGTH_SHORT).show();
          }
      });


    }




    @Override
    public int getItemCount() {
        return Category.size();
    }




    public static class MyViewHolderExoPlayer extends RecyclerView.ViewHolder {

        TextView tv_book_title;
        ImageView img_book_thumbnail;
        //CardView cardView ;
       FrameLayout cardView ;
        public MyViewHolderExoPlayer(View itemView) {
            super(itemView);

            tv_book_title = (TextView) itemView.findViewById(R.id.tv_species) ;
        //    img_book_thumbnail = (ImageView) itemView.findViewById(R.id.exo_channel_image);
//            cardView = (CardView) itemView.findViewById(R.id.cardview_id);
            cardView = (FrameLayout) itemView.findViewById(R.id.exo_channel_category_focus);


        }

    }
}