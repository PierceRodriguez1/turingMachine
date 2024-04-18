# CS361-Project3 #

* CS361 002
* Spring 2024
* Nolan Stetz and Pierce Rodriguez

## Overview ##

This project simulates a bi-infinite Turing Machine, that parses the encoding of the TM together
with the input string, and the simulates how thise instance of a TM processes the input.

## Reflection
This project was more difficult than the first but not as tough as the second one. The hardest
part of this project was figuring out the layout of the files since they had been given in the previous so we 
tried to base our project layout off those and keep them similar. This helped improve our agile development that
has been getting preached in mutiple classes. We had to consider the classes and how they interacted together. 
Especially since we tried to keep using a LinkedList originally like the previous projects but in developement 
decided to change to an ArrayList to make the turing machine run faster.

We had a couple print statements in our actual simulation that we had printing through each iteration to see
what was happening and our turing machine was running for what felt like forever as it printed out the results 
of each iteration. Trying to optimize our program we actually removed those and found our it sped up the program
immensely and we could see file5.txt finishing in seconds instead of watching it continually print out results
through each iteration running what felt like endlessly. This project was a good project to practice our Agile Development
that has been continually preached to use in Software Enginneering and a good practice to help choose a proper 
data structure for the project at hand because sticking with the LinnkedList would have made our project run
immensely longer. 


## Compiling and Using ##

Starting from the parent directory for tm

* cd tm
* javac *.java
* cd ..
* java tm.TMSimulator <./file0.txt || ./file2.txt || ./file5.txt>

## Sources Used ##

No sources were used
