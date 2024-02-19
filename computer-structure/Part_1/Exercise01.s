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
	
	b loop
end: b end
