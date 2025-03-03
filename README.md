# Flutter Android Mood Home Widget

A Flutter and Android project to study about Home Widgets:

- Basic home widgets UI;
- Home widgets synchronization from Flutter to Android using Method Channels;
- Android background workers to update home widgets.

In app, when the mood is selected, the Flutter notifies the Android native code using method channels to update the Home Widget (built with native Android code XML).

<img src="https://github.com/user-attachments/assets/58a5f6a2-8f68-40ad-ac9a-4a78ebd396fd" width="250px" />

<img src="https://github.com/user-attachments/assets/218409f4-3e5d-4fda-905d-1cc78ef91368" width="250px" />

<img src="https://github.com/user-attachments/assets/c8557ae5-4c6a-4c4f-b5f0-2d0db6cb5037" width="250px" />

<img src="https://github.com/user-attachments/assets/dfb1d1dc-abd4-4772-b914-25d9c015b1ec" width="250px" />

Furthermore, there is a background worker that runs every 15 minutes to reset the state from the Home Widget (even the app is closed). It run when the app is opened too.

## Getting Started

```
fvm flutter run
```
