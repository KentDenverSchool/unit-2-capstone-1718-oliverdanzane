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
      Someone please check this code. It doesn't check collisions (it's not supposed to).
     */

    //Updates m to the new value. Rehashes all keys
    public void resize(int newM){
        ArrayList<Value>[] hashMapCopy;


        for(int i = 0; i < hashCode; i++) {
            Key k = (Key) hashMap[i];
            temp.put(temp.hash(i),hashMap[i].get(i));
        }
        //not sure if this works^^ just some ideas

        //other idea [incorrect] I can't make a copy of hash map!
//        hashMapCopy = new ArrayList[newM];
//        for(int i = 0; i < hashCode; i++){
//            Key k = (Key)hashMap[i];
//
//            hashMapCopy.put(k.hash(),hashMap[i].get(i));
//           }
//        hashMap = hashMapCopy;
//        for (int i = 0; i < hashMapCopy.length; i++) {
//            ArrayList<Value> values = hashMapCopy[i];

        }
    }



















// _________________________________________________________________________________________________________
//|  Operation               |  #  |   ArrayDictionary(1)      BinarySearchTree(2)      HashingDictionary(3)|
//|__________________________|_____|________________________________________________________________________|
//|                          |     |                                                                        |
//|  Get a value given key   | (1) |          O(1)                   O(n)                     O(n)          |
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