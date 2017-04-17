package others;

/** My implementation of the vector */
public class MyVector<T extends Number>{
	
	/** Array with data */
	private T [] array;
	
	/** The size allocated for the memory array */
	private int capacity;
	
	/** Real number of elements in array */
	private int size;

	public int getCapacity() {
		return capacity;
	}

	public int getSize() {
		return size;
	}
	
	public void set(int index,T val){
		if(index  < capacity){
			array[index] = val;
		}
	}
	
	public T get(int index){
		if(index < capacity){
			return array[index];			
		}
		return null;
	}
	
	/** Print vector */
	public void printVector(){
		for(int i = 0; i < size;i++){
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
	
	public MyVector(){
		capacity = 5;
		array = (T[]) new Number[capacity];
		size = 0;
	}
	
	/** Calculate need capacity
	 * @param newSize New count 
	 **/
	private void calcCapacity(int newSize){
		if(newSize >= capacity){
			capacity = capacity * 2;
		}
		else if(newSize < (capacity / 2)){
			capacity = capacity / 2;
		}
	}
	
	/**
	 * Change size of vector
	 * @param newSize New size of vector
	 */
	private void resize(int newSize){
		int oldCapacity = capacity;
		calcCapacity(newSize);
		if(oldCapacity!=capacity){
			T[] tmp = (T[]) new Number[capacity];
			for(int i = 0; i < size;i++){
				tmp[i] = array[i];
			}
			array = tmp;			
		}
	}
	
	/** Add new element in vector
	 * @param val New value
	 **/
	public void add(T val){
		if(size < capacity - 1){
			array[size] = val;
		}
		else{
			resize(size+1);
			array[size] = val;
		}
		size++;
	}
	
	/** Remove last element */
	public void removeLast(){
		size--;
		if(size < (capacity / 2)){
			resize(size);
		}
	}
}
