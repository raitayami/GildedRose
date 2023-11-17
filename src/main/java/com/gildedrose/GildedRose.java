package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    //method
    public void updateQuality() {
        for (Item item : items)
            item.updateItem();
    }

}
