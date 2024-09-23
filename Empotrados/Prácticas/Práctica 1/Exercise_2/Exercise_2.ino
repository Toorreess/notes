/*
  Arduino Uno
  C++ code
*/

const int ledPin = 2;

void setup(){
  pinMode(ledPin, OUTPUT);
}

void loop(){
  digitalWrite(ledPin, HIGH);
}
