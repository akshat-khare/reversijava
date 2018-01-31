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
    mov r3, #0
    mov r0, #0
xinterrupt:    
    swi 0x203
    cmp r0, r3
    beq xinterrupt
    bl computelogtwo
    ldr r6,=IndexArray
    str r1,[r6]
    mov r5,r1   @x-coordinate as in mathematical 2d array
    mov r0,#8
    mov r1,#12
    mov r2, r5
    swi 0x205
    mov r0,#0
    mov r1,#13
    ldr r2,=Promptforpressy
    swi 0x204
    mov r3, #0
    mov r0,#0
yinterrupt:
    swi 0x203
    cmp r0, r3
    beq yinterrupt
    bl computelogtwo
    sub r1,r1,#8  @  Required
    str r1,[r6,#4]
    mov r5,r1   @y-coordinate as in mathematical 2d array
    mov r0,#8
    mov r1,#13
    mov r2, r5
    swi 0x205
    @Now we must call the function checkvalidity
    bl checkvalidity
    bl scoreupdate
    bl arrayprinter
    mov r0,#1
    cmp r0, r4
    beq twasvalidmove
    b twasinvalidmove
twasvalidmove:
    mov r8, r5
    b gameundergoing
twasinvalidmove:
    b gameundergoing


    b endgame

checkvalidity:
    STMFD sp!, {lr}
    @STMFD sp!,{r0-r6} @ r8 -> dynamic current turn, r5 -> x-coordinate, r6 -> y-coordinate, r4 -> dynamic other player's turn
    @ mov r2, r5
    @ mov r3, r6
    rsb r5,r8,#3    @other player
    @r3 -> maintains a counter on how many times the value flipped
    ldr r3, [r6]
    str r3,[r6,#16]
    ldr r4, [r6,#4]
    str r4,[r6,#20]
    mov r1,#8
    mla r0,r1,r4,r3
    str r0,[r6,#8]
    ldrb r4,[r9,r0] @r4 is 8*r6 +r5
    str r4, [r6,#12]  @r6 has structure [x,y,8*y+x,r9[8*y+x],x',y',8*y'+x,r9[8*y'+x']]
    mov r0, #0                         @[0,4,8,    12,       16,20,24,    28]
    cmp r4,r0
    bne invalidmove
    b horizontalright
endhorizontalright:
    @[r6,#32] has the boolean
    b horizontalleft
endhorizontalleft:
    @[r6,#36] has the boolean
    b verticalup
endverticalup:
    @[r6,#40] has the boolean
    b verticaldown
endverticaldown:
    @[r6,#44] has the boolean
    b forwardup
endforwardup:
    @[r6,#48] has the boolean
    b forwarddown
endforwarddown:
    @[r6,#52] has the boolean
    b backup
endbackup:
    @[r6,#56] has the boolean
    b backdown
endbackdown:
    @[r6,#60] has the boolean




    ldr r4, [r6,#32]
    mov r3, #1
    cmp r3,r4
    beq hasvalidmove
    ldr r4, [r6,#36]
    cmp r3,r4
    beq hasvalidmove
    ldr r4, [r6,#40]
    cmp r3,r4
    beq hasvalidmove
    ldr r4, [r6,#44]
    cmp r3,r4
    beq hasvalidmove
    ldr r4, [r6,#48]
    cmp r3,r4
    beq hasvalidmove
    ldr r4, [r6,#52]
    cmp r3,r4
    beq hasvalidmove
    ldr r4, [r6,#56]
    cmp r3,r4
    beq hasvalidmove
    ldr r4, [r6,#60]
    cmp r3,r4
    beq hasvalidmove
    b invalidmove


horizontalright:
    @STMFD sp! , {r5-r6}
    mov r0, #6 @check if y==5 or y==7
    ldr r4,[r6,#0]
    cmp r0,r4
    ble notvalidhorizontalright
    @STMFD sp!, r4
    ldr r4,[r6,#8]
    add r4, r4,#1
    str r4,[r6,#24]

    ldr r0,[r6,#0]
    add r0, r0,#1
    str r0,[r6,#16]

     
    ldrb r0,[r9,r4]  @check if r5+1 is otherone or not
    str r0,[r6,#28]
    cmp r0, r5
    beq loophorizontalright
    b notvalidhorizontalright
loophorizontalright:
    ldr r4,[r6,#24]
    add r4, r4,#1
    str r4,[r6,#24]
    ldr r4,[r6,#16]
    add r4,r4,#1
    str r4,[r6,#16]
    mov r0,#8
    cmp r0, r4
    beq notvalidhorizontalright
    ldr r4,[r6,#24]
    ldrb r0, [r9,r4]
    mov r1, #0
    cmp r1, r0
    beq notvalidhorizontalright
    cmp r0, r5
    beq loophorizontalright
    cmp r0,r8
    beq foundhorizontalright
foundhorizontalright:
    ldr r4, [r6,#24]
    ldr r3, [r6,#8]
    b loopoffoundhorizontalright
loopoffoundhorizontalright:
    sub r4,r4,#1
    cmp r3,r4
    beq endvalidhorizontalright
    strb r8, [r9,r4]
    b loopoffoundhorizontalright
    @LDMFD sp! , r4

notvalidhorizontalright:
    mov r4,#0
    str r4,[r6,#32]
    b endhorizontalright


endvalidhorizontalright:
    str r4, [r6,#24]
    mov r4,#1
    str r4, [r6,#32]
    ldr r4, [r6,#8]
    strb r8, [r9,r4]
    b endhorizontalright

horizontalleft:
    mov r0, #1
    ldr r4, [r6,#0]
    cmp r0, r4
    bge notvalidhorizontalleft
    ldr r4, [r6,#8]
    sub r4, r4, #1
    str r4, [r6,#24]
    ldr r0, [r6,#0]
    sub r0,r0,#1
    str r0, [r6,#16]
    ldrb r0, [r9,r4]
    str r0, [r6,#28]
    cmp r0, r5
    beq loophorizontalleft
    b notvalidhorizontalleft
loophorizontalleft:
    ldr r4, [r6,#24]
    sub r4, r4,#1
    str r4, [r6,#24]
    ldr r4, [r6,#16]
    sub r4,r4,#1
    str r4, [r6,#16]
    mov r0, #-1
    cmp r0, r4
    beq notvalidhorizontalleft
    ldr r4,[r6,#24]
    ldrb r0,[r9,r4]
    mov r1,#0
    cmp r1, r0
    beq notvalidhorizontalleft
    cmp r0, r5
    beq loophorizontalleft
    cmp r0, r8
    beq foundhorizontalleft
foundhorizontalleft:
    ldr r4, [r6,#24]
    ldr r3, [r6,#8]
    b loopoffoundhorizontalleft
loopoffoundhorizontalleft:
    add r4, r4, #1
    cmp r3, r4
    beq endvalidhorizontalleft
    strb r8, [r9,r4]
    b loopoffoundhorizontalleft
notvalidhorizontalleft:
    mov r4, #0
    str r4, [r6,#36]
    b endhorizontalleft
endvalidhorizontalleft:
    str r4, [r6,#24]
    mov r4, #1
    str r4, [r6, #36]
    ldr r4, [r6,#8]
    strb r8, [r9,r4]
    b endhorizontalleft    

verticalup:
    mov r0, #1
    ldr r4, [r6,#4]
    cmp r0, r4
    bge notvalidverticalup
    ldr r4, [r6,#8]
    sub r4,r4,#8
    str r4, [r6,#24]

    ldr r0, [r6,#4]
    sub r0,r0,#1
    str r0, [r6,#20]

    ldrb r0, [r9,r4]
    str r0, [r6,#28]
    cmp r0, r5
    beq loopverticalup
    b notvalidverticalup
loopverticalup:
    ldr r4, [r6,#24]
    sub r4, r4, #8
    str r4, [r6,#24]
    ldr r4, [r6,#20]
    sub r4,r4,#1
    str r4, [r6,#20]
    mov r0,#-1
    cmp r0,r4
    beq notvalidverticalup
    ldr r4, [r6,#24]
    ldrb r0, [r9, r4]
    mov r1, #0
    cmp r1, r0
    beq notvalidverticalup
    cmp r0, r5
    beq loopverticalup
    cmp r0, r8
    beq foundverticalup
foundverticalup:
    ldr r4, [r6,#24]
    ldr r3, [r6,#8]
    b loopoffoundverticalup
loopoffoundverticalup:
    add r4, r4, #8
    cmp r3, r4
    beq endvalidverticalup
    strb r8, [r9,r4]
    b loopoffoundverticalup
notvalidverticalup:
    mov r4,#0
    str r4, [r6, #40]
    b endverticalup
endvalidverticalup:
    str r4, [r6, #24]
    mov r4,#1
    str r4, [r6,#40]
    ldr r4, [r6,#8]
    strb r8, [r9,r4]
    b endverticalup

verticaldown:
    mov r0, #6
    ldr r4, [r6,#4]
    cmp r0, r4
    ble notvalidverticaldown
    ldr r4, [r6,#8]
    add r4, r4, #8
    str r4, [r6,#24]

    ldr r0, [r6,#4]
    add r0,r0,#1
    str r0, [r6,#20]

    ldrb r0, [r9,r4]
    str r0, [r6,#28]
    cmp r0, r5
    beq loopverticaldown
    b notvalidverticaldown
loopverticaldown:
    ldr r4, [r6,#24]
    add r4, r4, #8
    str r4, [r6,#24]
    ldr r4, [r6,#20]
    add r4,r4,#1
    str r4, [r6,#20]
    mov r0,#8
    cmp r0,r4
    beq notvalidverticaldown
    ldr r4, [r6,#24]
    ldrb r0, [r9, r4]
    mov r1, #0
    cmp r1, r0
    beq notvalidverticaldown
    cmp r0, r5
    beq loopverticaldown
    cmp r0, r8
    beq foundverticaldown
foundverticaldown:
    ldr r4, [r6,#24]
    ldr r3, [r6,#8]
    b loopoffoundverticaldown
loopoffoundverticaldown:
    sub r4, r4, #8
    cmp r3, r4
    beq endvalidverticaldown
    strb r8, [r9,r4]
    b loopoffoundverticaldown
notvalidverticaldown:
    mov r4,#0
    str r4, [r6, #44]
    b endverticaldown
endvalidverticaldown:
    str r4, [r6, #24]
    mov r4,#1
    str r4, [r6,#44]
    ldr r4, [r6,#8]
    strb r8, [r9,r4]
    b endverticaldown

forwardup:
    mov r0, #1
    ldr r4, [r6,#4]
    cmp r0,r4
    bge notvalidforwardup
    mov r0,#6
    ldr r4, [r6]
    cmp r0,r4
    ble notvalidforwardup
    ldr r0, [r6]
    add r0,r0,#1
    str r0, [r6,#16]
    ldr r0, [r6,#4]
    sub r0,r0,#1
    str r0, [r6,#20]
    ldr r4, [r6,#8]
    sub r4,r4,#7
    str r4, [r6,#24]
    ldrb r0, [r9,r4]
    cmp r0, r5
    beq loopforwardup
    b notvalidforwardup
loopforwardup:
    ldr r4, [r6,#24]
    sub r4, r4, #7
    str r4, [r6,#24]
    ldr r4, [r6,#16]
    add r4,r4,#1
    str r4, [r6,#16]
    ldr r4, [r6,#20]
    sub r4,r4,#1
    str r4, [r6,#20]
    ldr r4, [r6,#16]
    mov r0,#8
    cmp r0,r4
    beq notvalidforwardup
    ldr r4, [r6,#20]
    mov r0, #-1
    cmp r0, r4
    beq notvalidforwardup
    ldr r4, [r6,#24]
    ldrb r0, [r9,r4]
    mov r1, #0
    cmp r1, r0
    beq notvalidforwardup
    cmp r0, r5
    beq loopforwardup
    cmp r0, r8
    beq foundforwardup
foundforwardup:
    ldr r4, [r6,#24]
    ldr r3, [r6,#8]
    b loopoffoundforwardup
loopoffoundforwardup:
    add r4, r4, #7
    cmp r3,r4
    beq endvalidforwardup
    strb r8, [r9,r4]
    b loopoffoundforwardup
notvalidforwardup:
    mov r4, #0
    str r4, [r6,#48]
    b endforwardup
endvalidforwardup:
    mov r4, #1
    str r4, [r6,#48]
    ldr r4, [r6,#8]
    strb r8, [r9,r4]
    b endforwardup

forwarddown:
    mov r0, #6
    ldr r4, [r6,#4]
    cmp r0, r4
    ble notvalidforwarddown
    mov r0, #1
    ldr r4, [r6]
    cmp r0, r4
    bge notvalidforwarddown
    ldr r0, [r6]
    sub r0, r0, #1
    str r0, [r6,#16]
    ldr r0, [r6,#4]
    add r0, r0,#1
    str r0, [r6,#20]
    ldr r4, [r6, #8]
    add r4, r4, #7
    str r4, [r6,#24]
    ldrb r0, [r9,r4]
    cmp r0, r5
    beq loopforwarddown
    b notvalidforwarddown
loopforwarddown:
    ldr r4, [r6,#24]
    add r4, r4, #7
    str r4, [r6,#24]
    ldr r4, [r6,#16]
    sub r4,r4,#1
    str r4, [r6,#16]
    ldr r4, [r6,#20]
    add r4,r4,#1
    str r4, [r6,#20]
    ldr r4, [r6,#16]
    mov r0,#-1
    cmp r0, r4
    beq notvalidforwarddown
    ldr r4, [r6,#20]
    mov r0, #8
    cmp r0, r4
    beq notvalidforwarddown
    ldr r4, [r6,#24]
    ldrb r0, [r9,r4]
    mov r1, #0
    cmp r1, r0
    beq notvalidforwarddown
    cmp r0, r5
    beq loopforwarddown
    cmp r0, r8
    beq foundforwarddown
foundforwarddown:
    ldr r4, [r6,#24]
    ldr r3, [r6,#8]
    b loopoffoundforwarddown
loopoffoundforwarddown:
    sub r4, r4, #7
    cmp r3,r4
    beq endvalidforwarddown
    strb r8, [r9,r4]
    b loopoffoundforwarddown
notvalidforwarddown:
    mov r4, #0
    str r4, [r6,#52]
    b endforwarddown
endvalidforwarddown:
    mov r4, #1
    str r4, [r6,#52]
    ldr r4, [r6,#8]
    strb r8, [r9,r4]
    b endforwarddown

backup:
    mov r0,#1
    ldr r4, [r6]
    cmp r0, r4
    bge notvalidbackup
    ldr r4, [r6,#4]
    cmp r0, r4
    bge notvalidbackup
    ldr r0, [r6]
    sub r0, r0, #1
    str r0, [r6, #16]
    ldr r0, [r6,#4]
    sub r0, r0,#1
    str r0, [r6,#20]
    ldr r4, [r6, #8]
    sub r4, r4, #9
    str r4, [r6, #24]
    ldrb r0, [r9,r4]
    cmp r0, r5
    beq loopbackup
    b notvalidbackup
loopbackup:
    ldr r4, [r6,#24]
    sub r4, r4, #9
    str r4, [r6,#24]
    ldr r4, [r6,#16]
    sub r4,r4,#1
    str r4, [r6,#16]
    ldr r4, [r6,#20]
    sub r4,r4,#1
    str r4, [r6,#20]
    ldr r4, [r6,#16]
    mov r0,#-1
    cmp r0,r4
    beq notvalidbackup
    ldr r4, [r6,#20]
    cmp r0, r4
    beq notvalidbackup
    ldr r4, [r6,#24]
    ldrb r0, [r9,r4]
    mov r1, #0
    cmp r1, r0
    beq notvalidbackup
    cmp r0, r5
    beq loopbackup
    cmp r0, r8
    beq foundbackup
foundbackup:
    ldr r4, [r6,#24]
    ldr r3, [r6,#8]
    b loopoffoundbackup
loopoffoundbackup:
    add r4, r4, #9
    cmp r3,r4
    beq endvalidbackup
    strb r8, [r9,r4]
    b loopoffoundbackup
notvalidbackup:
    mov r4, #0
    str r4, [r6,#56]
    b endbackup
endvalidbackup:
    mov r4, #1
    str r4, [r6,#56]
    ldr r4, [r6,#8]
    strb r8, [r9,r4]
    b endbackup

backdown:
    mov r0,#6
    ldr r4, [r6]
    cmp r0, r4
    ble notvalidbackdown
    ldr r4, [r6,#4]
    cmp r0, r4
    ble notvalidbackdown
    ldr r0, [r6]
    add r0, r0, #1
    str r0, [r6, #16]
    ldr r0, [r6,#4]
    add r0, r0,#1
    str r0, [r6,#20]
    ldr r4, [r6, #8]
    add r4, r4, #9
    str r4, [r6, #24]
    ldrb r0, [r9,r4]
    cmp r0, r5
    beq loopbackdown
    b notvalidbackdown
loopbackdown:
    ldr r4, [r6,#24]
    add r4, r4, #9
    str r4, [r6,#24]
    ldr r4, [r6,#16]
    add r4,r4,#1
    str r4, [r6,#16]
    ldr r4, [r6,#20]
    add r4,r4,#1
    str r4, [r6,#20]
    ldr r4, [r6,#16]
    mov r0,#8
    cmp r0,r4
    beq notvalidbackdown
    ldr r4, [r6,#20]
    cmp r0, r4
    beq notvalidbackdown
    ldr r4, [r6,#24]
    ldrb r0, [r9,r4]
    mov r1, #0
    cmp r1, r0
    beq notvalidbackdown
    cmp r0, r5
    beq loopbackdown
    cmp r0, r8
    beq foundbackdown
foundbackdown:
    ldr r4, [r6,#24]
    ldr r3, [r6,#8]
    b loopoffoundbackdown
loopoffoundbackdown:
    sub r4, r4, #9
    cmp r3,r4
    beq endvalidbackdown
    strb r8, [r9,r4]
    b loopoffoundbackdown
notvalidbackdown:
    mov r4, #0
    str r4, [r6,#60]
    b endbackdown
endvalidbackdown:
    mov r4, #1
    str r4, [r6,#60]
    ldr r4, [r6,#8]
    strb r8, [r9,r4]
    b endbackdown

invalidmove:
    mov r4,#0
    LDMFD sp!, {lr}
    mov pc, lr
    @b endgame
hasvalidmove:
    mov r4,#1
    LDMFD sp!,{lr}
    mov pc,lr


computelogtwo:
    @ STMFD sp! {r1}
    mov r1,#0
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
    mov r0,#1
    strb r0, [r9,#27]
    strb r0, [r9,#36]
    mov r0,#2
    strb r0, [r9,#28]
    strb r0, [r9,#35]
    ldr r7,=Score
    mov r0, #2
    str r0,[r7]
    str r0,[r7,#4]
    LDMFD sp!,{r0-r1}
    mov pc,lr
    @ END OF initialize FUNCTION------------------

scoreupdate:
    STMFD sp!, {r0-r4}
    mov r0, #0
    mov r1, #0
    mov r2, #0
loopscoreupdate:
    ldrb r4, [r9, r2]
    mov r3, #1
    cmp r4 , r3
    addeq r0, r0, #1
    mov r3, #2
    cmp r4, r3
    addeq r1, r1, #1
    add r2, r2, #1
    cmp r2, #64
    beq exitscoreupdate
    b loopscoreupdate
exitscoreupdate:
    str r0, [r7]
    str r1, [r7,#4]
    LDMFD sp!, {r0-r4}
    mov pc, lr


totalexit:
    ldr r0, =Message
    swi 0x02

    
    .data
    Array: .space 64
    Score: .space 8
    IndexArray: .space 64
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