package simple.dbms;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;

import client.Operator;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class OperationTest {
	Operator op = new Operator();
	
	@SuppressWarnings("unused")
	private Object[] operationToBinary() 
	{
		return new Object[] 
		{
			new Object[] {"INSERT",0b001},
			new Object[] {"UPDATE",0b010},
			new Object[] {"DELETE",0b011},
			new Object[] {"READ",0b100},
			new Object[] {"DELETEALL",0b101},
			new Object[] {"READALL",0b110},
			new Object[] {"INFO",0b111},
			new Object[] {"random input",0},
			new Object[] {"abcd",0},
			new Object[] {"1234",0},
			new Object[] {"ab12",0},
			new Object[] {"insert",0b001},
			new Object[] {"update",0b010},
			new Object[] {"delete",0b011},
			new Object[] {"read",0b100},
			new Object[] {"DeleteAll",0b101},
		};
	}
	
	@Test
	@Parameters(method = "operationToBinary")
	public void operationToBinaryConversionTest(String input,int expected) 
	{
		assertEquals(op.operator(input), expected);
	}
	
	@Test(expected = IllegalArgumentException.class) 
	public void operationToBinaryConversionThrowingIllegalArgumentExceptionTest()
	{
		assertEquals(op.operator(null), null);
	}
	
}
