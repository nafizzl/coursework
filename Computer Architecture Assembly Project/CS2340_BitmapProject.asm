# hex values of certain colors
.eqv BLACK	0x00000000
.eqv RED	0x00FF0000
.eqv GREEN	0x0000FF00
.eqv BLUE	0x000000FF
.eqv WHITE	0x00FFFFFF
.eqv YELLOW	0x00FFFF00
.eqv CYAN	0x0000FFFF
.eqv MAGENTA	0x00FF00FF

# settings of bitmap display
.eqv WIDTH 	64
.eqv HEIGHT	64
.eqv MEM	0x10008000


.data
colors: .word 	WHITE, MAGENTA, CYAN, YELLOW, BLUE, GREEN, RED, BLACK # words for color values
intro:	.asciiz "Hello, and welcome to the MIPS drawing application. Please read the included manual for instructions on what and how to use this program."
squareselect: .asciiz "Please select a number from 1-5 to determine the size of your square. Each size increments by 5 pixels. Press n again to back out."
output: .asciiz	"Thank you for using this drawing application!"
after:	.asciiz	""

.text
main:
	addi 	$a0, $0, WIDTH    
	sra 	$a0, $a0, 1
	addi 	$a1, $0, HEIGHT   
	sra 	$a1, $a1, 1	 # sets up cursor in middle of the screen
	
	
	addi 	$a2, $0, MAGENTA # default starting color for cursor (because why not)
	la $s3, colors	# load address of colors array (used for background)
	la $s4, colors	# load address of colors array (used for cursor)
	li $t6, 0	# a counter that will help us go through colors array (used more in background)
	li $t7, 0	# since bitmap loads in with default black background, have this be the background color replacer for cursor at this point
	li $t8, 0       # a counter that will help us go through colors array (used more in cursor)
	li $t9, MAGENTA	# temp register is used in cursor color selection, so it should have a default color
	
program: # the main section of the application where keybind presses will branch off other directions
	jal	draw_pixel	# this is the "cursor" appearing on the bitmap, which will be the brush
	lw $t0, 0xffff0000 	# start of keyboard input memory	
    	beq $t0, 0, program	# checks for input
    	
    	lw 	$s2, 0xffff0004	# data address that holds keyboard input
	beq	$s2, 32, exit	# press space to exit the function
	beq	$s2, 98, background_color	# press b to cycle through different colors for your background
	beq	$s2, 99, cursor_color	# press c to cycle through different colors for your cursor
	beq	$s2, 118, roam # press v to roam around the drawing without coloring the pixels along the way (the same color as cursor, meant to keep background same)
	beq	$s2, 119, up	# input w to move cursor up
	beq	$s2, 115, down	# input s to move cursor down
	beq	$s2, 97, left  	# input a to move cursor left
	beq	$s2, 100, right	# input d to move cursor right
	beq	$s2, 110, square	# input n to start drawing a square
	j	program
	
up:
	add $a2, $0, $t9	# changes based on cursor color
	jal	draw_pixel
	addi $a1, $a1, -1	# move up
	add $a2, $0, $t9	# changes based on cursor color
	jal	draw_pixel
	j	program
down:
	add $a2, $0, $t9	# changes based on cursor color
	jal	draw_pixel
	addi $a1, $a1, 1	# move down
	add $a2, $0, $t9	# changes based on cursor color
	jal	draw_pixel
	j	program
left:
	add $a2, $0, $t9	# changes based on cursor color
	jal	draw_pixel
	addi $a0, $a0, -1	# move left
	add $a2, $0, $t9	# changes based on cursor color
	jal	draw_pixel
	j	program
right:
	add $a2, $0, $t9	# changes based on cursor color
	jal	draw_pixel
	addi $a0, $a0, 1	# move right
	add $a2, $0, $t9	# changes based on cursor color
	jal	draw_pixel
	j	program	
	
background_color:
	beq $t6, 8, color_reset1      # reset counter if we go past colors array
	move $t2, $a0
	move $t3, $a1		# save the original (x,y) coordinates and original color of cursor 
	move $t4, $a2
	
	li $a0, 0
        li $a1, 0	# start from the first pixel so we can color everything 
        
	li $t5, 64	# goes from pixel 0 to pixel 63 height/width wise, helps as a counter measure
	
	
	lw $t7, ($s3)			# get the color value we want
	add $a2, $0, $t7		# set background color
	addi $s3, $s3, 4		# go to next color in colors array
	addi $t6, $t6, 1		# increment counter to keep track

	j	backgroundloophorizontal 	# proceed to replace background
	
color_reset1:
	la $s3, colors	# reloads address and resets array counter 
	li $t6, 0
	
	j	background_color # goes back to background program so that it's a cycle rather than an extra step in between
	
backgroundloophorizontal:	# an embedded loop that will help us color all pixels row by row as we go down a column, fiulling in all pixels
	beq $t5, $a0, backgroundloopvertical	# if x = 64, all pixels in row have been colored in (0-63)
	jal	draw_pixel		
	addi $a0, $a0, 1	# move right to color next pixel
	j	backgroundloophorizontal
	
backgroundloopvertical:
	beq $t5, $a1, cursor_replacement # if y = 64, all pixels in column have been colored in (0-63), background is fully changed, now get the cursor back
	li $a0, 0
	addi $a1, $a1, 1	# increment down column for next row to color in
	j	backgroundloophorizontal	

cursor_replacement:
	move $a0, $t2
	move $a1, $t3		# get the cursor info back
	move $a2, $t4
	
	jal	draw_pixel	# now redraw the cursor
	j	program		# and go back to the program

cursor_color:
	beq $t8, 8, color_reset2	# reset counter if we go past colors array
	lw $t9, ($s4)			# get the color value we want
	add $a2, $0, $t9		# set background color
	addi $s4, $s4, 4		# go to next color in colors array
	addi $t8, $t8, 1		# increment counter to keep track
	
	j	program			# go back to make a selection using keybinds

color_reset2:

	la $s4, colors	# reloads address and resets array counter 
	li $t8, 0
	
	j	cursor_color	# go back to cursor program to cycle through colors



roam:
	jal	draw_pixel	# have the cursor appear
	lw $t0, 0xffff0000 	# start of keyboard input memory	
    	beq $t0, 0, roam	# checks for input
    	
    	lw 	$s2, 0xffff0004	# data address that holds keyboard input
	beq	$s2, 118, program  # press v again in order to go back to the main choice selection  
	beq	$s2, 119, up_r	# input w to move cursor up
	beq	$s2, 115, down_r	# input s to move cursor down
	beq	$s2, 97, left_r  	# input a to move cursor left
	beq	$s2, 100, right_r	# input d to move cursor right 	
	j	roam		# if no proper choice is given, loop until one is given

up_r:
	add $a2, $0, $t7	# changes based on background color
	jal	draw_pixel
	addi $a1, $a1, -1	# move up
	add $a2, $0, $t9	# changes based on cursor color
	jal	draw_pixel
	j	roam		# go back to roam in order to roam as long as you need until you draw
down_r:
	add $a2, $0, $t7	# changes based on background color
	jal	draw_pixel
	addi $a1, $a1, 1	# move down
	add $a2, $0, $t9	# changes based on cursor color
	jal	draw_pixel
	j	roam		# go back to roam in order to roam as long as you need until you draw
left_r:
	add $a2, $0, $t7	# changes based on background color
	jal	draw_pixel
	addi $a0, $a0, -1	# move left
	add $a2, $0, $t9	# changes based on cursor color
	jal	draw_pixel
	j	roam		# go back to roam in order to roam as long as you need until you draw
right_r:
	add $a2, $0, $t7	# changes based on background color
	jal	draw_pixel
	addi $a0, $a0, 1	# move right
	add $a2, $0, $t9	# changes based on cursor color
	jal	draw_pixel
	j	roam		# go back to roam in order to roam as long as you need until you draw

square:
	lw $t0, 0xffff0000 	# start of keyboard input memory	
    	beq $t0, 0, square	# checks for input, makes you stay in square choices otherwise
    	
    	lw 	$s2, 0xffff0004	# data address that holds keyboard input
    	beq	$s2, 49, square5
    	beq	$s2, 50, square10	# squarex means make a square of side length x 
    	beq	$s2, 51, square15
    	beq	$s2, 52, square20
    	beq	$s2, 53, square25 
    	beq	$s2, 110, program	# press n to go back to main choice selection
	j	square		# makes you stay in choice selection if proper choice isn't made
square5:
	li 	$s7, 0
	li	$s6, 5		# counters to keep track of side lengths (in this case, 5)
	
	j	squaredraw
square10:
	li 	$s7, 0
	li	$s6, 10		# counters to keep track of side lengths (in this case, 10)
	
	j	squaredraw
square15:
	li 	$s7, 0
	li	$s6, 15		# counters to keep track of side lengths (in this case, 15)
	
	j	squaredraw
square20:
	li 	$s7, 0
	li	$s6, 20		# counters to keep track of side lengths (in this case, 20)
	
	j	squaredraw
square25:
	li 	$s7, 0
	li	$s6, 25		# counters to keep track of side lengths (in this case, 25)
	
	j	squaredraw
	
squaredraw:	
	sub	$sp, $sp, 20
	sw	$s7, 0($sp)
	sw	$s7, 4($sp)
	sw	$s7, 8($sp)	# use a stack to keep 4 "counters" for each side and one for the limit
	sw	$s7, 12($sp)
	sw	$s6, 16($sp)
	
	j	top_square
	j	program		# go back to main choice selection once square is drawn.
	
top_square:
	lw	$s7, 0($sp)
	lw	$s6, 16($sp)	# load the counter values from stack
	beq  	$s7, $s6, right_square	# branch if limit is reached
	jal	draw_pixel	
	addi 	$a0, $a0, 1	# move along the side
	addi 	$s7, $s7, 1	# increment counter
	sw	$s7, 0($sp)	# store changed counter value to be used again in loop
	j	top_square

right_square:
	lw	$s7, 4($sp)
	lw	$s6, 16($sp)
	beq  	$s7, $s6, bottom_square	# similar to top_square, view comments there
	jal	draw_pixel
	addi 	$a1, $a1, 1
	addi 	$s7, $s7, 1
	sw	$s7, 4($sp)
	j	right_square
	
bottom_square:
	lw	$s7, 8($sp)
	lw	$s6, 16($sp)
	beq  	$s7, $s6, left_square	# similar to top_square, view comments there
	jal	draw_pixel
	addi 	$a0, $a0, -1
	addi 	$s7, $s7, 1
	sw	$s7, 8($sp)
	j	bottom_square

left_square:
	lw	$s7, 12($sp)
	lw	$s6, 16($sp)
	beq  	$s7, $s6, shape_done	# similar to top_square, view comments there
	jal	draw_pixel
	addi 	$a1, $a1, -1
	addi 	$s7, $s7, 1
	sw	$s7, 12($sp)
	j	left_square
	
shape_done:
	add 	$sp, $sp, 20	  # since program is done, release space in stack 
	
	j	program		  # when shape is drawn, go back to main selection of choices in program
	
draw_pixel:	# partially borrowed from GitHub sample bitmap programs

	mul	$s1, $a1, WIDTH   # y * WIDTH
	add	$s1, $s1, $a0	  # add X
	mul	$s1, $s1, 4	  # multiply by 4 to get word offset
	add	$s1, $s1, MEM	  # add to base address
	
	sw	$a2, 0($s1)	  # store color at memory location using temp register that has final color saved
	jr 	$ra		  # return to called line

exit:
	
	li $v0, 59	# Syscall 59 for message dialog string
	la $a0, output	# this is so that we get an application feel of exiting the program
	la $a1, after
	syscall
	
	li $v0, 10
	syscall
