/* Basic skeleton for programs using interrupts */

.include "configuration.inc" 
.include "symbolic.inc"

/* Vector Table inicialization */
	mov r0,#0
	ADDEXC 0x18, regular_interrupt @only if us
	ADDEXC 0x1C, fast_interrupt      @only if used

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
	mov 	r1, #0b0010 @(#0b1000 for C3)
	str	r1,[r0,#INTENIRQ1]
	
	ldr r0, =INTBASE
	ldr r1, =0x083@081 for C1
	str r1, [r0, #INTFIQCON]
    
	/* STEP 2 */
	mov     r1, #0b00010011
	msr     cpsr_c, r1
	
	/* STEP 3 */
	ldr     r0, =STBASE
	ldr     r1, [r0, #STCLO]
	ldr     r2, =500000 @ y microseconds
	add   r1, r1, r2
	str     r1, [r0, #STC1] @#STC3 for C3
	str     r1, [r0, #STC3]
	
/* Continue my program here */
	mov r5,#0
	end:		b end


regular_interrupt:
	push {r0, r1, r2, r3, r9, r10}
	ldr r0, =GPBASE
	ldr r1, =0b00001000010000100000111000000000
	str r1, [r0,#GPCLR0]
	
	/* led_counter update */
	ldr r1, =led_counter
	ldr r2, [r1]
	add r2, r2, #1
	@ Reseting the led_counter if needed
		cmp r2, #7
		moveq r2, #1
	str r2, [r1]
	
	@ Turns on the corresponding LED
	ldr r2, [r1, +r2, LSL #2]
	str r2, [r0, #GPSET0]
	
	/* sound_index update */
	ldr r9, =sound_index
	ldr r10, [r9]
	add r10, r10, #1 
	@ Reseting the index of the sound array if needed
		cmp r10, #11
		moveq r10, #1
	str r10, [r9]
	
	/* STEP 4 */
	ldr     r0, =STBASE
	ldr     r1, [r0, #STCLO]
	ldr     r2, =500000 @ y microseconds
	add   r1, r1, r2
	str     r1, [r0, #STC1] @#STC3 for C3
	
	/* STEP 5 */
	ldr     r0, =STBASE 
	mov   r1, #0b0010   @(#0b1000 for C3)
	str      r1,[r0,#STCS]
	
	pop {r0, r1, r2, r3, r9, r10}
	subs pc, lr, #4

fast_interrupt:
	ldr r8, = GPBASE
	ldr r9, = speaker_state
	
	/* Inverting the bit of speaker_state  */
	ldr r10, [r9]
	eors r10, #1
	str r10, [r9]
	
	/* Reading the corresponding sound in notes array */
	ldr r9, =sound_index
	ldr r10, [r9]
	ldr r9, [r9, +r10, LSL #2]
	
	/* Turning on or off, depending on speaker_state */
	mov r10, #0b10000
	streq r10, [r8, #GPSET0]
	strne r10, [r8, #GPCLR0]
	
	/* Reseting C3 */ 
	ldr r8, =STBASE
	mov r10, #0b1000
	str r10, [r8, #STCS]
	
	ldr r10, [r8, #STCLO]
	add r10, r9
	str r10, [r8, #STC3]
	
	subs pc, lr, #4

led_counter: .word 6 
leds: .word 0x200
		.word 0x400
		.word 0x800
		.word 0x20000
		.word 0x400000
		.word 0x8000000
		
speaker_state:	.word 0
sound_index:		.word 10

notes: 		.word 1984	@ Do
				.word 1706	@ Re
				.word 1515	@ Mi
				.word 1432	@ Fa
				.word 1351	@ Fa#
				.word 1275	@ Sol
				.word 1136	@ La
				.word 1012	@ Si
				.word 956		@ Do`
				.word 851		@ Re`
