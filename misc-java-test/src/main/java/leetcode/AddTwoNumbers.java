package leetcode;


public class AddTwoNumbers {

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
        	return l1 == null ? l2 : l1;
        }
        
        ListNode head = null;
        ListNode p = null;
        int addToNext = 0;
        int val = 0;
        
        ListNode p1 = l1;
        ListNode p2 = l2;
        int total, v1,v2;
        while (p1 != null || p2 != null) {
        	v1 = p1 == null ? 0 : p1.val;
        	v2 = p2 == null ? 0 : p2.val;
        	total = v1 + v2 + addToNext;
        	addToNext = total / 10;
        	val = total % 10;
        	if (head == null) {
        		head = new ListNode(val);;
        		p = head;
        	} else {
        		p.next = new ListNode(val);
        		p = p.next;
        	}
        	
        	if (addToNext == 0) {
        		if (p1 != null && p2 == null) {
        			p.next = p1.next;
        			break;
        		}
        		if (p2 != null && p1 == null) {
        			p.next = p2.next;
        			break;
        		}
        	}
        	
        	p1 = p1 == null ? null : p1.next;
        	p2 = p2 == null ? null : p2.next;
        }
        
        if (addToNext > 0) {
        	p.next = new ListNode(addToNext);
        }
        
		return head;
		
    }
	
	public ListNode addV2(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
        	return l1 == null ? l2 : l1;
        }
		
        long n1 = toNum(l1);
        long n2 = toNum(l2);
        System.out.println(n1 + ", " + n2);
        long total = n1 + n2;
        String s = String.valueOf(total);
        char[] c = s.toCharArray();
        ListNode h = new ListNode(c[c.length - 1] - 48);
        ListNode n = h;
        for (int i = c.length - 2; i >= 0; i--) {
        	n.next = new ListNode(c[i] - 48);
        	n = n.next;
        }
		
		return h;
	}
	
	private long toNum(ListNode l) {
		if (l == null) return 0;
        int level = 0;
        long num = l.val;
        ListNode n = l;
        int pow = 1;
        while ( (n = n.next) != null) {
        	level ++;
        	for (int i = 0; i < level; i++) {
        		pow *= 10;
        	}
        	long x = ((long) n.val) * pow;
        	num += x;
//        	System.out.println("power[" + level + "]= " + pow + ", " + n.val + ", " + x + ", " + num);
        	pow = 1;
        }
        return num;
	}
	
	
	public static void main(String[] args) {
		// (2 -> 4 -> 3) + (5 -> 6 -> 4)
		// out: 7 -> 0 -> 8
		ListNode n = new ListNode(1);
		n.next = new ListNode(9);
		n.next.next = new ListNode(9);
		n.next.next.next = new ListNode(9);
		n.next.next.next.next = new ListNode(9);
		n.next.next.next.next.next = new ListNode(9);
		n.next.next.next.next.next.next = new ListNode(9);
		n.next.next.next.next.next.next.next = new ListNode(9);
		n.next.next.next.next.next.next.next.next = new ListNode(9);
		n.next.next.next.next.next.next.next.next.next = new ListNode(9);
		
		
		ListNode m = new ListNode(9);
		
		System.out.println(n);
		System.out.println(m);
		
		AddTwoNumbers util = new AddTwoNumbers();
		ListNode x = util.addTwoNumbers(n, m);
		System.out.println(x);
		
		System.out.println(util.addV2(m, n));
		
	}
	
}

class ListNode {
    int val;
    ListNode next;
	ListNode(int x) { val = x; }
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(val);
		ListNode nxt = this;
		int depth = 0;
		while ( (nxt = nxt.next) != null && (++depth) < 100) {
			sb.append(" -> ").append(nxt.val);
		}
		
		return sb.toString();
		
	}
}