package resources;

public enum ApiResources {
		ADDCUSTOMERAPI("v1/customers"),
		GETCUSTOMERAPI("v1/customers/{path}"),
		DELETE_CUSTOMER_API("v1/customers/{path}");
	
		String resource;
		ApiResources(String resource) {
			this.resource=resource;
		}
		public String getResource() {
			return resource;
		}
}
