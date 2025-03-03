import 'package:flutter/material.dart';
import 'package:flutter_android_mood_home_widget/logic/models.dart';
import 'package:flutter_android_mood_home_widget/logic/mood_service.dart';

class MoodScreen extends StatefulWidget {
  const MoodScreen({super.key});

  @override
  State<MoodScreen> createState() => _MoodScreenState();
}

class _MoodScreenState extends State<MoodScreen> {
  final List<Mood> moods = Mood.getAllMoods();
  Mood selectedMood = Mood.normal;

  @override
  void initState() {
    super.initState();
    _loadMood();
  }

  Future<void> _loadMood() async {
    final mood = await MoodService.get();
    setState(() {
      selectedMood = mood;
    });
  }

  Future<void> _saveMood(Mood mood) async {
    await MoodService.save(mood);
    setState(() {
      selectedMood = mood;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text("Mood")),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            const Text(
              "How are you feeling today?",
              style: TextStyle(fontSize: 20),
            ),
            const SizedBox(height: 20),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: moods.map((mood) {
                return GestureDetector(
                  onTap: () => _saveMood(mood),
                  child: Container(
                    margin: const EdgeInsets.symmetric(horizontal: 8),
                    padding: const EdgeInsets.all(10),
                    decoration: BoxDecoration(
                      shape: BoxShape.circle,
                      color: mood == selectedMood
                          ? Colors.blue.shade100
                          : Colors.transparent,
                      border: Border.all(
                        color: Colors.blue,
                        width: mood == selectedMood ? 3 : 1,
                      ),
                    ),
                    child: Text(
                      mood.toEmoji(),
                      style: const TextStyle(fontSize: 30),
                    ),
                  ),
                );
              }).toList(),
            ),
          ],
        ),
      ),
    );
  }
}
