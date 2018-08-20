package de.vp.dennis.vp2018;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final Button back = findViewById(R.id.settings_back_but);
        back.setVisibility(View.VISIBLE);
        back.setBackgroundColor(Color.TRANSPARENT);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startIntent();
            }
        });

    }

    private void startIntent(){
        Intent intent = new Intent(this, main_vp.class);
        startActivity(intent);
    }
}
