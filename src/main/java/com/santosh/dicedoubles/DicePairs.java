package com.santosh.dicedoubles;

public class DicePairs{
    public int rollResult;


    public DicePairs() {
        // Constructor.  Rolls the dice, so that they initially
        // show some random values.
        roll();  // Call the roll() method to roll the dice.
    }

    public void roll() {
        // Roll the dice by setting each of the dice to be
        // a random number between 1 and 6.
        rollResult = (int)(Math.random()*6) + 1;
    }

}