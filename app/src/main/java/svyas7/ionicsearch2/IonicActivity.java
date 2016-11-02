package svyas7.ionicsearch2;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class IonicActivity extends AppCompatActivity {
    private SearchView searchBar;
    private TextView resultView;//= (TextView) findViewById(R.id.textView4);
    private IonicDictionary dictionary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //SeachManager seachManager = (SeachManager)getSystemService(Context.SEARCH_SERVICE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ionic);
        searchBar = (SearchView) findViewById(R.id.searchView);
        resultView = (TextView) findViewById(R.id.textView4);
        //Toolbar toolbar = (Toolbar) findViewById(R.id;
        //setSupportActionBar(toolbar);
        AssetManager assetManager = getAssets();
        try {
            InputStream inputStream = assetManager.open("ionicCompounds.txt");
            dictionary = new IonicDictionary(inputStream);
        } catch (IOException e) {
            Toast toast = Toast.makeText(this, "Could not load dictionary", Toast.LENGTH_LONG);
            toast.show();
        }
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //searchList(query);
            //searchList("zn");
        }
//        searchBar.setIconified(false);
//        //setOnSearchClickListener(searchBar);
//        searchBar.setOnSearchClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("yohan", "onclick happened");
//                String query = searchBar.getQuery().toString();
//                searchList(query);
//            }
//   `     });
        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                resultView.setText("");
                searchList(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


    }
    /*
    private void clearScreen(boolean clear)
    {
        if(clear==true)
            resultView.setText("");
    }
    */
    public void searchList (String query) {
        ArrayList<String> list = dictionary.getList(query);
        list.clear();
        list = dictionary.getList(query);

        TextView clear = (TextView) findViewById(R.id.textView4);
        //clearScreen(true);
        //clear.getEditableText().delete(0,clear.getLineCount()+1);
        for(int i=0;i<list.size();i++)
        {
            resultView.append(list.get(i)+"\n");
        }
    }
    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            searchList(query);
            //searchList("zn");
        }
    }
}
