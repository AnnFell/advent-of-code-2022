package org.example.puzzledays.day11;

import java.util.ArrayList;
import java.util.List;

public class Monkey {
    List<Integer> items = new ArrayList<>();
    String operationType;
    int operationValue;
    int testValue;
    int throwTrue;
    int throwFalse;
    int inspections;

    public Monkey(String[] items, String operationType, int operationValue, int testValue, int throwTrue, int throwFalse) {
        for (String item : items) {
            this.items.add(Integer.parseInt(item));
        }
        this.operationType = operationType;
        this.operationValue = operationValue;
        this.testValue = testValue;
        this.throwTrue = throwTrue;
        this.throwFalse = throwFalse;
    }

    public int[] inspection(int itemIndex) {
        int worryLevel = this.items.get(itemIndex);

        int workingValue = operationValue < 0 ? worryLevel : operationValue;
        int newWorryLevel;
        if (operationType.equals("+")) {
            newWorryLevel = worryLevel + workingValue;
        } else {
            newWorryLevel = worryLevel * workingValue;
        }
        newWorryLevel = newWorryLevel / 3;

        int[] throwTargetAndItem = new int[2];
        if (newWorryLevel % testValue == 0) {
            throwTargetAndItem[0] = throwTrue;
        } else {
            throwTargetAndItem[0] = throwFalse;
        }
        throwTargetAndItem[1] = newWorryLevel;
        inspections++;
        return throwTargetAndItem;
    }

    public void clearItemsAfterRound() {
        this.items.clear();
    }

    public void catchItem(Integer item) {
        items.add(item);
    }

    public List<Integer> getItems() {
        return this.items;
    }

    public int getInspections() {
        return this.inspections;
    }

}
