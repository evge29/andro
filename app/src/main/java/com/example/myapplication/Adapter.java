package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.HoroscopeViewHolder> {

    private GradientDrawable mGradientDrawable;
    private ArrayList<Horoscope> mData;
    private Context mContext;

    Adapter(Context context, ArrayList<Horoscope> Data) {
        this.mData = Data;
        this.mContext = context;


        mGradientDrawable = new GradientDrawable();
        mGradientDrawable.setColor(Color.GRAY);


        Drawable drawable = ContextCompat.getDrawable
                (mContext,R.drawable.aries);
        if(drawable != null) {
            mGradientDrawable.setSize(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        }
    }



    @Override
    public Adapter.HoroscopeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Adapter.HoroscopeViewHolder(mContext, LayoutInflater.from(mContext).
                inflate(R.layout.activity_adapter, parent,false), mGradientDrawable);
    }

    @Override
    public void onBindViewHolder(Adapter.HoroscopeViewHolder holder, int position) {

        Horoscope currentsing = mData.get(position);


        holder.bindTo(currentsing);

    }


    public int getItemCount() {
        return mData.size();
    }



    static class HoroscopeViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private TextView mTitleText;
        private TextView mInfoText;
        private ImageView mImage;
        private TextView mText;
        private Context mContext;
        private Horoscope mCurrent;
        private GradientDrawable mGradientDrawable;


        HoroscopeViewHolder(Context context, View itemView, GradientDrawable gradientDrawable) {
            super(itemView);


            mTitleText = (TextView)itemView.findViewById(R.id.title);
            mInfoText = (TextView)itemView.findViewById(R.id.subTitle);
            mImage = (ImageView)itemView.findViewById(R.id.Image);
            mText=(TextView)itemView.findViewById(R.id.subTitleDetail);

            mContext = context;
            mGradientDrawable = gradientDrawable;


            itemView.setOnClickListener(this);
        }

        void bindTo(Horoscope currentsing){

            mTitleText.setText(currentsing.getTitle());
            mInfoText.setText(currentsing.getInfo());
           mText.setText(currentsing.getText());

            mCurrent = currentsing;




            Glide.with(mContext).load(currentsing.
                    getImageResource()).apply(RequestOptions.placeholderOf(mGradientDrawable)).into(mImage);
        }

        @Override
        public void onClick(View view) {


            Intent detailIntent = Horoscope.starter(mContext, mCurrent.getTitle(),
                    mCurrent.getImageResource(), (String) mText.getText());



            mContext.startActivity(detailIntent);
        }
    }
}
