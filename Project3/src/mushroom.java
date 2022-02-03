
import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class mushroom {
	
	public static void main(String[] args) {
		try {
			// Prepare datasetfrom file
			DataSource source= new DataSource("mushroom.arff");
			Instances dataset= source.getDataSet();
			dataset.setClassIndex(dataset.numAttributes() -1);
			
			// Generated model
			J48 classifier= new J48();
			classifier.buildClassifier(dataset);
			
			// Visualize decision tree
			Visualizer v= new Visualizer();
			v.start(classifier);
			
			//cross validation test
			Evaluation eval= new Evaluation(dataset);
			eval.crossValidateModel(classifier, dataset, 10, new Random(1));
			System.out.println(eval.toSummaryString("Results\n ", false));
			System.out.println(eval.toMatrixString());
			System.out.println(classifier.toString());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
