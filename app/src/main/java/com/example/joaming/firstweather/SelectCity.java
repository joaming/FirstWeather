package com.example.joaming.firstweather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.joaming.firstweather.app.MyApplication;
import com.example.joaming.firstweather.bean.City;

import java.util.List;

public class SelectCity extends Activity implements View.OnClickListener {
    private List<City> mCityList;
    private ImageView mBackBtn;
    private ListView mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_city);
        initViews();
        mBackBtn = (ImageView)findViewById(R.id.title_back);
        mBackBtn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.title_back:
                Intent i=new Intent();
                i.putExtra("cityCode","101160101");
                setResult(RESULT_OK,i);
                finish();
                break;
            default:
                break;
        }
    }
    private void initViews(){
        mBackBtn=(ImageView)findViewById(R.id.title_back);
        mBackBtn.setOnClickListener(this);
        mList=(ListView)findViewById(R.id.title_list);
        MyApplication myApplication=(MyApplication)getApplication();
        mCityList=myApplication.getmCityList();
        int size=mCityList.size();
        String[] data=new String[size];
        for(int i=0;i<mCityList.size();i++){
            City item=mCityList.get(i);
            data[i]=item.getAllFristPY()+"  "+item.getCity()+"  "+item.getNumber();
        }

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(
                SelectCity.this,android.R.layout.simple_list_item_1,data);
        mList.setAdapter(adapter);
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
             @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                City city=mCityList.get(position);
                Intent i =new Intent();
                i.putExtra("cityCode",city.getNumber());
                setResult(RESULT_OK,i);
                finish();
            }
        });
    }

}













