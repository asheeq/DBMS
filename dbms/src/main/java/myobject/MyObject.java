package myobject;

import java.io.Serializable;

@SuppressWarnings("serial")
public class MyObject implements Serializable {
    public int operation;
    public Object key;
    public Object value;
    public int clientNumber;
    public String reply;

    public MyObject(int clientNumber,int operation, Object key, Object value)
    {
        this.clientNumber = clientNumber;
        this.operation = operation;
        this.key = key;
        this.value = value;
    }
    public MyObject(int operation, Object key, Object value)
    {

        this.operation = operation;
        this.key = key;
        this.value = value;
    }
    public MyObject(int operation, Object key)
    {
        this.operation = operation;
        this.key = key;
    }
    public MyObject(int operation)
    {
        this.operation = operation;
    }
    public MyObject(String reply)
    {
        this.reply = reply;
    }
    public MyObject()
    {

    }
}