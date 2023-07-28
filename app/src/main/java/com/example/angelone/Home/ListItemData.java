package com.example.angelone.Home;
public class ListItemData {
    String itemName;
    String itemTitle;

    ListItemData(String itemName, String itemTitle) {
        this.itemName = itemName;
        this.itemTitle = itemTitle;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }
}