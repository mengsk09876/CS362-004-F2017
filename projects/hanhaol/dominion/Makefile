CFLAGS = -w -Wall -fpic -coverage -lm

rngs.o: rngs.h rngs.c
	gcc -c rngs.c -g  $(CFLAGS)

dominion.o: dominion.h dominion.c rngs.o
	gcc -c dominion.c -g  $(CFLAGS)

playdom: dominion.o playdom.c
	gcc -o playdom playdom.c -g dominion.o rngs.o $(CFLAGS)

testdominion: dominion.o testdominion.c 
	gcc -o testdominion testdominion.c -g dominion.o rngs.o $(CFLAGS)

interface.o: interface.h interface.c
	gcc -c interface.c -g  $(CFLAGS)

player: player.c interface.o
	gcc -o player player.c -g  dominion.o rngs.o interface.o $(CFLAGS)



randomtest:
	gcc -o randomtestadventurer randomtestadventurer.c -g dominion.o rngs.o interface.o $(CFLAGS)
	gcc -o randomtestcard1 randomtestcard1.c -g dominion.o rngs.o interface.o $(CFLAGS)
	gcc -o randomtestcard2 randomtestcard2.c -g dominion.o rngs.o interface.o $(CFLAGS)

randomtestresults:
	./randomtestcard1 42 
	echo "GCOV AFTER RANDOMTEST CARD1" >> randomtestcard1.out
	gcov -fb dominion.c >> randomtestcard1.out 
	make clean
	make all
	./randomtestcard2 42
	echo "GCOV AFTER RANDOMTEST CARD2" >> randomtestcard2.out
	gcov -fb dominion.c >> randomtestcard2.out
	make clean
	make all
	./randomtestadventurer 42
	echo "GCOV AFTER RANDOM ADVENTURER" >> randomtestadventurer.out
	gcov -fb dominion.c >> randomtestadventurer.out 


all: playdom player unittest cardtest 

clean:
	rm -f *.o playdom.exe playdom test.exe test player unittest1 unittest2 unittest3 unittest4 cardtest1 cardtest2 cardtest3 cardtest4 player.exe testInit testInit.exe *.gcda *.gcno *.so *.a *.dSYM randomtestadventurer randomtestcard1 randomtestcard2
