#include <stdio.h>
#include <pthread.h>
#include <unistd.h>

typedef struct{
	char character;
	int repetitions;
} ThreadParams;

void *writeChar(void* arg){
	ThreadParams* params = (ThreadParams*) arg;
	char character = params->character;
	int reps = params->repetitions;

	for (int i = 0; i < reps;i++) {
		printf("%c\n", character);
		usleep(100000);
	}

	return NULL;
}

int main(){
	// Threads declaration
	pthread_t thr1, thr2, thr3;
	
	// Threads parameters
	ThreadParams params1 = {'A', 5};
	ThreadParams params2 = {'B', 7};
	ThreadParams params3 = {'C', 3};

	// Threads creation
	pthread_create(&thr1, NULL, writeChar, (void*)&params1);
	pthread_create(&thr2, NULL, writeChar, (void*)&params2);
	pthread_create(&thr3, NULL, writeChar, (void*)&params3);

	// Wait for threads to finish
	pthread_join(thr1,NULL);
	pthread_join(thr2,NULL);
	pthread_join(thr3,NULL);
	
	return 0;
}
