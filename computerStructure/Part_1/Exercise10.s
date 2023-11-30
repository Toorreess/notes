/* Basic skeleton for programs using ports (without interruptions) */

.include "configuration.inc" 
.include "symbolic.inc"

/* Stack init for SVC mode	*/
	mov     r0, #0b11010011
	msr     cpsr_c, r0
	mov     sp, #0x8000000
	
/* Continue my program here */

	ldr r4, =GPBASE
	ldr r0, =STBASE
	ldr r5, =0x010				@ Speaker
	
loop:
	
	ldr r8, [r4, #GPLEV0]
	tst r8, #0b00100			@ GPIO2
	ldreq r1, =1908				@ Note DO 
	beq note
	
	tst r8, #0b01000			@GPIO3
	ldreq r1, =1278				@ Note SOL
	beq note
	
	strne r5, [r4, #GPCLR0]
	b loop
	
note:
	str r5, [r4, #GPSET0]
	bl wait
	str r5, [r4, #GPCLR0]
	bl wait
	b  loop

wait:
	push {r4, r5}
	ldr r4, [r0, #STCLO]
	add r4, r1
	
	ret1:
		ldr r5, [r0, #STCLO]
		cmp r5, r4
		blo ret1
		pop {r4,r5}
		bx lr
		
end:   b end
