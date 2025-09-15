# ESP32 Potentiometer Monitoring Android App

An IoT project using ESP32 to monitor potentiometer values in real time. Data is sent to Firebase and visualized through an Android application for mobile monitoring. This project was developed as part of my final thesis.

---

## 🧾 Table of Contents

1. [Features](#features)  
2. [Architecture / Overview](#architecture--overview)  
3. [Components](#components)  
4. [Setup / Installation](#setup--installation)  
5. [Usage](#usage)  
6. [Screenshots](#screenshots) *(optional, kalau kamu punya gambar)*  
7. [Project Structure](#project-structure)  
8. [License](#license)  

---

## Features

- Real-time monitoring of potentiometer sensor values via ESP32  
- Data pushed to Firebase Realtime Database (atau Firestore, tergantung yang kamu pakai)  
- Android app menampilkan nilai sensor secara langsung  
- Mungkin tambahan: grafik, threshold, alert (kalau kamu tambahin)  

---

## Architecture / Overview

Project ini terdiri dari tiga bagian utama:

1. **ESP32 Device**  
   - Membaca nilai dari potentiometer  
   - Mengirim data ke Firebase  

2. **Firebase Backend**  
   - Menyimpan data sensor  
   - Bisa diakses oleh aplikasi Android  

3. **Android App**  
   - Mengambil data dari Firebase  
   - Menampilkan data sensor secara real time  
   - User interface sederhana untuk monitoring mobile  

---

## Components

- ESP32 (board hardware)  
- Potentiometer sensor  
- Firebase account (Realtime DB atau Firestore)  
- Android Studio / Android SDK  
- Bahasa & tools:  
  - Arduino IDE (atau PlatformIO) untuk code ESP32  
  - Kotlin / Java atau
