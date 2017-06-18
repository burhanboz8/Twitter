package com.bitirme;

import com.bitirme.classification.Classification;
import com.bitirme.classification.PrepareData;
import com.bitirme.database.DatabaseHelper;
import twitter4j.TwitterException;

import java.sql.SQLException;

/**
 * Created by abdullahtellioglu on 10/06/17.
 */
public class ModelUpdater extends Thread {
    boolean lock = false;
    private static final long DELAY = 60*10*1000;//10 dakika TODO saniyeyi duzelt
    private int threshold = 10;// kullanicilarin veritabani tablosundaki farki threshold oldugunda modele ekle yoksa feedbackte birak
    public boolean isLock(){
        return lock;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public synchronized void start() {
        super.start();
    }

    @Override
    public void run() {

        while(true){
            System.out.println("Update started!");
            lock = true;
            //TODO Delete table values in temporary tweets table§
            String filePathCsv = null;
            String filePathArff = null;
            try {
                DatabaseHelper dbhelper = new DatabaseHelper();
                dbhelper.updateUsers(threshold);
                filePathCsv = PrepareData.createUserCSVFile(dbhelper.getUsers());
                filePathArff = PrepareData.createUserARFFFile(filePathCsv);
                //System.out.println(filePathArff);
                Classification.updateModel(filePathArff);
            }catch (SQLException ex){
                ex.printStackTrace();
            }catch (TwitterException ex){
                ex.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            lock = false;
            try{
                System.out.println("Update finished!");
                Thread.sleep(DELAY);
            }catch (InterruptedException ex){
                ex.printStackTrace();
            }
        }
    }
}
