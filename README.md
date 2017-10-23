## Unit Capstone - Using Hashing in a Data Structure

You will be working on a data structure that "implements" our Dictionary interface. You will work in groups of three on this project. You may consult documentation, but you may not look up the solution to any of these problems. Here are the [unit's notes](https://github.com/lhaynes92/AT-DataStructures-17-18).

#### First task

Your first task is fairly simple. Assuming that you have a given **hash space `m`** write a data structure that provides implementations for the following methods of Dictionary:

```java

//add an key-value pair to the dictionary
void put(Key key, Value value)

//get the value associated with a given key
Value get(Key key)

//remove a key-value pair and return its value
Value remove(Key key)

//returns true if the dictionary is empty
boolean isEmpty()

//returns the number of key-value pairs in the dictionary
int size()

```

For this task you can overwrite an entry if you have a collision.

**Quick tips:**
- How should your data be stored in you array? What information do you need to preserve?
- How do we know that we can hash any `Object`? How might we do that?

#### Second Task

Sometimes we start with a hashing space that is too large or too small for our key set.
In these instances we would like to be able to make `m` larger to reduce collisions or smaller to save memory.
To accomplish this you are going to add the following method to your data structure:

```java
//Updates m to the new value. Rehashes all keys
public void resize(int newM)
```

You may still ignore collisions.

#### Third task

It is time to resolve collisions. There are several different ways to do this, but I am going to encourage you to use "chaining." This means that all the keys that hash to the same value will be stored in a new data structure that starts at the appropriate index in the your main array. E.g.

| Array  |
|--------|
| []     |
| [K1, V1]     |
| []     |
| [K2, V2], [K3, V3]|
| []     |
| [K0, V0] |
| []     |


**Quick tips:**
- Given how data is stored in your dictionary, what is the easiest way to just expand that entry in your array?
- Make sure to update your `get`, `put`, and `remove` methods to account for this new behavior.

#### Fourth task

Modify your class to double the size of `m` when more than the total number of key-value pairs stored exceeds 80% of `m`.

#### Fifth task

Fill in the following table concerning the computational complexity (big O notation) of the dictionary implementations that we have discussed this unit.

|Operation|ArrayDictionary|BinarySearchTree|HashingDictionary|
|---------|---------------|----------------|-----------------|
|Get a value given a key||||
|Insert a key-value pair||||
|Remove a key-value pair||||

#### Optional - Sixth Task

You may wish to write a driver to make sure that your code works the way you expect it to. 
