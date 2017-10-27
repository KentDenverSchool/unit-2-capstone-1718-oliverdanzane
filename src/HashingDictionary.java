import java.util.ArrayList;

/*
 * HashingDictionary
 * Program makes a HashMap and it has all of the fundamental methods
 * Zane, Dan, Oliver
 * 10/27/17
 */

public class HashingDictionary <Key extends Comparable, Value> {

    private int size;
    private int hashCode = 5;
    private ArrayList<ArrayList>[] hashMap;

    public HashingDictionary(){
        size = 0;
        hashMap = new ArrayList[hashCode];
    }

    //Hashes a key using the hashcode
    public int hash(Key key){
        String s = key.toString();
        int index = s.hashCode();
        return Math.abs(index % hashCode);
    }

    //add an key-value pair to the dictionary
    public void put(Key key, Value value){
        ArrayList<ArrayList> outer = new ArrayList();
        ArrayList inner = new ArrayList();
        inner.add(key);
        inner.add(value);

        int count = 0;

        int index = hash(key);
        if (hashMap[index] == null) {
            outer.add(inner);
            hashMap[index] = outer;
            size++;

        } else {

            while (count < hashMap[index].size()) {
                if (((Key) hashMap[index].get(count).get(0)).compareTo(key) == 0) {
                    hashMap[index].set(count, inner);
                    break;
                }
                count++;
            }

            if (count >= hashMap[index].size()) {
                hashMap[index].add(inner);
                size++;
            }
        }
        resize80Percent();
    }

    //returns the Value at the entered Key
    public Value get(Key key) {
        int index = this.hash(key);

        for (int i = 0; i < hashMap[index].size(); i++) {
            Key k = (Key) hashMap[index].get(i).get(0);
            if (k.compareTo(key) == 0){
                return (Value) hashMap[index].get(i).get(1);
            }
        }

        return null;

    }

    //returns true if the dictionary is empty
    public boolean isEmpty(){
        if (size == 0){
            return true;
        } else {
            return false;
        }
    }

    //remove a key-value pair and return its value
    public Value remove(Key key){
        int index = hash(key);
        for (int i = 0; i < hashMap[index].size(); i++) {
            if (((Key)hashMap[index].get(i).get(0)).compareTo(key) == 0){
                Value v = (Value) hashMap[index].get(i).get(1);
                hashMap[index].remove(i);
                size--;
                return v;
            }
        }
        return null;
    }

    //returns the number of key-value pairs in the dictionary
    public int size(){
        return size;
    }

    //Updates m to the new value. Rehashes all keys
    public void resize(int newM){
        ArrayList<ArrayList>[] oldHashMap = this.hashMap;
        this.hashMap = new ArrayList[newM];
        this.hashCode = newM;

        //make two, outer array and then the inner array list
        for(int i = 0; i < oldHashMap.length; i++) {
            //checks if I should traverse the array
            if(oldHashMap[i] != null){
                for(int j = 0; j < oldHashMap[i].size(); j++){
                //rehash the key for the index
                ArrayList newKeyValuePair = oldHashMap[i].get(j);
                this.put((Key)newKeyValuePair.get(0), (Value)newKeyValuePair.get(1));
                    size--;
                }
            }
        }
    }

    //Doubles the size of m when more than the total number of key-value pairs stored exceeds 80% of m.
    public void resize80Percent() {
        if ((1.0*size)/hashCode >= 0.8) {
            System.out.println("This is the current size (should be 4) --> " + size);
            System.out.println("This is the current capacity of the Hashmap (should be 5) --> " + hashMap.length);
            resize(hashCode*2);
            System.out.println("This is the updated capacity of the Hashmap after the size exceeded 80% of the capacity (Should be 10 because 5 was doubled) --> " + hashMap.length);

        }
    }

// STEP 5:
// _________________________________________________________________________________________________________
//|  Operation               |  #  |   ArrayDictionary(1)      BinarySearchTree(2)      HashingDictionary(3)|
//|__________________________|_____|________________________________________________________________________|
//|                          |     |                                                                        |
//|  Get a value given key   | (1) |          O(n)                   O(n)                     O(n)          |
//|                          |     |                                                                        |
//|  Insert a key-value pair | (2) |          O(n)                   O(n)                     O(1)          |
//|                          |     |                                                                        |
//|  Remove a key-value pair | (3) |          O(n)                   O(n)                     O(n)          |
//|                          |     |                                                                        |
//|  Space                   | (4) |          O(n)                   O(n)                     O(n)          |
//|__________________________|_____|________________________________________________________________________|
// Source: http://bigocheatsheet.com/
//
//(1,1) --> It is O(n) because it triggers a resize but normally it would be O(1)
//(1,2) --> It is O(n) because that is the worst case if the search tree is all on one side of n levels
//(1,3) --> It is O(n) because it has to go through n amount of levels to get the value
//(2,1) --> It is O(n) because in array dictionary it has to go n times through to get the value of n
//(2,2) --> It is O(n) because in a search tree the worst case still has to go through everything on the one side
//(2,3) --> It is O(1) because it inserts the key-value wherever the index is so it only goes through 1 time
//(3,1) --> it is O(n) because removing still has to go through n times in the array dictionary
//(3,2) --> It is O(n) because it has to go through the entire function and the worst case is still going through the entire side
//(3,3) --> It is O(n) because in hashing dictionary it has to go through n times to find the value to remove
//(4,1) --> It is O(n) because the worst cases are O(n) so that is the amount of space it will take up
//(4,2) --> It is O(n) because the worst cases are O(n) so that is the amount of space it will take up
//(4,3) --> It is O(n) because the worst cases are O(n) so that is the amount of space it will take up


    public static void main(String[] args) {

        //Driver

        HashingDictionary h = new HashingDictionary();

        System.out.println("The size of the Hashing Dictionary should be 0 --> " + h.size());
        System.out.println("The size of the Hashing Dictionary is 0, so it should be empty (true) --> " + h.isEmpty());
        System.out.println("First put");
        h.put("Age", 10);
        System.out.println("Second put ");
        h.put("Weight", 75);
        System.out.println("Third put");
        h.put("height", 50);
        System.out.println("Fourth put");
        h.put("numFingers", 10);
        System.out.println("The size of the Hashing Dictionary should be 4 --> " + h.size());
        System.out.println("The size of the Hashing Dictionary is 4, so it should NOT be empty (false) --> " + h.isEmpty());
        System.out.println("The Value for Age is 10 --> " + h.get("Age"));
        System.out.println("The Value for Weight is 75 --> " + h.get("Weight"));
        System.out.println("Removing \"Age\" should return 10 --> " + h.remove("Age"));
        System.out.println("Removing \"Weight\" should return 75 --> " + h.remove("Weight"));
        System.out.println("The size of the Hashing Dictionary should be 2 (removed Age and Weight) --> " + h.size());
        System.out.println("The size of the Hashing Dictionary is 2, so it should be empty (false) --> " + h.isEmpty());

    }


}



