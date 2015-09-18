package vic.test.temp;

import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

public class Test {
	
	public static void main(String[] args) throws UnsupportedEncodingException {
	    class k1 {
	    	int value;
	    	String name;
	    	k1(int v, String name) {
	    		this.value = v;
	    		this.name = name;
	    	}
	    	public boolean equals(Object o) {
	    		if (o instanceof k1) {
	    			k1 x = (k1) o;
	    			return name.equals(x.name);
	    		}
	    		return false;
	    	}
	    	public int hashCode() {
	    		return value;
	    	}
	    }
	    class k2 {
	    	int value;
	    	k2(int v) {
	    		this.value = v;
	    	}
	    	public boolean equals(Object o) {
	    		if (o instanceof k1) {
	    			k1 x = (k1) o;
	    			return value == x.value;
	    		}
	    		return false;
	    	}
	    	public int hashCode() {
	    		return value;
	    	}
	    }
	    
	    
	    Map<Object, String> map = new HashMap<Object, String>();
	    System.out.println("P= " + map.put(new k1(1, "vic"), "vic"));
	    System.out.println("p2=" + map.put(new k1(1, "liu"), "liu"));
	    System.out.println(map.toString());
	    System.out.println(map.get(new k1(1, "vic")));
	    System.out.println(map.get(new k1(1, "liu")));
	    System.out.println(map.get(new k1(9, "liu")));

	}
	
}

class Super {
	
	public void doIt() throws SocketException {
		executeIt();
	}
	
	private void executeIt() {
		System.out.println("--> super");
	}
}

class Sub extends Super {
	
	public void doIt() throws ConnectException {
		executeIt();
	}
	
	private void executeIt() {
		System.out.println("--> sub");
	}
}
