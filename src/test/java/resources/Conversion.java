package resources;

import java.util.HashMap;

import pojo.Customer;

public class Conversion {
	   Customer customer=new TestData().addCustomerPayload(); 

		public HashMap<String, String> convertFormParam(String name,String email) {
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put("name", name);
			hm.put("email", email);
			hm.put("description", customer.getDescription());
			hm.put("address[country]", customer.getAddress().getCountry());
			hm.put("address[state]", customer.getAddress().getState());
			hm.put("phone", customer.getPhone());
			return hm;
		}
		
		public HashMap<String, Integer> convertFormParamInt(){
			HashMap<String, Integer> hm = new HashMap<String, Integer>();
			hm.put("balance", 1475);
			return hm;
		}
}
