package com.example.flutter_android_mood_home_widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

class MoodHomeWidget : AppWidgetProvider() {
    companion object {
        private const val SHARED_PREFERENCES_KEY = "mood";
        private const val SHARED_PREFERENCES_MOOD_KEY = "current_mood";
    }

    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        val prefs = context?.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE);
        val currentMood = prefs?.getString(SHARED_PREFERENCES_MOOD_KEY, "😃") ?: "😃";

        for (appWidgetId in appWidgetIds ?: intArrayOf()) {
            updateMoodTrackerHomeWidget(context, appWidgetManager, appWidgetId, currentMood);
        }
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent);

        if (intent == null || context == null) return;

        if (intent.action == AppWidgetManager.ACTION_APPWIDGET_UPDATE) {
            val mood = intent.getStringExtra("mood") ?: return;
            val prefs = context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE);
            prefs.edit().putString(SHARED_PREFERENCES_MOOD_KEY, mood).apply();

            val appWidgetManager = AppWidgetManager.getInstance(context);
            val ids = appWidgetManager.getAppWidgetIds(ComponentName(context, MoodHomeWidget::class.java));
            onUpdate(context, appWidgetManager, ids);
        }
    }

    private fun updateMoodTrackerHomeWidget(context: Context?, appWidgetManager: AppWidgetManager?, appWidgetId: Int, currentMood: String) {
        if (context == null || appWidgetManager == null) return;

        val views = RemoteViews(context.packageName, R.layout.mood_tracker_home_widget);
        views.setTextViewText(R.id.mood, currentMood);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }
}