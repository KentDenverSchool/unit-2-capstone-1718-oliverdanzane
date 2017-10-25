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



    }