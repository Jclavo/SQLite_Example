package com.example.jclavo.sqlite_example;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtProductName;
    EditText txtProductQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void addProduct (View view) {

        int quantity;
        Product product;

        txtProductName = (EditText) findViewById(R.id.txtProductName);
        txtProductQuantity = (EditText) findViewById(R.id.txtProductQuantity);

        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);

        quantity = Integer.parseInt(txtProductQuantity.getText().toString());

        product = new Product(txtProductName.getText().toString(), quantity);

        dbHandler.addProduct(product);

        mensaje("Adition Product");
        txtProductName.setText("");
        txtProductQuantity.setText("");
    }

    public void lookupProduct (View view) {

        txtProductName = (EditText) findViewById(R.id.txtProductName);
        txtProductQuantity = (EditText) findViewById(R.id.txtProductQuantity);

        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);

        Product product = dbHandler.findProduct(txtProductName.getText().toString());

        if (product != null) {
            //idView.setText(String.valueOf(product.getId()));
            Log.i("Id product",String.valueOf(product.getId()));
            txtProductQuantity.setText(String.valueOf(product.getQuantity()));
        } else {

            mensaje("No Match Found");
        }
    }

    public void deleteProduct (View view) {

        boolean result;

        txtProductName = (EditText) findViewById(R.id.txtProductName);
        txtProductQuantity = (EditText) findViewById(R.id.txtProductQuantity);

        MyDBHandler dbHandler = new MyDBHandler(this, null,null, 1);

        result = dbHandler.deleteProduct(txtProductName.getText().toString());

        if (result)
        {
            mensaje("Record Deleted");
            txtProductName.setText("");
            txtProductQuantity.setText("");
        }
        else
            mensaje("No Match Found");
    }

    public void mensaje(String mensaje){
        Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
    }
}
