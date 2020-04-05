package client;
import java.util.Arrays;

public class Converter {

    public Object strToBinary(String s) throws NullPointerException
    {
    	if (s == null) throw new NullPointerException("String cannot be null");
    	
    	Object o = null;
    	byte[] bytes = s.getBytes();
        StringBuffer binary = new StringBuffer();
        for (byte b : bytes)
        {
            int val = b;
            for (int i = 0; i < 8; i++)
            {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
        }
        o = binary;
        return o;
    }

    public String binToString(Object i) throws NullPointerException
    {
    	if (i == null) throw new NullPointerException("Object cannot be null.");
    	
    	StringBuffer sb = new StringBuffer();
    	String str = new String(String.valueOf(i));
        
        Arrays.stream(
                str.split("(?<=\\G.{8})")
        ).forEach(s ->
                sb.append((char) Integer.parseInt(s, 2))
        );
        return sb.toString();
    }
}
