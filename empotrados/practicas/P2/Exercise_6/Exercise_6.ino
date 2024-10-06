/*
  Arduino Uno
  C++ code
*/
const int ledPin = 3;
const int buttonPin = 2;

void setup() {
  pinMode(ledPin, OUTPUT);
  pinMode(buttonPin, INPUT);
  Serial.begin(9600);
}

void loop() {
  int val = digitalRead(buttonPin);
  digitalWrite(ledPin, val);
  Serial.println(val);
}
