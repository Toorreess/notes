#include <stdio.h>
#include <pthread.h>
#include <unistd.h>
#include <semaphore.h>

// Global variable
int v = 0;

pthread_mutex_t mutex_v;

void *Access(void* args){
	for (int i = 0; i<100; i++){
		pthread_mutex_lock(&mutex_v);
		v++;
		pthread_mutex_unlock(&mutex_v);
	}
	return NULL;
}

int main(){
	pthread_mutex_init(&mutex_v, NULL);
	pthread_t thr0, thr1;

	pthread_create(&thr0, NULL, &Access, NULL);
	pthread_create(&thr1, NULL, &Access, NULL);
	
	pthread_join(thr0, NULL);
	pthread_join(thr1, NULL);
	
	printf("Final value of v:%d\n", v);

	pthread_mutex_destroy(&mutex_v);
	return 0;
}
