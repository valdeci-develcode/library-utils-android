package com.mylibrary.utilsandroid.ui;

import android.os.Bundle;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mylibrary.brazildecimalnumber.TextWatcherBrazilDecimal;
import com.mylibrary.utilsandroid.databinding.FragmentBrazilDecimalBinding;

import java.lang.ref.WeakReference;

public class BrazilDecimalFragment extends Fragment {
    private FragmentBrazilDecimalBinding binder;
    private int HOUSE = 8;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binder = FragmentBrazilDecimalBinding.inflate(inflater, container, false);
        return binder.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final TextWatcher textWatcher = new TextWatcherBrazilDecimal(new WeakReference<>(binder.myEditText), HOUSE);
        binder.myEditText.addTextChangedListener(textWatcher);
    }
}
