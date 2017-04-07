package others;

public class MyVector<T extends Number>{
	private T [] array;
	private int capacity;
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
	
	private void calcCapacity(int newSize){
		if(newSize >= capacity){
			capacity = capacity * 2;
		}
		else if(newSize < (capacity / 2)){
			capacity = capacity / 2;
		}
	}
	
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
		
	public void removeLast(){
		size--;
		if(size < (capacity / 2)){
			resize(size);
		}
	}
}
