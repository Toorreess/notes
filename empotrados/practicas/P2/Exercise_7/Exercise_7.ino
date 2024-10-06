/*
  Arduino Uno
  C++ code
*/
const int redPin = 3;
const int greenPin = 4;
const int buttonPin = 2;
bool check;
int greenState;

void setup() {
  pinMode(redPin, OUTPUT);
  pinMode(greenPin, OUTPUT);
  pinMode(buttonPin, INPUT);
  greenState = LOW;
  check = false;
  Serial.begin(9600);
}

void loop() {
  if (Serial){
    Serial.println(greenState);
    Serial.print("\t");
    Serial.println(digitalRead(buttonPin));
  }

  int val = digitalRead(buttonPin);
  //Serial.println(val);
  digitalWrite(redPin, val);

  if (val == LOW){
    check = true;
  }

  if (val == HIGH && check){
    if (greenState == HIGH){
      greenState = LOW;
    } else{
      greenState = HIGH;
    }

    digitalWrite(greenPin, greenState);
    check = false;
  }
  delay(50);
}
