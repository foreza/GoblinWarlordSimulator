package com.vartyr.givemeonereason;

public class MonetizationManager {

    public String LOG_TAG = "[MONEYMANAGER]";

    public int numBannerSwiped = 0;
    public int numVideosWatched = 0;
    public int numEncountersScrolled = 0;


    // static variable single_instance of type Singleton
    private static MonetizationManager instance = null;

    // private constructor restricted to this class itself
    private MonetizationManager() {
            populateWithTestValues();
    }

    // static method to create instance of Singleton class
    public static MonetizationManager getInstance()
    {
        if (instance == null){
            instance = new MonetizationManager();
        }

        return instance;
    }


    // TODO - implement so we can persist values to disk and then eventually to server
    private boolean retrieveValuesFromStorage(){
        return false;
    }

    private void populateWithTestValues(){
        numBannerSwiped = 100;
        numVideosWatched = 20;
        numEncountersScrolled = 9000;
    }

    // PUBLIC ACCESSOR METHODS

    public String getNumBannerSwiped(){
        return Integer.toString(numBannerSwiped);
    }

    public String getNumVideosWatched(){
        return Integer.toString(numVideosWatched);
    }

    public String getNumEncountersScrolled(){
        return Integer.toString(numEncountersScrolled);
    }


    // Incrementers

    public void incrementNumBannerSwiped(){
        numBannerSwiped++;
    }

    public void incrementNumVideosWatched(){
        numVideosWatched++;
    }

    public void incrementNumEncountersScrolled(){
        numEncountersScrolled++;
    }


    // Setters

    public void setNumBannerSwiped(int v){
        numBannerSwiped = v;
    }

    public void setNumVideosWatched(int v){
        numVideosWatched = v;
    }

    public void setNumEncountersScrolled(int v){
        numEncountersScrolled = v;
    }

    // Clear one or all

    public void resetNumBannerSwiped(){
        numBannerSwiped = 0;
    }

    public void resetNumVideosWatched(){
        numVideosWatched = 0;
    }

    public void resetNumEncountersScrolled(){
        numEncountersScrolled = 0;
    }

    public void resetAll(){
        numBannerSwiped = 0;
        numVideosWatched = 0;
        numEncountersScrolled = 0;
    }



}
