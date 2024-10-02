/*
  Arduino Uno
  C++ code
*/

const uint8_t ledPin = 2;

void setup(){
  pinMode(ledPin, OUTPUT);
}

void loop(){
  digitalWrite(ledPin, HIGH);
}
