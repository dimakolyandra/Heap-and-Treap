package others;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import heap.Heap;
import interfaces.DataStructureInterface;
import treap.Treap;

public class Main {
	
	DataStructureInterface struct;
	static int [] inputArr = {1,5,3,4,6,7,12,32}; 
	
	public static void main(String[] args){
		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		DataStructureInterface structure = (DataStructureInterface)context.getBean("Heap");
		
		structure.create(inputArr);
		structure.print();
		structure.add(0);
		structure.print();
		System.out.println("Minimun = " + structure.extractMin());
		structure.print();
		
		structure = (DataStructureInterface) context.getBean("Treap");
		Treap tr = (Treap) structure;
		tr.setKey(inputArr[0]);
		for(int i = 1; i < inputArr.length;i++){
			tr = tr.add(inputArr[i]);
		}
		tr.print();
		System.out.println("-------------");
		tr = tr.remove(6);
		tr = tr.remove(32);
		tr.print();
		Treap ftr = tr.search(5, tr);
		if(ftr!=null){
			System.out.println("ELEMENT:" + ftr.key + "-" + ftr.priority);
		}
	}
}
