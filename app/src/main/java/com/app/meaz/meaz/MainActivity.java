package com.app.meaz.meaz;

import android.Manifest;
import android.app.Fragment;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.meaz.meaz.Adapters.ProductAdapter;
import com.app.meaz.meaz.Adapters.ProductListAdapter;
import com.app.meaz.meaz.Dialogs.ProductsDialog;
import com.app.meaz.meaz.Models.Product;
import com.app.meaz.meaz.Networking.Controllers.FirebaseController;
import com.app.meaz.meaz.Utils.DatabaseUtils;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.client.android.CaptureActivity;
import com.mongodb.Mongo;
import com.mongodb.client.MongoDatabase;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @BindView(R.id.search_box)
    EditText searchBox;

    @BindView(R.id.product_title)
    TextView title;

    @BindView(R.id.front_name)
    TextView frontName;

    @BindView(R.id.front_part_number)
    TextView frontPartNumber;

    @BindView(R.id.back_name)
    TextView backName;

    @BindView(R.id.back_part_number)
    TextView  backPartNumber;

    @BindView(R.id.thread_color_text)
    TextView threadColor;

    @BindView(R.id.size_1)
    TextView size_1;

    @BindView(R.id.img_holder)
    ImageView imageView;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkCameraPermission();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        DatabaseUtils.fireBaseSignIn();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 0) {
            if(resultCode == RESULT_OK) {
                String contents = data.getStringExtra("SCAN_RESULT");
                Log.d(TAG, "contents: " + contents);
                searchBox.setText(contents);
            }
            else if (resultCode == RESULT_CANCELED) {
                Log.d(TAG, "RESULT_CANCELED");
            }
        }
    }

    private void checkCameraPermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        try {
            Log.d(TAG, "The value of the permissions check is: " + permissionCheck);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.scanner_btn)
    void startBarcodeScan() {

        Log.d(TAG, "Scanner Button was clicked!");
        Intent intent = new Intent(getApplicationContext(), CaptureActivity.class);
        intent.setAction("com.google.zxing.client.android.SCAN");
        intent.putExtra("SAVE_HISTORY", false);
        startActivityForResult(intent, 0);
    }

    @OnClick(R.id.search_btn)
    void startTextSearch() {
        progressBar.setVisibility(View.VISIBLE);
        String s = searchBox.getText().toString();
        if(s != null) {
            Log.d(TAG, "The search button was clicked" + s);


            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ProductsDialog productsDialog = ProductsDialog.newInstance();
            Bundle bundle = new Bundle();
            bundle.putString("search", s);
            productsDialog.setArguments(bundle);
            productsDialog.show(ft, "dialog");

//            DatabaseUtils databaseUtils = new DatabaseUtils(this);
//            databaseUtils.fireTextBaseQuery("s");
//            FirebaseController firebaseController = new FirebaseController();
//            firebaseController.start();
//            DatabaseUtils databaseUtils = new DatabaseUtils(this);
//            databaseUtils.fireBaseInit();
//            databaseUtils.fireScanBaseQuery(s);
            progressBar.setVisibility(View.INVISIBLE);
        }
        else {
            progressBar.setVisibility(View.INVISIBLE);
            Log.d(TAG, "The value of the text box is empty");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    private void setActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void productSelection(Product product) {
        Log.d(TAG, "The product selected was: " + product);
        ProductAdapter pd = new ProductAdapter(this, R.layout.activity_main, product);
        pd.setData();
    }

}
