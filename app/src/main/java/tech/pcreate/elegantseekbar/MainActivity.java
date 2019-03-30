package tech.pcreate.elegantseekbar;

import androidx.appcompat.app.AppCompatActivity;
import tech.pcreate.elegantseekbarlibrary.ElegantSeekbar;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ElegantSeekbar elegantSeekbar = findViewById(R.id.elegantSeekBar);
        //elegantSeekbar.setSize(400);  //specify in Pixels
        elegantSeekbar.setOnProgressChangedListener(new ElegantSeekbar.OnProgressChangedListener() {
            @Override
            public void startValue(String start) {
                //do something
            }

            @Override
            public void endValue(String end) {
                //do something
            }
        });

    }
}
