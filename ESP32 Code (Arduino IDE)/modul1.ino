#include <WiFi.h>
#include <Firebase_ESP_Client.h>

/* 1. Define the WiFi credentials */
#define WIFI_SSID "zz"
#define WIFI_PASSWORD "kocakgaming"

/* 2. Define the API Key */
#define API_KEY "AIzaSyCtizg-bnqsstt_sHOLXEc_TKXgFr59n6A"

/* 3. Define the RTDB URL */
#define DATABASE_URL "https://modul-1-80c16-default-rtdb.asia-southeast1.firebasedatabase.app/" 

/* 4. Define the user Email and password that alreadey registerd or added in your project */
#define USER_EMAIL "taufiqrahman.work@gmail.com"
#define USER_PASSWORD "kocakgaming123"

// Define Firebase Data object
FirebaseData fbdo;
FirebaseJson json;
FirebaseAuth auth;
FirebaseConfig config;

unsigned long sendDataPrevMillis = 0;

int potentio = 34;

void setup()
{
  pinMode(potentio, INPUT);

  Serial.begin(9600);
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);

  Serial.print("Connecting to Wi-Fi");
  while (WiFi.status() != WL_CONNECTED)
  {
    Serial.print(".");
    delay(300);
  }
  Serial.println();
  Serial.print("Connected with IP: ");
  Serial.println(WiFi.localIP());
  Serial.println();

  /* Assign the api key (required) */
  config.api_key = API_KEY;

  /* Assign the user sign in credentials */
  auth.user.email = USER_EMAIL;
  auth.user.password = USER_PASSWORD;

  /* Assign the RTDB URL (required) */
  config.database_url = DATABASE_URL;

  // Comment or pass false value when WiFi reconnection will control by your code or third party library e.g. WiFiManager
  Firebase.reconnectNetwork(true);

  // Since v4.4.x, BearSSL engine was used, the SSL buffer need to be set.
  // Large data transmission may require larger RX buffer, otherwise connection issue or data read time out can be occurred.
  fbdo.setBSSLBufferSize(4096 /* Rx buffer size in bytes from 512 - 16384 */, 1024 /* Tx buffer size in bytes from 512 - 16384 */);

  // Limit the size of response payload to be collected in FirebaseData
  fbdo.setResponseSize(2048);

  Firebase.begin(&config, &auth);

  Firebase.setDoubleDigits(5);

  config.timeout.serverResponse = 10 * 1000;
}

void loop()
{
  // Firebase.ready() should be called repeatedly to handle authentication tasks.
  if (Firebase.ready() && (millis() - sendDataPrevMillis > 1000 || sendDataPrevMillis == 0))
  {
    sendDataPrevMillis = millis();

  int vresdata;
    vresdata = analogRead(potentio);
    Serial.println(vresdata);
    json.set("/data", vresdata);

  // Write JSON object to Realtime Database
  if (Firebase.setJSON(&fbdo, "/data", json) {
    Serial.println("Potentiometer value written successfully!");
  } else {
    Serial.println("Potentiometer value write failed!");
    Serial.println("Reason: " + fbdo.errorReason());
  }
  }
}