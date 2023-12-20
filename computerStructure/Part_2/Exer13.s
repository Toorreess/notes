/* Basic skeleton for programs using interrupts */

.include "configuration.inc" 
.include "symbolic.inc"

/* Vector Table inicialization */
	mov r0,#0
	ADDEXC 0x18, regular_interrupt @only if used
	@ADDEXC 0x1C, fast_interrupt      @only if used


/* Stack init for IRQ mode */	
	mov     r0, #0b11010010   
	msr     cpsr_c, r0
	mov     sp, #0x8000
/* Stack init for FIQ mode */	
	mov     r0, #0b11010001
	msr     cpsr_c, r0
	mov     sp, #0x4000
/* Stack init for SVC mode	*/
	mov     r0, #0b11010011
	msr     cpsr_c, r0
	mov     sp, #0x8000000
	
/* Continue my program here */
	mov r5, #0
	
	ldr 	r0,=INTBASE
	mov 	r1, #0b0010 @(#0b1000 for C3)
	str	r1,[r0,#INTENIRQ1]

	mov     r1, #0b01010011
	msr     cpsr_c, r1
	
	ldr     r0, =STBASE
    ldr     r1, [r0, #STCLO]
    ldr     r2, =200000 @ y microseconds
    add   r1, r1, r2
	str     r1, [r0, #STC1] @#STC3 for C3


end:   b end

/* Regular interrupt (only if used) */
regular_interrupt: 
@	push { r0, r1, r2 }

	ldr r0, =GPBASE
	eors r5, #1
	ldr r2, =0x8420E00
	streq r2,[r0, #GPSET0]
	strne r2,[r0, #GPCLR0]
	
	ldr     r0, =STBASE 
	mov   r1, #0b0010   @(#0b1000 for C3)
	str      r1,[r0,#STCS]

	ldr     r0, =STBASE
    ldr     r1, [r0, #STCLO]
    ldr     r2, =200000 @ y microseconds
    add   r1, r1, r2
    str     r1, [r0, #STC1] @#STC3 for C3

@	pop { r0, r1, r2 }
	subs  pc, lr, #4
