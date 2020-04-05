package server;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Singleton {
    private static volatile ConcurrentMap<Object,Object> map = new ConcurrentHashMap<>();
    private static volatile Singleton instance = null;

    private Singleton()
    {

    }
    public static Singleton getInstance()
    {
        if (instance == null)
        {
            synchronized (Singleton.class)
            {
                if(instance == null)
                {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
    public void insert(Object key,Object value) throws IllegalArgumentException
    {
    	if(key == null || value == null) throw new IllegalArgumentException("cannot be null");
        map.put(key,value);
    }
    public void update(Object key, Object value) throws IllegalArgumentException
    {
    	if(key == null || value == null) throw new IllegalArgumentException("cannot be null");
        map.put(key,value);
    }
    public void delete(Object key)
    {
        for (Map.Entry<Object,Object> entryDel : map.entrySet())
        {
            if((entryDel.getKey().toString()).equals(key.toString()))
            {
                map.remove(entryDel.getKey());
            }
        }
    }
    public Object read(Object key) throws IllegalArgumentException
    {
    	Object value = null;
    	if(key == null) throw new IllegalArgumentException("cannot be null");
        for (Map.Entry<Object,Object> entryRead : map.entrySet())
        {
            if((entryRead.getKey().toString()).equals(key.toString()))
            {
                value = entryRead.getValue();
            }
        }
        return value;
    }
    public void deleteAll()
    {
        map.clear();
    }
    public Set<Map.Entry<Object,Object>> readAll()
    {
        return map.entrySet();
    }
    public String info()
    {
        int size = map.size();
        int memory = (int) ((32*size)+(4*(size/0.75)));
        String s = "Info : Total key-value pair is: " + Integer.toString(size) + "\n" + "Total in memory usage is: " + Integer.toString(memory)+" bytes";
        return s;
    }
    public int getSize() 
    {
    	return map.size();
    }
    public int getMemory() 
    {
    	int size = map.size();
        int memory = (int) ((32*size)+(4*(size/0.75)));
        return memory;
    }
}