package me.muhammadshah.politicaltracker;

import android.os.AsyncTask;

import com.buzzilla.webhose.client.WebhoseClient;
import com.buzzilla.webhose.client.WebhosePost;
import com.buzzilla.webhose.client.WebhoseResponse;

import java.io.IOException;

public class loadWebHose extends AsyncTask<String, Void, String[]> {
    public AsyncResponse delegate = null;

    protected String[] doInBackground(String... args) {
        try {
            if (args.length < 1) {
                System.exit(1);
            }

            WebhoseClient client = new WebhoseClient(args[0]);
            WebhoseResponse response = client.search("site:memeorandum.com is_first:true  language:english");
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

        delegate.processFinish(result);
    }

}