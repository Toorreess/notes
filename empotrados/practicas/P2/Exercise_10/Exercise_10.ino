const uint8_t led_pin = 3;
const uint8_t ldr_pin = 0;

void setup(){
	pinMode(led_pin, OUTPUT);
	pinMode(ldr_pin, INPUT);
}

void loop(){
	int val = analogRead(ldr_pin)
	long valMapped = (val, 0, 1023, 0, 255);
	analogWrite(led_pin, 255 - valMapped);
}
