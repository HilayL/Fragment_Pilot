package com.example.smellslikebaking;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

public class ViewPagerFragment extends Fragment {

    public static final String KEY_RECIPE_INDEX = "recipe_index";
    final IngredientsFragment ingredientsFragment = new IngredientsFragment();
    final DirectionsFragment directionsFragment = new DirectionsFragment();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        int index = getArguments().getInt(KEY_RECIPE_INDEX);
        getActivity().setTitle(Recipes.names[index]);

        View view = inflater.inflate(R.layout.fragment_viewpager,container,false);
        ViewPager2 viewPager = view.findViewById(R.id.viewPager);
        // za views and za viewsPager

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getActivity());
        viewPager.setAdapter(viewPagerAdapter);
        //implimanting ma own pager adapter

        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Ingredients"));
        tabLayout.addTab(tabLayout.newTab().setText("Directions"));
        //adding the tabLayout widget

        Bundle ingrediantsBundle = new Bundle();
        ingrediantsBundle.putInt(KEY_RECIPE_INDEX,index);
        ingredientsFragment.setArguments(ingrediantsBundle);
        //ingrediants bundle

        Bundle directionsBundle = new Bundle();
        directionsBundle.putInt(KEY_RECIPE_INDEX,index);
        directionsFragment.setArguments(directionsBundle);
        //direction bundle


        // connecting the tabLayout to the viewPager
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        // connecting the viewPager to the tabLayout
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().setTitle(R.string.app_name);
    }

    //ma own adapter cues ViewPagerAdapter is decade
    public class ViewPagerAdapter extends FragmentStateAdapter {

        public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return position == 0 ? ingredientsFragment : directionsFragment;
        }

        @Override
        public int getItemCount() {
            return 2;
        }
    }
}
