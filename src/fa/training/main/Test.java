package fa.training.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fa.training.services.CustomerService;

public class Test {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		CustomerService customerService = new CustomerService();
		int choice;
		String phone;
		String status;
		List<String> listCustomer = new ArrayList<>();
		List<String> newlistCustomer;

		do {
			System.out.println("Choose function:");
			System.out.println("1. Add a new Customer");
			System.out.println("2. Show all Customers");
			System.out.println("3. Search Customer");
			System.out.println("4. Remove Customer");
			System.out.println("5. Exit");

			choice = sc.nextInt();

			switch (choice) {
			case 1:
				listCustomer.addAll(customerService.createCustomer());
//				try {
//					status = customerService.save(listCustomer);
//					System.out.println("Save: " + status);
//				} catch (Exception e) {
//					System.out.println("Save Fail!");
//				}
				break;
			case 2:
				customerService.display(listCustomer);
				break;
			case 3:
				newlistCustomer = new ArrayList<>();
				System.out.print("Enter phone number of customer to search: ");
				phone = sc.next();
				
				System.out.println(customerService.search(phone));
//				newlistCustomer.addAll(customerService.search(phone));
//				System.out.println(newlistCustomer.toString());
				break;
			case 4:
				System.out.print("Enter course id to remove: ");
				phone = sc.nextLine();

//				try {
//					status = courseService.remove(courseId);
//					System.out.println("Remove: " + status);
//				} catch (Exception e) {
//					System.out.println("Remove Fail!");
//				}
			}
		} while (choice != 5);

	}

}
