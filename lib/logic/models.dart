/// Mood enumarator to declare the user mood.
enum Mood {
  sad,
  happy,
  normal,
  nervous;

  String toEmoji() {
    return switch (this) {
      Mood.sad => "😢",
      Mood.happy => "😃",
      Mood.normal => "😐",
      Mood.nervous => "😡",
    };
  }

  static List<Mood> getAllMoods() {
    final allMoods = [Mood.sad, Mood.nervous, Mood.normal, Mood.happy];
    return allMoods;
  }

  static Mood fromEmoji(String emoji) {
    return switch (emoji) {
      "😢" => Mood.sad,
      "😃" => Mood.happy,
      "😐" => Mood.normal,
      "😡" => Mood.nervous,
      String() => Mood.normal,
    };
  }
}
