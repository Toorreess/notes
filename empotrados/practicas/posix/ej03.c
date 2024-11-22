#include <pthread.h>

#define ACTIVE 1
#define INACTIVE 0

#define MONITOR_PERIOD_SEC = 1
#define MONITOR_PERIOD_NSEC = 0
#define MONITOR_PERIOD_OFFSET = 8e8 // ? Plazo

#define TEMP_PERIOD_SEC = 0
#define TEMP_PERIOD_NSEC = 5e8
#define TEMP_UPPER_THRESHOLD = 100 // ºC
#define TEMP_BOTTOM_THRESHOLD = 90 // ºC

#define TEMP_SENSOR_PERIOD_SEC = 0
#define TEMP_SENSOR_PERIOD_NSEC = 4e8
#define TEMP_SENSOR_PERIOD_OFFSET = 3.7e8 // ? Plazo
#define TEMP_SENSOR_INIT = 80 // ºC
#define TEMP_SENSOR_INCREMENT = 1
#define TEMP_SENSOR_DECREMENT = 2

#define PRES_PERIOD_SEC = 0
#define PRES_PERIOD_NSEC = 3.5e8
#define PRES_PERIOD_OFFSET = 3.5e8
#define PRES_UPPER_THRESHOLD = 1000 //Kpa
#define PRES_BOTTOM_THRESHOLD = 900 //Kpa

#define PRES_SENSOR_PERIOD_SEC = 0
#define PRES_SENSOR_PERIOD_NSEC = 3e8
#define PRES_SENSOR_PERIOD_OFFSET = 2.5e8 // ? Plazo
#define PRES_SENSOR_INIT = 800 //Kpa
#define PRES_SENSOR_INCREMENT = 10 //Kpa
#define PRES_SENSOR_DECREMENT = 20 //Kpa

struct Data_Temp{};
struct Data_Pres{};
struct Data_Mtr{};

void* init_ctrTemp(){}
void* init_sTemp(){}
void* init_ctrPres(){}
void* init_sPres(){}
void* init_mtr(){}

int main() {
	// * Priorities definition
	int prio0 = 0;
	int prio1 = 2;
	int prio2 = 3;
	int prio3 = 4;
	int prio4 = 5;

	// * Attr
	pthread_attr_t attr;
	pthread_attr_init(&attr); // Initialize attr
	pthread_attr_setinheritsched(&attr, PTHREAD_EXPLICIT_SCHED); // Set attr inherit scheduler

	// * Threads creation
	pthread_t mtr, ctrTmp, sTmp, ctrPres, sPres;
	pthread_create(&mtr, &attr, void *(*start_routine)(void *), void *restrict arg)
	



// TODO Exec threads

	return 0;
}
