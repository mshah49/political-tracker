package me.muhammadshah.politicaltracker;

import android.app.Application;

import com.buzzilla.webhose.client.WebhoseClient;
import com.parse.Parse;
import com.parse.ParseACL;

public class PoliticalTrackerApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //Parse
        // Enable Local Data store.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "AjYdYYDl8USstWJJ55Tk30cuDHcXUawhawaeWnic", "sN85OeRLBjfxi6Kie1Q6IwMiRj7vQPiA4IEiubup");


        ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this
        // line.
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);

        //Webhose
        //API KEY
        WebhoseClient client = new WebhoseClient("a52796b6-09dc-4413-af7b-9f08d894bfc2");
    }
}
