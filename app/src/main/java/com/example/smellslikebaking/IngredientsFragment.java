package com.example.smellslikebaking;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;

public class IngredientsFragment extends Fragment {
    private static final String KEY_CHECK_BOXES = "key chekBoxes";
    private CheckBox[] mCheckBoxes;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int index = getArguments().getInt(ViewPagerFragment.KEY_RECIPE_INDEX);

        View view = inflater.inflate(R.layout.fragment_ingredients,container,false);

        LinearLayout linearLayout = view.findViewById(R.id.ingredientsLayout);
        String[] ingredients = Recipes.ingredients[index].split("`");
        mCheckBoxes = new CheckBox[ingredients.length];

        boolean[] checkedBoxes = new boolean[mCheckBoxes.length];
        if (savedInstanceState != null && savedInstanceState.getBooleanArray(KEY_CHECK_BOXES) != null){
            checkedBoxes = savedInstanceState.getBooleanArray(KEY_CHECK_BOXES);
        }

        setUpCheckBoxes(ingredients,linearLayout,checkedBoxes);

        return view;
    }

    private void setUpCheckBoxes(String[] ingredients, ViewGroup container, boolean[] checkedBoxes) {
        int i = 0;
        for (String ingrediant : ingredients){
            mCheckBoxes[i] = new CheckBox(getActivity());
            mCheckBoxes[i].setText(ingrediant);
            mCheckBoxes[i].setTextSize(20f);
            mCheckBoxes[i].setPadding(8,16,8,16);
            container.addView(mCheckBoxes[i]);
            if (checkedBoxes[i])
                mCheckBoxes[i].toggle();
            i++;
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        boolean[] stateOfCheckBoxes = new boolean[mCheckBoxes.length];
        int i = 0;
        for (CheckBox checkBox : mCheckBoxes){
            stateOfCheckBoxes[i] = checkBox.isChecked();
            i++;
        }

        outState.putBooleanArray(KEY_CHECK_BOXES,stateOfCheckBoxes);

        super.onSaveInstanceState(outState);
    }
}
