package treap;

import interfaces.DataStructureInterface;

/** Class of treap */
public class Treap implements DataStructureInterface{
	
	/** Key of node */
	public int key;
	
	/** Priority of node */
	public int priority;
	
	/** Link to the left child node */
	public Treap left;
	
	/** Link to the right child node */
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
	
	/** Searching in threap 
	 * @param k Desired key
	 * @param tr Current node
	 * @return If key was found 
	 * returns node with this key ,
	 *  else returns null
	 */
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
	
	/** Set key */
	public void setKey(int val){
		key = val;
	}
	
	/** Set priority */
	public void setPriority(int pr){
		priority = pr;
	}
	
	/** Merge two treaps 
	 * @param l First treap, where all keys less than keys in treap r
	 * @param r Second treap, where all keys bigger than keys in treap l
	 * @return Returns new treap 
	 **/
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
	
	/** Split two treaps by key 
	 * @param x Key for splitting
	 * @param l Object wrapper for tnode l
	 * @param r Object wrapper for tnode r
	 * @param t Current tnode
	 **/
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
	
	/** Print need count of spaces */
	private static void printSpace(int l){
		for(int i = 0; i < l;i++){
			System.out.print(" ");
		}
	}
	
	/** Main method which print tree by tree traversal*/
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

	/** Print tree */
	public static void printree(Treap t){
	  rRl(t, 0);
	  System.out.println();
	}
	
	/** Add new tnode 
	 * @param val It is new key for new tnode
	 * @return Returns new treap 
	 **/
	@Override
	public Treap add(int val) {
		// TODO Auto-generated method stub
		int pr = (int)(Math.random()*1000);
		Treap newTreap = new Treap(val,pr);
		ResultOfSplit resOfSplitL = new ResultOfSplit();
		ResultOfSplit resOfSplitR = new ResultOfSplit();
		split(val,resOfSplitL,resOfSplitR,this);
		Treap lAndX = Treap.merge(resOfSplitL.tr,newTreap);
		Treap fullTreap = Treap.merge(lAndX,resOfSplitR.tr);
		return fullTreap;
	}
	
	/** Find minimum key */
	@Override
	public int extractMin() {
		// TODO Auto-generated method stub
		Treap tr = this;
		while(tr.left!=null){
			tr = tr.left;
		}
		return tr.key;
	}
	
	/** Remove from treap
	 * @param val Key of removing tnode
	 * @return Returns new treap without
	 * tnode with key val
	 **/
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

	/** Print treap */
	@Override
	public void print() {
		// TODO Auto-generated method stub
		printree(this);
	}
	/** Create treap from array of keys */
	@Override
	public DataStructureInterface create(int[] val,DataStructureInterface struct) {
		// TODO Auto-generated method stub
		Treap tr = new Treap();
		tr.setKey(val[0]);
		for(int i = 1; i < val.length; i++){
			tr = tr.add(val[i]);
		}
		return tr;
	}
	/** Class wrapper for node */
	class ResultOfSplit{
		Treap tr;
		ResultOfSplit(){
			tr = null;
		}
	}
}
