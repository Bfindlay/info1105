package lambdas;

import java.util.ArrayList;
import java.util.stream.Stream;

public class Lambdas {
	public static void main(String[] args) {
		Animal dog = new Animal("geoff", "dog");
		Animal cat = new Animal("Scratch", "cat");
		Animal fish = new Animal("Bubbles", "fish");

		ArrayList<Animal> animals = new ArrayList<>();
		animals.add(dog);
		animals.add(cat);
		animals.add(fish);

		Stream<Animal> names = animals.stream().filter(animal -> animal.species == "dog");
		names.forEach(e -> System.out.println(e.name));
		animals.forEach(animal -> System.out.println(animal.name));
	}
}
