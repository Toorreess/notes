const uint8_t red_pin = 3;
const uint8_t potentiometer_pin = 0;

void setup() {
  pinMode(red_pin, OUTPUT);
  pinMode(potentiometer_pin, INPUT);
}

void loop() {
  int val = analogRead(potentiometer_pin);
  long valMapped = map(val, 0, 1023, 0, 255);

  analogWrite(red_pin, valMapped);
}
