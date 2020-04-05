package client;

import myobject.MyObject;

public class Receiver implements Runnable {

    public void run() {
        while (Client.flag) {
            try {
                MyObject m = (MyObject) Client.is.readObject();
                if(m.reply != null)
                {
                    System.out.println(m.reply);
                }
                else if(m.key != null)
                {
                    Converter conv = new Converter();
                    System.out.println("Key: "+ conv.binToString(m.key) + " Value: " + conv.binToString(m.value));
                }
                Client.finish = System.currentTimeMillis();
                long durationInNano = (Client.finish - Client.start);
                System.out.println("Execution time: "+durationInNano+"ms");
            }
            catch (Exception e)
            {
                System.out.println("Logging out.Thank You.");
                break;
            }
        }
    }

}
