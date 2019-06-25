import java.lang.Math.*;
import java.util.Random;
class EntropyRandom{
	
	public static void main(String[] args) {
		Random r = new Random();
		double numero=0,uno=0,cero=0;

		for(int i = 0 ; i<1000 ; ++i)
		{
			numero = r.nextInt(2);
			
			if(numero == 1)
				++uno;
			else if(numero == 0)
				++cero;
		}

		double probabilidad0 = cero/(double)1000, probabilidad1 = uno/(double)1000;
		double entropy = (-1)*(probabilidad0*(Math.log(probabilidad0)/Math.log(2)) +
			 probabilidad1*(Math.log(probabilidad1)/Math.log(2)));

		System.out.println();
		System.out.println("Entropy: "+entropy);
		System.out.println("Probalidad1: "+probabilidad1);
		System.out.println("Probalidad0: "+probabilidad0);
		System.out.println("cero: "+cero);
		System.out.println("uno: "+uno);

	}
}