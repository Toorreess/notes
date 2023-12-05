.include "configuration.inc"
.include "symbolic.inc"

	ldr r0, =GPBASE

	@ Turn on red led on GPIO9
	ldr r1, =0x200
	str r1,[r0, #GPSET0]
	
	@ Turn on yellow led on GPIO11
	ldr r2, =0x800
	str r2,[r0, #GPSET0]
	
	@ Turn on green led on GPIO22
	ldr r3, =0x400000
	str r3,[r0, #GPSET0]
	
	@ Alternative way
	ldr r4, = 0b00000000010000000000101000000000
	str r4,[r0,#GPSET0]

end: b end
	