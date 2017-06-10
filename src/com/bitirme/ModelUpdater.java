package com.bitirme;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by abdullahtellioglu on 10/06/17.
 */
public class ModelUpdater extends Thread {
    boolean lock = false;
    private static final long DELAY = 10*1000;//10 saniye TODO saniyeyi duzelt
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
            //TODO Update model
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
