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
	ldr r1, =0b00001000010000000000000000000000
	ldr r2, =1000000
	ldr r3, =500000
	ldr r6, =250000
	
loop:
	
	str r1, [r4, #GPSET0]
	bl wait1s
	str r1, [r4, #GPCLR0]
	bl wait1s
	
	str r1, [r4, #GPSET0]
	bl wait500ms
	str r1, [r4, #GPCLR0]
	bl wait500ms
	
	str r1, [r4, #GPSET0]
	bl wait250ms
	str r1, [r4, #GPCLR0]
	bl wait250ms
	b loop


wait1s:
	push {r4, r5}
	ldr r4, [r0, #STCLO]
	add r4, r2
	
	ret1:
		ldr r5, [r0, #STCLO]
		cmp r5, r4
		blo ret1
		pop {r4,r5}
		bx lr

wait500ms:
	push {r4, r5}
	ldr r4, [r0, #STCLO]
	add r4, r3
	
	ret2:
		ldr r5, [r0, #STCLO]
		cmp r5, r4
		blo ret2
		pop {r4,r5}
		bx lr


wait250ms:
	push {r4, r5}
	ldr r4, [r0, #STCLO]
	add r4, r6
	
	ret3:
		ldr r5, [r0, #STCLO]
		cmp r5, r4
		blo ret3
		pop {r4,r5}
		bx lr

end:   b end
