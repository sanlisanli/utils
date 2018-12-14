package me.mikasa.popupwindow;

import android.content.pm.LabeledIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mButton1,mButton2,mButton3,mButton4,mButton5,mButton6,mButton7;
    private CustomPopupWindow mCustomPopWindow,mListPopWindow,mPopWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }
    private void initViews(){
        mButton1 = (TextView) findViewById(R.id.button1);
        mButton1.setOnClickListener(this);
        mButton2 = (TextView) findViewById(R.id.button2);
        mButton2.setOnClickListener(this);
        mButton3 = (TextView) findViewById(R.id.button3);
        mButton3.setOnClickListener(this);
        mButton4 = (TextView) findViewById(R.id.button4);
        mButton4.setOnClickListener(this);
        mButton5 = (TextView) findViewById(R.id.button5);
        mButton5.setOnClickListener(this);
        mButton6 = (TextView) findViewById(R.id.button6);
        mButton6.setOnClickListener(this);
        mButton7 = (TextView) findViewById(R.id.button7);
        mButton7.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                showPopBottom();
                break;
            case R.id.button2:
                showPopTop();
                break;
            case R.id.button3:
                showPopMenu();
                break;
            case R.id.button4:
                showPopListView();
                //showListView();
                break;
            case R.id.button5:
                showPopTopWithDarkBg();
                break;
            case R.id.button6:
                useInAndOutAnim();
                break;
            case R.id.button7:
                touchOutsideDontDisMiss();
                break;
        }
    }
    private void touchOutsideDontDisMiss(){

    }
    private void showPopBottom(){
        View view=LayoutInflater.from(this).inflate(R.layout.pop_layout1,null);
        TextView textView=view.findViewById(R.id.tv_tip_two);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"tip",Toast.LENGTH_SHORT).show();
            }
        });
        CustomPopupWindow popupWindow=new CustomPopupWindow.PopupWindowBuilder(this)
                .setView(view)
                .setFocusable(true)
                .setOutsideTouchable(true)
                .create();
        popupWindow.displayAsDropDown(mButton1,0,0);//displayAsDropDown()
    }
    private void showPopTop(){
        CustomPopupWindow popupWindow=new CustomPopupWindow.PopupWindowBuilder(this)
                .setView(R.layout.pop_layout2)
                .create();
        popupWindow.displayAsDropDown(mButton2,0,-(mButton2.getHeight()+popupWindow.getHeight()));
    }
    private void showPopTopWithDarkBg(){
        View contentView=LayoutInflater.from(this).inflate(R.layout.pop_menu,null);
        handleLogic(contentView);
        mCustomPopWindow=new CustomPopupWindow.PopupWindowBuilder(this)
                .setView(contentView)
                .enableBackgroundDark(true)
                .setBgDarkAlpha(0.7f)
                .setOnDissmissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        //
                    }
                }).create().displayAsDropDown(mButton5,0,0);
    }
    private void showPopMenu(){
        View contentView = LayoutInflater.from(this).inflate(R.layout.pop_menu,null);
        //处理popWindow 显示内容
        handleLogic(contentView);
        mCustomPopWindow=new CustomPopupWindow.PopupWindowBuilder(this)
                .setView(contentView)
                .create()
                .displayAsDropDown(mButton3,0,0);
    }
    private void showPopListView(){
        View contentView=LayoutInflater.from(this).inflate(R.layout.pop_list,null);
        handleListView(contentView);
        mListPopWindow=new CustomPopupWindow.PopupWindowBuilder(this)
                .setView(contentView)
                .create()
                .displayAsDropDown(mButton4,0,0);
    }
    private void useInAndOutAnim(){
        CustomPopupWindow popupWindow=new CustomPopupWindow.PopupWindowBuilder(this)
                .setView(R.layout.pop_layout1)
                .setFocusable(true)
                .setOutsideTouchable(true)
                .setAnimationStyle(R.style.CustomPopWindowStyle)
                .create();
        popupWindow.displayAsDropDown(mButton6,0,0);
    }
    private void handleLogic(View contentView){
        View.OnClickListener listener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCustomPopWindow!=null){
                    mCustomPopWindow.dismiss();
                }
                String showContent = "";
                switch (v.getId()){
                    case R.id.menu1:
                        showContent = "点击 Item菜单1";
                        break;
                    case R.id.menu2:
                        showContent = "点击 Item菜单2";
                        break;
                    case R.id.menu3:
                        showContent = "点击 Item菜单3";
                        break;
                    case R.id.menu4:
                        showContent = "点击 Item菜单4";
                        break;
                    case R.id.menu5:
                        showContent = "点击 Item菜单5" ;
                        break;
                }
                Toast.makeText(MainActivity.this,showContent,Toast.LENGTH_SHORT).show();
                //
            }
        };
        contentView.findViewById(R.id.menu1).setOnClickListener(listener);
        contentView.findViewById(R.id.menu2).setOnClickListener(listener);
        contentView.findViewById(R.id.menu3).setOnClickListener(listener);
        contentView.findViewById(R.id.menu4).setOnClickListener(listener);
        contentView.findViewById(R.id.menu5).setOnClickListener(listener);
    }
    private void handleListView(View view){
        RecyclerView recyclerView=view.findViewById(R.id.recyclerView);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        CustomAdapter adapter=new CustomAdapter();
        adapter.setData(mockData());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    private List<String> mockData(){
        List<String> data = new ArrayList<>();
        for (int i=0;i<100;i++){
            data.add("Item:"+i);
        }
        return data;
    }
    //
}
