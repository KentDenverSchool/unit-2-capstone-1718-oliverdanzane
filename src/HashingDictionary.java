import java.util.ArrayList;


/*
Program makes a HashMap and it has all of the fundamental methods
 */
public class HashingDictionary <Key, Value> implements Dictionary{

    private int size;
    private int hashCode = 619;
    private ArrayList<Value>[] hashMap;

    public HashingDictionary(){
        size = 0;
        hashMap = new ArrayList[hashCode];
    }

    public int hash(Key key){
        String s = key.toString();
        int index = s.hashCode();
        return index%hashCode;
    }

    //add an key-value pair to the dictionary
    public void put(Key key, Value value){
        int index = hash(key);
        hashMap[index].add(value);
    }

    //returns the Value at the entered Key
    public Value get(Key key) {
        int index = this.hash(key);
        return hashMap[index].get(index);
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
        Value ret = (Value)hashMap[index];
        hashMap[index]= null;
        return ret;
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
        HashingDictionary temp = new HashingDictionary();

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


}
