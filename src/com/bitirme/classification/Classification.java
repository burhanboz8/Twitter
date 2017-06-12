package com.bitirme.classification;

// Save the Best Classifier
 //weka.core.SerializationHelper.write("best.model", bestClassifier);

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.evaluation.NominalPrediction;
import weka.classifiers.rules.DecisionTable;
import weka.classifiers.rules.OneR;
import weka.classifiers.rules.PART;
import weka.classifiers.trees.DecisionStump;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomForest;
import weka.core.FastVector;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;

public class Classification {
	private String[] modelNames = {"NaiveBayes","J48","PART","DecisionTable","OneR","DecisionStump","RandomForest"};
    private Evaluation[] eval = null;
    private Classifier[] models = {     
			new weka.classifiers.bayes.NaiveBayes(),
			new J48(),
            new PART(),
            new DecisionTable(),
            new OneR(),
            new DecisionStump(),
            new RandomForest()};
    public Classification(){
    	
    }
    public String[] getModelNames(){
    	return modelNames;
    }
  
    public double[] classify(String userFilePath) throws Exception{
    	double[] arr = new double[modelNames.length];
    	Instances instance = new Instances(new FileReader(userFilePath));
    	for(int i =0;i<models.length;i++){
        	double accuracy = models[i].classifyInstance(instance.get(0));
        	arr[i] = accuracy;
    	}
    	return arr;
    }
    public void training(String filePath) throws Exception{
    	if(eval!=null)
    		return;
    	eval = new Evaluation[modelNames.length];
        BufferedReader datafile = readDataFile(filePath);
        
        Instances data = new Instances(datafile);
        data.setClassIndex(data.numAttributes() - 1);
        
        
        // Choose a set of classifiers
        
        
        // Run for each classifier model
        for(int j = 0; j < models.length; j++) {
            eval[j] = simpleClassify(models[j], data);
        }
    }
    public Evaluation simpleClassify(Classifier model, Instances trainingSet) throws Exception {
        Evaluation validation = new Evaluation(trainingSet);
        
        model.buildClassifier(trainingSet);
        //validation.evaluateModel(model, testingSet);
        
        return validation;
    }
    
    public static double calculateAccuracy(FastVector predictions) {
        double correct = 0;
        
        for (int i = 0; i < predictions.size(); i++) {
            NominalPrediction np = (NominalPrediction) predictions.elementAt(i);
            if (np.predicted() == np.actual()) {
                correct++;
            }
        }
        
        return 100 * correct / predictions.size();
    }
    
    public static BufferedReader readDataFile(String filename) {
        BufferedReader inputReader = null;
        
        try {
            inputReader = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException ex) {
            System.err.println("File not found: " + filename);
        }
        
        return inputReader;
    }
    public  String predicate(String filepath) throws Exception{
      	 BufferedReader datafile = readDataFile(filepath);   
           Instances originalTrain = new Instances(datafile);
           originalTrain.setClassIndex(originalTrain.numAttributes()-1);
      	Classifier cls = (Classifier) weka.core.SerializationHelper.read("best.model");
      	
      	//which instance to predict class value
      	int s1=0;  

      	//perform your prediction
     	double value=cls.classifyInstance(originalTrain.instance(s1));

      	//get the name of the class value
      	String prediction=originalTrain.classAttribute().value((int)value); 

      	return prediction;
      }

      public static void updateModel (String filePathArff) throws Exception {
          //load datasets
          DataSource source = new DataSource(filePathArff);
          Instances dataset = source.getDataSet();
          //set class index to the last attribute
          dataset.setClassIndex(dataset.numAttributes()-1);
          //create and build the classifier!
          RandomForest tree = new RandomForest();
          tree.buildClassifier(dataset);

          Evaluation eval = new Evaluation(dataset);
          Random rand = new Random(1);
          int folds = 10;


          //Notice we build the classifier with the training dataset
          //we initialize evaluation with the training dataset and then
          //evaluate using the test dataset

          //test dataset for evaluation
          DataSource source1 = new DataSource(filePathArff);
          Instances testDataset = source1.getDataSet();
          //set class index to the last attribute
          testDataset.setClassIndex(testDataset.numAttributes()-1);
          //now evaluate model
          //eval.evaluateModel(tree, testDataset);
          eval.crossValidateModel(tree, testDataset, folds, rand);
       /*   System.out.println(eval.toSummaryString("Evaluation results:\n", false));

          System.out.println("Correct % = "+eval.pctCorrect());
          System.out.println("Incorrect % = "+eval.pctIncorrect());
          System.out.println("AUC = "+eval.areaUnderROC(1));
          System.out.println("kappa = "+eval.kappa());
          System.out.println("MAE = "+eval.meanAbsoluteError());
          System.out.println("RMSE = "+eval.rootMeanSquaredError());
          System.out.println("RAE = "+eval.relativeAbsoluteError());
          System.out.println("RRSE = "+eval.rootRelativeSquaredError());
          System.out.println("Precision = "+eval.precision(1));
          System.out.println("Recall = "+eval.recall(1));
          System.out.println("fMeasure = "+eval.fMeasure(1));
          System.out.println("Error Rate = "+eval.errorRate());
          //the confusion matrix
          System.out.println(eval.toMatrixString("=== Overall Confusion Matrix ===\n")); */
          weka.core.SerializationHelper.write("best.model", tree);

      }
}
