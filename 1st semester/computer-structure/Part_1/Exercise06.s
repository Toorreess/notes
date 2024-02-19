/* Basic skeleton for programs using ports (without interruptions) */

.include "configuration.inc" 
.include "symbolic.inc"

/* Stack init for SVC mode	*/
	mov     r0, #0b11010011
	msr     cpsr_c, r0
	mov     sp, #0x8000000
	
/* Continue my program here */
	
	ldr r1, =GPBASE
	ldr r2, =0x200
	ldr r3, =0x800
	ldr r4, =0x400000
	
loop:
	
	str r2,[r1, #GPSET0]
	str r3,[r1, #GPSET0]
	str r4,[r1, #GPSET0]
	BL wait
	str r2,[r1, #GPCLR0]
	str r3,[r1, #GPCLR0]
	str r4,[r1, #GPCLR0]
	BL wait
	b loop
	
wait:
	ldr r7, =STBASE
	ldr r5, [r7, #STCLO]
	ldr r6, =1000000
	add r6, r5, r6

ret1:
	ldr r5, [r7, #STCLO]
	cmp r5, r6
	blt ret1
	bx lr
	
end: b end