package com.example.myapplication;


import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private ArrayList<Horoscope> mData;
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);


        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));



        mData = new ArrayList<>();


        mAdapter = new Adapter(this, mData) ;

        mRecyclerView.setAdapter(mAdapter);


        initializeData();


        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback
                (ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN
                        | ItemTouchHelper.UP, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

                     @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {


                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();


                Collections.swap(mData, from, to);
                mAdapter.notifyItemMoved(from, to);
                return true;
            }


            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

                mData.remove(viewHolder.getAdapterPosition());

                mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });


        helper.attachToRecyclerView(mRecyclerView);
    }


    private void initializeData() {

        String[] signList = getResources().getStringArray(R.array.titles);
        String[] signInfo = getResources().getStringArray(R.array.info);
        String[] signListinfo = getResources().getStringArray(R.array.filler_text);
        TypedArray sportsImageResources = getResources().obtainTypedArray(R.array.images);
              mData.clear();


        for(int i=0; i<signList.length; i++){
            mData.add(new Horoscope(signList[i], signInfo[i],
                    sportsImageResources.getResourceId(i,0)));
        }


        sportsImageResources.recycle();


        mAdapter.notifyDataSetChanged();
    }


    public void reset(View view) {
        initializeData();
    }
}
