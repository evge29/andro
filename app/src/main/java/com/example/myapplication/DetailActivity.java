package com.example.myapplication;


import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;




public class DetailActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        TextView Title = (TextView)findViewById(R.id.titleDetail);
        ImageView Image = (ImageView)findViewById(R.id.ImageDetail);
        TextView Text=(TextView)findViewById(R.id.subTitleDetail);

        Drawable drawable = ContextCompat.getDrawable
                (this,getIntent().getIntExtra(Horoscope.IMAGE_KEY, 0));


        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(Color.GRAY);


        if(drawable!=null) {
            gradientDrawable.setSize(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        }


        Title.setText(getIntent().getStringExtra(Horoscope.TITLE_KEY));
        String[] signList = getResources().getStringArray(R.array.titles);


        Text.setText(getIntent().getStringExtra(Horoscope.TEXT_KEY));


        Glide.with(this).load(getIntent().getIntExtra(Horoscope.IMAGE_KEY, 0)).
                apply(RequestOptions.placeholderOf(gradientDrawable)).into(Image);
    }
}
