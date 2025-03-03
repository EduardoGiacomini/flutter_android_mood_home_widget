import 'package:flutter/material.dart';
import 'package:flutter_android_mood_home_widget/ui/screens/mood_screen.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      home: MoodScreen(),
    );
  }
}
