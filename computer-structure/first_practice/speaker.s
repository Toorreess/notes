.include "configuration.inc"
.include "symbolic.inc"

	ldr r0, =GPBASE
	ldr r1, =0x010 @ Speaker is conected to GPIO4
	
loop: 
	str r1,[r0, #GPSET0] /*HIGH*/
	BL wait
	str r1,[r0, #GPCLR0] /*LOW*/
	BL wait
	B loop
	
wait:
	ldr r7, =STBASE
	ldr r3,[r7, #STCLO]
	ldr r4, =10000 /*in microseconds*/
	add r4, r3, r4
ret1: 
	ldr r3,[r7, #STCL0]
	cmp r3,r4
	blt ret1
	bx lr

end: b end
