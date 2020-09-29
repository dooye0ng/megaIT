package day10;

public class FunctionFactory {
	
	String strGugudan(int num) {
		String res = "";
		for(int i=1; i<10; ++i) {
			res += (Integer.toString(num) + " X " + Integer.toString(i) + " = " + Integer.toString(num * i) + "\n");
		}
		return res;
	}
	
	double getAverage(int kr, int en, int ma) {
		double res = (kr + en + ma) / 3.0;
		
		return res;
	}
	
	String getRandomPokemon() {
		String[] pokemons = {"ÇÇÄ«Ãò", "¶óÀÌÃò", "ÆÄÀÌ¸®", "²¿ºÎ±â"};
		int rand = (int)(Math.random() * pokemons.length);
		String res = pokemons[rand];
		
		return res;
	}
	
	int getMax(int[] arr) {
		int res = arr[0];
		
		for(int num : arr) {
			if(num > res) {
				res = num;
			}
		}
		
		return res;
	}
}
