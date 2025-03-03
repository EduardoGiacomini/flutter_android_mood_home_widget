package com.example.flutter_android_mood_home_widget

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import java.util.concurrent.TimeUnit
import MoodBackgroundWorker

class MoodHomeWidgetSyncer private constructor() {
    companion object {
        private const val MOOD_BINARY_MESSENGER_NAME = "carlosgiacomini.dev/mood/flutter_to_native";
        private const val MOOD_BACKGROUND_WORKER_NAME = "mood_background_worker"

        fun register(
            context: Context,
            binaryMessenger: BinaryMessenger,
        ) {
            registerMethodChannel(context, binaryMessenger);
            registerBackgroundWorker(context);
        }

        private fun registerMethodChannel(
            context: Context,
            binaryMessenger: BinaryMessenger,
        ) {
            val channel = MethodChannel(binaryMessenger, MOOD_BINARY_MESSENGER_NAME);
            channel.setMethodCallHandler { call, result ->
                when (call.method) {
                    "syncMood" -> syncMood(context, result, call)
                }
            }
        }

        private fun registerBackgroundWorker(context: Context) {
            // The minimum repeatInterval value allowed from Android is 15 minutes.
            val workRequest = PeriodicWorkRequestBuilder<MoodBackgroundWorker>(15, TimeUnit.MINUTES)
                .setInitialDelay(0, TimeUnit.MINUTES)
                .build();
            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                MOOD_BACKGROUND_WORKER_NAME,
                ExistingPeriodicWorkPolicy.UPDATE,
                workRequest,
            );
        }

        private fun syncMood(context: Context, result: MethodChannel.Result, call: MethodCall) {
            val intent = Intent(context, MoodHomeWidget::class.java);
            intent.action = AppWidgetManager.ACTION_APPWIDGET_UPDATE;
            val mood = call.argument<String>("mood") ?: "😐";
            intent.putExtra("mood", mood);
            val ids = AppWidgetManager.getInstance(context).getAppWidgetIds(ComponentName(context, MoodHomeWidget::class.java));
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
            context.sendBroadcast(intent);
            result.success(true);
        }
    }
}