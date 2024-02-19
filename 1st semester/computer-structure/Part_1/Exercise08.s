/* Basic skeleton for programs using ports (without interruptions) */

.include "configuration.inc" 
.include "symbolic.inc"

/* Stack init for SVC mode	*/
	mov     r0, #0b11010011
	msr     cpsr_c, r0
	mov     sp, #0x8000000
	
/* Continue my program here */
	
	ldr r1, =GPBASE
	ldr r2, =0x010

loop:
	
	str r2,[r1, #GPSET0]
	BL wait
	str r2,[r1, #GPCLR0]
	BL wait
	b loop
	
wait:
	ldr r7, =STBASE
	ldr r3, [r7, #STCLO]
	ldr r4, =1136
	add r4, r3, r4

ret1:
	ldr r3, [r7, #STCLO]
	cmp r3, r4
	blt ret1
	bx lr
	
end: b end
	