.include "configuration.inc"
.include "symbolic.inc"

	ldr r0, =GPBASE
	
	@ Turn on green led on GPIO22
	ldr r1, =0x400000
	
loop:
	str r1,[r0, #GPSET0]
	BL wait
	str r1,[r0, #GPCLR0]
	BL wait
	B loop
	
wait:
	ldr r7, =STBASE
	ldr r3,[r7, #STCLO]
	ldr r4, =500000 /*in microseconds*/
	add r4, r3, r4
ret1: 
	ldr r3,[r7, #STCLO]
	cmp r3,r4
	blt ret1
	bx lr 

end: b end
