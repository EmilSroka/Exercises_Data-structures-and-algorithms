# Set of exercises about linked list

## Exercise 1
Create a dynamic singly linked list class.  
The class have to contain the following methods:
- `addFirst` - adds a new node with given value as head
- `addLast` - adds a new node with given value as tail
- `add` - adds a new node with given value at given index
- `deleteFirst` - deletes head
- `deleteLast` - deletes tail
- `delete` - deletes a node at a given index

## Exercise 2
Implement the below methods:
- `size` - returns a count of nodes in the linked list 
- `contains` - returns a boolean that indicates if a given value is in list
- `indexOf` - returns an index of a node with the given value (if exists)
- `toArray` - returns an array that contains values from nodes

## Exercise 3
Implement a method that reverses linked list in place. Time complexity should be O(n)

## Exercise 4 
Implement two new methods in list class:
- `get` - returns an nth value from the start of the list
- `getFromTheEnd` - returns an nth value from the end

Don't use a variable that stores the size of the array to stop the iteration. You can index from 0 or 1. Time complexity should be O(n) 

## Exercise 5 
Add method `getMiddleElements` that finds the middle of a linked list in one pass. If the list has an even number of nodes, there would be two middle nodes.  
Note: Assume that you don’t know the size of the list ahead of time

## Exercise 6 
Implement two methods in list class:
- `hasLoop` - checks if the list has a loop. Returns boolean
- `getLoopStart` - returns first element of loop or null (if loop not exist)