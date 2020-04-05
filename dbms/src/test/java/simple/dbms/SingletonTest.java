package simple.dbms;

import static org.junit.Assert.assertEquals;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.Set;
import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import server.Singleton;

@RunWith(JUnitParamsRunner.class)
public class SingletonTest {
	
	Singleton st = Singleton.getInstance();
	ConcurrentMap<Object,Object> map = new ConcurrentHashMap<>();
	@SuppressWarnings("unused")
	private Object[] singletonOperations() 
	{
		return new Object[] 
		{
			new Object[] {"asheeq","rahman"},
			new Object[] {"asif","khairuzzaman"},
			new Object[] {"SSD","TECH"},
			new Object[] {"Numbers",12345},
			new Object[] {12345,"also numbers"},
			new Object[] {"", ""},
			new Object[] {"key", ""},
		};
	}
	
	@Test
	public void singletonSharesTheSameReferenceTest() 
	{
		Singleton st1 = Singleton.getInstance();
		Singleton st2 = Singleton.getInstance();
		assertEquals(st1,st2);
	}
	
	@Test
	@Parameters(method = "singletonOperations")
	public void insertOperationTest(Object key, Object value) 
	{
		st.insert(key,value);
		assertEquals(st.read(key),value);
	}
	
	@Test
	@Parameters(method = "singletonOperations")
	public void updateOperationTest(Object key,Object value) 
	{
		st.update(key,value);
		assertEquals(st.read(key),value);
	}
	
	@Test
	@Parameters(method = "singletonOperations")
	public void readOperationWhenDatabaseHasTheValue(Object key, Object value) 
	{
		st.insert(key,value);
		assertEquals(st.read(key), value);
	}
	
	@Test
	public void readOperationWhenDatabaseDoesNotHaveTheValue() 
	{
		assertEquals(st.read("a random key"), null);
	}
	
	@Test
	@Parameters(method = "singletonOperations")
	public void deleteOperation(Object key,Object value) 
	{
		st.delete(key);
		assertEquals(st.read(key), null);
	}
	
	@Test
	public void deleteallOperation() 
	{
		st.deleteAll();
		assertEquals(st.getSize(), 0);
	}
	
	@Test
	public void getSizeOperationTest() 
	{
		st.deleteAll();
		assertEquals(st.getSize(),0);
		st.insert("asheeq", "rahman");
		assertEquals(st.getSize(),1);
		st.insert("ssd", "tech");
		assertEquals(st.getSize(),2);
	}
	
	@Test
	public void readallOperationTest() 
	{
		st.deleteAll();
		st.insert("asheeq", "rahman");
		map.put("asheeq", "rahman");
		st.insert("ssd","tech");
		map.put("ssd","tech");
		st.insert(1234, "numbers");
		map.put(1234, "numbers");
		Set<Entry<Object, Object>> set1 = map.entrySet();
		Set<Entry<Object, Object>> set2 = st.readAll();
		assertEquals(set1,set2);
	}
	
	@Test
	public void getMemoryOperationTest() 
	{
		st.deleteAll();
		int occupiedMemory = (int) ((32*0) + (4*0)/0.75);
		assertEquals(st.getMemory(),occupiedMemory);
		st.insert("asheeq", "rahman");
		occupiedMemory = (int) ((32*1) + (4*1)/0.75);
		assertEquals(st.getMemory(),occupiedMemory);
		st.insert("ssd", "tech");
		occupiedMemory = (int) ((32*2) + (4*2)/0.75);
		assertEquals(st.getMemory(),occupiedMemory);
	}
	
	@Test(expected = IllegalArgumentException.class) 
	public void insertOperationThrowingIllegalArgumentException()
	{
		st.insert(null,null);
		assertEquals(st.read(null),null);
	}
	
	@Test(expected = IllegalArgumentException.class) 
	public void updateOperationThrowingIllegalArgumentException()
	{
		st.update(null,null);
		assertEquals(st.read(null),null);
	}
	
	@Test(expected = IllegalArgumentException.class) 
	public void readOperationThrowingIllegalArgumentException()
	{
		//st.insert(null,null);
		assertEquals(st.read(null),null);
	}
}
