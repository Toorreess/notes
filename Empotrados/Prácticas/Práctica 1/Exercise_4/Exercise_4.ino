/*
  Arduino Uno
  C++ code
*/

const int redPin = 3;
const int greenPin = 4;

const unsigned long redPeriod = 1024;
const unsigned long greenPeriod = 1458;

struct Led{
  int pin;
  int state;
  unsigned long period;
  unsigned long nextTimestamp;
};

struct Led red;
struct Led green;

void execute(Led *led, unsigned long currentMillis){
  if (currentMillis >= led->nextTimestamp) {
    led->state = led->state == HIGH ? LOW:HIGH;
    digitalWrite(led->pin, led->state);
    led->nextTimestamp = led->period + currentMillis;
  }
}

void setup(){
  pinMode(redPin, OUTPUT);
  pinMode(greenPin, OUTPUT);

  unsigned long currentMillis = millis();
  
  // Red led setup
  red.state = LOW;
  red.period = redPeriod / 2;
  red.pin = 3;
  red.nextTimestamp = red.period + currentMillis;
  
  // Green led setup
  green.state = LOW;
  green.period = greenPeriod / 2;
  green.pin = 4;
  green.nextTimestamp = green.period + currentMillis;
}

void loop(){
  unsigned long current_millis = millis();
  execute(&red, current_millis);
  execute(&green, current_millis);
}
