#define N 5
active proctype ARRAY() {
	int a[N];
	int prod = 1;
	int i = 0;
	do
		:: i<N -> a[i] = 1;
			prod = prod * a[i];
			i = i+1;
		:: i<N -> a[i] = 2;
			prod = prod * a[i];
			i = i+1;
		:: i<N -> a[i] = 3;
			prod = prod * a[i];
			i = i+1;
		:: i<N -> a[i] = 4;
			prod = prod * a[i];
			i = i+1;
		:: i<N -> a[i] = 5;
			prod = prod * a[i];
			i = i+1;
		:: i>=N -> break;
	od
	printf ("El producto es: %d\n", prod)
}
