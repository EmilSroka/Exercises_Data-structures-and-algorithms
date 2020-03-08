# Set of exercises about dynamic arrays

## Exercise 1
Create a dynamic array class (like Vector in C++ or ArrayList in Java).  
Class have to contain the following methods:
- set - replace the element at the specified index in this list
- get - return the element at the specified index in this list
- insert - add element at the end of array
- insertAt - add element at given index and shift all the items on the right one index to the right
- removeAt - remove element at given index and shift all the items on the right one index to the left
- indexOf - return index of given element. If array doesn't contain element, method should return -1.  
- toString - return string representation of the array
You can implement class as generic.

## Exercise 2
Extend the Array class and add a three new methods:
 - max - return greatest value
 - min - return lowest value
 - minmax - return array of two elements. First element should be minimum value and second maximum value.
You can also implement method to find n’th smallest element (order statistic).
