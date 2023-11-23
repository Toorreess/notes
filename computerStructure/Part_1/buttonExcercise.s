.include "configuration.inc"
.include "symbolic.inc"

	ldr r0, =GPBASE
	
	@ Red led on GPIO19
	ldr r1, =0x200
	
	@ Yellow led on GPIO11
	ldr r2, =0x800

loop:
	ldr r8,[r0, #GPLEV0]
	
	@ Checks if button on GPIO2 is pressed
	tst r8, #0b00100
	streq r2,[r0, #GPSET0]	@ if z=1 -> button1 pressed
	strne r2,[r0, #GPCLR0] 	@ if z=0 -> button1 released
	
	@ Checks if button on GPIO3 is pressed
	tst r8, #0b01000
	streq r1,[r0, #GPSET0]	@ if z=1 -> button2 pressed
	strne r1,[r0, #GPCLR0]	@ if z=0 -> button2 released
	
	b loop

end: b end
