package com.example.joaming.firstweather.bean;

/**
 * Created by joaming on 2017/10/13.
 */

public class TodayWeather {
    private String city;
    private String updatetime;
    private String wendu;
    private String shidu;
    private String pm25;
    private String quality;
    private String fengxiang;
    private String fengli;
    private String date;
    private String high;
    private String low;
    private String type;

    //昨天信息
    private String weekyesterday;
    private String temperatureyesterday;
    private String climateyesterday;
    private String windyesterday;
    private String qualityyesterday;
    private String highyesterday;
    private String lowyesterday;



    //未来一天信息
    private String  week_next2;
    private String temperature_next2;
    private String climate_next2;
    private String wind_next2;
    private String imageViewnext2;
    private String high2;
    private String low2;
    //未来两天信息
    private String week_next3;
    private String temperature_next3;
    private String climate_next3;
    private String wind_next3;
    private String imageViewnext3;
    private String high3;
    private String low3;
    //未来三天信息
    private String week_next4;
    private String temperature_next4;
    private String climate_next4;
    private String wind_next4;
    private String imageViewnext4;
    private String high4;
    private String low4;
    //未来四天信息
    private String week_next5;
    private String temperature_next5;
    private String climate_next5;
    private String wind_next5;
    private String imageViewnext5;
    private String high5;

    public void setHighyesterday(String highyesterday) {
        this.highyesterday = highyesterday;
    }

    public void setLowyesterday(String lowyesterday) {
        this.lowyesterday = lowyesterday;
    }

    public void setHigh2(String high2) {
        this.high2 = high2;
    }

    public void setLow2(String low2) {
        this.low2 = low2;
    }

    public void setHigh3(String high3) {
        this.high3 = high3;
    }

    public void setLow3(String low3) {
        this.low3 = low3;
    }

    public void setHigh4(String high4) {
        this.high4 = high4;
    }

    public void setLow4(String low4) {
        this.low4 = low4;
    }

    public void setHigh5(String high5) {
        this.high5 = high5;
    }

    public void setLow5(String low5) {
        this.low5 = low5;
    }

    public String getHighyesterday() {
        return highyesterday;
    }

    public String getLowyesterday() {
        return lowyesterday;
    }

    public String getHigh2() {
        return high2;
    }

    public String getLow2() {
        return low2;
    }

    public String getHigh3() {
        return high3;
    }

    public String getLow3() {
        return low3;
    }

    public String getHigh4() {
        return high4;
    }

    public String getLow4() {
        return low4;
    }

    public String getHigh5() {
        return high5;
    }

    @Override
    public String toString() {
        return "TodayWeather{" +
                "city='" + city + '\'' +
                ", updatetime='" + updatetime + '\'' +
                ", wendu='" + wendu + '\'' +
                ", shidu='" + shidu + '\'' +
                ", pm25='" + pm25 + '\'' +
                ", quality='" + quality + '\'' +
                ", fengxiang='" + fengxiang + '\'' +
                ", fengli='" + fengli + '\'' +
                ", date='" + date + '\'' +
                ", high='" + high + '\'' +
                ", low='" + low + '\'' +
                ", type='" + type + '\'' +
                ", weekyesterday='" + weekyesterday + '\'' +
                ", temperatureyesterday='" + temperatureyesterday + '\'' +
                ", climateyesterday='" + climateyesterday + '\'' +
                ", windyesterday='" + windyesterday + '\'' +
                ", qualityyesterday='" + qualityyesterday + '\'' +
                ", highyesterday='" + highyesterday + '\'' +
                ", lowyesterday='" + lowyesterday + '\'' +
                ", week_next2='" + week_next2 + '\'' +
                ", temperature_next2='" + temperature_next2 + '\'' +
                ", climate_next2='" + climate_next2 + '\'' +
                ", wind_next2='" + wind_next2 + '\'' +
                ", imageViewnext2='" + imageViewnext2 + '\'' +
                ", high2='" + high2 + '\'' +
                ", low2='" + low2 + '\'' +
                ", week_next3='" + week_next3 + '\'' +
                ", temperature_next3='" + temperature_next3 + '\'' +
                ", climate_next3='" + climate_next3 + '\'' +
                ", wind_next3='" + wind_next3 + '\'' +
                ", imageViewnext3='" + imageViewnext3 + '\'' +
                ", high3='" + high3 + '\'' +
                ", low3='" + low3 + '\'' +
                ", week_next4='" + week_next4 + '\'' +
                ", temperature_next4='" + temperature_next4 + '\'' +
                ", climate_next4='" + climate_next4 + '\'' +
                ", wind_next4='" + wind_next4 + '\'' +
                ", imageViewnext4='" + imageViewnext4 + '\'' +
                ", high4='" + high4 + '\'' +
                ", low4='" + low4 + '\'' +
                ", week_next5='" + week_next5 + '\'' +
                ", temperature_next5='" + temperature_next5 + '\'' +
                ", climate_next5='" + climate_next5 + '\'' +
                ", wind_next5='" + wind_next5 + '\'' +
                ", imageViewnext5='" + imageViewnext5 + '\'' +
                ", high5='" + high5 + '\'' +
                ", low5='" + low5 + '\'' +
                '}';
    }

    public String getLow5() {
        return low5;
    }

    private String low5;

    public void setWeekyesterday(String weekyesterday) {
        this.weekyesterday = weekyesterday;
    }

    public void setTemperatureyesterday(String temperatureyesterday) {
        this.temperatureyesterday = temperatureyesterday;
    }

    public void setClimateyesterday(String climateyesterday) {
        this.climateyesterday = climateyesterday;
    }

    public void setWindyesterday(String windyesterday) {
        this.windyesterday = windyesterday;
    }

    public void setQualityyesterday(String qualityyesterday) {
        this.qualityyesterday = qualityyesterday;
    }

    public void setWeek_next2(String week_next2) {
        this.week_next2 = week_next2;
    }

    public void setTemperature_next2(String temperature_next2) {
        this.temperature_next2 = temperature_next2;
    }

    public void setClimate_next2(String climate_next2) {
        this.climate_next2 = climate_next2;
    }

    public void setWind_next2(String wind_next2) {
        this.wind_next2 = wind_next2;
    }

    public void setImageViewnext2(String imageViewnext2) {
        this.imageViewnext2 = imageViewnext2;
    }

    public void setWeek_next3(String week_next3) {
        this.week_next3 = week_next3;
    }

    public void setTemperature_next3(String temperature_next3) {
        this.temperature_next3 = temperature_next3;
    }

    public void setClimate_next3(String climate_next3) {
        this.climate_next3 = climate_next3;
    }

    public void setWind_next3(String wind_next3) {
        this.wind_next3 = wind_next3;
    }

    public void setImageViewnext3(String imageViewnext3) {
        this.imageViewnext3 = imageViewnext3;
    }

    public void setWeek_next4(String week_next4) {
        this.week_next4 = week_next4;
    }

    public void setTemperature_next4(String temperature_next4) {
        this.temperature_next4 = temperature_next4;
    }

    public void setClimate_next4(String climate_next4) {
        this.climate_next4 = climate_next4;
    }

    public void setWind_next4(String wind_next4) {
        this.wind_next4 = wind_next4;
    }

    public void setImageViewnext4(String imageViewnext4) {
        this.imageViewnext4 = imageViewnext4;
    }

    public void setWeek_next5(String week_next5) {
        this.week_next5 = week_next5;
    }

    public void setTemperature_next5(String temperature_next5) {
        this.temperature_next5 = temperature_next5;
    }

    public void setClimate_next5(String climate_next5) {
        this.climate_next5 = climate_next5;
    }

    public void setWind_next5(String wind_next5) {
        this.wind_next5 = wind_next5;
    }

    public void setImageViewnext5(String imageViewnext5) {
        this.imageViewnext5 = imageViewnext5;
    }

    public String getWeekyesterday() {

        return weekyesterday;
    }

    public String getTemperatureyesterday() {
        return temperatureyesterday;
    }

    public String getClimateyesterday() {
        return climateyesterday;
    }

    public String getWindyesterday() {
        return windyesterday;
    }

    public String getQualityyesterday() {
        return qualityyesterday;
    }

    public String getWeek_next2() {
        return week_next2;
    }

    public String getTemperature_next2() {
        return temperature_next2;
    }

    public String getClimate_next2() {
        return climate_next2;
    }

    public String getWind_next2() {
        return wind_next2;
    }

    public String getImageViewnext2() {
        return imageViewnext2;
    }

    public String getWeek_next3() {
        return week_next3;
    }

    public String getTemperature_next3() {
        return temperature_next3;
    }

    public String getClimate_next3() {
        return climate_next3;
    }

    public String getWind_next3() {
        return wind_next3;
    }

    public String getImageViewnext3() {
        return imageViewnext3;
    }

    public String getWeek_next4() {
        return week_next4;
    }

    public String getTemperature_next4() {
        return temperature_next4;
    }

    public String getClimate_next4() {
        return climate_next4;
    }

    public String getWind_next4() {
        return wind_next4;
    }

    public String getImageViewnext4() {
        return imageViewnext4;
    }

    public String getWeek_next5() {
        return week_next5;
    }

    public String getTemperature_next5() {
        return temperature_next5;
    }

    public String getClimate_next5() {
        return climate_next5;
    }

    public String getWind_next5() {
        return wind_next5;
    }

    public String getImageViewnext5() {
        return imageViewnext5;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getWendu() {
        return wendu;
    }

    public void setWendu(String wendu) {
        this.wendu = wendu;
    }

    public String getShidu() {
        return shidu;
    }

    public void setShidu(String shidu) {
        this.shidu = shidu;
    }

    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getFengxiang() {
        return fengxiang;
    }

    public void setFengxiang(String fengxiang) {
        this.fengxiang = fengxiang;
    }

    public String getFengli() {
        return fengli;
    }

    public void setFengli(String fengli) {
        this.fengli = fengli;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



}
