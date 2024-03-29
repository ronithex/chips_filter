package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Scroller;
import android.widget.Toast;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    CustomChipGroup chipGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chipGroup = findViewById(R.id.custom_chip_group);

        chipGroup.Mode = CustomChipGroup.SINGLE_MODE;

        ArrayList<String> chipNamesList = new ArrayList<>();
        chipNamesList.add("Present");
        chipNamesList.add("Absent");
        chipNamesList.add("Late");
        chipNamesList.add("Half Day");
        chipNamesList.add("Leave");
        chipNamesList.add("Working");
        chipNamesList.add("Out");

        Drawable checkedIcon = ContextCompat.getDrawable(this, R.drawable.check);

        chipGroup.initialize(chipNamesList, checkedIcon, new CustomChipGroup.Listener() {
            @Override
            public void onChipSelected(String text, int index) {
                if (index != -1) {
                    Toast.makeText(getApplicationContext(), text + "Selected", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMultipleChipsSelected(ArrayList<String> text, ArrayList<Integer> indexes) {
                Toast.makeText(getApplicationContext(),  text + "Selected", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onChipDeselected(String text) {
                Toast.makeText(getApplicationContext(), text + " Deselected", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
