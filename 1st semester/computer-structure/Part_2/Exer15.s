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
	
	/* STEP 1 */
	ldr     r0, =GPBASE 
	mov   r1, #0b01100 @Select button(s)
	str      r1,[r0,#GPFEN0]

	
	/* STEP 2 */
	ldr     r0, =INTBASE 
	ldr     r1,=0x100000
	str      r1,[r0,#INTENIRQ2]

	
	/* STEP 3 */
	mov   r1, #0b01010011
	msr    cpsr_c, r1
	
	ldr r0, =GPBASE
	ldr r2, =0x600
	str r2,[r0, #GPSET0]
	
end:   b end

/* Regular interrupt (only if used) */
regular_interrupt: 
@	push { r0, r1, r2, r3}

	ldr    r0, =GPBASE 
	ldr r2, =0x200
	ldr r3, =0x400
	
	ldr r1, [r0,#GPLEV0]
	tst r1,#0b00100
	
	streq r2,[r0, #GPSET0]
	streq r3,[r0, #GPCLR0]
	
	tst r1,#0b01000
	streq r2,[r0, #GPCLR0]
	streq r3,[r0, #GPSET0]
	
	mov   r1, #0b01100
	str      r1,[r0,#GPEDS0]

@	pop { r0, r1, r2, r3}
	subs  pc, lr, #4
