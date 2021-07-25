# Calculator
This is assignment 2 for creating a simple calculator in the OOP module of MSc Computer Science.
It includes 4 exercises to build this calculator. 

Exercise 1: A Basic Calculator
The calculator can evaluate basic expressions of the following form: operand[space]operator[space]operand. For example, 3 + 4, 2 ∗ 10.5, 10 / 5, 39.255 − 47.
If the user enters an expression that is not precisely of the form shown above then the program would output “Invalid input.” If the user enters an expression  involving a ‘divide-by-zero’ error then the program would also output “Invalid input.”

Exercise 2: Memory and History
We need to expand the functionality of the calculator to include memory and history functions. If the user presses presses the ‘m’ key the program should store the most recent calculator result. If the user types “mr”, the program should print to the console the stored memory value (or zero if no value has been stored).If the user presses the ‘c’ key, the program should clear the memory by setting it to zero. If the user presses ‘h’ then the program should print to the console all of the results since the program was started, on one line, separatedby spaces.

Exercise 3: Basic bracket functionality
For this exercise we should expand the evaluate() method so that it can evaluate expressions of the following form:(3.5 + 4.5) + (2−1)

Exercise 4: Arbitrary length expressions 
We should expand the evaluate() method so that it can handle arbitrary length ex-pressions (without brackets).  This will mean we will now need to take account of the order of precedenceof arithmetic operations. The method should be able to correctly handle (using order of precedence) expressions like the following:
3 + 4*6
3*0 + 2/6
12−2*10 + 2*6/2
