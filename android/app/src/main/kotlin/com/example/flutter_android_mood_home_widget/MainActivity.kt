package com.example.flutter_android_mood_home_widget

import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine


class MainActivity: FlutterActivity() {
    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine);
        val binaryMessenger = flutterEngine.dartExecutor.binaryMessenger;
        MoodHomeWidgetSyncer.register(this, binaryMessenger);
    }
}
