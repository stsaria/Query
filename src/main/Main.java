package main;

import etc.Check;

class Main {
	public static void main(String[] args) {
        double queryVersion = 0.1;
        String queryEdition = "alpha";
		System.out.println("Autoer!\nQuery Version: "+String.valueOf(queryVersion)+"-"+queryEdition);

		Check.run();
	}
}