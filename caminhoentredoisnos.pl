node((0,0)).
node((0,1)).
node((1,2)).
node((2,1)).
node((2,0)).

edge((0,0),(0,1),1).
edge((0,0),(2,0),6).
edge((0,1),(1,2),1).
edge((0,1),(2,1),3).
edge((1,2),(2,1),1).
edge((2,1),(2,0),1).

next(X,L):-
    node(X),
    findall(Y,edge(X,Y,_),L).

memberOf(X,[X|_]).
memberOf(X,[_|T]):-memberOf(X,T).

minimum([X],X).
minimum([X,H|T],M):-
    X<H,
    minimum([X|T],M).
minimum([X,H|T],M):-
    X>=H,
    minimum([H|T],M).

path(X,Y,[X,Y],C):-
    edge(X,Y,C).
path(X,Y,[X|P],C):-
    next(X,L),
    memberOf(A,L),
    node(A),
    edge(X,A,D),
    path(A,Y,P,CL), C is CL+D.

/**shortest_path(A,B,P,C):-
    path(A,B,P,C),
    \+((path(A,B,_,D), D<C)). **/

shortest_path(A,B,P,C):-
    findall(C,path(A,B,_,C),L),
    minimum(L,M),
    C=M,
    path(A,B,P,C).












