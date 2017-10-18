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


    public void put(Key key, Value value){
        int index = hash(key);
        hashMap[index].add(value);
    }












































      public Value remove(Key key){
        int index = hash(key);
        Value ret = (Value)hashMap[index];
        hashMap[index]= null;
        return ret;
    }










}
