package vic.test.junit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class TestLifecycle {

	// only once
	@BeforeClass
	public static void init() {
		System.out.println("** before class");
	}

	// only
	@AfterClass
	public static void destroy() {
		System.out.println("** after class");
	}
	
	// for each test case
	@Before
	public void setUp() {
		System.out.println("-- before");
	}

	// for each test case
	@After
	public void tearDown() {
		System.out.println("-- after");
	}

	@Test
	public void testA() {
		System.out.println("++ testA");
	}

	@Test
	public void testB() {
		System.out.println("++ testB");
	}

	@Test
	@Ignore
	public void testC() {
		System.out.println("++ testC");
	}

}
