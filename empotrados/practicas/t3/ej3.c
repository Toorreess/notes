#include <stdio.h>
#include <pthread.h>
#include <semaphore.h>
#include <unistd.h>

#define MAX_BOYS 5
#define MAX_GIRLS 1

sem_t boy_sem, girl_sem;
pthread_mutex_t bathroom_mutex;
int boys_in_bathroom = 0, boys_waiting = 0;
int girl_in_bathroom = 0;


void* boy(void* id) {
	while (1) {
		pthread_mutex_lock(&bathroom_mutex);
		boys_waiting++;
		pthread_mutex_unlock(&bathroom_mutex);
		
		sem_wait(&boy_sem);  // Los chicos esperan a su turno
		
		pthread_mutex_lock(&bathroom_mutex);
		boys_waiting--;
		if (boys_in_bathroom < MAX_BOYS && girl_in_bathroom == 0) {
			boys_in_bathroom++;
			printf("Chico %ld ha entrado en el baño. Chicos en el baño: %d\n", (long)id, boys_in_bathroom);
			pthread_mutex_unlock(&bathroom_mutex);

			sleep(1);  // Simula el tiempo que el chico pasa en el baño

			pthread_mutex_lock(&bathroom_mutex);
			boys_in_bathroom--;
			printf("Chico %ld ha salido del baño. Chicos en el baño: %d\n", (long)id, boys_in_bathroom);
		}
		pthread_mutex_unlock(&bathroom_mutex);

		sem_post(&boy_sem);  // Da paso al siguiente chico
		sleep(3);
	}
}

void* girl(void* id) {
	while (1) {
	    
		sem_wait(&girl_sem);  // Las chicas esperan a su turno (sin restricciones por parte de chicos)

		pthread_mutex_lock(&bathroom_mutex);
		if (boys_in_bathroom == 0 && boys_waiting == 0 && girl_in_bathroom < MAX_GIRLS) {
		    girl_in_bathroom ++;
			printf("Chica %ld ha entrado en el baño.\n", (long)id);
			pthread_mutex_unlock(&bathroom_mutex);

			sleep(1);  // Simula el tiempo que la chica pasa en el baño

			pthread_mutex_lock(&bathroom_mutex);
			girl_in_bathroom --;
			printf("Chica %ld ha salido del baño.\n", (long)id);
		}
		pthread_mutex_unlock(&bathroom_mutex);

		sem_post(&girl_sem);  // Da paso a la siguiente chica
		sleep(3);
	}
}

int main() {
	pthread_t boys[6], girls[2];
	sem_init(&boy_sem, 0, MAX_BOYS);  // Los chicos pueden entrar hasta un maximo de 5
	sem_init(&girl_sem, 0, 1);  // Solo puede haber una chica

	pthread_mutex_init(&bathroom_mutex, NULL);

	// Crear hebras de chicos
	for (long i = 0; i < 6; i++) {
		pthread_create(&boys[i], NULL, boy, (void*)i);
	}

	// Crear hebras de chicas
	for (long i = 0; i < 2; i++) {
		pthread_create(&girls[i], NULL, girl, (void*)i);
	}

	// Unir hebras
	for (int i = 0; i < 6; i++) {
		pthread_join(boys[i], NULL);
	}
	for (int i = 0; i < 2; i++) {
		pthread_join(girls[i], NULL);
	}

	pthread_mutex_destroy(&bathroom_mutex);
	sem_destroy(&boy_sem);
	sem_destroy(&girl_sem);

	return 0;
}
