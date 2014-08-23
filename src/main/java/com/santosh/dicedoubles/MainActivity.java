/**
 * DiceDoubles Game
 * Author: Santosh Gautam
 * Date:   03/08/2013
 */
package com.santosh.dicedoubles;

import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
    private EditText yourAmtTxt;
    private EditText betAmtTxt;
    private TextView statusMsgTxt;
    private TextView dice1Text, dice2Text;
    private TextView moneyLeftTxt;
    private Button diceBtn;
    private ImageView dice1Img, dice2Img;

    double billAmt;
    double betAmt;
    String statusMsg;
    double moneyLeft;

    //instances of dice
    DicePairs firstDice;  // Refers to the first pair of dice.
    DicePairs secondDice; // Refers to the second pair of dice.

    int total1;      // Total showing on first pair of dice.
    int total2;      // Total showing on second pair of dice.

    private View.OnClickListener myClickListener = new View.OnClickListener()//responds to calculate button click
    {
        public void onClick(View v) {

            billAmt = Double.parseDouble((yourAmtTxt.getText().toString()));
            betAmt = Double.parseDouble((betAmtTxt.getText().toString()));

            //first dice
            firstDice.roll();    // Roll the first pair of dice.

            total1 = firstDice.rollResult; // Get total.

            //display images
            switch (total1){
                case 1:
                    dice1Img.setImageResource(R.drawable.dice1);
                    break;
                case 2:
                    dice1Img.setImageResource(R.drawable.dice2);
                    break;
                case 3:
                    dice1Img.setImageResource(R.drawable.dice3);
                    break;
                case 4:
                    dice1Img.setImageResource(R.drawable.dice4);
                    break;
                case 5:
                    dice1Img.setImageResource(R.drawable.dice5);
                    break;
                case 6:
                    dice1Img.setImageResource(R.drawable.dice6);
                    break;
            }

            dice1Text.setText(Integer.toString(total1)); //Display result as text

            //second dice
            secondDice.roll();    // Roll the second pair of dice.
            total2 = secondDice.rollResult;  // Get total.

            //display images
            switch (total2){
                case 1:
                    dice2Img.setImageResource(R.drawable.dice1);
                    break;
                case 2:
                    dice2Img.setImageResource(R.drawable.dice2);
                    break;
                case 3:
                    dice2Img.setImageResource(R.drawable.dice3);
                    break;
                case 4:
                    dice2Img.setImageResource(R.drawable.dice4);
                    break;
                case 5:
                    dice2Img.setImageResource(R.drawable.dice5);
                    break;
                case 6:
                    dice2Img.setImageResource(R.drawable.dice6);
                    break;
            }

            dice2Text.setText(Integer.toString(total2)); //Display result as text

                //If there is a winner
                if (total1 == total2)
                {
                    statusMsgTxt.setText("You win $ "+ Double.toString(betAmt) );
                    moneyLeft= billAmt + betAmt;
                    moneyLeftTxt.setText("$ " + Double.toString(moneyLeft));
                    yourAmtTxt.setText(Double.toString(moneyLeft));
                }

                //If no winner
                else
                {
                    statusMsgTxt.setText("You lose $ "+ Double.toString(betAmt) );
                    moneyLeft= billAmt - betAmt;
                    moneyLeftTxt.setText("$ " + Double.toString(moneyLeft));
                    yourAmtTxt.setText(Double.toString(moneyLeft));


                    if (moneyLeft < betAmt)
                    {
                        //No money means sorry, no bet
                        if (moneyLeft==0)
                        {
                            moneyLeftTxt.setText("You lost all your money!!");
                            diceBtn.setEnabled(false);
                        }

                        //Money in pocket is less than bet, ask user to reduce the bet amount
                        else
                        moneyLeftTxt.setText("Reduce your bet amount!!");
                        diceBtn.setEnabled(false);
                    }
                }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        yourAmtTxt = (EditText) findViewById(R.id.editTextMoney);

        betAmtTxt = (EditText) findViewById(R.id.editTextBet);
        betAmtTxt.addTextChangedListener(betTxtWatcher);

        dice1Text = (TextView) findViewById(R.id.textViewDice1);
        dice2Text = (TextView) findViewById(R.id.textViewDice2);
        moneyLeftTxt = (TextView) findViewById(R.id.textViewMoneyLeft);
        dice1Img = (ImageView) findViewById(R.id.imageView);
        dice2Img = (ImageView) findViewById(R.id.imageView2);

        statusMsgTxt = (TextView) findViewById(R.id.textViewStatus);

        diceBtn = (Button) findViewById(R.id.button);
        diceBtn.setOnClickListener(myClickListener);

        firstDice = new DicePairs();

        secondDice = new DicePairs();

    }

    TextWatcher betTxtWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (!charSequence.equals(""))
            {
                billAmt = Double.parseDouble((yourAmtTxt.getText().toString()));

                if (billAmt>1){
                    //If money in pocket is more than $1, enable user to play game
                    diceBtn.setEnabled(true);
                }
                else
                    //else disable gameplay
                    diceBtn.setEnabled(false);

            }

        }

        @Override
        public void afterTextChanged(Editable editable) {


            }

    };

}
