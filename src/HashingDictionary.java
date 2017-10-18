import java.util.ArrayList;


/*

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

    //returns the number of key-value pairs in the dictionary
    public int size(){
        return size;
    }
}
