import 'package:flutter/services.dart';
import 'package:flutter_android_mood_home_widget/logic/models.dart';

/// Syncs the state with Native Android implementation.
class MoodSyncer {
  static const platform =
      MethodChannel("carlosgiacomini.dev/mood/flutter_to_native");

  static Future<void> syncMood(Mood mood) async {
    try {
      await platform.invokeMethod("syncMood", {"mood": mood.toEmoji()});
    } on PlatformException catch (e) {
      print("Failed to update widget: ${e.message}");
    }
  }
}
