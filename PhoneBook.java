package Project;
import java.util.Hashtable;
import java.util.Scanner;
import java.io.IOException;

public class PhoneBook {
	
	// Inner class Contact for each contact entry using doubly linked list
	private static class Contact{
		
		String name;
		long number;
		Contact prev = this;
		Contact next = this;
		
		Contact(){ }
		
		Contact(String name,long number){
			this.name = name;
			this.number = number;
		}
		
		Contact(String name,long number,Contact prev,Contact next){
			this.name = name;
			this.number = number;
			this.prev = prev;
			this.next =  next;
		}
		
	}
	// Ending of Contact inner class
	
	// PhoneBook instance Variables 
	private Contact head = new Contact(null,0);
	private int size;
	
	public int size() {
		return size;
	}
	
	// adding a new Contact in the list
    // adding from first
	public void addFirst(String name,long number) {
		head.next = head.next.prev  = new Contact(name,number,head,head.next);
		++size;
		starLine();
		System.out.println("             Contact added successfully!");
	}
	
	// adding from last
	public void addLast(String name,long number) {
		head.prev = head.prev.next = new Contact(name,number,head.prev,head);
		++size;
		starLine();
		System.out.println("             Contact added successfully!");
	}
	
	
	// Methods for viewing the contact list
	// Method for viewing first contact
	public void viewFirst() {
		if(size==0) { 
			System.out.println("Contact list is empty!");
		    return;
		}
		starLine();
		System.out.println("\n               First Contact : "+head.next.name+"  "+head.next.number);
		starLine();
	}
	
	// Method for viewing last contact
	public void viewLast() {
		if(size==0) { 
			System.out.println("Contact list is empty!");
		    return;
		}
		starLine();
		System.out.println("\n               Last Contact : "+head.prev.name+"  "+head.prev.number);
		starLine();
	}
	
	// Method for viewing all contacts
   public void viewAll() {
	   if(size==0) { 
			System.out.println("Contact list is empty!");
		    return;
		}
	   
	   starLine();
	    for(Contact a = head.next;a!=head.prev;a=a.next) {
		    System.out.println("                 "+a.name+"  "+a.number);
		}
		System.out.println("                 "+head.prev.name+"  "+head.prev.number);
		starLine();
	}
	
   // removing contacts from list
   // removing from first
	 public void removeFirst() {
		 if(size == 0) {
			 starLine();
				System.out.println("              Contact list is empty!");
				return;
			}
    	 String a = head.next.name + "  "+ head.next.number;
    	 head.next =head.next.next;
    	 head.next.prev = head;
    	 --size;
    	 starLine();
    	System.out.println("           First contact is removed!");
     }
	 
	 // removing from last
	 public void removeLast() {
		 if(size == 0) {
			 starLine();
				System.out.println("              Contact list is empty!");
				return;
			}
    	 String a = head.prev.name + "  " + head.prev.number;
    	 head.prev =head.prev.prev;
    	 head.prev.next = head;
    	 --size;
    	 starLine();
     	System.out.println("            Last contact is removed!");
     }
	 
	 // removing all contacts from the list
	public void removeAll() {
		if(size == 0) {
			starLine();
			System.out.println("               Contact list is already empty!");
			return;
		}
		head.name = null;
		head.number = 0;
		head.next = null;
		head.prev = null;
		size = 0;
		starLine();
		System.out.println("                Contact list is empty now");
	}
	
	// removing a contact by name
	public void removeByName(Object name) {
		if(size == 0) {
			starLine();
			System.out.println("              Contact list is empty!");
			return;
		}
		if(head.next.name.equals(name)) {
			head.next = head.next.next;
			head.next.prev = head;
			starLine();
			System.out.println("              "+name +" is removed!");
			return;
		}
		for(Contact i=head.next;i!=head.prev;i=i.next) {
			if(i.next.name.equals(name)) {
				i.next = i.next.next;
				i.next.prev = i;
				--size;
				starLine();
				System.out.println("              "+name +" is removed!");
				return;
			}
		}
		if(head.prev.name.equals(name)) {
			head.prev =head.prev.prev;
	    	head.prev.next = head;
	    	--size;
	    	starLine();
			System.out.println("              "+name +" is removed!");
	    	return;
		}
		starLine();
		System.out.println("           "+name +" is not present in the list!");
		return;
	}
	
	// removing a contact by number
	public void removeByNumber(long number) {
		if(size == 0) {
			starLine();
			System.out.println("            Contact list is empty!");
			return;
		}
		
		if(head.next.number == number) {
			head.next = head.next.next;
			head.next.prev = head;
			starLine();
			System.out.println("             "+number +" is removed!");
			return;
		}
		
		for(Contact i=head.next;i!=head.prev;i=i.next) {
			if(i.next.number == number) {
				i.next = i.next.next;
				i.next.prev = i;
				starLine();
				System.out.println("             "+number +" is removed!");
				return;
			}
		}
		if(head.prev.number == number) {
			head.prev = head.prev.prev;
	    	head.prev.next = head;
	    	--size;
	    	starLine();
			System.out.println("             "+number +" is removed!");
			return;
		}
		
		starLine();
		System.out.println("            "+number +" is not present in the list!");
	}
	
	// sorting contacts according to some order
	// sorting by number using bubble sort ascending
	public void sortByNumberASC() {
		if(size == 0) {
			System.out.println("               Contact list is empty!");
			return;
		}
		Contact contact[] = new Contact[size];
		int count = 0;
		Contact temp;
		for(Contact i = head.next;i != head.prev;i=i.next) {
			contact[count] = i;
			count++;
		}
		contact[contact.length-1] = head.prev;
		
		for(int i=0;i<contact.length;i++) {
			for(int j=0;j<contact.length-1-i;j++) {
				if(contact[j+1].number < contact[j].number) {
					temp = contact[j+1];
					contact[j+1] = contact[j];
					contact[j] = temp;
				}
			}
		}
		for(int i=0;i<contact.length;i++) {
			System.out.println("          "+contact[i].name + "  " + contact[i].number);
		}
		
	}
	
	// sorting by number using bubble sort descending
		public void sortByNumberDESC() {
			if(size == 0) {
				System.out.println("               Contact list is empty!");
				return;
			}
			Contact contact[] = new Contact[size];
			int count = 0;
			Contact temp;
			for(Contact i = head.next;i != head.prev;i=i.next) {
				contact[count] = i;
				count++;
			}
			contact[contact.length-1] = head.prev;
			
			for(int i=0;i<contact.length;i++) {
				for(int j=0;j<contact.length-1-i;j++) {
					if(contact[j+1].number > contact[j].number) {
						temp = contact[j+1];
						contact[j+1] = contact[j];
						contact[j] = temp;					}
				}
			}
			for(int i=0;i<contact.length;i++) {
				System.out.println("          "+contact[i].name + "  " + contact[i].number);
			}
			
		}
	
	// sorting by names using bubble sort ascending 
	public void sortByNameASC() {
		if(size == 0) {
			System.out.println("               Contact list is empty!");
			return;
		}
		Contact contact[] = new Contact[size];
		int count = 0;
		Contact temp ;
		
		for(Contact i = head.next;i != head.prev;i=i.next) {
			contact[count] = i;
			count++;
		}
		contact[contact.length-1] = head.prev;
		
		for(int i = 0;i<contact.length;i++) {
			for(int j=0;j<contact.length-1-i;j++) {
				if(contact[j].name.compareTo(contact[j+1].name)>0) {
					temp = contact[j];
					contact[j] = contact[j+1];
					contact[j+1] = temp;
				}
			}
		}
		
		for(int k=0;k<contact.length;k++) {
			System.out.println("          "+contact[k].name + "  " + contact[k].number);
		}
		
	}
	
	// sorting by names using bubble sort descending
	public void sortByNameDESC() {
		if(size == 0) {
			System.out.println("               Contact list is empty!");
			return;
		}
		Contact contact[] = new Contact[size];
		int count = 0;
		Contact temp ;
		
		for(Contact i = head.next;i != head.prev;i=i.next) {
			contact[count] = i;
			count++;
		}
		contact[contact.length-1] = head.prev;
		
		for(int i = 0;i<contact.length;i++) {
			for(int j=0;j<contact.length-1-i;j++) {
				if(contact[j].name.compareTo(contact[j+1].name)<0) {
					temp = contact[j];
					contact[j] = contact[j+1];
					contact[j+1] = temp;
				}
			}
		}
		
		for(int k=0;k<contact.length;k++) {
			System.out.println("          "+contact[k].name + "  " + contact[k].number);
		}
		
	}
	
	// update contacts
	// update by name 
	public void updateName(String oldName,String newName) {
		if(size == 0) {
			starLine();
			System.out.println("              Contact list is empty!");
			return ;
		}
		for(Contact i = head.next;i!=head.prev;i=i.next) {
			if(i.name.equals(oldName)) {
				i.name = newName;
				starLine();
				System.out.println("              Contact updated successfully!");
				return;
			}
		}
		if(head.prev.name.equals(oldName)) {
			head.prev.name = newName;
			starLine();
			System.out.println("              Contact updated successfully!");
			return;
		}
		starLine();
		System.out.println("             Previous name is not present in the list!");
	}
	
	// update by number 
		public void updateNumber(long oldNumber,long newNumber) {
			if(size == 0) {
				starLine();
				System.out.println("              Contact list is empty!");
				return;
			}
			for(Contact i = head.next;i!=head.prev;i=i.next) {
				if(i.number == oldNumber) {
					i.number = newNumber;
					starLine();
					System.out.println("              Contact updated successfully!");
					return;
				}
			}
			if(head.prev.number == oldNumber) {
				head.prev.number = newNumber;
				starLine();
				System.out.println("              Contact updated successfully!");
				return;
			}
			starLine();
			System.out.println("             Previous number is not present in the list!");
		}
	
	// searching a specific contact
	// searching by full name
		public void searchByName(String key) {
			if(size == 0) {
				System.out.println("                  Contact list is empty!");
				return;
			}
			Hashtable h = new Hashtable();
			for(Contact i = head.next;i!=head.prev;i=i.next) {
				h.put(i.name, i.number);
			}
			h.put(head.prev.name, head.prev.number);
			Object value = h.get(key);
			if(value!=null) {
				starLine();
				System.out.println("                Contact Found : "+key + "  "+ value);
			}
			else {
				starLine();
				System.out.println("              "+key +" is not present");}
		}
	// searching by full number	
		public void searchByNumber(long key) {
			if(size == 0) {
				System.out.println("                  Contact list is empty!");
				return;
			}
			Hashtable h = new Hashtable();
			for(Contact i = head.next;i!=head.prev;i=i.next) {
				h.put(i.number, i.name);
			}
			h.put(head.prev.number, head.prev.name);
			Object value = h.get(key);
			if(value!=null) {
				starLine();
				System.out.println("                Contact Found : "+key + "  "+ value);
			}
			else {
				starLine();
				System.out.println("              "+key +" is not present");}
		}
		
		public static void starLine() {
			System.out.println();
			for(int i=1;i<61;i++) {
				System.out.print("*");
			}
			System.out.println();
		}
		
		public static void cls() {
			try {
				new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
			}
			catch(Exception E){
				
			}
		}
		
		public static void display(PhoneBook l) {
			
			String ch;
			
			do{
				l.starLine();
				System.out.print("                   Phone Book Application");
				l.starLine();
				
				System.out.println("\n                   1. VIEW CONTACTS");
				System.out.println("                   2. ADD CONTACTS");
				System.out.println("                   3. REMOVE CONTACTS");
				System.out.println("                   4. UPDATE CONTACTS");
				System.out.println("                   5. SEARCH CONTACTS");
				System.out.println("                   6. SORT CONTACTS");
				System.out.println("                   7. EXIT");
				
				Scanner sc = new Scanner(System.in);
				l.starLine();
				System.out.print("\n\nEnter your choice : ");
				ch = sc.next();
				
				
				switch(ch) {
				case "1" :
					l.starLine();
					System.out.println("                   VIEW OPTIONS");
					l.starLine();
					System.out.println("\n                   1. VIEW ALL CONTACTS");
					System.out.println("                   2. VIEW FIRST CONTACT");
					System.out.println("                   3. VIEW LAST CONTACT");
					System.out.println("                   4. BACK");
					l.starLine();
					System.out.print("Enter your choice : ");
					String ch1 = sc.next();
					
					switch(ch1) {
					case "1" :
						l.viewAll();
						break;
					case "2" :
						l.viewFirst();
						break;
					case "3" :
						l.viewLast();
						break;
					case "4" :
						break;
					default : System.out.println("Invalid Choice!");	
					}
					break;
				case "2" :
					starLine();
					System.out.println("                         ADD OPTIONS");
					starLine();
					System.out.println("\n                   1. ADD AT FIRST");
					System.out.println("                   2. ADD AT LAST");
					System.out.println("                   3. BACK");
					
					System.out.print("Enter your choice : ");
					String ch3 = sc.next();
					
					switch(ch3) {
					case "1" :
						System.out.print("\nEnter name : ");
						String name = sc.next();
						System.out.print("\nEnter Number : ");
						String number;
						do {
							 number = sc.next();
							 
							 if(number.matches("^[0-9]*$") && number.length()==9) {
								 
							 }
							 else {
								 System.out.print("Enter Valid Number : ");
							 }
							 
						}while(!(number.matches("^[0-9]*$") && number.length()==9));
						l.addFirst(name, Long.parseLong(number));
						break;
					case "2" :
						System.out.print("\nEnter name : ");
						String name1 = sc.next();
						System.out.print("\nEnter Number : ");
						String number1;
						do {
							 number1 = sc.next();
							 
							 if(number1.matches("^[0-9]*$") && number1.length()==9) {
								 
							 }
							 else {
								 System.out.print("Enter Valid Number : ");
							 }
							 
						}while(!(number1.matches("^[0-9]*$") && number1.length()==9));
						l.addLast(name1, Long.parseLong(number1));
						break;
					case "3" :
						break;
					default : System.out.println("Invalid Choice!");	
					}
					break;
				case "3" :
					starLine();
					System.out.println("                      REMOVE OPTIONS");
					starLine();
					System.out.println("\n                   1. REMOVE ALL");
					System.out.println("                   2. REMOVE FIRST");
					System.out.println("                   3. REMOVE LAST");
					System.out.println("                   4. REMOVE BY NAME");
					System.out.println("                   5. REMOVE BY NUMBER");
					System.out.println("                   6. BACK");
					
					System.out.print("Enter your choice : ");
					String ch4 = sc.next();
					
					switch(ch4) {
					case "1" :
						l.removeAll();
						break;
					case "2" :
						l.removeFirst();
						break;
					case "3" :
						l.removeLast();
						break;
					case "4" :
						System.out.print("\nEnter name : ");
						String name = sc.next();
						l.removeByName(name);
						break;
					case "5" :
						System.out.print("\nEnter number : ");
						String number;
						do {
							 number = sc.next();
							 
							 if(number.matches("^[0-9]*$") && number.length()==9) {
								 
							 }
							 else {
								 System.out.print("Enter Valid Number : ");
							 }
							 
						}while(!(number.matches("^[0-9]*$") && number.length()==9));
						l.removeByNumber(Long.parseLong(number));
						break;	
					default : System.out.println("Invalid Choice!");	
					}
					break;
				case "4" :
					starLine();
					System.out.println("\n                      UPDATE OPTIONS");
					starLine();
					System.out.println("\n                   1. UPDATE USING NAME");
					System.out.println("                   2. UPDATE USING NUMBER");
					System.out.println("                   3. BACK");
					
					System.out.print("Enter your choice : ");
					String ch5 = sc.next();
					
					switch(ch5) {
					case "1" :
						System.out.print("\nEnter name you want to update : ");
						String name = sc.next();
						System.out.print("\nEnter new name : ");
						String newName = sc.next();
						l.updateName(name, newName);
						break;
					case "2" :
						System.out.print("\nEnter number you want to update : ");
						String number;
						do {
							 number = sc.next();
							 
							 if(number.matches("^[0-9]*$") && number.length()==9) {
								 
							 }
							 else {
								 System.out.print("Enter Valid Number : ");
							 }
							 
						}while(!(number.matches("^[0-9]*$") && number.length()==9));
						System.out.print("\nEnter new number : ");
						String newNumber;
						do {
							 newNumber = sc.next();
							 
							 if(newNumber.matches("^[0-9]*$") && newNumber.length()==9) {
								 
							 }
							 else {
								 System.out.print("Enter Valid Number : ");
							 }
							 
						}while(!(newNumber.matches("^[0-9]*$") && newNumber.length()==9));
						l.updateNumber(Long.parseLong(number), Long.parseLong(newNumber));
						break;
					case "3" :
						break;
					default : System.out.println("Invalid Choice!");	
					}
					break;
				case "5" :
					starLine();
					System.out.println("\n                     SEARCHING OPTIONS");
					starLine();
					System.out.println("\n                   1. SEARCH BY NAME");
					System.out.println("                   2. SEARCH BY NUMBER");
					System.out.println("                   3. BACK");
					
					System.out.print("Enter your choice : ");
					String ch6 = sc.next();
					
					switch(ch6) {
					case "1" :
						System.out.print("\nEnter name  : ");
						String name = sc.next();
						l.searchByName(name);
						break;
					case "2" :
						System.out.print("\nEnter number : ");
						String number;
						String no = null;
						do {
							 number = sc.next();
							 
							 if(number.matches("^[0-9]*$") && number.length()==9) {
								 
							 }
							 else {
								 System.out.print("Enter Valid Number : ");
							 }
							 
						}while(!(number.matches("^[0-9]*$") && number.length()==9));
						
						l.searchByNumber(Long.parseLong(number));
						break;
					case "3" :
						break;
					default : System.out.println("Invalid Choice!");	
					}
					break;
				case "6" :
					starLine();
					System.out.println("\n                      SORTING OPTIONS");
					starLine();
					System.out.println("\n                   1. SORTING BY NUMBER ASCENDING");
					System.out.println("                   2. SORTING BY NUMBER DESCENDING");
					System.out.println("                   3. SORTING BY NAME ASCENDING");
					System.out.println("                   4. SORTING BY NAME DESCENDING");
					System.out.println("                   5. BACK");
					System.out.print("Enter your choice : ");
					String ch2 = sc.next();
					
					switch(ch2) {
					case "1" :
						starLine();
						System.out.println("                 Sorting By Number Ascending");
						starLine();
						l.sortByNumberASC();
						break;
					case "2" :
						starLine();
						System.out.println("                 Sorting By Number Descending");
						starLine();
						l.sortByNumberDESC();
						break;
					case "3" :
						starLine();
						System.out.println("                 Sorting By Name Ascending");
						starLine();
						l.sortByNameASC();
						break;
					case "4" :
						starLine();
						System.out.println("                 Sorting By Name Descending");
						starLine();
						l.sortByNameDESC();
						break;
					case "5" :
						break;
					default : System.out.println("Invalid Choice!");	
					}
					break;
				case "7" : 
					System.out.println("Thank You!");
					break;
				default : System.out.println("Invalid choice try again!");	
				}
				
			}while(ch != "7");
		
		}
		
		
		
	public static void main(String args[]) {
		PhoneBook MyPhoneBook = new PhoneBook();
		MyPhoneBook.addFirst("Afsa",123422999);
		MyPhoneBook.addLast("Marya",345334568);
		MyPhoneBook.addLast("Fariha",675578456);
		MyPhoneBook.addFirst("Aliza",465371234);
		MyPhoneBook.addLast("Palwasha",675067856);
		MyPhoneBook.addFirst("Navera",123456789);
		display(MyPhoneBook);
	}

}
