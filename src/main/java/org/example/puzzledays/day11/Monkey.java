package org.example.puzzledays.day11;

import java.util.ArrayList;
import java.util.List;

public class Monkey {
    List<Long> items = new ArrayList<>();
    String operationType;
    int operationValue;
    int testValue;
    int throwTrue;
    int throwFalse;
    int inspections;
    long roundReset;

    public Monkey(String[] items, String operationType, int operationValue, int testValue, int throwTrue, int throwFalse, long roundReset) {
        for (String item : items) {
            this.items.add(Long.parseLong(item));
        }
        this.operationType = operationType;
        this.operationValue = operationValue;
        this.testValue = testValue;
        this.throwTrue = throwTrue;
        this.throwFalse = throwFalse;
        this.roundReset = roundReset;
    }

    public int[] inspection(int itemIndex) {
        long worryLevel = this.items.get(itemIndex);

        long workingValue = operationValue < 0 ? worryLevel : operationValue;
        if (operationType.equals("+")) {
            worryLevel += workingValue;
        } else {
            worryLevel *= workingValue;
        }

        if (roundReset == 0) { // for part 1
            worryLevel = Math.floorDiv(worryLevel, 3);
        } else { // for part 2
            worryLevel %= roundReset;
        }

        // build array to return with [0] target and [1] item value
        int[] throwTargetAndItem = new int[2];
        if (worryLevel % testValue == 0) {
            throwTargetAndItem[0] = throwTrue;
        } else {
            throwTargetAndItem[0] = throwFalse;
        }
        throwTargetAndItem[1] = (int) worryLevel;

        inspections++;
        return throwTargetAndItem;
    }

    public void clearItemsAfterRound() {
        this.items.clear();
    }

    public void catchItem(long item) {
        items.add(item);
    }

    public List<Long> getItems() {
        return this.items;
    }

    public int getInspections() {
        return this.inspections;
    }

}
