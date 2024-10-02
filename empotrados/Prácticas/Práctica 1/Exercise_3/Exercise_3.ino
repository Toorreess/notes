/*
  Arduino Uno
  C++ code
*/

const int ledPin = 2;
const int waitTime = 500;

void setup() {
  pinMode(ledPin, OUTPUT);
}

void loop() {
  digitalWrite(ledPin, HIGH);
  delay(waitTime);
  digitalWrite(ledPin, LOW);
  delay(waitTime);
}
