package lesson9.labs.prob7a;

import lesson9.labs.prob7b.TriFunction;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		List<Employee> list = Arrays.asList(new Employee("Joe", "Davis", 120000),
				          new Employee("John", "Sims", 110000),
				          new Employee("Joe", "Stevens", 200000),
		                  new Employee("Andrew", "Reardon", 80000),
		                  new Employee("Joe", "Cummings", 760000),
		                  new Employee("Steven", "Walters", 135000),
		                  new Employee("Thomas", "Blake", 111000),
		                  new Employee("Alice", "Richards", 101000),
		                  new Employee("Donald", "Trump", 100000));
		
		//your stream pipeline here
		list.stream()
				.filter(e -> e.getSalary() > 100000)
				.filter((Employee e) -> e.getLastName().charAt(0) > 'M')
				.collect(Collectors.toList()).stream()
				.map(employee -> employee.getFirstName() + " " + employee.getLastName()).forEach(System.out::println);

	}

}