public interface Dictionary<Key, Value>{
    //add an key-value pair to the dictionary
    public void put(Key key, Value value);

    //get the value associated with a given key
    public Value get(Key key);

    //remove a key-value pair and return its value
    public Value remove(Key key);

    //returns true if the dictionary is empty
    public boolean isEmpty();

    //returns the number of key-value pairs in the dictionary
    public int size();
}
