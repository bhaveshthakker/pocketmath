# pocketmath

- All the programmes are tested using Java 8, though not any specific functionality of java 8 is used and it can be compiled using java 7 also.

- All the programmes are in package : com.pocketmath (except for last programme)

1) PartitionEvenOddNumbersArray
Problem Statement : Take an array of integers and partition it so that all the even integers in the array precede all the odd integers in the array. Your solution must take linear time in the size of the array and operate in-place with only a constant amount of extra space.

Solution : PartitionEvenOddNumbersArray.java
It is an interactive program after execution


2) SequentialThreading
Problem Statement : The same instance of Foo will be passed to three different threads. Thread A will call first(), Thread B will call second(), and Thread C will call third(). Design a mechanism to ensure that first() is called before second() and second() is called before third().

Solution : SequentialThreading.java has 3 below classes
SequentialThreading.java (main)
Foo.java
LockValues.java



3) CustomQueueProcessor
Problem Statement : Implement a MyQueue class which implements a queue using two stacks.

Solution : CustomQueueProcessor.java has 2 below classes
CustomQueueProcessor.java (main)
CustomQueue<T>


4) Trader Stats
Problem Statement : "challenges": [
        "Find all traders from Singapore and sort them by name.",
        "Find the transaction with the highest value.",
        "Find all transactions in the year 2016 and sort them by value (high to small).",
        "Find the average of transactions' values from the traders living in Beijing."
    ]
Solution : com.pocketmath.trader.Processor
Setup : 
Required jar file to be added in build path : http://www.java2s.com/Code/Jar/j/Downloadjacksonall190jar.htm
Have tried to add as minimum jars as I can, so have not use apache jar file for REST call
