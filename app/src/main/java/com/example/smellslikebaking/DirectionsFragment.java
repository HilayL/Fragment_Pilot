package com.example.smellslikebaking;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DirectionsFragment extends Fragment {
    private CheckBox[] mCheckBoxes;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int index = getArguments().getInt(ViewPagerFragment.KEY_RECIPE_INDEX);

        View view = inflater.inflate(R.layout.fragment_directions, container, false);

        LinearLayout directionLayout = view.findViewById(R.id.directionLayout);
        String[] directions = Recipes.directions[index].split("`");
        mCheckBoxes = new CheckBox[directions.length];
        setUpCheckBoxes(directions,directionLayout);

        return view;
    }

    private void setUpCheckBoxes(String[] directions, ViewGroup container) {
        int i = 0;
        for (String direction : directions) {
            mCheckBoxes[i] = new CheckBox(getActivity());
            mCheckBoxes[i].setText(direction);
            mCheckBoxes[i].setTextSize(20f);
            mCheckBoxes[i].setPadding(8, 16, 8, 16);
            container.addView(mCheckBoxes[i]);
        }
    }
}
