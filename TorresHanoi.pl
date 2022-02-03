torre(left).
torre(center).
torre(right).

move(1,TA,TD,Taux):-
    torre(TA), torre(TD), torre(Taux),
    write("Move top disk from "),
    write(TA),
    write(" to "),
    write(TD),nl.

move(N,TA,TD,Taux):- N>1, NL is N-1,
    move(NL,TA,Taux,TD),
    move(1,TA,TD,Taux),
    move(NL,Taux,TD,TA).
