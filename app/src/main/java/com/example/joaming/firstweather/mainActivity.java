package com.example.joaming.firstweather;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.joaming.firstweather.util.NetUtil;
import com.example.joaming.firstweather.bean.TodayWeather;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by joaming on 2017/9/20.
 */

public class mainActivity extends Activity implements View.OnClickListener,ViewPager.OnPageChangeListener{

    private static final int UPDATE_TODAY_WEATHER =1;

    private ImageView mUpdateBtn;
    private ImageView mCitySelect;

    private ViewPagerAdapter vpAdapter;
    private ViewPager vp;
    private List<View> views;

    private ImageView[] dots;
    private int[] ids={R.id.iv1,R.id.iv2};
    private TextView cityTv, timeTv, humidityTv, weekTv, pmDataTv, pmQualityTv,
            temperatureTv, climateTv, windTv, city_name_Tv,fengxiangTv;
    private ImageView weatherImg, pmImg;

//    private ImageView weather_imgW;
//    private TextView temperatureW,climateW;

    private  TextView[] week_next;
    private  ImageView[] imageView_next;
    private TextView[]  temperature_next;
    private TextView[]  climate_next;
    private  TextView[] wind_next;
    private  int[] wn={R.id.week_next1,R.id.week_next2,R.id.week_next3,R.id.week_next4,R.id.week_next5};
    private  int[] in={R.id.imageViewnext1,R.id.imageViewnext2,R.id.imageViewnext3,R.id.imageViewnext4,R.id.imageViewnext5};
    private int[]  tn={R.id.temperature_next1,R.id.temperature_next2,R.id.temperature_next3,R.id.temperature_next4,R.id.temperature_next5};
    private int[]  cn={R.id.climate_next1,R.id.climate_next2,R.id.climate_next3,R.id.climate_next4,R.id.climate_next5};
    private  int[]  wind_n={R.id.wind_next1,R.id.wind_next2,R.id.wind_next3,R.id.wind_next4,R.id.wind_next5};


   private Handler mHandler = new Handler(){
       public void handleMessage(android.os.Message msg){
           switch (msg.what){
               case UPDATE_TODAY_WEATHER:
                   updateTodayWeather((TodayWeather)msg.obj);
                   break;
               default:
                   break;
           }
       }
   };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_info);

        mUpdateBtn = (ImageView) findViewById(R.id.title_update_btn);
        mUpdateBtn.setOnClickListener(this);

        mCitySelect = (ImageView)findViewById(R.id.title_city_manager);
        mCitySelect.setOnClickListener(this);

        if (NetUtil.getNetworkState(this) != NetUtil.NETWORN_NONE) {
            Log.d("myWeather", "网络OK");
            Toast.makeText(mainActivity.this,"网络OK！", Toast.LENGTH_LONG).show();
        }else
        {
            Log.d("myWeather", "网络挂了");
            Toast.makeText(mainActivity.this,"网络挂了！", Toast.LENGTH_LONG).show();
        }
        initView();
        initViews();
        initDots();
    }
    void initDots(){
        dots = new ImageView[views.size()];
        for(int i=0;i<views.size();i++){
            dots[i]=(ImageView) findViewById(ids[i]);
        }
    }
    private void initViews() {
        LayoutInflater inflater = LayoutInflater.from(this);
        views = new ArrayList<View>();
        views.add(inflater.inflate(R.layout.detailsone, null));
        views.add(inflater.inflate(R.layout.detailstwo, null));
        vpAdapter = new ViewPagerAdapter(views, this);
        vp = (ViewPager) findViewById(R.id.vierpager);
        vp.setAdapter(vpAdapter);
        vp.setOnPageChangeListener(this);
        week_next = new TextView[5];
        imageView_next = new ImageView[5];
        temperature_next = new TextView[5];
        climate_next = new TextView[5];
        wind_next = new TextView[5];

        for (int i = 0; i < 3; i++) {
            week_next[i] = (TextView) views.get(0).findViewById(wn[i]);
            week_next[i].setText("N/A");
            imageView_next[i] = (ImageView) views.get(0).findViewById(in[i]);
            temperature_next[i] = (TextView) views.get(0).findViewById(tn[i]);
            temperature_next[i].setText("N/A");
            climate_next[i] = (TextView) views.get(0).findViewById(cn[i]);
            climate_next[i].setText("N/A");
            wind_next[i] = (TextView) views.get(0).findViewById(wind_n[i]);
            wind_next[i].setText("N/A");
        }
        week_next[3] = (TextView) views.get(1).findViewById(wn[3]);
        week_next[3].setText("N/A");
        imageView_next[3] = (ImageView) views.get(1).findViewById(in[3]);
        temperature_next[3] = (TextView) views.get(1).findViewById(tn[3]);
        temperature_next[3].setText("N/A");
        climate_next[3] = (TextView) views.get(1).findViewById(cn[3]);
        climate_next[3].setText("N/A");
        wind_next[3] = (TextView) views.get(1).findViewById(wind_n[3]);
        wind_next[3].setText("N/A");

        week_next[4] = (TextView) views.get(1).findViewById(wn[4]);
        week_next[4].setText("N/A");
        imageView_next[4] = (ImageView) views.get(1).findViewById(in[4]);
        temperature_next[4] = (TextView) views.get(1).findViewById(tn[4]);
        temperature_next[4].setText("N/A");
        climate_next[4] = (TextView) views.get(1).findViewById(cn[4]);
        climate_next[4].setText("N/A");
        wind_next[4] = (TextView) views.get(1).findViewById(wind_n[4]);
        wind_next[4].setText("N/A");

    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.title_city_manager) {
            Intent i = new Intent(this, SelectCity.class);
            //startActivity(i);
            startActivityForResult(i,1);
        }
        if (view.getId() == R.id.title_update_btn) {
            view.setVisibility(View.INVISIBLE);
            ProgressBar mUpadeprogress=(ProgressBar)findViewById(R.id.title_update_btn_progress);
            mUpadeprogress.setVisibility(mUpadeprogress.VISIBLE);
            SharedPreferences sharedPreferences = getSharedPreferences("config", MODE_PRIVATE);
            String cityCode = sharedPreferences.getString("main_city_code", "101010100");
            Log.d("myWeather", cityCode);


            if (NetUtil.getNetworkState(this) != NetUtil.NETWORN_NONE) {
                Log.d("myWeather", "网络OK");
                queryWeatherCode(cityCode);
            } else {
                Log.d("myWeather", "网络挂了");
                Toast.makeText(mainActivity.this, "网络挂了！", Toast.LENGTH_LONG).show();
            }
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            String newCityCode= data.getStringExtra("cityCode");
            Log.d("myWeather", "选择的城市代码为"+newCityCode);
            if (NetUtil.getNetworkState(this) != NetUtil.NETWORN_NONE) {
                Log.d("myWeather", "网络OK");
                queryWeatherCode(newCityCode);
            } else {
                Log.d("myWeather", "网络挂了");
                Toast.makeText(mainActivity.this, "网络挂了！", Toast.LENGTH_LONG).show();
            }
        }
    }


    /**
     *
     * @param cityCode
     */
    private void queryWeatherCode(String cityCode)  {
        final String address = "http://wthrcdn.etouch.cn/WeatherApi?citykey=" + cityCode;
        Log.d("myWeather", address);
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection con=null;
                TodayWeather todayWeather = null;
                try{
                    URL url = new URL(address);
                    con = (HttpURLConnection)url.openConnection();
                    con.setRequestMethod("GET");
                    con.setConnectTimeout(8000);
                    con.setReadTimeout(8000);
                    InputStream in = con.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String str;
                    while((str=reader.readLine()) != null){
                        response.append(str);
                        Log.d("myWeather", str);
                    }
                    String responseStr=response.toString();
                    Log.d("myWeather", responseStr);
                    todayWeather = parseXML(responseStr);
                    if (todayWeather != null) {
                        Log.d("myWeather", todayWeather.toString());
                    }
                    Message msg = new Message();
                    msg.what=UPDATE_TODAY_WEATHER;
                    msg.obj=todayWeather;
                    mHandler.sendMessage(msg);
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    if(con != null){
                        con.disconnect();
                    }
                }
            }
        }).start();
    }
    /**
     * 解析函数
     */
    private TodayWeather parseXML(String xmldate) {
        TodayWeather todayWeather = null;
        int fengxiangCount = 0;
        int fengliCount = 0;
        int dateCount = 0;
        int highCount = 0;
        int lowCount = 0;
        int typeCount = 0;
        try {
            XmlPullParserFactory fac = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = fac.newPullParser();
            xmlPullParser.setInput(new StringReader(xmldate));
            int eventType = xmlPullParser.getEventType();
            Log.d("myWeather", "parseXML");
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        if(xmlPullParser.getName().equals("resp")){
                            todayWeather =new TodayWeather();
                        }
                        if(todayWeather!=null){
                            if (xmlPullParser.getName().equals("city")) {
                                eventType = xmlPullParser.next();
                                todayWeather.setCity(xmlPullParser.getText());
                                Log.d("myWeather", "city:" + xmlPullParser.getText());
                            } else if (xmlPullParser.getName().equals("updatetime")) {
                                eventType = xmlPullParser.next();
                                todayWeather.setUpdatetime(xmlPullParser.getText());
                                Log.d("myWeather", "updatetime:" + xmlPullParser.getText());
                            } else if (xmlPullParser.getName().equals("shidu")) {
                                eventType = xmlPullParser.next();
                                todayWeather.setShidu(xmlPullParser.getText());
                                Log.d("myWeather", "shidu:    " + xmlPullParser.getText());
                            } else if (xmlPullParser.getName().equals("wendu")) {
                                eventType = xmlPullParser.next();
                                todayWeather.setWendu(xmlPullParser.getText());
                                Log.d("myWeather", "wendu:    " + xmlPullParser.getText());
                            } else if (xmlPullParser.getName().equals("pm25")) {
                                eventType = xmlPullParser.next();
                                todayWeather.setPm25(xmlPullParser.getText());
                                Log.d("myWeather", "pm25:    " + xmlPullParser.getText());
                            } else if (xmlPullParser.getName().equals("quality")) {
                                eventType = xmlPullParser.next();
                                todayWeather.setQuality(xmlPullParser.getText());
                                Log.d("myWeather", "quality:    " + xmlPullParser.getText());
                            } else if (xmlPullParser.getName().equals("fengxiang") && fengxiangCount == 0) {
                                eventType = xmlPullParser.next();
                                todayWeather.setFengxiang(xmlPullParser.getText());
                                Log.d("myWeather", "fengxiang:    " + xmlPullParser.getText());
                                fengxiangCount++;
                            } else if (xmlPullParser.getName().equals("fengli") && fengliCount == 0) {
                                eventType = xmlPullParser.next();
                                todayWeather.setFengli(xmlPullParser.getText());
                                Log.d("myWeather", "fengli:    " + xmlPullParser.getText());
                                fengliCount++;
                            } else if (xmlPullParser.getName().equals("date") && dateCount == 0) {
                                eventType = xmlPullParser.next();
                                todayWeather.setDate(xmlPullParser.getText());
                                Log.d("myWeather", "date:    " + xmlPullParser.getText());
                                dateCount++;
                            } else if (xmlPullParser.getName().equals("high") && highCount == 0) {
                                eventType = xmlPullParser.next();
                                todayWeather.setHigh(xmlPullParser.getText().substring(2).trim());
                                Log.d("myWeather", "high:    " + xmlPullParser.getText());
                                highCount++;
                            } else if (xmlPullParser.getName().equals("low") && lowCount == 0) {
                                eventType = xmlPullParser.next();
                                todayWeather.setLow(xmlPullParser.getText().substring(2).trim());
                                Log.d("myWeather", "low:    " + xmlPullParser.getText());
                                lowCount++;
                            } else if (xmlPullParser.getName().equals("type") && typeCount == 0) {
                                eventType = xmlPullParser.next();
                                todayWeather.setType(xmlPullParser.getText());
                                Log.d("myWeather", "type:    " + xmlPullParser.getText());
                                typeCount++;
                            }//更新昨天信息
                            else if (xmlPullParser.getName().equals("date_1") ) {
                                eventType = xmlPullParser.next();
                                todayWeather.setWeekyesterday(xmlPullParser.getText());
                                Log.d("myWeather", "Weekyesterday:    " + xmlPullParser.getText());
                            }
                            else if (xmlPullParser.getName().equals("high_1") ) {
                                eventType = xmlPullParser.next();
                                todayWeather.setHighyesterday(xmlPullParser.getText());
                                Log.d("myWeather", "Highyesterday:    " + xmlPullParser.getText());
                            }
                            else if (xmlPullParser.getName().equals("low_1") ) {
                                eventType = xmlPullParser.next();
                                todayWeather.setLowyesterday(xmlPullParser.getText());
                                Log.d("myWeather", "Lowyesterday:    " + xmlPullParser.getText());
                            }
                            else if (xmlPullParser.getName().equals("type_1") ) {
                                eventType = xmlPullParser.next();
                                todayWeather.setClimateyesterday(xmlPullParser.getText());
                                Log.d("myWeather", "Climateyesterday:    " + xmlPullParser.getText());
                            }
                            else if (xmlPullParser.getName().equals("fx_1") ) {
                                eventType = xmlPullParser.next();
                                todayWeather.setWindyesterday(xmlPullParser.getText());
                                Log.d("myWeather", "Windyesterday:    " + xmlPullParser.getText());
                            }
                            //未来第一天信息
                            else if (xmlPullParser.getName().equals("date") && dateCount == 1) {
                                eventType = xmlPullParser.next();
                                todayWeather.setWeek_next2(xmlPullParser.getText());
                                Log.d("myWeather", "Week_next2:    " + xmlPullParser.getText());
                                dateCount++;
                            }
                            else if (xmlPullParser.getName().equals("high")&& highCount == 1)  {
                                eventType = xmlPullParser.next();
                                todayWeather.setHigh2(xmlPullParser.getText());
                                highCount++;
                            }
                            else if (xmlPullParser.getName().equals("low")&& lowCount == 1 ) {
                                eventType = xmlPullParser.next();
                                todayWeather.setLow2(xmlPullParser.getText());
                                Log.d("myWeather", "setLow2:    " + xmlPullParser.getText());
                                lowCount++;
                            }
                            else if (xmlPullParser.getName().equals("type")&&typeCount==1 ) {
                                eventType = xmlPullParser.next();
                                todayWeather.setClimate_next2(xmlPullParser.getText());
                                Log.d("myWeather", "Climate_next2:    " + xmlPullParser.getText());
                                typeCount++;
                            }
                            else if (xmlPullParser.getName().equals("fengxiang")&& fengxiangCount == 1) {
                                eventType = xmlPullParser.next();
                                todayWeather.setWind_next2(xmlPullParser.getText());
                                Log.d("myWeather", "Wind_next2:    " + xmlPullParser.getText());
                                fengxiangCount++;
                            }
                            //未来第二天信息
                            else if (xmlPullParser.getName().equals("date") && dateCount == 2) {
                                eventType = xmlPullParser.next();
                                todayWeather.setWeek_next3(xmlPullParser.getText());
                                Log.d("myWeather", "Week_next3:    " + xmlPullParser.getText());
                                dateCount++;
                            }
                            else if (xmlPullParser.getName().equals("high")&& highCount == 2)  {
                                eventType = xmlPullParser.next();
                                todayWeather.setHigh3(xmlPullParser.getText());
                                highCount++;
                            }
                            else if (xmlPullParser.getName().equals("low")&& lowCount == 2 ) {
                                eventType = xmlPullParser.next();
                                todayWeather.setLow3(xmlPullParser.getText());
                                Log.d("myWeather", "setLow3:    " + xmlPullParser.getText());
                                lowCount++;
                            }
                            else if (xmlPullParser.getName().equals("type")&&typeCount==2) {
                                eventType = xmlPullParser.next();
                                todayWeather.setClimate_next3(xmlPullParser.getText());
                                Log.d("myWeather", "Climate_next3:    " + xmlPullParser.getText());
                                typeCount++;
                            }
                            else if (xmlPullParser.getName().equals("fengxiang")&& fengxiangCount == 2) {
                                eventType = xmlPullParser.next();
                                todayWeather.setWind_next3(xmlPullParser.getText());
                                Log.d("myWeather", "Wind_next3:    " + xmlPullParser.getText());
                                fengxiangCount++;
                            }
                            //未来第三天信息
                            else if (xmlPullParser.getName().equals("date") && dateCount == 3) {
                                eventType = xmlPullParser.next();
                                todayWeather.setWeek_next4(xmlPullParser.getText());
                                Log.d("myWeather", "Week_next4:    " + xmlPullParser.getText());
                                dateCount++;
                            }
                            else if (xmlPullParser.getName().equals("high")&& highCount == 3)  {
                                eventType = xmlPullParser.next();
                                todayWeather.setHigh4(xmlPullParser.getText());
                                highCount++;
                            }
                            else if (xmlPullParser.getName().equals("low")&& lowCount == 3) {
                                eventType = xmlPullParser.next();
                                todayWeather.setLow4(xmlPullParser.getText());
                                Log.d("myWeather", "setLow4:    " + xmlPullParser.getText());
                                lowCount++;
                            }
                            else if (xmlPullParser.getName().equals("type")&&typeCount==3) {
                                eventType = xmlPullParser.next();
                                todayWeather.setClimate_next4(xmlPullParser.getText());
                                Log.d("myWeather", "Climate_next4:    " + xmlPullParser.getText());
                                typeCount++;
                            }
                            else if (xmlPullParser.getName().equals("fengxiang")&& fengxiangCount == 3) {
                                eventType = xmlPullParser.next();
                                todayWeather.setWind_next4(xmlPullParser.getText());
                                Log.d("myWeather", "Wind_next4:    " + xmlPullParser.getText());
                                fengxiangCount++;
                            }
                            //未来第四天信息
                            else if (xmlPullParser.getName().equals("date") && dateCount == 4) {
                                eventType = xmlPullParser.next();
                                todayWeather.setWeek_next5(xmlPullParser.getText());
                                Log.d("myWeather", "Week_next5:    " + xmlPullParser.getText());
                                dateCount++;
                            }
                            else if (xmlPullParser.getName().equals("high")&& highCount == 4)  {
                                eventType = xmlPullParser.next();
                                todayWeather.setHigh5(xmlPullParser.getText());
                                highCount++;
                            }
                            else if (xmlPullParser.getName().equals("low")&& lowCount == 4) {
                                eventType = xmlPullParser.next();
                                todayWeather.setLow5(xmlPullParser.getText());
                                Log.d("myWeather", "setLow5:    " + xmlPullParser.getText());
                                lowCount++;
                            }
                            else if (xmlPullParser.getName().equals("type")&&typeCount==4) {
                                eventType = xmlPullParser.next();
                                todayWeather.setClimate_next5(xmlPullParser.getText());
                                Log.d("myWeather", "Climate_next5:    " + xmlPullParser.getText());
                                typeCount++;
                            }
                            else if (xmlPullParser.getName().equals("fengxiang")&& fengxiangCount == 4) {
                                eventType = xmlPullParser.next();
                                todayWeather.setWind_next5(xmlPullParser.getText());
                                Log.d("myWeather", "Wind_next5:    " + xmlPullParser.getText());
                                fengxiangCount++;
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                }
                eventType = xmlPullParser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return todayWeather;
    }
    void initView(){
        city_name_Tv = (TextView) findViewById(R.id.title_city_name);
        cityTv = (TextView) findViewById(R.id.city);
        timeTv = (TextView) findViewById(R.id.time);
        humidityTv = (TextView) findViewById(R.id.humidity);
        weekTv = (TextView) findViewById(R.id.week_today);
        pmDataTv = (TextView) findViewById(R.id.pm2_5_data);
        pmQualityTv = (TextView) findViewById(R.id.pm2_5_quality);
        pmImg = (ImageView) findViewById(R.id.pm2_5_img);
        temperatureTv = (TextView) findViewById(R.id.temperature);
        climateTv = (TextView) findViewById(R.id.climate);
        windTv = (TextView) findViewById(R.id.wind);
        weatherImg = (ImageView) findViewById(R.id.weather_img);
        fengxiangTv=(TextView)findViewById(R.id.fengxiang);



        city_name_Tv.setText("N/A");
        cityTv.setText("N/A");
        timeTv.setText("N/A");
        humidityTv.setText("N/A");
        pmDataTv.setText("N/A");
        pmQualityTv.setText("N/A");
        weekTv.setText("N/A");
        temperatureTv.setText("N/A");
        climateTv.setText("N/A");
        windTv.setText("N/A");
        fengxiangTv.setText("N/A");
        SharedPreferences settings = (SharedPreferences)getSharedPreferences("config", MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("city_name","北京");
        editor.putString("main_city_code", "101010100");
        editor.commit();
    }
    void updateTodayWeather(TodayWeather todayWeather){






        city_name_Tv.setText(todayWeather.getCity()+"天气");
        cityTv.setText(todayWeather.getCity());
        timeTv.setText(todayWeather.getUpdatetime()+"发布");
        humidityTv.setText("湿度："+todayWeather.getShidu());
        pmDataTv.setText(todayWeather.getPm25());

        //跟新昨天日期
        week_next[0].setText(todayWeather.getWeekyesterday());
        temperature_next[0].setText(todayWeather.getHighyesterday()+"~"+todayWeather.getLowyesterday());
        climate_next[0].setText(todayWeather.getClimateyesterday());
        wind_next[0].setText(todayWeather.getWindyesterday());
        switch (todayWeather.getClimateyesterday()){
            case "暴雪":
                imageView_next[0].setImageResource(R.drawable.biz_plugin_weather_baoxue);
                break;
            case "暴雨":
                imageView_next[0].setImageResource(R.drawable.biz_plugin_weather_baoyu);
                break;
            case "大暴雨":
                imageView_next[0].setImageResource(R.drawable.biz_plugin_weather_dabaoyu);
                break;
            case "大雪":
                imageView_next[0].setImageResource(R.drawable.biz_plugin_weather_daxue);
                break;
            case "大雨":
                imageView_next[0].setImageResource(R.drawable.biz_plugin_weather_dayu);
                break;
            case "多云":
                imageView_next[0].setImageResource(R.drawable.biz_plugin_weather_duoyun);
                break;
            case "雷阵雨":
                imageView_next[0].setImageResource(R.drawable.biz_plugin_weather_leizhenyu);
                break;
            case "雷阵雨冰雹":
                imageView_next[0].setImageResource(R.drawable.biz_plugin_weather_leizhenyubingbao);
                break;
            case "晴":
                imageView_next[0].setImageResource(R.drawable.biz_plugin_weather_qing);
                break;
            case "沙尘暴":
                imageView_next[0].setImageResource(R.drawable.biz_plugin_weather_shachenbao);
                break;
            case "特大暴雨":
                imageView_next[0].setImageResource(R.drawable.biz_plugin_weather_tedabaoyu);
                break;
            case "雾":
                imageView_next[0].setImageResource(R.drawable.biz_plugin_weather_wu);
                break;
            case "小雪":
                imageView_next[0].setImageResource(R.drawable.biz_plugin_weather_xiaoxue);
                break;
            case "小雨":
                imageView_next[0].setImageResource(R.drawable.biz_plugin_weather_xiaoyu);
                break;
            case "阴":
                imageView_next[0].setImageResource(R.drawable.biz_plugin_weather_yin);
                break;
            case "雨夹雪":
                imageView_next[0].setImageResource(R.drawable.biz_plugin_weather_yujiaxue);
                break;
            case "阵雪":
                imageView_next[0].setImageResource(R.drawable.biz_plugin_weather_zhenxue);
                break;
            case "阵雨":
                imageView_next[0].setImageResource(R.drawable.biz_plugin_weather_zhenyu);
                break;
            case "中雪":
                imageView_next[0].setImageResource(R.drawable.biz_plugin_weather_zhongxue);
                break;
            case "中雨":
                imageView_next[0].setImageResource(R.drawable.biz_plugin_weather_zhenyu);
                break;
            default:
                break;

        }
        //跟新未来第一天日期
        week_next[1].setText(todayWeather.getWeek_next2());
        temperature_next[1].setText(todayWeather.getHigh2()+"~"+todayWeather.getLow2());
        climate_next[1].setText(todayWeather.getClimate_next2());
        wind_next[1].setText(todayWeather.getWind_next2());
        switch (todayWeather.getClimate_next2()){
            case "暴雪":
                imageView_next[1].setImageResource(R.drawable.biz_plugin_weather_baoxue);
                break;
            case "暴雨":
                imageView_next[1].setImageResource(R.drawable.biz_plugin_weather_baoyu);
                break;
            case "大暴雨":
                imageView_next[1].setImageResource(R.drawable.biz_plugin_weather_dabaoyu);
                break;
            case "大雪":
                imageView_next[1].setImageResource(R.drawable.biz_plugin_weather_daxue);
                break;
            case "大雨":
                imageView_next[1].setImageResource(R.drawable.biz_plugin_weather_dayu);
                break;
            case "多云":
                imageView_next[1].setImageResource(R.drawable.biz_plugin_weather_duoyun);
                break;
            case "雷阵雨":
                imageView_next[1].setImageResource(R.drawable.biz_plugin_weather_leizhenyu);
                break;
            case "雷阵雨冰雹":
                imageView_next[1].setImageResource(R.drawable.biz_plugin_weather_leizhenyubingbao);
                break;
            case "晴":
                imageView_next[1].setImageResource(R.drawable.biz_plugin_weather_qing);
                break;
            case "沙尘暴":
                imageView_next[1].setImageResource(R.drawable.biz_plugin_weather_shachenbao);
                break;
            case "特大暴雨":
                imageView_next[1].setImageResource(R.drawable.biz_plugin_weather_tedabaoyu);
                break;
            case "雾":
                imageView_next[1].setImageResource(R.drawable.biz_plugin_weather_wu);
                break;
            case "小雪":
                imageView_next[1].setImageResource(R.drawable.biz_plugin_weather_xiaoxue);
                break;
            case "小雨":
                imageView_next[1].setImageResource(R.drawable.biz_plugin_weather_xiaoyu);
                break;
            case "阴":
                imageView_next[1].setImageResource(R.drawable.biz_plugin_weather_yin);
                break;
            case "雨夹雪":
                imageView_next[1].setImageResource(R.drawable.biz_plugin_weather_yujiaxue);
                break;
            case "阵雪":
                imageView_next[1].setImageResource(R.drawable.biz_plugin_weather_zhenxue);
                break;
            case "阵雨":
                imageView_next[1].setImageResource(R.drawable.biz_plugin_weather_zhenyu);
                break;
            case "中雪":
                imageView_next[1].setImageResource(R.drawable.biz_plugin_weather_zhongxue);
                break;
            case "中雨":
                imageView_next[1].setImageResource(R.drawable.biz_plugin_weather_zhenyu);
                break;
            default:
                break;

        }
        //跟新未来第二天日期
        week_next[2].setText(todayWeather.getWeek_next3());
        temperature_next[2].setText(todayWeather.getHigh3()+"~"+todayWeather.getLow3());
        climate_next[2].setText(todayWeather.getClimate_next3());
        wind_next[2].setText(todayWeather.getWind_next3());
        switch (todayWeather.getClimate_next3()){
            case "暴雪":
                imageView_next[2].setImageResource(R.drawable.biz_plugin_weather_baoxue);
                break;
            case "暴雨":
                imageView_next[2].setImageResource(R.drawable.biz_plugin_weather_baoyu);
                break;
            case "大暴雨":
                imageView_next[2].setImageResource(R.drawable.biz_plugin_weather_dabaoyu);
                break;
            case "大雪":
                imageView_next[2].setImageResource(R.drawable.biz_plugin_weather_daxue);
                break;
            case "大雨":
                imageView_next[2].setImageResource(R.drawable.biz_plugin_weather_dayu);
                break;
            case "多云":
                imageView_next[2].setImageResource(R.drawable.biz_plugin_weather_duoyun);
                break;
            case "雷阵雨":
                imageView_next[2].setImageResource(R.drawable.biz_plugin_weather_leizhenyu);
                break;
            case "雷阵雨冰雹":
                imageView_next[2].setImageResource(R.drawable.biz_plugin_weather_leizhenyubingbao);
                break;
            case "晴":
                imageView_next[2].setImageResource(R.drawable.biz_plugin_weather_qing);
                break;
            case "沙尘暴":
                imageView_next[2].setImageResource(R.drawable.biz_plugin_weather_shachenbao);
                break;
            case "特大暴雨":
                imageView_next[2].setImageResource(R.drawable.biz_plugin_weather_tedabaoyu);
                break;
            case "雾":
                imageView_next[2].setImageResource(R.drawable.biz_plugin_weather_wu);
                break;
            case "小雪":
                imageView_next[2].setImageResource(R.drawable.biz_plugin_weather_xiaoxue);
                break;
            case "小雨":
                imageView_next[2].setImageResource(R.drawable.biz_plugin_weather_xiaoyu);
                break;
            case "阴":
                imageView_next[2].setImageResource(R.drawable.biz_plugin_weather_yin);
                break;
            case "雨夹雪":
                imageView_next[2].setImageResource(R.drawable.biz_plugin_weather_yujiaxue);
                break;
            case "阵雪":
                imageView_next[2].setImageResource(R.drawable.biz_plugin_weather_zhenxue);
                break;
            case "阵雨":
                imageView_next[2].setImageResource(R.drawable.biz_plugin_weather_zhenyu);
                break;
            case "中雪":
                imageView_next[2].setImageResource(R.drawable.biz_plugin_weather_zhongxue);
                break;
            case "中雨":
                imageView_next[2].setImageResource(R.drawable.biz_plugin_weather_zhenyu);
                break;
            default:
                break;

        }
        //跟新未来第三天日期
        week_next[3].setText(todayWeather.getWeek_next4());
        temperature_next[3].setText(todayWeather.getHigh4()+"~"+todayWeather.getLow4());
        climate_next[3].setText(todayWeather.getClimate_next4());
        wind_next[3].setText(todayWeather.getWind_next4());
        switch (todayWeather.getClimate_next4()){
            case "暴雪":
                imageView_next[3].setImageResource(R.drawable.biz_plugin_weather_baoxue);
                break;
            case "暴雨":
                imageView_next[3].setImageResource(R.drawable.biz_plugin_weather_baoyu);
                break;
            case "大暴雨":
                imageView_next[3].setImageResource(R.drawable.biz_plugin_weather_dabaoyu);
                break;
            case "大雪":
                imageView_next[3].setImageResource(R.drawable.biz_plugin_weather_daxue);
                break;
            case "大雨":
                imageView_next[3].setImageResource(R.drawable.biz_plugin_weather_dayu);
                break;
            case "多云":
                imageView_next[3].setImageResource(R.drawable.biz_plugin_weather_duoyun);
                break;
            case "雷阵雨":
                imageView_next[3].setImageResource(R.drawable.biz_plugin_weather_leizhenyu);
                break;
            case "雷阵雨冰雹":
                imageView_next[3].setImageResource(R.drawable.biz_plugin_weather_leizhenyubingbao);
                break;
            case "晴":
                imageView_next[3].setImageResource(R.drawable.biz_plugin_weather_qing);
                break;
            case "沙尘暴":
                imageView_next[3].setImageResource(R.drawable.biz_plugin_weather_shachenbao);
                break;
            case "特大暴雨":
                imageView_next[3].setImageResource(R.drawable.biz_plugin_weather_tedabaoyu);
                break;
            case "雾":
                imageView_next[3].setImageResource(R.drawable.biz_plugin_weather_wu);
                break;
            case "小雪":
                imageView_next[3].setImageResource(R.drawable.biz_plugin_weather_xiaoxue);
                break;
            case "小雨":
                imageView_next[3].setImageResource(R.drawable.biz_plugin_weather_xiaoyu);
                break;
            case "阴":
                imageView_next[3].setImageResource(R.drawable.biz_plugin_weather_yin);
                break;
            case "雨夹雪":
                imageView_next[3].setImageResource(R.drawable.biz_plugin_weather_yujiaxue);
                break;
            case "阵雪":
                imageView_next[3].setImageResource(R.drawable.biz_plugin_weather_zhenxue);
                break;
            case "阵雨":
                imageView_next[3].setImageResource(R.drawable.biz_plugin_weather_zhenyu);
                break;
            case "中雪":
                imageView_next[3].setImageResource(R.drawable.biz_plugin_weather_zhongxue);
                break;
            case "中雨":
                imageView_next[3].setImageResource(R.drawable.biz_plugin_weather_zhenyu);
                break;
            default:
                break;

        }
        //跟新未来第四天日期
        week_next[4].setText(todayWeather.getWeek_next5());
        temperature_next[4].setText(todayWeather.getHigh5()+"~"+todayWeather.getLow5());
        climate_next[4].setText(todayWeather.getClimate_next5());
        wind_next[4].setText(todayWeather.getWind_next5());
        switch (todayWeather.getClimate_next5()){
            case "暴雪":
                imageView_next[4].setImageResource(R.drawable.biz_plugin_weather_baoxue);
                break;
            case "暴雨":
                imageView_next[4].setImageResource(R.drawable.biz_plugin_weather_baoyu);
                break;
            case "大暴雨":
                imageView_next[4].setImageResource(R.drawable.biz_plugin_weather_dabaoyu);
                break;
            case "大雪":
                imageView_next[4].setImageResource(R.drawable.biz_plugin_weather_daxue);
                break;
            case "大雨":
                imageView_next[4].setImageResource(R.drawable.biz_plugin_weather_dayu);
                break;
            case "多云":
                imageView_next[4].setImageResource(R.drawable.biz_plugin_weather_duoyun);
                break;
            case "雷阵雨":
                imageView_next[4].setImageResource(R.drawable.biz_plugin_weather_leizhenyu);
                break;
            case "雷阵雨冰雹":
                imageView_next[4].setImageResource(R.drawable.biz_plugin_weather_leizhenyubingbao);
                break;
            case "晴":
                imageView_next[4].setImageResource(R.drawable.biz_plugin_weather_qing);
                break;
            case "沙尘暴":
                imageView_next[4].setImageResource(R.drawable.biz_plugin_weather_shachenbao);
                break;
            case "特大暴雨":
                imageView_next[4].setImageResource(R.drawable.biz_plugin_weather_tedabaoyu);
                break;
            case "雾":
                imageView_next[4].setImageResource(R.drawable.biz_plugin_weather_wu);
                break;
            case "小雪":
                imageView_next[4].setImageResource(R.drawable.biz_plugin_weather_xiaoxue);
                break;
            case "小雨":
                imageView_next[4].setImageResource(R.drawable.biz_plugin_weather_xiaoyu);
                break;
            case "阴":
                imageView_next[4].setImageResource(R.drawable.biz_plugin_weather_yin);
                break;
            case "雨夹雪":
                imageView_next[4].setImageResource(R.drawable.biz_plugin_weather_yujiaxue);
                break;
            case "阵雪":
                imageView_next[4].setImageResource(R.drawable.biz_plugin_weather_zhenxue);
                break;
            case "阵雨":
                imageView_next[4].setImageResource(R.drawable.biz_plugin_weather_zhenyu);
                break;
            case "中雪":
                imageView_next[4].setImageResource(R.drawable.biz_plugin_weather_zhongxue);
                break;
            case "中雨":
                imageView_next[4].setImageResource(R.drawable.biz_plugin_weather_zhenyu);
                break;
            default:
                break;

        }


        SharedPreferences settings = (SharedPreferences)getSharedPreferences("config", MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        String ss=(String)city_name_Tv.getText();
        editor.putString("city_name",ss);
        editor.commit();
        try{
            int pmDate=Integer.parseInt(todayWeather.getPm25());
            if(pmDate>=0&&pmDate<=50){
                pmImg.setImageResource(R.drawable.biz_plugin_weather_0_50);
            }else if(pmDate>50&&pmDate<=100){
                pmImg.setImageResource(R.drawable.biz_plugin_weather_51_100);
            }else if(pmDate>100&&pmDate<=150){
                pmImg.setImageResource(R.drawable.biz_plugin_weather_101_150);
            }else if(pmDate>150&&pmDate<=200){
                pmImg.setImageResource(R.drawable.biz_plugin_weather_151_200);
            }else if(pmDate>200&&pmDate<=300){
                pmImg.setImageResource(R.drawable.biz_plugin_weather_201_300);
            }else if(pmDate>300){
                pmImg.setImageResource(R.drawable.biz_plugin_weather_greater_300);
            }


        }catch(NumberFormatException e){
            e.printStackTrace();
        }
        switch (todayWeather.getType()){
            case "暴雪":
                weatherImg.setImageResource(R.drawable.biz_plugin_weather_baoxue);
//                weather_imgW.setImageResource(R.drawable.biz_plugin_weather_baoxue);
                break;
            case "暴雨":
                weatherImg.setImageResource(R.drawable.biz_plugin_weather_baoyu);
//                weather_imgW.setImageResource(R.drawable.biz_plugin_weather_baoyu);
                break;
            case "大暴雨":
                weatherImg.setImageResource(R.drawable.biz_plugin_weather_dabaoyu);
//                weather_imgW.setImageResource(R.drawable.biz_plugin_weather_dabaoyu);
                break;
            case "大雪":
                weatherImg.setImageResource(R.drawable.biz_plugin_weather_daxue);
//                weather_imgW.setImageResource(R.drawable.biz_plugin_weather_daxue);
                break;
            case "大雨":
                weatherImg.setImageResource(R.drawable.biz_plugin_weather_dayu);
//                weather_imgW.setImageResource(R.drawable.biz_plugin_weather_dayu);
                break;
            case "多云":
                weatherImg.setImageResource(R.drawable.biz_plugin_weather_duoyun);
//                weather_imgW.setImageResource(R.drawable.biz_plugin_weather_duoyun);

                break;
            case "雷阵雨":
                weatherImg.setImageResource(R.drawable.biz_plugin_weather_leizhenyu);
//                weather_imgW.setImageResource(R.drawable.biz_plugin_weather_leizhenyu);

                break;
            case "雷阵雨冰雹":
                weatherImg.setImageResource(R.drawable.biz_plugin_weather_leizhenyubingbao);
//                weather_imgW.setImageResource(R.drawable.biz_plugin_weather_leizhenyubingbao);

                break;
            case "晴":
                weatherImg.setImageResource(R.drawable.biz_plugin_weather_qing);
//                weather_imgW.setImageResource(R.drawable.biz_plugin_weather_qing);

                break;
            case "沙尘暴":
                weatherImg.setImageResource(R.drawable.biz_plugin_weather_shachenbao);
//                weather_imgW.setImageResource(R.drawable.biz_plugin_weather_shachenbao);

                break;
            case "特大暴雨":
                weatherImg.setImageResource(R.drawable.biz_plugin_weather_tedabaoyu);
//                weather_imgW.setImageResource(R.drawable.biz_plugin_weather_tedabaoyu);

                break;
            case "雾":
                weatherImg.setImageResource(R.drawable.biz_plugin_weather_wu);
//                weather_imgW.setImageResource(R.drawable.biz_plugin_weather_wu);

                break;
            case "小雪":
                weatherImg.setImageResource(R.drawable.biz_plugin_weather_xiaoxue);
//                weather_imgW.setImageResource(R.drawable.biz_plugin_weather_xiaoxue);

                break;
            case "小雨":
                weatherImg.setImageResource(R.drawable.biz_plugin_weather_xiaoyu);
//                weather_imgW.setImageResource(R.drawable.biz_plugin_weather_xiaoyu);

                break;
            case "阴":
                weatherImg.setImageResource(R.drawable.biz_plugin_weather_yin);
//                weather_imgW.setImageResource(R.drawable.biz_plugin_weather_yin);

                break;
            case "雨夹雪":
                weatherImg.setImageResource(R.drawable.biz_plugin_weather_yujiaxue);
//                weather_imgW.setImageResource(R.drawable.biz_plugin_weather_yujiaxue);

                break;
            case "阵雪":
                weatherImg.setImageResource(R.drawable.biz_plugin_weather_zhenxue);
//                weather_imgW.setImageResource(R.drawable.biz_plugin_weather_zhenxue);

                break;
            case "阵雨":
                weatherImg.setImageResource(R.drawable.biz_plugin_weather_zhenyu);
//                weather_imgW.setImageResource(R.drawable.biz_plugin_weather_zhenyu);

                break;
            case "中雪":
                weatherImg.setImageResource(R.drawable.biz_plugin_weather_zhongxue);
//                weather_imgW.setImageResource(R.drawable.biz_plugin_weather_zhongxue);

                break;
            case "中雨":
                weatherImg.setImageResource(R.drawable.biz_plugin_weather_zhenyu);
//                weather_imgW.setImageResource(R.drawable.biz_plugin_weather_zhenyu);

                break;
            default:
                break;

        }
        pmQualityTv.setText(todayWeather.getQuality());
        weekTv.setText(todayWeather.getDate());
        temperatureTv.setText(todayWeather.getHigh()+"~"+todayWeather.getLow());
//        temperatureW.setText(todayWeather.getHigh()+"~"+todayWeather.getLow());
        SharedPreferences settings1 = (SharedPreferences)getSharedPreferences("config", MODE_PRIVATE);
        SharedPreferences.Editor editor1 = settings.edit();
        editor.putString("temperatureTv",todayWeather.getHigh()+"~"+todayWeather.getLow());
        editor.commit();

        climateTv.setText(todayWeather.getType());
//        climateW.setText(todayWeather.getType());

        windTv.setText("风力:"+todayWeather.getFengli());
        fengxiangTv.setText(todayWeather.getFengxiang());
        ProgressBar mUpadeprogress=(ProgressBar)findViewById(R.id.title_update_btn_progress);
        mUpadeprogress.setVisibility(mUpadeprogress.INVISIBLE);
        View view1=(View)findViewById(R.id.title_update_btn);
        view1.setVisibility(view1.VISIBLE);
        Toast.makeText(mainActivity.this,"更新成功！",Toast.LENGTH_SHORT).show();



    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for(int a=0;a<ids.length;a++){
            if(a==position){
                dots[a].setImageResource(R.drawable.page_indicator_focused);
            }else{
                dots[a].setImageResource(R.drawable.page_indicator_unfocused);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void onBroadcastTtent(View v){
        Intent intent=new Intent();
        intent.setAction("APPWIDGET_UPDATE");
        sendBroadcast(intent);
    }

}














