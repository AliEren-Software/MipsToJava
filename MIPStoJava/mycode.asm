.data

.text
again: add $4,$25,$1
       sll $9,$1,5
show:  addi $8,$9,-500

       beq $3,$7,show
       srl $7,$4,3
       sllv $5,$6,$10
       bgtz $7,show
       lw $9,5($5)
       sw $1,3($9)

       sub $2,$4,$13
       and $5,$3,$8
       or $1,$5,$9
       j again
 x1:      bne $4,$6,x1
       blez $4,show
       srlv $5,$8,$6
       andi $3,$2,10

