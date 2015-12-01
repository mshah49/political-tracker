package me.muhammadshah.politicaltracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.parse.ParseUser;

public class ProfileActivity extends AppCompatActivity {

    ListView likesListView, dislikesListView;

    // Defined Array values to show in ListViews
    String[] likesValues = new String[]{
            "World News",
            "Elections",
            "Taxes"
    };

    String[] dislikesValues = new String[]{
            "Gun Control",
            "Health Care"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Get ListView objects from xml
        likesListView = (ListView) findViewById(R.id.likesList);
        dislikesListView = (ListView) findViewById(R.id.dislikesList);


        // Assign adapters to ListViews
        likesListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, likesValues));
        dislikesListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dislikesValues));

        ListUtils.setDynamicHeight(likesListView);
        ListUtils.setDynamicHeight(dislikesListView);
    }

    public static class ListUtils {
        public static void setDynamicHeight(ListView mListView) {
            ListAdapter mListAdapter = mListView.getAdapter();
            if (mListAdapter == null) {
                // when adapter is null
                return;
            }
            int height = 0;
            int desiredWidth = View.MeasureSpec.makeMeasureSpec(mListView.getWidth(), View.MeasureSpec.UNSPECIFIED);
            for (int i = 0; i < mListAdapter.getCount(); i++) {
                View listItem = mListAdapter.getView(i, null, mListView);
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                height += listItem.getMeasuredHeight();
            }
            ViewGroup.LayoutParams params = mListView.getLayoutParams();
            params.height = height + (mListView.getDividerHeight() * (mListAdapter.getCount() - 1));
            mListView.setLayoutParams(params);
            mListView.requestLayout();
        }

    }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_profile, menu);
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
    }
