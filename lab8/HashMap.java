package paganiniK;


import java.util.*;

public class HashMap<V, K> implements Map<K, V> {

    private LinkedList<Entry<K, V>>[] map;
    private static final int START_CAPACITY = 1009; //Prime number for less collisions
    private double LoadThreshold;
    private int numKeys;
    private final Entry<K, V> DELETED = new Entry<>(null, null);

    private static class Entry<K, V> {
        //K Key
        private final K key;
        //V value
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public V setValue(V newValue) {
            V oldVal = value;
            value = newValue;
            return oldVal;
        }
    }



    public HashMap() {

        map = new LinkedList[START_CAPACITY];
    }


    private int findIndex(Object key) {


        int index = key.hashCode() % START_CAPACITY;
        if (index < 0) {
            index *= -1;
        }
        return index;

    }



    @Override
    public int size() {
        return numKeys;
    }


    @Override
    public boolean isEmpty() {
        return size() == 0;
    }


    @Override
    public boolean containsKey(Object key) {
        boolean contains = false;
        int index = findIndex(key);
        LinkedList<Entry<K, V>> current = map[index];
        if(current == null || current.isEmpty()) {
            contains = false;
        } else {
            for(int i = 0; i < current.size(); ++i) {
                if (current.get(i).getKey().equals(key)){
                    contains = true;
                }
            }

        }

        return contains;
    }




    @Override
    public V get(Object key) {
       V value = null;
       if (containsKey(key)) {
           LinkedList<Entry<K, V>> current = map[findIndex(key)];
           for (int i = 0; i < current.size(); ++i) {
               if (current.get(i).getKey().equals(key)) {
                   value = current.get(i).getValue();
               }
           }
       }

        return value;
    }



    public V put(K key, V value) {
        int index = findIndex(key);
        if(map[index] == null) {
            map[index] = new LinkedList<>();
        }
        for(Entry<K, V> nextItem : map[index]) {
            if(nextItem.getKey().equals(key)) {
                V oldVal = nextItem.getValue();
                nextItem.setValue(value);
                return oldVal;
            }
        }
        map[index].addFirst(new Entry<>(key, value));
        numKeys++;
        return null;


    }


    public V remove(Object key) {
        V remove = null;
        int index = findIndex(key);
        if(map[index] == null) {
            return null;
        }
        LinkedList<Entry<K, V>> current = map[index];
        if(current.isEmpty()) {
            map[index] = null;
        }
        for(int i = 0; i < current.size(); ++i) {

            if(current.get(i).getKey().equals(key)) {
                remove = current.get(i).getValue();
                current.remove(i);
                numKeys--;


            }

        }
        return remove;
    }


    public void putAll(Map<? extends K, ? extends V> m) {
        throw new UnsupportedOperationException("Too lazy...");
    }


    public void clear() {
        throw new UnsupportedOperationException("Too lazy...");
    }


    public Set<K> keySet() {
        throw new UnsupportedOperationException("Too lazy...");
    }


    public Collection<V> values() {
        throw new UnsupportedOperationException("Too lazy...");
    }


    public Set<Map.Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException("Too lazy...");
    }


    public boolean containsValue(Object value)
    {
        throw new UnsupportedOperationException("Too lazy...");
    }


}