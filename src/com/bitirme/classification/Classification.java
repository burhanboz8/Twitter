package com.bitirme.classification;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.bitirme.model.TwitterUser;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.evaluation.NominalPrediction;
import weka.classifiers.rules.DecisionTable;
import weka.classifiers.rules.OneR;
import weka.classifiers.rules.PART;
import weka.classifiers.trees.DecisionStump;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instances;

public class Classification {
	private String[] modelNames = {"NaiveBayes","J48","PART","DecisionTable","OneR","DecisionStump"};
    private Evaluation[] eval = null;
    private Classifier[] models = {     
			new weka.classifiers.bayes.NaiveBayes(),
			new J48(),
            new PART(),
            new DecisionTable(),
            new OneR(),
            new DecisionStump() };
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
      	Classifier cls = (Classifier) weka.core.SerializationHelper.read("RandomForest.model");
      	
      	//which instance to predict class value
      	int s1=0;  

      	//perform your prediction
     	double value=cls.classifyInstance(originalTrain.instance(s1));
      //double value =  cls.classifyInstance(originalTrain.lastInstance());

      	//get the name of the class value
      	String prediction=originalTrain.classAttribute().value((int)value); 

    /*  	System.out.println("The predicted value of instance "+
      	                    Integer.toString(s1)+
      	                    ": "+prediction); */
      	return prediction;
      }
}
