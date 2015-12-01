package me.muhammadshah.politicaltracker;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.buzzilla.webhose.client.WebhoseClient;
import com.buzzilla.webhose.client.WebhosePost;
import com.buzzilla.webhose.client.WebhoseResponse;
import com.parse.ParseUser;

import java.io.IOException;

public class MainFeedActivity extends AppCompatActivity {

    protected ProgressBar mProgressBar;
    ListView listView;

    private class loadWebHose extends AsyncTask<String, Void, String[]> {
        protected String[] doInBackground(String... args) {
            try {
                if (args.length < 1) {
                    Toast.makeText(MainFeedActivity.this,
                            "API Error, Please Try Again", Toast.LENGTH_LONG).show();
                    System.exit(1);
                }

                WebhoseClient client = new WebhoseClient(args[0]);
                WebhoseResponse response = client.search("site:telegraph.co.uk is_first:true");
                String[] titleList;
                titleList = new String[response.totalResults];
                int i = 0;

                for (WebhosePost post : response.posts) {
                    titleList[i] = (post.title);
                    i++;
                }
                return titleList;

            } catch (IOException e2) {
                String[] errorList;
                errorList = new String[1];
                errorList[0] = "error";
                return errorList;
            }
        }

        protected void onProgressUpdate(Integer... progress) {
        }

        protected void onPostExecute(String[] result) {

        }

    }


        @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handleIntent(getIntent());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar1);


        //Webhose Calls
        //Input API KEY
        String[] webhoseKey = new String[1];
        new loadWebHose().execute(webhoseKey);

        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.newsList);

        // Defined Array values to show in ListView
        String[] values = new String[] {
                "UK Prime Minister David Cameron calls for British air strikes against IS in Syria",
                "Gunman attacks Planned Parenthood clinic, killing three, before surrendering",
                "Navigator of downed Russian jet found alive in Syria",
                "At least 52, including six Shia Muslim activists, to be executed in Saudi Arabia",
                "New Polish government takes down findings on Russian air disaster",
                "Athens bomb targets business group, damages Cypriot embassy",
                "Canadian politician Manmeet Bhullar dies aged 35 after traffic collision",
                "Pakistani female fighter pilot Marium Mukhtiar dies in jet crash",
                "Ferry MV Suilven sinks in Suva, Fiji",
                "Suspects detained in Belgium raids",
                "Barça thrashes Real Madrid 4-0 in first El Clásico 2015/2016"
        };

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1,values);


        // Assign adapter to ListView
        listView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logoutButton:
			/*
			 * Log current user out using ParseUser.logOut()
			 */
                ParseUser.logOut();
                Intent logoutIntent = new Intent(this, LoginActivity.class);
                logoutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(logoutIntent);
                return true;
            case R.id.profileButton:
            Intent profileIntent = new Intent(this, ProfileActivity.class);
                profileIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(profileIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search
        }
    }
}