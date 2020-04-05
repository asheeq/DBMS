package server;
import java.io.*;
import java.util.*;
import java.net.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import myobject.MyObject;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.Properties;
import java.util.Timer;
public class Server
{
    static Vector<ClientHandler> ar = new Vector<>();
    static BlockingQueue<MyObject> q = new LinkedBlockingDeque<>();
    static Logger logger = LogManager.getLogger(Server.class);
    static int i = 1;
    @SuppressWarnings("resource")
	public static void main(String[] args) throws Exception
    {
    	Properties prop = new Properties();
        ServerSocket ss;
        if(args.length == 1)
        {
            ss = new ServerSocket(Integer.parseInt(args[0]));
            prop.setProperty("ServerSocketAddress", ss.getInetAddress().toString());
            prop.setProperty("ServerSocketPort", String.valueOf(ss.getLocalPort()));
        }
        else
        {
            ss = new ServerSocket(1234);
            prop.setProperty("ServerSocketAddress", ss.getInetAddress().toString());
            prop.setProperty("ServerSocketPort", String.valueOf(ss.getLocalPort()));
        }
        try
        {
            String filepath = "data.log";
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filepath));
            String lineText = null;
            Singleton singleton = Singleton.getInstance();
            while ((lineText = bufferedReader.readLine() )!= null)
            {
            	StringTokenizer st = new StringTokenizer(lineText, " ");
                String key = st.nextToken();
                String value = st.nextToken();
                singleton.insert(key,value);
            }
            bufferedReader.close();
        }
        catch (IOException e)
        {
            System.err.println(e);
        }
        Operations op = new Operations();
        Thread opt = new Thread(op);
        opt.start();
        Timer time = new Timer();
		ScheduledTask st = new ScheduledTask();
		time.schedule(st, 0, 600000);
        while (true)
        {
            try 
            {
                Socket s = ss.accept();
                prop.setProperty("SocketAddress", s.getInetAddress().toString());
                prop.setProperty("SocketPort", String.valueOf(s.getLocalPort()));
                prop.store(new FileOutputStream("config.properties"), null);
                System.out.println("New client request received : " + s);
                ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
                ObjectInputStream is = new ObjectInputStream(s.getInputStream());
                System.out.println("Creating a new handler for this client...");
                ClientHandler match = new ClientHandler(s, i, is, os);
                Thread t = new Thread(match);
                System.out.println("Adding this client to active client list");
                ar.add(match);
                t.start();
                i++;
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
