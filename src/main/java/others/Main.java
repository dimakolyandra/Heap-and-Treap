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
		DataStructureInterface structure1 = (DataStructureInterface)context.getBean("Heap");
		structure1.create(inputArr,structure1);
		structure1.print();
		structure1.add(0);
		structure1.print();
		System.out.println("Minimun = " + structure1.extractMin());
		structure1.print();
		
		DataStructureInterface structure2 = (DataStructureInterface) context.getBean("Treap");
		structure2 = structure2.create(inputArr,structure2);
		structure2.print();
		structure2 = structure2.remove(6);
		structure2 = structure2.remove(32);
		structure2.print();
		System.out.println("Minimum key: " + structure2.extractMin());
	}
}
