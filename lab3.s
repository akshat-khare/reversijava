    .equ SEG_A,0x80
    .equ SEG_B,0x40
    .equ SEG_C,0x20
    .equ SEG_D,0x08
    .equ SEG_E,0x04
    .equ SEG_F,0x02
    .equ SEG_G,0x01
    .equ SEG_P,0x10    
    Digits:
    .word SEG_A|SEG_B|SEG_C|SEG_D|SEG_E|SEG_G @0
    .word SEG_B|SEG_C @1
    .word SEG_A|SEG_B|SEG_F|SEG_E|SEG_D @2
    .word SEG_A|SEG_B|SEG_F|SEG_C|SEG_D @3
    .word SEG_G|SEG_F|SEG_B|SEG_C @4
    .word SEG_A|SEG_G|SEG_F|SEG_C|SEG_D @5
    .word SEG_A|SEG_G|SEG_F|SEG_E|SEG_D|SEG_C @6
    .word SEG_A|SEG_B|SEG_C @7
    .word SEG_A|SEG_B|SEG_C|SEG_D|SEG_E|SEG_F|SEG_G @8
    .word SEG_A|SEG_B|SEG_F|SEG_G|SEG_C @9
    .word 0 @Blank display
    .text
    bl initialize @gives array in r9 and score in r7
    bl arrayprinter
    mov r8,#1 @chance of player
    b gameundergoing
endgame:
    STMFD sp!,{r0-r2}
    mov r0,#0
    mov r1,#11
    ldr r2,=EndMessage
    swi 0x204
    LDMFD sp!,{r0-r2}
    



    b totalexit
gameundergoing:
    STMFD sp!,{r0,r2}
    mov r0,r8
    ldr r2,=Digits
    ldr r0,[r2,r0,lsl#2]
    swi 0x200
    LDMFD sp!,{r0,r2}
    mov r0,#0
    mov r1,#12
    ldr r2,=Promptforpressx
    swi 0x204
    swi 0x203
    bl computelogtwo
    mov r5,r1   @x-coordinate
    mov r0,#0
    mov r1,#12
    ldr r2,=Promptforpressy
    swi 0x204
    swi 0x203
    bl computelogtwo
    mov r6,r1   @y-coordinate
    @Now we must call the function checkvalidity
    bl checkvalidity
    b endgame

checkvalidity:
    STMFD sp!,{r0,r5-r6} @ r0 -> turn, r5 -> x-coordinate, r6 -> y-coordinate
    mov r1,r5
    mov r2,r6
horizontalright:
    

    LDMFD sp!,{r0,r5-r6}

computelogtwo:
    @ STMFD sp! {r1}
    mov r1,#1
loopcomputlogtwo:
    mov r0,r0,LSR #1
    cmp r0,#0
    beq endcomputelogtwo
    add r1,r1,#1
    b loopcomputlogtwo
endcomputelogtwo:
    @ LDMFD sp! {r1}
    mov pc, lr 

    

    @ START OF ARRAY PRINTER FUNCTION------------------------------------
arrayprinter:
    STMFD sp!, {r0-r3,r7,r9}
    mov r0,#1
    mov r1,#0
    ldr r2,=Xindex
    swi 0x204
    mov r0,#0
    mov r1,#1
    mov r2,#0
    swi 0x205
    mov r3,#0
    mov r0,#1
    mov r1,#1

gotoprintloop:
    ldrb r2, [r9,r3]
    swi 0x205
    add r0,r0,#1
    add r3, r3,#1
    cmp r0,#9
    beq changerow
    b gotoprintloop
changerow:
    add r1,r1,#1
    mov r0,#1
    cmp r1,#9
    beq exitprinter
    mov r0,#0
    sub r2,r1,#1
    swi 0x205
    mov r0,#1
    b gotoprintloop
exitprinter:
    mov r0,#0
    mov r1,#9
    ldr r2,=ScoreString1
    swi 0x204
    mov r0,#22
    ldr r2,[r7]
    swi 0x205
    mov r0,#0
    mov r1,#10
    ldr r2,=ScoreString2
    swi 0x204
    mov r0, #22
    ldr r2,[r7,#4]
    swi 0x205
    LDMFD sp!, {r0-r3,r7,r9}
    mov pc, lr

    @ END OF ARRAYPRINTER FUNCTION-------------------


    @ START OF initialize FUNCTION---------------    
initialize:
    STMFD sp!,{r0-r1}
    ldr r9,=Array  
    mov r0,#0
    mov r1,#0
loopofinitialize:
    strb r0, [r9,r1]
    add r1,r1,#1
    cmp r1,#64
    beq endofinitialize
    b loopofinitialize
endofinitialize:
    ldr r7,=Score
    str r0,[r7]
    str r0,[r7,#4]
    LDMFD sp!,{r0-r1}
    mov pc,lr
    @ END OF initialize FUNCTION------------------



totalexit:
    ldr r0, =Message
    swi 0x02

    
    .data
    Array: .space 64
    Score: .space 8
    Message: .asciz "reached here\n"
    ScoreString1: .asciz "score of player 1 is: "
    ScoreString2: .asciz "score of player 2 is: "
    EndMessage: .asciz "game over"
    Promptforpressx: .asciz "enter x"
    Promptforpressy: .asciz "enter y" 
    Xindex: .asciz "01234567"
    @Description
    @r8: player
    @r9: array