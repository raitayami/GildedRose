package com.gildedrose;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    @DisplayName("Check item name is stored correctly")
    public void checkItemName() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }

    @Test
    @DisplayName("Check item quality is stored correctly")
    public void checkItemQuality() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

    @Test
    @DisplayName("Check item quality does not go under 0")
    public void checkItemQualityIsNotNegative() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(true, app.items[0].quality >= 0);
    }

    // Need to think of an item that increases in quality...
    @Test
    @DisplayName("Check item quality does not exceed 50")
    public void checkItemQualityDoesNotExceed50() {
        Item[] items = new Item[] { new Item("Aged Brie", 5, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(true, app.items[0].quality <= 50);
    }

    @Test
    @DisplayName("Check item value degrades normally given a positive SellIn date")
    public void checkItemDegradesNormallyGivenPositiveSellInDate() {
        Item[] items = new Item[] { new Item("foo", 20, 40) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(40-1, app.items[0].quality );
    }

    @Test
    @DisplayName("Check item sellin decrements correctly")
    public void checkItemSellInDegrades() {
        Item[] items = new Item[] { new Item("foo", 20, 40) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(20-1, app.items[0].sellIn );
    }

    @Test
    @DisplayName("Check expired item value degrades at x2 rate ")
    public void checkExpiredItemDegradesAtTwiceRate() {
        Item[] items = new Item[] { new Item("foo", 0, 40) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(40-2, app.items[0].quality );
    }

    @Test
    @DisplayName("Check Sulfuras doesn't degrade")
    public void checkSulfurasDoesntDegrade() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 5, 80) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(80, app.items[0].quality );
    }

    @Test
    @DisplayName("Check aged brie increases in quality")
    public void checkAgedBrieQualityIncrease() {
        Item[] items = new Item[] { new Item("Aged Brie", 50, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(21, app.items[0].quality);
    }

    @Test
    @DisplayName("Check conjured items degrade at x2 rate")
    public void checkConjuredItemsDegradeAtx2Rate() {
        Item[] items = new Item[] { new Item("Conjured Mana Cake", 3, 30) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(28, app.items[0].quality);


    }

}
