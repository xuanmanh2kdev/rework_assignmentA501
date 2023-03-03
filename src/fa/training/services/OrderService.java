package fa.training.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fa.training.entities.Order;

public class OrderService {
	List<String> orders;

	public List<String> createOrder() {
		orders = new ArrayList<>();
		Order order = new Order();
		Scanner sc = new Scanner(System.in);

		System.out.print("+ number: ");
		String number = sc.nextLine();
		order.setNumber(number);

		System.out.print("+ date: ");
		String date = sc.nextLine();
		order.setDate(null);

		orders.add(order.toString());

		return orders;

	}

//	public static void main(String[] args) {
//		OrderService orderService = new OrderService();
//		orderService.createOrder();
//
//		System.out.println(String.join(", ", orderService.orders));
//
//	}
}
