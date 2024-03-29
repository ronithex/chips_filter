package com.example.myapplication;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.graphics.drawable.Drawable;
import android.widget.CompoundButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {


    ChipGroup chipGroup;


    private static final int NUM_CHIPS = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        chipGroup = findViewById(R.id.chip_group);

        ArrayList<String> chipNamesList = new ArrayList<>();
        chipNamesList.add("Present");
        chipNamesList.add("Absent");
        chipNamesList.add("Late");
        chipNamesList.add("Half Day");
        chipNamesList.add("Leave");
        chipNamesList.add("Working");
        chipNamesList.add("Out");

        Drawable checkedIcon = ContextCompat.getDrawable(this, R.drawable.check);




        for (int i = 0; i < NUM_CHIPS; i++) {
            Chip chip = new Chip(this);
            chip.setText(chipNamesList.get(i));
            chip.setClickable(true);
            chip.setCheckable(true);
            chipGroup.addView(chip);


            chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        chipGroup.removeView(chip);
                        chipGroup.addView(chip, 0);
                        chip.setChipIcon(checkedIcon);
                    }
                    else {
                        chipGroup.removeView(chip);
                        chipGroup.addView(chip);
                        chip.setChipIcon(null);
                    }
                }
            });



        }



    }
}
