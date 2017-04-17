package heap;
import interfaces.DataStructureInterface;
import others.MyVector;

/** Binary Heap
 *	@author dimakolyandra */
public class Heap implements DataStructureInterface{
	
	/** Vector of priority */
	private MyVector<Integer> priority;
	
	/** Set vector of priority */
	public void setPriority(MyVector<Integer> pr){
		priority = pr;
	}
	
	/** Add new value in heap
	 * @param val New value
	 * @return Link on the calling object */
	@Override
	public DataStructureInterface add(int val) {
		// TODO Auto-generated method stub
		priority.add(new Integer(val));
		siftUp(priority.getSize()-1,priority);
		return this;
	}
	
	/** Prints heap */
	@Override
	public void print() {
		// TODO Auto-generated method stub
		for(int i = 0; i < priority.getSize(); i++){
			System.out.print(priority.get(i) + " ");
		}
		System.out.println();
	}
	
	/** Sifting up
	 * @param i Index of first element for sifting
	 * @param arr Vector where heap is being stored */
	private void siftUp(int i,MyVector<Integer> arr){
		while(arr.get(i) < arr.get((i-1)/2)){
			swap(arr,i,(i-1)/2);
			i = (i-1)/2;
		}
	}
	
	/** Changes two elements in array
	 * @param arr Vector where elements must be changed
	 * @param i Index of first element
	 * @param j Index of second element */
	private void swap(MyVector<Integer> arr,int i, int j){
		int tmp = arr.get(i);
		arr.set(i,arr.get(j));
		arr.set(j,new Integer(tmp));
	}
	
	/** Sifting down
	 * @param i Index of first element for sifting
	 * @param arr Vector where heap is being stored */
	private void siftDown(int i,MyVector<Integer> arr){
		while((2*i + 1) < arr.getSize()){
			int left = 2*i + 1;
			int right = 2*i + 2;
			int tmp = left;
			if(right < arr.getSize() && arr.get(right) < arr.get(left)){
				tmp = right;
			}
			if(arr.get(i) < arr.get(tmp)){
				break;
			}
			swap(arr,i,tmp);
			i = tmp;
		}
	}
	
	/** Create heap
	 * @param val Vector with priorities */
	@Override
	public DataStructureInterface create(int [] val, DataStructureInterface struct) {
		for(int i = 0; i < val.length;i++){
			priority.add(new Integer(val[i]));
		}
		for(int i = priority.getSize()/2; i >= 0;i--){
			siftDown(i,priority);
		}
		return this;
	}
	
	/** Exctract minimum element 
	 * @return Returns minimum of heap*/
	public int extractMin() {
		// TODO Auto-generated method stub
		int res = priority.get(0);
		swap(priority,0,priority.getSize()-1);
		remove(priority.getSize() - 1);
		//priority.removeLast();
		remove(-1);
		siftDown(0,priority);
		return res;
	}
	
	/** Remove last element*/
	@Override
	public DataStructureInterface remove(int val) {
		// TODO Auto-generated method stub
		if(val == -1){
			priority.removeLast();
		}
		return this;
	}
}
