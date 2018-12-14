package me.mikasa.imageloader;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private GridView mPhotoWall=null;
    private Context mContext=null;
    private int mImageThumbSize;
    private int mImageThumbSpacing;
    private PhotoWallAdapter mAdapter=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageThumbSize=this.getResources().getDimensionPixelSize(R.dimen.image_thumbnail_size);
        mImageThumbSpacing=this.getResources().getDimensionPixelSize(R.dimen.image_thumbnail_spacing);
        mPhotoWall=findViewById(R.id.id_photo_wall);
        mAdapter=new PhotoWallAdapter(this,0,Images.imageThumbUrls,mPhotoWall);
        mContext=MainActivity.this;
        mPhotoWall.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mContext,position+"",Toast.LENGTH_SHORT).show();
            }
        });
        mPhotoWall.setAdapter(mAdapter);
        mPhotoWall.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                final int numColumns = (int) Math.floor(mPhotoWall.getWidth() / (mImageThumbSize + mImageThumbSpacing));
                if (numColumns > 0) {
                    int columnWidth = (mPhotoWall.getWidth() / numColumns) - mImageThumbSpacing;
                    mAdapter.setItemHeight(columnWidth);
                    mPhotoWall.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        mAdapter.fluchCache();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter.cancelAllTasks();
    }
}
