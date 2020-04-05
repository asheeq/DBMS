package server;

import java.util.TimerTask;
import java.util.Map;
import java.util.Set;

public class ScheduledTask extends TimerTask {

	public void run() {
		
		 Singleton singleton = Singleton.getInstance();
		 Set<Map.Entry<Object,Object>> set = singleton.readAll();
         for (Map.Entry<Object,Object> entry : set)
         {
         	Object k = entry.getKey();
         	Object v = entry.getValue();
         	Server.logger.info(k+" "+v);         	
         }
	}
}