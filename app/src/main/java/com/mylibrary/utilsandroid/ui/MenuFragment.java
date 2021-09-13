package com.mylibrary.utilsandroid.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.mylibrary.utilsandroid.R;
import com.mylibrary.utilsandroid.adapters.ListMenuAdapter;
import com.mylibrary.utilsandroid.databinding.FragmentMenuBinding;
import com.mylibrary.utilsandroid.pojos.ItemMenuPojo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MenuFragment extends BaseFragment {
    private FragmentMenuBinding binder;
    private ListMenuAdapter adapter;
    private List<ItemMenuPojo> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binder = FragmentMenuBinding.inflate(inflater, container, false);
        return binder.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setupConfigure();
    }

    private void setupConfigure(){
        list = new ArrayList<>();
        list.add(new ItemMenuPojo("BrazilDecimalFragment", "Brazil Decimal"));

        adapter = new ListMenuAdapter(list, item -> {
            navController().navigate(R.id.action_menuFragment_to_brazilDecimalFragment);
            return item;
        });

        binder.listMenu.setAdapter(adapter);
    }
}
