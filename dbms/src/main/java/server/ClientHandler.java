package server;

import myobject.MyObject;
import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable
{
    int clientNumber;
    ObjectInputStream is;
    ObjectOutputStream os;
    Socket s;
    MyObject mq;
    public ClientHandler(Socket s, int clientNumber, ObjectInputStream is, ObjectOutputStream os) {
        this.is = is;
        this.os = os;
        this.clientNumber = clientNumber;
        this.s = s;
    }

    @Override
    public void run() {
        try
        {
            while(true)
            {
                MyObject m = (MyObject) is.readObject();
                mq = new MyObject(clientNumber,m.operation,m.key,m.value);
                Server.q.put(mq);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}