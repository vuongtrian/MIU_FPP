package lesson3.labs.prob3;


public class Trailer implements Properties {
	private static final double RENT = 500;
	private Address address;
	public Address getAddress() {
		return address;
	}
	public Trailer(Address address) {
		this.address = address;
	}

	@Override
	public double computeRent(){
		return RENT;
	}
	
}
