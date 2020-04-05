package client;

import java.io.*;
import java.net.*;

public class Client
{
    static InetAddress ip;
    static int ServerPort;
    static Socket s;
    static boolean flag = true;
    static long start;
    static long finish;
    static ObjectOutputStream os;
    static ObjectInputStream is;
    public static void main(String args[]) throws UnknownHostException, IOException
    {
        if(args.length>1)
        {
            ip = InetAddress.getByName(args[2]);
            ServerPort = Integer.parseInt(args[4]);

        }
        else
        {
            ip = InetAddress.getByName("localhost");
            ServerPort = 1234;
        }
        System.out.println("Connected.");
		@SuppressWarnings("resource")
		Socket s = new Socket(ip, ServerPort);
        is = new ObjectInputStream(s.getInputStream());
        os = new ObjectOutputStream(s.getOutputStream());
        Sender sender = new Sender();
        Thread sendMessage = new Thread(sender);
        Receiver receiver = new Receiver();
        Thread readMessage = new Thread(receiver);
        sendMessage.start();
        readMessage.start();
    }
}
