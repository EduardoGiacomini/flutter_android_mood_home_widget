import 'package:flutter_android_mood_home_widget/logic/models.dart';
import 'package:flutter_android_mood_home_widget/logic/mood_syncer.dart';
import 'package:shared_preferences/shared_preferences.dart';

/// Controlls the mood persistence using device SharedPreferences.
class MoodService {
  static const _sharedPreferencesKey = "mood";

  static Future<Mood> get() async {
    final prefs = await SharedPreferences.getInstance();
    final mood = Mood.fromEmoji(prefs.getString(_sharedPreferencesKey) ?? "");
    return mood;
  }

  static Future<void> save(Mood mood) async {
    final prefs = await SharedPreferences.getInstance();
    await prefs.setString(_sharedPreferencesKey, mood.toEmoji());
    await MoodSyncer.syncMood(mood);
  }
}
