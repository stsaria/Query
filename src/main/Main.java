package main;

import etc.Check;

class Main {
	public static void main(String[] args) {
		Check.run();

        double queryVersion = 0.1;
        String queryEdition = "alpha";
		System.out.println("Autoer!\nQuery Version: "+String.valueOf(queryVersion)+"-"+queryEdition);
	}
}