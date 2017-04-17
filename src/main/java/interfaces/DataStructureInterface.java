package interfaces;

import others.MyVector;
import treap.Treap;

public interface DataStructureInterface {
	public DataStructureInterface add(int val);
	public int extractMin();
	public DataStructureInterface remove(int val);
	public void print();
	public DataStructureInterface create(int [] val, DataStructureInterface struct);
}
