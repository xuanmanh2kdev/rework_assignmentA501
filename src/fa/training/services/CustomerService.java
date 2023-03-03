package fa.training.services;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import fa.training.entities.Customer;
import fa.training.entities.Order;
import fa.training.utils.Constants;

public class CustomerService {
	Scanner sc = new Scanner(System.in);
	List<String> customers;

	public List<String> createCustomer() {
		customers = new ArrayList<>();
		Customer customer = new Customer();
		Order order = new Order();
		OrderService orderService = new OrderService();

		System.out.println("----Enter Customer information--");

		System.out.print("Enter name: ");
		String name = sc.nextLine();
		customer.setName(name);

		System.out.print("Enter phone: ");
		String phone = sc.nextLine();
		customer.setPhoneNumber(phone);

		System.out.print("Enter address: ");
		String address = sc.nextLine();
		customer.setAddress(address);

		System.out.println("Enter order infor: ");

		customer.setOrder(orderService.createOrder());

		customers.add(customer.toString());

		return customers;
	}

	public String save(List<String> customers) throws Exception {
		ObjectOutputStream objectOutputStream = null;

		try {
			objectOutputStream = new ObjectOutputStream(new FileOutputStream(Constants.FILE_PATH));
			objectOutputStream.writeObject(customers);
		} catch (Exception exception) {
			throw new Exception();
		} finally {
			if (objectOutputStream != null) {
				objectOutputStream.close();
			}
		}
		return Constants.SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public List<String> findAll() throws IOException {
		ObjectInputStream objectInputStream = null;
		List<String> customers;
		try {
			objectInputStream = new ObjectInputStream(new FileInputStream(Constants.FILE_PATH));
			customers = (List<String>) objectInputStream.readObject();
		} catch (Exception exception) {
			throw new IOException();
		} finally {
			if (objectInputStream != null) {
				objectInputStream.close();
			}
		}
		return customers;
	}

	public void display(List<String> customers) {
		System.out.println("---------------LIST OF Customer-------------------");

		for (String customer : customers) {
			System.out.println(customer);
		}
	}

	public List<String> search(String phone) throws IOException {
		List<String> customers = findAll();

		System.out.println(customers.toString());

		List<String> ordersSearch = new ArrayList<String>();

		for (int i = 0; i < customers.size(); i++) {
			if (customers.get(i).contains(phone)) {
				System.out.println("Contain");
				ordersSearch.add(customers.get(i));
			}
//			for (String customer : customers) {
//				if (customer.contains(phone)) {
//					ordersSearch.add(customer);
//				}
//			}

		}

		System.out.println("result" + ordersSearch.toString());
		return ordersSearch;
	}

	public boolean remove(String phone) throws Exception {
		boolean removed = false;

		List<String> customers = findAll();
		if (customers == null) {
			throw new IOException();
		}
		Iterator<String> iterator = customers.iterator();
		while (iterator.hasNext()) {
			String customer = iterator.next();
			if (phone.equalsIgnoreCase(customer)) {
				iterator.remove();
				removed = true;
				break;
			}
		}

		if (removed) {
			try {
				// update list
				save(customers);
			} catch (Exception e) {
				throw new Exception();
			}
			return true;
		}
		return false;
	}
}
