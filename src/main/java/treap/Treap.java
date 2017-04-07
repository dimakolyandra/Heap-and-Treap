package treap;

import interfaces.DataStructureInterface;

public class Treap implements DataStructureInterface{
	
	public int key;
	public int priority;
	public Treap left;
	public Treap right;
	
	public Treap(){
		left = right = null;
	}
	
	public Treap(int k,int p){
		key = k;
		priority = p;
		left = null;
		right = null;
	}
	
	public Treap(int k,int p,Treap l,Treap r){
		key = k;
		priority = p;
		left = l;
		right = r;
	}
	public Treap search(int k,Treap tr){
		Treap res = null;
		while(tr!=null){
			if(tr.key < k){
				tr = tr.right;
			}
			else if(tr.key > k){
				tr = tr.left;
			}
			else{
				res = new Treap(tr.key,tr.priority);
				break;
			}
		}
		return res;
	}
	public void setKey(int val){
		key = val;
	}
	
	public void setPriority(int pr){
		priority = pr;
	}
	
	public static Treap merge(Treap l,Treap r){
		if(l == null) return r;
		if(r == null) return l;
		Treap result;
		if(l.priority < r.priority){
			result = new Treap(r.key,r.priority);
			result.right = r.right;
			result.left = Treap.merge(l,r.left);
		}
		else{
			result = new Treap(l.key,l.priority);
			result.left = l.left;
			result.right = Treap.merge(l.right,r);
		}
		return result;
	}
	
	public void split(int x,ResultOfSplit l,ResultOfSplit r,Treap t){
		ResultOfSplit newRes = new ResultOfSplit();
		if(t.key <= x){	
			if(t.right == null){
				r.tr = null;
			}
			else{
				split(x,newRes,r,t.right);
			}
			l.tr = new Treap(t.key,t.priority,t.left,newRes.tr);
		}
		else{
			if(t.left == null){
				l.tr = null;
			}
			else{
				split(x,l,newRes,t.left);
			}
			r.tr = new Treap(t.key,t.priority,newRes.tr,t.right);
		}
	}
	
	private static void printSpace(int l){
		for(int i = 0; i < l;i++){
			System.out.print(" ");
		}
	}
	
	private static void rRl(Treap t, int l){
		if (t != null) {
			rRl(t.right,l + 1);
			printSpace(4*l);
			System.out.println(t.key + "-" + t.priority);
			rRl(t.left,l+1);
		}
		else{
			printSpace(4*l);
			System.out.println("NULL");
		}
	}

	public static void printree(Treap t)
	{
	  rRl(t, 0);
	  System.out.println();
	}
	
	@Override
	public Treap add(int val) {
		// TODO Auto-generated method stub
		int pr = (int)(Math.random()*100);
		Treap newTreap = new Treap(val,pr);
		ResultOfSplit resOfSplitL = new ResultOfSplit();
		ResultOfSplit resOfSplitR = new ResultOfSplit();
		split(val,resOfSplitL,resOfSplitR,this);
		Treap lAndX = Treap.merge(resOfSplitL.tr,newTreap);
		Treap fullTreap = Treap.merge(lAndX,resOfSplitR.tr);
		return fullTreap;
	}

	@Override
	public int extractMin() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Treap remove(int val) {
		// TODO Auto-generated method stub
		ResultOfSplit l = new ResultOfSplit();
		ResultOfSplit r = new ResultOfSplit();
		split(val - 1,l,r,this);
		ResultOfSplit tnodesWithRemKey = new ResultOfSplit();
		ResultOfSplit newR = new ResultOfSplit();
		split(val,tnodesWithRemKey,newR,r.tr);
		Treap res = Treap.merge(l.tr, newR.tr);
		return res;
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		printree(this);
	}

	@Override
	public void create(int[] val) {
		// TODO Auto-generated method stub
		
	}	
	class ResultOfSplit{
		Treap tr;
		ResultOfSplit(){
			tr = null;
		}
	}
}
