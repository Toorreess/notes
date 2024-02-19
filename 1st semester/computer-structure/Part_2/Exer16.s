/* Basic skeleton for programs using interrupts */

.include "configuration.inc" 
.include "symbolic.inc"

/* Vector Table inicialization */
	mov r0,#0
	ADDEXC 0x18, regular_interrupt @only if us
@	ADDEXC 0x1C, fast_interrupt      @only if used


/* Stack init for IRQ mode */	
	mov     r0, #0b11010010   
	msr     cpsr_c, r0
	mov     sp, #0x8000
/* Stack init for FIQ mode */	
	mov     r0, #0b11010001
	msr     cpsr_c, r0
	mov     sp, #0x4000
	mov r8,#0
/* Stack init for SVC mode	*/
	mov     r0, #0b11010011
	msr     cpsr_c, r0
	mov     sp, #0x8000000
	
	/* STEP 1 */
	ldr 	r0,=INTBASE
	mov 	r1, #0b1010			@C1 & C3
	str	r1,[r0,#INTENIRQ1]
	
	/* STEP 2 */
	mov     r1, #0b01010011
	msr     cpsr_c, r1
	
	/* STEP 3 */
	@ Preparing C1
	ldr     r0, =STBASE
    ldr     r1, [r0, #STCLO]
    ldr     r2, =2000000 @ microseconds
    add   r1, r1, r2
    str     r1, [r0, #STC1]
	
	@ Preparing C3
	ldr r4, =1136 @ microseconds
	add r3, r3, r4
	str r3, [r0, #STC3]

	mov r5,#0
	mov r6,#0
	end:		b end
	
/* Regular interrupt (only if used) */
regular_interrupt: 
	push { r0,r1,r2, r3, r4 }
	/* STEP 4 */
	ldr r0, =STBASE
	ldr r2, [r0, #STCS]
	tst r2, #0b0010	@ C1
	
	bne led_routine

	ldr r0, =STBASE
	ldr r2, [r0, #STCS]
	tst r2, #0b1000	@ C3

	beq speaker_routine

	pop { r0,r1,r2,r3,r4 }

led_routine:
	@LED routine
	ldr r0, =GPBASE
	ldr r2, =0x200
	cmp r5, #0
	streq r2,[r0,#GPSET0]
	strne r2,[r0,#GPCLR0]
	
	ldr r2, =0x400
	cmp r5, #1
	streq r2,[r0,#GPSET0]
	strne r2,[r0,#GPCLR0]
	
	ldr r2, =0x800
	cmp r5, #2
	streq r2,[r0,#GPSET0]
	strne r2,[r0,#GPCLR0]
	
	ldr r2, =0x20000
	cmp r5, #3
	streq r2,[r0,#GPSET0]
	strne r2,[r0,#GPCLR0]
	
	ldr r2, =0x400000
	cmp r5, #4
	streq r2,[r0,#GPSET0]
	strne r2,[r0,#GPCLR0]
	
	ldr r2, =0x8000000
	cmp r5, #5
	streq r2,[r0,#GPSET0]
	strne r2,[r0,#GPCLR0]
	
	moveq r5, #-1
	add r5, r5, #1

	@ Reset comparator 1
	ldr     r0, =STBASE 
	mov   r1, #0b0010
	str      r1,[r0,#STCS]
	
	ldr     r0, =STBASE
	ldr     r1, [r0, #STCLO]
	ldr     r2, =250000 @ y microseconds
	add   r1, r1, r2
	str     r1, [r0, #STC1]
	subs  pc, lr, #4

speaker_routine:
	@ Speaker routine
	ldr r0, =GPBASE
	ldr r2, =0x010
	eors r6,#1
	streq r2,[r0,#GPSET0]
	strne r2,[r0,#GPCLR0]

	@ Reset Comparator 3
	ldr r0, =STBASE
	mov r1, #0b01000
	str r1,[r0,#STCS]
	
	ldr     r0, =STBASE
	ldr     r1, [r0, #STCLO]
	ldr     r4, =1136 @ y microseconds
	add   r3, r3, r4
	str     r3, [r0, #STC3]
	subs  pc, lr, #4
	