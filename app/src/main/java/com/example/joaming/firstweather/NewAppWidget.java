package com.example.joaming.firstweather;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.TextView;

import static android.R.attr.data;
import static android.content.Context.MODE_PRIVATE;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {



        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);


        SharedPreferences pref = context.getSharedPreferences
                ("config",MODE_PRIVATE);
        String temperatureTv = pref.getString("temperatureTv","");

        views.setTextViewText(R.id.temperaturewidget, temperatureTv);

        appWidgetManager.updateAppWidget(appWidgetIds, views);
    }



    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

