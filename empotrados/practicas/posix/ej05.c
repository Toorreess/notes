#include <stdlib.h>
#include <stddef.h>
#include <assert.h>
#include <string.h>
#include <stdio.h>
#include <ctype.h>
#include <sys/time.h>
#include <unistd.h>
#include <time.h>
#include <errno.h>
#include <sched.h>
#include <pthread.h>
#include <sys/mman.h>
#include <signal.h>

/* Eventos exterior */
#define EVENT_PERIODO_SEC 1
#define EVENT_PERIODO_NSEC 0
#define EVENT_PRIORIDAD 24

/* Control telefono */
#define TLF_PRIORIDAD 26

/* Consumo bateria */
#define BATTERY_PERIODO_SEC 1
#define BATTERY_PERIODO_NSEC 0
#define BATTERY_PRIORITY 30

#define BATTERY_INIT_VALUE 100
#define BATTERY_DESC 1

/* Monitor bateria */
#define MONITOR_PERIODO_SEC 5
#define MONITOR_PERIODO_NSEC 0
#define MONITOR_PRIORITY 22

/* Señales */
#define SIG_FIN (SIGRTMIN)
#define SIG_BAT (SIGRTMIN + 1)
#define SIG_EVT (SIGRTMIN + 2)
#define SIG_LLM (SIGRTMIN + 3)
#define SIG_SMS (SIGRTMIN + 4)
#define SIG_MTR (SIGRTMIN + 5)

struct Data_Bat
{
	pthread_mutex_t mutex;
	int val;
};

void *tarea_evt(void *arg) {}

void *tarea_mnt(void *arg)
{
	struct Data_Bat *data = arg;

	timer_t timerid;
	sigset_t sigset;

	struct sigevent sgev;
	int signum = 1;
	sgev.sigev_event = SIGEV_SIGNAL;
	sgev.sigev_signo = SIG_MTR;
	sgev.sigev_value.sival_ptr = &timerid;
	timer_create(CLOCK_MONOTONIC, &sgev, &timerid);

	const struct timespec periodo = {MONITOR_PERIODO_SEC, MONITOR_PERIODO_NSEC};
	struct itimerspec its;
	its.it_interval = periodo;
	its.it_value.tv_sec = 0;
	its.it_value.tv_nsec = 1;
	timer_settime(timerid, 0, &its, NULL);

	sigemptyset(&sigset);
	sigaddset(&sigset, SIG_FIN);
	sigaddset(&sigset, SIG_MTR);

	int err;
	while (1)
	{
		do
		{
			err = sigwait(&sigset, &signum);
		} while (err == EINTR);

		if (signum == SIG_FIN)
		{
			break;
		}

		if (signum == SIG_MTR)
		{
			pthread_mutex_lock(&data->mutex);
			printf("Batería: %d\n", data->val);
			pthread_mutex_unlock(&data->mutex);

			continue;
		}
		printf("Señal inesperada: %d\n", signum);
	}

	printf("Monitor: Fin de batería.\n");

	timer_delete(timerid);
	return NULL;
}

void *tarea_bat(void *arg)
{
	struct Data_Bat *data = arg;

	timer_t timerid;
	sigset_t sigset;

	struct sigevent sgev;
	int signum = 1;
	sgev.sigev_event = SIGEV_SIGNAL;
	sgev.sigev_signo = SIG_BAT;
	sgev.sigev_value.sival_ptr = &timerid;
	timer_create(CLOCK_MONOTONIC, &sgev, &timerid);

	const struct timespec periodo = {BATTERY_PERIODO_SEC, BATTERY_PERIODO_NSEC};
	struct itimerspec its;
	its.it_interval = periodo;
	its.it_value.tv_sec = 0;
	its.it_value.tv_nsec = 1;
	timer_settime(timerid, 0, &its, NULL);

	sigemptyset(&sigset);
	sigaddset(&sigset, SIG_BAT);

	int err;

	while (1)
	{
		do
		{
			err = sigwait(&sigset, &signum);
		} while (err == EINTR);

		pthread_mutex_lock(&data->mutex);
		data->val -= BATTERY_DESC;
		pthread_mutex_unlock(&data->mutex);

		if (data->val < 0)
		{
			break;
		}
	}

	printf("Batería: Fin de batería: %d\n", data->val);

	union sigval sig_value;

	sig_value.sival_int = 0;
	sigqueue(getpid(), SIG_FIN, sig_value);
	sigqueue(getpid(), SIG_FIN, sig_value);
	sigqueue(getpid(), SIG_FIN, sig_value);

	timer_delete(timerid);

	return NULL;
}
void *tarea_tlf(void *arg) {}
void *tarea_fin(void *arg) {}

int main()
{
	pthread_t t_evt, t_tlf, t_bat, t_mtr;

	struct Data_Bat data_bat;

	struct sched_param param;

	pthread_attr_t attr;

	int pcy, policy = SCHED_FIFO;

	int prio_main = 60;
	int prio_evt = EVENT_PRIORIDAD;
	int prio_tlf = TLF_PRIORIDAD;
	int prio_mtr = MONITOR_PRIORITY;
	int prio_bat = BATTERY_PRIORITY;

	mlockall(MCL_CURRENT | MCL_FUTURE);

	pthread_getschedparam(pthread_self(), &pcy, &param);
	param.sched_priority = prio_main;
	pthread_setschedparam(pthread_self(), policy, &param);

	sigset_t sigset;
	sigemptyset(&sigset);
	sigaddset(&sigset, SIG_FIN);
	sigaddset(&sigset, SIG_LLM);
	sigaddset(&sigset, SIG_SMS);
	sigaddset(&sigset, SIG_MTR);
	sigaddset(&sigset, SIG_EVT);
	sigaddset(&sigset, SIG_BAT);
	pthread_sigmask(SIG_BLOCK, &sigset, NULL);

	data_bat.val = BATTERY_INIT_VALUE;
	pthread_attr_init(&attr);
	pthread_attr_setinheritsched(&attr, PTHREAD_EXPLICIT_SCHED);
	pthread_attr_setschedpolicy(&attr, policy);

	pthread_mutex_init(&data_bat.mutex, NULL);

	param.sched_priority = prio_evt;
	pthread_attr_setschedparam(&attr, &param);
	pthread_create(&t_evt, &attr, tarea_evt, NULL);

	param.sched_priority = prio_bat;
	pthread_attr_setschedparam(&attr, &param);
	pthread_create(&t_bat, &attr, tarea_bat, &data_bat);

	param.sched_priority = prio_mtr;
	pthread_attr_setschedparam(&attr, &param);
	pthread_create(&t_mtr, &attr, tarea_mnt, &data_bat);

	param.sched_priority = prio_tlf;
	pthread_attr_setschedparam(&attr, &param);
	pthread_create(&t_tlf, &attr, tarea_tlf, NULL);

	pthread_attr_destroy(&attr);

	pthread_join(t_bat, NULL);
	pthread_join(t_evt, NULL);
	pthread_join(t_tlf, NULL);
	pthread_join(t_mtr, NULL);

	pthread_mutex_destroy(&data_bat.mutex);

	return 0;
}