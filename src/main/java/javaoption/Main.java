package javaoption;

import java.util.Optional;

public class Main {
	public static Optional<Integer> divide(int x, int y) {
		if (y == 0) {
			return Optional.empty();
		} else {
			return Optional.of(x / y);
		}
	}

	public static void main(String[] args) {
		System.out.println(divide(10, 2)); // Optional[5]
		System.out.println(divide(10, 0)); // Optional.empty
	}
}
