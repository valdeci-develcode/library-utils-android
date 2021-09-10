package com.mylibrary.brazildecimalnumber;

import android.os.Build;
import android.text.InputType;
import android.text.method.DigitsKeyListener;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.Locale;

public class DigitsKeyListenerDecimal extends DigitsKeyListener {
    @RequiresApi(api = Build.VERSION_CODES.O)
    public DigitsKeyListenerDecimal(@Nullable Locale locale, boolean sign, boolean decimal) {
        super(locale, sign, decimal);
    }

    @Override
    public int getInputType() {
        return  InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL;
    }
}
