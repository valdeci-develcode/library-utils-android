package com.mylibrary.brazildecimalnumber;

import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.widget.EditText;

import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

public class TextWatcherBrazilDecimal implements TextWatcher {
    WeakReference<EditText> editTextWeakReference;
    private final Locale locale = new Locale("pt", "BR");
    private final NumberFormat format = NumberFormat.getNumberInstance(locale);
    private final int MAX_LENGTH = 13;
    private int maxHouse;
    private final int MAX_DECIMAL = 8;
    private final int MIN_DECIMAL = 0;


    public TextWatcherBrazilDecimal(WeakReference<EditText> editTextWeakReference, int house){
        this.maxHouse = Math.max(MIN_DECIMAL, house <= MAX_DECIMAL ? house : MIN_DECIMAL);
        this.editTextWeakReference = editTextWeakReference;
        setupMyKeyListener();
    }


    private void setupMyKeyListener(){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            DigitsKeyListenerDecimal digitsKeyListenerDecimal = new DigitsKeyListenerDecimal(locale, false, true);
            this.editTextWeakReference.get().setKeyListener(digitsKeyListenerDecimal);
        }else{
            this.editTextWeakReference.get().setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        }
        format.setMaximumFractionDigits(maxHouse);
        format.setRoundingMode(RoundingMode.DOWN);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if(editTextWeakReference.get()==null) return;
        String text = s.toString();
        if(text.isEmpty()) return;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            if (text.equals(",")) setEditText(text, true);
            else {
                String result = getValueNumber(text);
                setEditText(result, false);
            }
        }else if (text.equals(".")) setEditText(text, true);
    }

    private void setEditText(String text, boolean isEmpty){
        StringBuilder stringBuilder = new StringBuilder(isEmpty ? "0" : "");
        stringBuilder.append(text);

        editTextWeakReference.get().removeTextChangedListener(this);
        editTextWeakReference.get().setText(stringBuilder.toString());
        editTextWeakReference.get().addTextChangedListener(this);
        editTextWeakReference.get().setSelection(stringBuilder.toString().length());
    }

    private String convertForNumberDefault(String number){
        return number.replaceAll("\\.", "").replace(",", ".");
    }

    private String getValueNumber(String textNumber){
        String textResult = textNumber;
        boolean isFormated = false;
        while (true){
            String numberFormatted = isFormated ? textResult.substring(0, textResult.length()-1) : textNumber;
            BigDecimal number = new BigDecimal(convertForNumberDefault(numberFormatted));
            textResult = format.format(number);
            if(!isFormated && textNumber.contains(",") && !textResult.contains(",") && textNumber.length() == textResult.length() + 1)
                return textNumber;
            if(textResult.length() <= MAX_LENGTH){
                if(!isFormated && textNumber.contains(",") && textNumber.charAt(textNumber.length() - 1) == '0' && textResult.length() < MAX_LENGTH) {
                    BigDecimal houseDecimal = number.remainder(new BigDecimal(1));
                    String resultHouses = addZeros(houseDecimal.toString());
                    int legth = resultHouses.length();

                    if(textResult.contains(",")) return textResult.length() <= MAX_LENGTH - legth -1 && isAddZeros(number.toString())? textResult + resultHouses : textResult;
                    else return textResult.length() < MAX_LENGTH - legth - 1 ? textResult + addZerosComma(number.toString(), resultHouses) : number.toString().contains(".") ? textResult + addZerosComma(number.toString(), resultHouses) : textResult;
                }
                return textResult;
            }
            isFormated = true;
        }
    }

    private String addZeros(String decimal){
        String result = "";
        if(decimal.contains("-")){
            try {
                int number = Integer.parseInt(decimal.substring(decimal.indexOf("-")+1));
                for(int i = 0; i < number; i++) result = result + '0';
                return result;
            }catch (Exception e){
                return result;
            }
        }else if(decimal.contains(".")){
            String reverse = new StringBuilder(decimal).reverse().toString();
            for (int i=0; reverse.charAt(i) == '0'; i++) result = result + '0';
            return result;
        }
        return result;
    }

    private boolean isAddZeros(String number){
        if( number== null || number.isEmpty() || !number.contains(".")) return false;
        String house = number.substring(number.indexOf(".")+1);
        return house.length() < maxHouse;
    }

    private String addZerosComma(String number,  String resultHouses){
        if (isAddZeros(number)) return ","+resultHouses;
        String house = number.substring(number.indexOf(".")+1);
        String result = ",";
        for(int i = 0; i< maxHouse;i++){
            result = result + house.charAt(i);
        }
        return result;
    }
}
