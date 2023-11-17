package com.gildedrose;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    @DisplayName("Check item's name is stored correctly")
    public void checkItemName() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }

    @Test
    @DisplayName("Check item's quality is stored correctly")
    public void checkItemQuality() {
        Item[] items = new Item[] { new Item("foo", 0, 5) };
        GildedRose app = new GildedRose(items);
        assertEquals(5, app.items[0].quality);
    }

    @Test
    @DisplayName("Check item quality doesn't go below 0")
    public void checkItemQualityIsNotNegative() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(true, app.items[0].quality >= 0);
    }

    @Test
    @DisplayName("Check item's quality doesn't go above 50")
    public void checkItemQualityDoesNotExceed50() {
        Item[] items = new Item[] { new Item("Aged Brie", 5, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(true, app.items[0].quality <= 50);
    }

    @Test
    @DisplayName("Check item's value goes down normally given a positive SellIn date")
    public void checkItemDegradesNormallyGivenPositiveSellInDate() {
        Item[] items = new Item[] { new Item("foo", 20, 40) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(40-1, app.items[0].quality );
    }

    @Test
    @DisplayName("Check the item sellIn decrements correctly")
    public void checkItemSellInDegrades() {
        Item[] items = new Item[] { new Item("foo", 20, 40) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(20-1, app.items[0].sellIn );
    }

    @Test
    @DisplayName("Check expired item's value degrades at double rate ")
    public void checkExpiredItemDegradesAtTwiceRate() {
        Item[] items = new Item[] { new Item("foo", 0, 40) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(40-2, app.items[0].quality );
    }

    @Test
    @DisplayName("Check that the Sulfuras doesn't degrade")
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
    @DisplayName("Check backstage passes quality increases by 1 for sellIn of 15")
    public void checkBackstageIncreasesBy1ForSellIn15() {
        Item[] items = new Item[]{new Item("Backstage passes to SYSOFDOWN", 15, 20)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(21, app.items[0].quality);
    }

    @Test
    @DisplayName("Check backstage passes quality increases by 2 for sellIn of 10")
    public void checkBackstageIncreasesBy2ForSellIn10() {
        Item[] items = new Item[]{new Item("Backstage passes to SYSOFDOWN", 10, 20)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(22, app.items[0].quality);
    }

    @Test
    @DisplayName("Check backstage passes quality increases by 3 for sellIn of 5")
    public void checkBackstageIncreasesBy3ForSellIn5() {
        Item[] items = new Item[]{new Item("Backstage passes to SYSOFDOWN", 5, 20)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(23, app.items[0].quality);
    }

    @Test
    @DisplayName("Check backstage passes quality is 0 for sellIn of 1")
    public void checkBackstageIs0ForSellIn1() {
        Item[] items = new Item[]{new Item("Backstage passes to SYSOFDOWN", 0, 20)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Aged Brie","Backstage passes"})
    @DisplayName("Check aging items do not exceed 50")
    public void checkAgingItemsDoNotExceed50(String itemName) {
        Item[] items = new Item[]{new Item(itemName, 25, 50)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
    }


}
