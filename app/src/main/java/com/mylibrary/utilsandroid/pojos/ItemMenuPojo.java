package com.mylibrary.utilsandroid.pojos;

import androidx.annotation.Nullable;

public class ItemMenuPojo {
    private String id;
    private String nameItem;

    @Override
    public boolean equals(@Nullable Object obj) {
        if(!(obj instanceof ItemMenuPojo)) return false;
        return ((ItemMenuPojo) obj).getId().equals(this.id);
    }

    public ItemMenuPojo(String id, String nameItem){
        this.id = id;
        this.nameItem = nameItem;
    }

    public String getNameItem() {
        return nameItem;
    }

    public ItemMenuPojo setNameItem(String nameItem) {
        this.nameItem = nameItem;
        return this;
    }

    public String getId() {
        return id;
    }

    public ItemMenuPojo setId(String id) {
        this.id = id;
        return this;
    }
}
