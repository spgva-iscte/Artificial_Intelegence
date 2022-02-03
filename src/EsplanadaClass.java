import java.util.Scanner;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;

public class EsplanadaClass {

	public static void main(String[] args) throws Exception {
		
		Scanner in= new Scanner(System.in);
		System.out.println("Qual é a distancia de segurancada esplanada? ");
		double distancia= in.nextDouble();
		System.out.println("Qual é a percentagem de ocupação da esplanada? ");
		double esplanada= in.nextDouble();
		in.close();
		
		String filename = "esplanada.fcl";
		FIS fis = FIS.load(filename, true);
		
		if(fis == null) {
			System.err.println("Can't load file: '" + filename + "'");
			System.exit(1);
		}

		FunctionBlock fb = fis.getFunctionBlock(null);
		
		fb.setVariable("Distancia_Seguranca", distancia);
		fb.setVariable("Ocupacao", esplanada);
		
		fb.evaluate();
		
		fb.getVariable("Semaforo").defuzzify();

		System.out.println("Semaforo: " + fb.getVariable("Semaforo").getValue());

	}
}
