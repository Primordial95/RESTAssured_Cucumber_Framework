package resources;


import pojo.Address;
import pojo.Customer;

public class TestData {
		
		public Customer addCustomerPayload() {
				Customer customer = new Customer();
				customer.setDescription("New Vampire");
				customer.setBalance(1490);
				customer.setPhone("+15781249");
				Address address = new Address();
				address.setCountry("US");
				address.setState("AR");
				customer.setAddress(address);
				return customer;
		}
}
