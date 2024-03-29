package com.example.myapplication;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import android.widget.HorizontalScrollView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import java.util.ArrayList;

public class CustomChipGroup extends ChipGroup {

    public interface Listener{
        void onChipSelected(String text,int index);
        void onMultipleChipsSelected(ArrayList<String> text,ArrayList<Integer> indexes);
        void onChipDeselected(String text);

    }

    Listener listener;

    static int SINGLE_MODE = 0;
    static int MULTIPLE_MODE = 1;
   public int Mode ;


    public CustomChipGroup(Context context) {
        super(context);
    }

    public CustomChipGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomChipGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void initialize(ArrayList<String> chipNamesList, Drawable checkedIcon, Listener listener) {
        this.listener = listener;
        removeAllViews();

        for (String chipName : chipNamesList) {
            Chip chip = new Chip(getContext());
            chip.setText(chipName);
            chip.setClickable(true);
            chip.setCheckable(true);
            addView(chip);


            chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if (isChecked) {
                        Chip chip = (Chip) buttonView;
                        chip.setChipIcon(checkedIcon);


                        if (Mode == SINGLE_MODE) {
                            for (int i = 0; i < getChildCount(); i++) {
                                Chip otherChip = (Chip) getChildAt(i);
                                if (otherChip != chip && otherChip.isChecked()) {
                                    otherChip.setChecked(false);
                                    otherChip.setChipIcon(null);
                                }
                            }

                            listener.onChipSelected(chip.getText().toString(), indexOfChild(chip));

                        }
                        else if (Mode == MULTIPLE_MODE) {

                            ArrayList<String> selectedChipNames = new ArrayList<>();
                            ArrayList<Integer> selectedChipIndexes = new ArrayList<>();


                            for (int i = 0; i < getChildCount(); i++) {
                                Chip otherChip = (Chip) getChildAt(i);
                                if (otherChip.isChecked()) {
                                    selectedChipNames.add(otherChip.getText().toString());
                                    selectedChipIndexes.add(i);
                                }
                            }

                            listener.onMultipleChipsSelected(selectedChipNames, selectedChipIndexes);
                        }

                        ChipToFirst(chip);
                    }
                    else {
                        listener.onChipSelected("",-1);
                        listener.onChipDeselected(chip.getText().toString());
                        ((Chip) buttonView).setChipIcon(null);
                    }

                    HorizontalScrollView scrollView = (HorizontalScrollView) getParent();
                    scrollView.post(new Runnable() {
                        @Override
                        public void run() {
                            scrollView.smoothScrollTo(0, 0);
                        }
                    });
                }


            });
        }
    }





    private void ChipToFirst(Chip chip) {
        int chipIndex = indexOfChild(chip);
        if (chipIndex > 0) {
            removeView(chip);
            addView(chip, 0);
        }
    }
}