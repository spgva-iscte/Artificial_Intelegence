import java.util.Random;
import java.util.Scanner;

import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class Hawks {
	
	public static void main(String[] args) {
		try {
			// Prepare datasetfrom file
			DataSource source= new DataSource("Hawks.arff");
			Instances dataset= source.getDataSet();
			dataset.setClassIndex(dataset.numAttributes() -1);
			
			// Generated model
			J48 classifier= new J48();
			classifier.buildClassifier(dataset);
			
			// Visualize decision tree
			Visualizer v= new Visualizer();
			v.start(classifier);
			
			//Cross validation test
			Evaluation eval= new Evaluation(dataset);
			eval.crossValidateModel(classifier, dataset, 10, new Random(1));
			System.out.println(eval.toSummaryString("Results\n ", false));
			System.out.println(eval.toMatrixString());
			System.out.println(classifier.toString());
			
			//New Instance
			NewInstances ni= new NewInstances(dataset);
			Scanner in= new Scanner(System.in);
			Boolean b= true;
			while(b) {
				System.out.println("Deseja colocar informações sobre um novo falcão? ");
				b= in.nextBoolean();
				if(b) {
					System.out.println("O falcão é jovem(I) ou adulto(A)? ");
					String age= in.next();
					System.out.println("Qual é a dimensão de asas em mm? ");
					int wing= in.nextInt();
					System.out.println("Qual é o peso em gramas? ");
					int weight= in.nextInt();
					System.out.println("Qual a dimensão do culmen em mm? ");
					double culmen= in.nextDouble();
					System.out.println("Qual a dimensão do hallux em mm? ");
					double hallux= in.nextDouble();
					System.out.println("Qual a dimensão da cauda em mm? ");
					int tail= in.nextInt();
					System.out.println("Qual a especie do falcão? Cooper's(CH), Red-tailed(RT), Sharp-shinned(SS) ");
					String species= in.next();
					String[] values= {age,String.valueOf(wing),String.valueOf(weight),String.valueOf(culmen),String.valueOf(hallux),String.valueOf(tail),species};
					ni.addInstance(values);
					Instances test_dt= ni.getDataset();
					for(int i= 0; i< test_dt.numInstances(); i++) {
						Instance inst= test_dt.instance(i);
						String actual= inst.stringValue(inst.numAttributes() -1);
						double predict= classifier.classifyInstance(inst);
						String pred= test_dt.classAttribute().value((int)(predict));
						System.out.println(actual+ " \t "+ pred);
					}
				}else {
					System.exit(0);
				}
			}
		
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
