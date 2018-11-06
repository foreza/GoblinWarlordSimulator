package com.vartyr.goblinwarlordsimulator;

import android.util.Log;

public class GameStateManager {

    public String LOG_TAG = "[GameStateManager]";

    public int numBannerSwiped = 0;                 // Implemented in Alpha
    public int numVideosWatched = 0;                // Implemented in Alpha
    public int numEncountersScrolled = 0;           // TODO: To be Implemented for Beta


    public String currencyName = "Treasure";            // Can update later
    public int numCurrency = 0;                         // Implemented in Alpha. Total amount of currency the player has.
    public int numIncrementCurrencyAmt = 1;             // Implemented in Alpha. The amount to increment the total currency by.

    public String objectiveName = "Isolated Village";        // Can update later
    public int numDamageDealt = 0;                      // Implemented in Alpha. Total amount of damage dealt.
    public int numAmountToDamage = 1;                  // Implemented in Alpha. The amount to increment the total damage done by.



    // static variable single_instance of type Singleton
    private static GameStateManager instance = null;

    // private constructor restricted to this class itself
    private GameStateManager() {
            populateWithTestValues();       // TODO: Disable this
            // populateWithDefaultValues(); // TODO: Use this
    }

    // static method to create instance of Singleton class
    public static GameStateManager getInstance()
    {
        if (instance == null){
            instance = new GameStateManager();
        }

        return instance;
    }


    // TODO for phase 2: implement so we can persist values to disk and then phase 3: eventually to server
    private boolean retrieveValuesFromStorage(){
        return false;
    }



    private void populateWithDefaultValues(){
        numBannerSwiped = 0;
        numVideosWatched = 0;
        numCurrency= 0;
        numIncrementCurrencyAmt = 1;
        numDamageDealt = 0;
        // numEncountersScroll
    }

    // TODO: Disable this method call before pushing to live.
    private void populateWithTestValues(){
        numBannerSwiped = 100;
        numVideosWatched = 20;
        numCurrency= 100;
        numIncrementCurrencyAmt = 1;
        numDamageDealt = 0;
        // numEncountersScrolled = 9000;
    }

    // PUBLIC ACCESSOR METHODS

    public String getCurrencyName() { return currencyName; }
    public String getObjectiveName() { return objectiveName; }

    public String getDisplay_NumBannerSwiped(){
        return Integer.toString(numBannerSwiped);
    }
    public String getDisplay_NumVideosWatched(){
        return Integer.toString(numVideosWatched);
    }
    public String getDisplay_NumEncountersScrolled(){ return Integer.toString(numEncountersScrolled); }
    public String getDisplay_NumCurrency(){ return Integer.toString(numCurrency); }
    public String getDisplay_NumDamageDealt(){
        return Integer.toString(numDamageDealt);
    }
    public String getDisplay_NumAmountDamage(){
        return Integer.toString(numAmountToDamage);
    }
    public String getDisplay_NumIncrementCurrencyAmt() { return Integer.toString(numIncrementCurrencyAmt); }


    public int getValue_NumBannerSwiped(){ return numBannerSwiped; }
    public int getValue_NumVideosWatched(){ return numVideosWatched; }
    public int getValue_NumEncountersScrolled() { return numEncountersScrolled; }
    public int getValue_NumCurrency(){ return numCurrency; }
    public int getValue_NumDamageDealt(){ return numAmountToDamage; }
    public int getValue_NumAmountDamage(){ return numDamageDealt; }
    public int getValue_NumIncrementCurrencyAmt() { return numIncrementCurrencyAmt; }



    // INCREMENTORS

    // Method to increment the number of banners that were swiped on. Purely more for metrics.
    public void incrementNumBannerSwiped(){
        numBannerSwiped++;
        Log.d(LOG_TAG, "incrementNumBannerSwiped : " + numBannerSwiped);
    }

    // Method to increment the number of videos that were watched. Purely more for metrics.
    public void incrementNumVideosWatched(){
        numVideosWatched++;
        Log.d(LOG_TAG, "incrementNumVideosWatched : " + numVideosWatched);
    }

    // Method to increment the number of encounteres. Not implemented presently. Purely more for metrics.
    public void incrementNumEncountersScrolled(){
        numEncountersScrolled++;
        Log.d(LOG_TAG, "incrementNumEncountersScrolled : " + numEncountersScrolled);
    }

    // Method to increment the currency by @numIncrementCurrencyAmt. Call this method to increment the currency on some behavior.
    public void incrementTotalCurrency(){
        numCurrency+= numIncrementCurrencyAmt;
        Log.d(LOG_TAG, "incrementTotalCurrency increment by : " + numIncrementCurrencyAmt +  " updated total : " + numCurrency);
    }

    // Method to increment the damage by @numAmountToDamage. Call this method to increment the damage done to "whatever"
    public void incrementTotalDamage(){
        numDamageDealt+= numAmountToDamage;
        Log.d(LOG_TAG, "incrementTotalDamage increment by : " + numAmountToDamage + " updated total : " + numDamageDealt);
    }





    // DECREMENTORS

    // Method to decrement the currency by some given integer value v. Call this method to decrement currency on some behavor.
    public void decrementTotalCurrencyByAmount(int v){
        numCurrency -= v;
        Log.d(LOG_TAG, "decrementTotalCurrencyByAmount decrement by : " + Integer.toString(v) + " updated total : " + numCurrency);
    }





    // SETTERS

    // Method to directly set the number of banner swipe
    public void setNumBannerSwiped(int v){
        numBannerSwiped = v;
    }

    // Method to directly set the number of videos watched
    public void setNumVideosWatched(int v){
        numVideosWatched = v;
    }

    // Method to directly set the num of encounters in scroll
    public void setNumEncountersScrolled(int v){
        numEncountersScrolled = v;
    }

    // Method to update the currency name on the fly
    public void setCurrencyName(String s){
        currencyName = s;
    }

    // Method to update the TOTAL currency amount
    public void setNumCurrency(int v){
        numCurrency = v;
    }

    // Method to update the TOTAL amount of damage done
    public void setNumDamage(int v){
        numDamageDealt = v;
    }

    // Method to update the amount of currency to increment by
    public void setNumIncrementCurrencyAmt(int v){
        numIncrementCurrencyAmt = v;
    }

    // Method to update the amount of damage that is dealt
    public void setNumAmountToDamage(int v){
        numAmountToDamage = v;
    }






    // TODO: Implement these

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
