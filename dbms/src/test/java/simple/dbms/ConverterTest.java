package simple.dbms;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;

import client.Converter;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class ConverterTest {
	
	Converter c = new Converter();
	
	@SuppressWarnings("unused")
	private Object[] inputsNotThrowingExceptions() {
	    return new Object[] { 
	        new Object[] {"ASHEEQ","010000010101001101001000010001010100010101010001"},
	        new Object[] {"Z","01011010"},
	        new Object[] {"DHAKA","0100010001001000010000010100101101000001"},
	        new Object[] {"RAHMAN","010100100100000101001000010011010100000101001110"},
	    };
	}
	
	@SuppressWarnings("unused")
	private Object[] inputsThatShouldThrowExceptions() {
		return new Object[] {
			new Object[] {null,null},
		};
	}
	
	@Test
	@Parameters(method = "inputsNotThrowingExceptions")
	public void StringToBinaryTest(String input, String expected)
	{
		assertEquals(c.strToBinary(input).toString(),expected);
	}
	
	@Test
	@Parameters(method = "inputsNotThrowingExceptions")
	public void BinaryToStringTest(String expected, String input)
	{
		assertEquals(expected,c.binToString(input).toString());
	}
	@Test(expected = NullPointerException.class)
	@Parameters(method = "inputsThatShouldThrowExceptions")
	public void CheckIfExceptionIsThrownInStringToBinaryMethod(String input, String expected) throws NullPointerException
	{
		assertEquals(c.strToBinary(input).toString(),expected);
	}
	
	@Test(expected = Exception.class)
	@Parameters(method = "inputsThatShouldThrowExceptions")
	public void CheckIfExceptionIsThrownExceptionInBinaryToStringMethod(String input, String expected) throws NullPointerException
	{
		assertEquals(input,c.binToString(expected));
	}
}
