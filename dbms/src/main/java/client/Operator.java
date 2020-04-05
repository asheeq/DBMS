package client;

public class Operator {
    public int operator(String str) throws IllegalArgumentException
    {
    	if (str == null) throw new IllegalArgumentException("String cannot be null");
        int i = 0;
        if(str.equalsIgnoreCase("INSERT"))
        {
            i = 0b001;
        }
        else if(str.equalsIgnoreCase("UPDATE"))
        {
            i = 0b010;
        }
        else if(str.equalsIgnoreCase("DELETE"))
        {
            i = 0b011;
        }
        else if(str.equalsIgnoreCase("READ"))
        {
            i = 0b100;
        }
        else if(str.equalsIgnoreCase("DELETEALL"))
        {
            i = 0b101;
        }
        else if(str.equalsIgnoreCase("READALL"))
        {
            i = 0b110;
        }
        else if(str.equalsIgnoreCase("INFO"))
        {
            i = 0b111;
        }
        return i;
    }
}
