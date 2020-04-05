package client;

import myobject.MyObject;

import java.util.StringTokenizer;
import java.util.*;

public class Sender implements Runnable {
    Scanner scn = new Scanner(System.in);
    Converter conv = new Converter();
    Operator cop = new Operator();
    public void run()
    {
        while (Client.flag) {
            String msg = scn.nextLine();
            Client.start = System.currentTimeMillis();
            StringTokenizer st = new StringTokenizer(msg, " ");
            String op = st.nextToken();
            try 
            {
                if (op.equals("LOGOUT")) 
                {
                    Client.flag = false;
                    Client.is.close();
                    Client.os.close();
                    Client.s.close();
                    scn.close();
                    break;
                } 
                else if (op.equals("INSERT")) 
                {
                    int opb = cop.operator(op);
                    Object key = conv.strToBinary(st.nextToken());
                    Object value = conv.strToBinary(st.nextToken());
                    MyObject m = new MyObject(opb,key,value);
                    Client.os.writeObject(m);
                }
                else if (op.equals("UPDATE")) 
                {
                    int opb = cop.operator(op);
                    Object key = conv.strToBinary(st.nextToken());
                    Object value = conv.strToBinary(st.nextToken());
                    MyObject m = new MyObject(opb,key,value);
                    Client.os.writeObject(m);
                }
                else if(op.equals("READ"))
                {
                    int opb = cop.operator(op);
                    Object key = conv.strToBinary(st.nextToken());
                    MyObject m = new MyObject(opb,key);
                    Client.os.writeObject(m);
                }
                else if(op.equals("DELETE"))
                {
                    int opb = cop.operator(op);
                    Object key = conv.strToBinary(st.nextToken());
                    MyObject m = new MyObject(opb,key);
                    Client.os.writeObject(m);
                }
                else if (op.equals("DELETEALL"))
                {
                    int opb = cop.operator(op);
                    MyObject m = new MyObject(opb);
                    Client.os.writeObject(m);
                }
                else if (op.equals("READALL"))
                {
                    int opb = cop.operator(op);
                    MyObject m = new MyObject(opb);
                    Client.os.writeObject(m);
                }
                else if (op.equals("INFO"))
                {
                    int opb = cop.operator(op);
                    MyObject m = new MyObject(opb);
                    Client.os.writeObject(m);
                }
                else 
                {
                    System.out.println("INVALID OPERATION.");
                    continue;
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
                continue;
            }
        }
    }
}