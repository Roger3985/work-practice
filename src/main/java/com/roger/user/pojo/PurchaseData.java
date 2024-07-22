package com.roger.user.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class PurchaseData {

    private final List<String> availableItems = new ArrayList<String>();

    private List<Purchase> allPurchases = new ArrayList<Purchase>();
    private int TOTAL_PURCHASES = 25;
    private Random randomGenerator = new Random();

    public PurchaseData() {
        availableItems.add("Java");
        availableItems.add("Latte");
        availableItems.add("Lungo");
        availableItems.add("Macchiato");
        availableItems.add("Mocha");
        generatePurchases();
    }


    public List<Purchase> getAllPurchases() {
        return this.allPurchases;
    }

    public List<String> getAvailableItems() {
        return availableItems;
    }

    private void generatePurchases() {
        for(int i=0; i<TOTAL_PURCHASES; i++) {
            int id = (i + 101);
            String purchasedItem = availableItems.get(randomGenerator.nextInt(availableItems.size()));
            Date randomDate = new Date(Math.abs(System.currentTimeMillis() - randomGenerator.nextInt(1000000)));
            boolean paid = randomGenerator.nextBoolean();
            allPurchases.add(new Purchase(id, purchasedItem, randomDate, paid));
        }
    }
}