package heap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import interfaces.DataStructureInterface;
import others.MyVector;

public class Heap implements DataStructureInterface{
	
	private MyVector<Integer> priority;
	
	public void setPriority(MyVector<Integer> pr){
		priority = pr;
	}
	@Override
	public DataStructureInterface add(int val) {
		// TODO Auto-generated method stub
		priority.add(new Integer(val));
		siftUp(priority.getSize()-1,priority);
		return this;
	}
	
	@Override
	public void print() {
		// TODO Auto-generated method stub
		for(int i = 0; i < priority.getSize(); i++){
			System.out.print(priority.get(i) + " ");
		}
		System.out.println();
	}
	
	private void siftUp(int i,MyVector<Integer> arr){
		while(arr.get(i) < arr.get((i-1)/2)){
			swap(arr,i,(i-1)/2);
			i = (i-1)/2;
		}
	}
	
	private void swap(MyVector<Integer> arr,int i, int j){
		int tmp = arr.get(i);
		arr.set(i,arr.get(j));
		arr.set(j,new Integer(tmp));
	}
	
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
	
	private void copy(int[] a,int [] b,int size){
		for(int i = 0; i < size;i++){
			a[i] = b[i];
		}
	}
	
	@Override
	public void create(int [] val) {
		for(int i = 0; i < val.length;i++){
			priority.add(new Integer(val[i]));
		}
		for(int i = priority.getSize()/2; i >= 0;i--){
			siftDown(i,priority);
		}
	}
	@Override
	public int extractMin() {
		// TODO Auto-generated method stub
		int res = priority.get(0);
		swap(priority,0,priority.getSize()-1);
		priority.removeLast();
		siftDown(0,priority);
		return res;
	}
	@Override
	public DataStructureInterface remove(int val) {
		// TODO Auto-generated method stub
		return null;
	}
}
