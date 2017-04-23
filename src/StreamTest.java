import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
//import java.util.stream.IntStream;

public class StreamTest {
	
	private Collection<Integer> intCol;
	
	private List<Car> carsList;
	
	private class Car{
		String brand;
		int year;
		
		Car(String brand, int year){
			this.brand = brand;
			this.year = year;
		}
		
		@Override
		public String toString() {
		    return brand;
		}
	}
	
	private class Pair{
		private String key;
		private int hash;
		
		public Pair(String key, int hash) {
			super();
			this.key = key;
			this.hash = hash;
		}		
	}
	
	StreamTest (){
		this.intCol = new ArrayList<Integer>();
		this.intCol.add(2);
		this.intCol.add(3);
		this.intCol.add(1);
		this.intCol.add(5);
		this.intCol.add(4);
		this.intCol.add(3);
		this.intCol.add(8);
		this.intCol.add(6);
		this.intCol.add(7);
		
		carsList = Arrays.asList(new Car("Toyota", 2010),
					new Car("Honda", 2012),
					new Car("Tesla", 2017),
					new Car("BMW", 2010),
					new Car("Lanos", 2005));
	}
	
	public void testBasics(){
		
		System.out.println("---Stream Basics---");
		this.intCol.stream()
		    .filter(s -> s%2==0)
		    //.map(String::toUpperCase)
		    .sorted()
		    .forEach(System.out::println);
		
		System.out.println("------");
		//IntStream.of(1, 4, 5, 7, 2)
		//.sum()
		//.ifPresent(System.out::println);
		Arrays.stream(new int[] {1, 2, 3})
	    .map(n -> 2 * n + 1)
	    .average()
	    .ifPresent(System.out::println);  // 5.0
		
		
	}
	
	public void testCollectors(){
		System.out.println("----Collectors-------");
		//System.out.println(carsList);
		List<Car> newerCars = this.carsList
				.stream()
				.filter(y -> y.year>=2011)
				.collect(Collectors.toList());
		
		newerCars
			.stream()
			.forEach(s -> System.out.println(s.brand + ": " + s.year));
	}
	
	public void maxValues(){
		System.out.println("----Max Values-------");
		IntSummaryStatistics summary = this.intCol.stream()
			.collect(Collectors.summarizingInt(s -> s));
		System.out.println("Largest value is: " + summary.getMax());
		
		
		System.out.print("Max 5 are: ");
		this.intCol.stream()
			.sorted(Comparator.reverseOrder())
			.limit(5)
			.forEach(s-> System.out.print(s + " "));
		
		System.out.println("");
	}
	
	public void testPairs(){
		System.out.println("----Pairs-------");
		Collection<Pair> colPair = new ArrayList<Pair>();
		this.carsList.stream()
			.forEach(s -> {
			String a = s.brand+ " " + s.year;
			colPair.add(new Pair(a, a.hashCode()));
			});
		colPair.forEach(s -> System.out.println(s.key + ": " + s.hash)); //hash of int 
	}
	
	public void testGrouping(){
		System.out.println("----Group by-------");
	Map<Integer, Long> carByYear = carsList
			.stream()
			.collect(
					Collectors.groupingBy(s -> s.year, Collectors.counting()));
	System.out.println(carByYear);
	}
}

