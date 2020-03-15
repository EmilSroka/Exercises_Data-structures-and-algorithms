# Set of exercises about dynamic arrays

## Exercise 1
Create a dynamic array class (like Vector in C++ or ArrayList in Java).  
Class have to contain the following methods:
- set - replace the element at the specified index in this list
- get - return the element at the specified index in this list
- insert - add element at the end of array
- insertAt - add element at given index and shift all the items on the right one index to the right
- removeAt - remove element at given index and shift all the items on the right one index to the left
- indexOf - return index of given element. If array doesn't contain element, method should return -1.  Use equals method to comparing objects.  
- toString - return string representation of the array  

You can implement class as generic.

## Exercise 2
Extend the Array class and add a three new methods:
 - max - return greatest value
 - min - return lowest value
 - minmax - return array of two elements. First element should be minimum value and second maximum value  
 
You can also implement method to find n’th smallest element (order statistic).

## Exercise 3
Implement iterator for dynamic array. Iterator when created, should be independent of iterable object state. 

## Exercise 4
Implement a three new methods in array class:
- contains - return boolean that indicating whether a value is in array.
- intersection - return new dynamic array that is an intersection of current object and given array.
- union - return new dynamic array that is a union of current object and given array.  

## Exercise 5  
Add two method to reverse the array. One of them should reverse array in place while second one should return new reversed array.

## Exercise 6  
Implement bubble sort. Use strategy pattern to extract algorithm to its own object.

## Exercise 7
Create new strategies for selection and insertion sort.