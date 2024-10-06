/*
  Arduino Uno
  C++ code
*/

const int redPin = 3;
const int greenPin = 4;

const int greenPeriod = 800;
const int redPeriod = 5000;
float redRate = 0;

struct Led{
  int state;
  unsigned long period;
  unsigned long nextTimestamp;
};

struct Led red;
struct Led green;

void executeGreen(Led *greenLed, unsigned long ms){
    if (ms >= greenLed->nextTimestamp) {
    greenLed->state = greenLed->state == HIGH ? LOW:HIGH;
    digitalWrite(greenPin, greenLed->state);
    greenLed->nextTimestamp = greenLed->period + ms;
  }
}

void executeRed(){
   if (ms >= redPeriod) {
  }
}


void setup() {
  unsigned long ms = millis();
  pinMode(redPin, OUTPUT);
  pinMode(greenPin, OUTPUT);

  green.state=LOW;
  green.period= greenPeriod/2;
  green.nextTimestamp= green.period + ms;


}

void loop() {
  unsigned long ms = millis();
  executeGreen(&green, ms);
}
