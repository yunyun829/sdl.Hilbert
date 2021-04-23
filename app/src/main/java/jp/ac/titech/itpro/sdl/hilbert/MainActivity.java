package jp.ac.titech.itpro.sdl.hilbert;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final static int MAX_ORDER = 9;
    private final static String KEY_NAME = "MainActivity.order";
    private int order = 1;

    private TextView orderView;
    private HilbertView hilbertView;
    private Button decButton;
    private Button incButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
            order = savedInstanceState.getInt(KEY_NAME);
        }
        setContentView(R.layout.activity_main);

        orderView = findViewById(R.id.order_view);
        hilbertView = findViewById(R.id.hilbert_view);
        decButton = findViewById(R.id.dec_button);
        incButton = findViewById(R.id.inc_button);

        decButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assertTrue(order > 1, "A room to decrement order should exist");
                order--;
                display();
            }
        });
        incButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assertTrue(order < MAX_ORDER, "A room to increment order should exist");
                order++;
                display();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        display();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_NAME,order);
    }


    private void display() {
        orderView.setText(getString(R.string.order_view_format, order));
        hilbertView.setOrder(order);
        decButton.setEnabled(order > 1);
        incButton.setEnabled(order < MAX_ORDER);
    }

    public static void assertTrue(boolean f, String message) {
        if (BuildConfig.DEBUG && !f) {
            throw new AssertionError(message);
        }
    }
}