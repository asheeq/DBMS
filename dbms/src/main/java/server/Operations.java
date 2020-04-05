package server;

import myobject.MyObject;

import java.util.Map;
import java.util.Set;

public class Operations implements Runnable {

    public void run() {
        try
        {
            while(true)
            {
                Thread.sleep(500);
                if(Server.q.size()>0)
                {
                    MyObject m = Server.q.take();
                    for (ClientHandler mc : Server.ar)
                    {
                        if(mc.clientNumber == m.clientNumber)
                        {
                        	if (m.operation == 0b001)
                            {
                                Singleton singleton = Singleton.getInstance();
                                Object key = m.key;
                                Object value = m.value;
                                singleton.insert(key, value);
                                MyObject mr = new MyObject("OK.");
                                mc.os.writeObject(mr);
                                break;
                            }
                            else if (m.operation == 0b010)
                            {
                                Singleton singleton = Singleton.getInstance();
                                Object key = m.key;
                                Object value = m.value;
                                singleton.update(key, value);
                                MyObject mr = new MyObject("OK.");
                                mc.os.writeObject(mr);
                                break;
                            }
                            else if (m.operation == 0b011)
                            {
                                Singleton singleton = Singleton.getInstance();
                                Object key = m.key;
                                System.out.println(mc.clientNumber+" "+key);
                                singleton.delete(key);
                                MyObject mr = new MyObject("OK.");
                                mc.os.writeObject(mr);
                                break;
                            }
                            else if (m.operation == 0b100) {
                                Singleton singleton = Singleton.getInstance();
                                Object key = m.key;
                                Object read = singleton.read(key);
                                if (read != null) {
                                    MyObject mr = new MyObject();
                                    mr.key = key;
                                    mr.value = read;
                                    mc.os.writeObject(mr);
                                    break;
                                }
                                MyObject mr = new MyObject("No Key Found.");
                                mc.os.writeObject(mr);
                                break;
                            }
                            else if (m.operation == 0b101)
                            {
                                Singleton singleton = Singleton.getInstance();
                                singleton.deleteAll();
                                MyObject mr = new MyObject("OK.");
                                mc.os.writeObject(mr);
                                break;
                            }
                            else if (m.operation == 0b110)
                            {
                                Singleton singleton = Singleton.getInstance();
								Set<Map.Entry<Object,Object>> set = singleton.readAll();
                                if (set.isEmpty())
                                {
                                    MyObject mr = new MyObject("Info :Database is empty.");
                                    mc.os.writeObject(mr);
                                }
                                else
                                {
                                    for (Map.Entry<Object,Object> entry : set)
                                    {
                                    	Object k = entry.getKey();
                                    	Object v = entry.getValue();
                                    	MyObject mr = new MyObject();
                                    	mr.key = k;
                                    	mr.value = v;
                                    	mc.os.writeObject(mr);
                                    }
                                }
                                break;
                            }
                            else if (m.operation == 0b111)
                            {
                                Singleton singleton = Singleton.getInstance();
                                int size = singleton.getSize();
                                int memory = singleton.getMemory();
                                MyObject mr = new MyObject("Info : Total key-value pair is: "+ size + "Total in memory usage is: "+ memory + "\n" + "Number of connected client is: " + Server.ar.size());
                                mc.os.writeObject(mr);
                                break;
                            }
                        }
                    }
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}