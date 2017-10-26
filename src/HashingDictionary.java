import java.util.ArrayList;


/*
Program makes a HashMap and it has all of the fundamental methods
 */
public class HashingDictionary <Key extends Comparable, Value> implements Dictionary{

    private int size;
    private int hashCode = 619;
    private ArrayList<ArrayList>[] hashMap;

    public HashingDictionary(){
        size = 0;
        hashMap = new ArrayList[hashCode];
    }

    public int hash(Key key){
        String s = key.toString();
        int index = s.hashCode();
        return index % hashCode;
    }

    //add an key-value pair to the dictionary
    public void put(Key key, Value value){
        ArrayList<ArrayList> outer = new ArrayList();
        ArrayList inner = new ArrayList();
        inner.add(key);
        inner.add(value);

        int index = hash(key);
        if (hashMap[index].get(index) != null){
            outer.add(inner);
            hashMap[index] = outer;

        } else {
            hashMap[index].add(inner);
        }
        size++;
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

    public Value remove(Key key){
        int index = hash(key);
        for (int i = 0; i < hashMap[index].size(); i++) {
            if (((Key)hashMap[index].get(i)).compareTo(key) == 0){
                hashMap[index].set(i, null);
                return (Value) hashMap[index].get(i).get(1);
            }
        }
        size--;
        return null;
    }

    //returns the number of key-value pairs in the dictionary
    public int size(){
        return size;
    }

    /*
      Second Task - Dan
      It doesn't check collisions (it's not supposed to).
      Updates m to the new value. Rehashes all keys
     */

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
                this.put((Key)newKeyValuePair.get(0), (Key)newKeyValuePair.get(1));
                }
            }
        }
    }




















// _________________________________________________________________________________________________________
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

        //YOU ARE IN THE DRIVER BRANCH!!!!

        HashingDictionary h = new HashingDictionary();

        System.out.println("The size of the Hashing Dictionary should be 0 --> " + h.size());
        System.out.println("The size of the Hashing Dictionary is 0, so it should be empty --> " + h.isEmpty());
        h.put("Age", 10);
        h.put("Age", 20);
        h.put("Weight", 75);
        h.put("Weight", 175);
        System.out.println("The size of the Hashing Dictionary should be 4 --> " + h.size());
        System.out.println("The size of the Hashing Dictionary is 4, so it should NOT be empty --> " + h.isEmpty());
        System.out.println("The Value for Age is 10,20 --> " + h.get("Age"));
        //^^What should happen here? Can you put something in the hashing dictionary that is the exact same Key?
        System.out.println("The Value for Weight is 75,175 --> " + h.get("Weight"));
        System.out.println("Removing \"Age\" should return 10,20 --> " + h.remove("Age"));
        System.out.println("Removing \"Weight\" should return 75,175 --> " + h.remove("Weight"));
        System.out.println("The size of the Hashing Dictionary should be 0 --> " + h.size());
        System.out.println("The size of the Hashing Dictionary is 0, so it should be empty --> " + h.isEmpty());

        //INCLUDE RESIZE (how would I test this??) WHEN DONE AND THE DOUBLING METHOD!!!





    }

    public void resize80Percent() {
        if ((1.0*size)/hashCode >= 0.8) {
            resize(hashCode*2);
        }
    }
}



