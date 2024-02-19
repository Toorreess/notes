/* Basic skeleton for programs using ports (without interruptions) */

.include "configuration.inc" 
.include "symbolic.inc"

/* Stack init for SVC mode	*/
	mov     r0, #0b11010011
	msr     cpsr_c, r0
	mov     sp, #0x8000000
	
/* Continue my program here */

	ldr r1, =GPBASE
	ldr r2, =0x800 		@ Yellow led on GPIO11
	
loop:
	
	ldr r8, [r1, #GPLEV0]
	tst r8, #0b00100
	
	streq r2, [r1, #GPSET0]
	strne r2, [r1, #GPCLR0]
	
	b loop
