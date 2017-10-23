.SUFFIXES:.c .o

CC=gcc

SRCS=testme.c
OBJS=$(SRCS:.c=.o)
EXEC=testme

start: $(OBJS)
	$(CC) -o $(EXEC) $(OBJS)
	@echo "-----------------------------OK-----------------------"

.c.o:
	$(CC) -Wall -o $@ -c $<

clean:
	rm -rf $(EXEC) $(OBJS)