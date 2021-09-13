package com.mylibrary.utilsandroid.ui;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

public class BaseFragment extends Fragment {

    public NavController navController() {
        return NavHostFragment.findNavController(this);
    }
}
