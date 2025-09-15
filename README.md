# ESP32 Potentiometer Monitoring with Android App

This project demonstrates real-time monitoring of a potentiometer using an ESP32 microcontroller. The ESP32 reads potentiometer values, sends the data to **Firebase Realtime Database**, and an Android application displays the data on a smartphone. The project was developed as part of coursework in microcontroller and IoT systems.

## Requirements
- ESP32 development board  
- Potentiometer  
- Android smartphone  
- Firebase account (Realtime Database)  
- Arduino IDE  
- Android Studio  

## Notes
To run this project, you need to:  
1. Install the required **Firebase libraries** in Arduino IDE.  
2. Set up your own **Firebase Realtime Database** and configure the reference URL, API key, and credentials in the ESP32 code.  

⚠️ For a complete step-by-step tutorial (from Firebase setup, ESP32 programming, wiring, to Android app integration), please refer to the provided **PDF guide** in this repository. The PDF contains the full instructions from start to finish.

## Usage
1. Upload the ESP32 code after configuring Wi-Fi and Firebase credentials.  
2. Set up the Firebase Realtime Database as described in the PDF tutorial.  
3. Open the Android app project in Android Studio, connect it to your Firebase project, and build the APK.  
4. Install the app on your smartphone.  
5. Rotate the potentiometer connected to the ESP32; the value will be updated in Firebase and displayed in the Android application in real time.  
