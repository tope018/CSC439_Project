package de.fhdw.ergoholics.brainphaser.activities.main;

import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import de.fhdw.ergoholics.brainphaser.R;

public class PhaserAttackActivity extends AppCompatActivity implements View.OnClickListener {

    //define widget variables
    private TextView timerText;
    private TextView instruction;
    private Button buttonOne;
    private Button buttonTwo;
    private Button buttonThree;
    private Button buttonFour;
    private TextView questionText;
    private Button startButton;
    private TextView titleText;

    //define questions and answers in separate arrays

    String[] questionArray = new String[] {
            "What more can I do? All I want _________ is you.",
            "Every Day is a winding road. ________",
            "If I don't get some shelter, Lord, I'm gonna fade away. War, children ________",
            "Billie Jean is not my lover. ________ who claims that I am the one.",
            "If you wanna be my lover you _________ Make it last forever. Friendship never ends.",
            "Since you been gone, I can ________",
            "Don't go _________ Just stick to the rivers and the lakes that you're used to",
            "Which president is on the United States 1,000 dollar bill?",
            "What building is found on the back of the United States 100 dollar bill?",
            "What year was the two dollar bill last printed in the United States?",
            "What is the spanish word for money?",
            "What is the official currency of Equador?",
            "How much does a United States dollar bill weigh?"
    };
    String[] buttonOneArray = new String[] {
            "for the spring sale",
            "It gets a further away",
            "It's a trap. It's a trap.",
            "She just don't know",
            "gotta meet my family",
            "do whatever I want",
            "chasing waterfalls",
            "Grover Cleveland",
            "Lincoln Memorial",
            "2000",
            "peso",
            "Pound",
            "1.5 Grams"
    };
    String[] buttonTwoArray = new String[] {
            "for New Year's",
            "I get a little bit closer",
            "It's so cold and dark. It's so cold and dark",
            "She's a cleaning lady",
            "have to buy me a pizza.",
            "breathe for the first time",
            "chasing cars.",
            "Abraham Lincoln",
            "Independence Hall",
            "1998",
            "dinero",
            "Euro",
            "1 Gram"
    };
    String[] buttonThreeArray = new String[] {
            "for Christmas",
            "I keep drifting away",
            "It's just a shot away. It's just a shot away",
            "I know this girl",
            "gotta get with my friends.",
            "have tons of parties",
            "chasing pavements",
            "Thomas Jefferson",
            "White House",
            "2003",
            "dinner",
            "Peso",
            "2 Grams"
    };
    String[] buttonFourArray = new String[] {
            "for Halloween",
            "I feel a little bit safer",
            "Brother help me please. Brother help me please",
            "She's just a girl",
            "gotta fly me to France.",
            "leave all the memories behind",
            "chasing dreams",
            "Jimmy Carter",
            "Twin Towers",
            "1996",
            "deeniro",
            "United States Dollar",
            "0.5 Grams"
    };
    boolean[] buttonOneAnswer = new boolean[] {
            false,
            false,
            false,
            false,
            false,
            false,
            true,
            true,
            false,
            false,
            false,
            false,
            false
    };
    boolean[] buttonTwoAnswer = new boolean[] {
            false,
            true,
            false,
            false,
            false,
            true,
            false,
            false,
            true,
            false,
            true,
            false,
            true
    };
    boolean[] buttonThreeAnswer = new boolean[] {
            true,
            false,
            true,
            false,
            true,
            false,
            false,
            false,
            false,
            true,
            false,
            false,
            false
    };
    boolean[] buttonFourAnswer = new boolean[] {
            false,
            false,
            false,
            true,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            true,
            false
    };
    int arrayValue = 0;
    int score = 0;
    CountDownTimer Count;


    //define the SharedPreferences object
    private SharedPreferences savedValues;

    //define instance variables that should be saved
    private String buttonOneString = "";
    private String buttonTwoString = "";
    private String buttonThreeString = "";
    private String buttonFourString = "";
    private String questionTextString = "";
    private int currentArrayValue = 0;
    private int scoreValue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phaser_attack);

        //get references to the widgets
        buttonOne = (Button) findViewById(R.id.buttonOne);
        buttonTwo = (Button) findViewById(R.id.buttonTwo);
        buttonThree = (Button) findViewById(R.id.buttonThree);
        buttonFour = (Button) findViewById(R.id.buttonFour);
        questionText = (TextView) findViewById(R.id.questionText);
        startButton = (Button) findViewById(R.id.startButton);
        titleText = (TextView) findViewById(R.id.title);
        timerText = (TextView) findViewById(R.id.timerText);
        instruction = (TextView) findViewById(R.id.instruction);

        //set onClick listener for buttons
        buttonOne.setOnClickListener(this);
        buttonTwo.setOnClickListener(this);
        buttonThree.setOnClickListener(this);
        buttonFour.setOnClickListener(this);
        startButton.setOnClickListener(this);

        //get SharedPreferences object
        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);
    }

    @Override
    public void onPause() {
        //save the instance variables
        SharedPreferences.Editor editor = savedValues.edit();
        editor.putString("buttonOneString", buttonOne.getText().toString());
        editor.putString("buttonTwoString", buttonTwo.getText().toString());
        editor.putString("buttonThreeString", buttonThree.getText().toString());
        editor.putString("buttonFourString", buttonFour.getText().toString());
        editor.putString("questionTextString", questionText.getText().toString());
        editor.putInt("currentArrayValue", arrayValue);
        editor.commit();

        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();

        //get the instance variables
        buttonOneString = savedValues.getString("buttonOneString", "");
        buttonTwoString = savedValues.getString("buttonTwoString", "");
        buttonThreeString = savedValues.getString("buttonThreeString", "");
        buttonFourString = savedValues.getString("buttonFourString", "");
        questionTextString = savedValues.getString("questionTextString", "");
        currentArrayValue = savedValues.getInt("currentArrayValue", 0);

        //set the instance variables on their widgets
        buttonOne.setText(buttonOneString);
        buttonTwo.setText(buttonTwoString);
        buttonThree.setText(buttonThreeString);
        buttonFour.setText(buttonFourString);
        questionText.setText(questionTextString);
        arrayValue = currentArrayValue;
    }

    //Set up screen for a new game
    public void displayStart() {
        buttonOne.setVisibility(View.VISIBLE);
        buttonTwo.setVisibility(View.VISIBLE);
        buttonThree.setVisibility(View.VISIBLE);
        buttonFour.setVisibility(View.VISIBLE);
        questionText.setVisibility(View.VISIBLE);
        instruction.setVisibility(View.INVISIBLE);
        titleText.setVisibility(View.INVISIBLE);
        buttonOne.setText(buttonOneArray[0]);
        buttonTwo.setText(buttonTwoArray[0]);
        buttonThree.setText(buttonThreeArray[0]);
        buttonFour.setText(buttonFourArray[0]);
        questionText.setText(questionArray[0]);
        arrayValue = 0;
        score = 0;
        startButton.setText("New Game");
    }

    //Displays next question
    public void displayNext() {
        if(arrayValue < questionArray.length) {
            buttonOne.setText(buttonOneArray[arrayValue]);
            buttonTwo.setText(buttonTwoArray[arrayValue]);
            buttonThree.setText(buttonThreeArray[arrayValue]);
            buttonFour.setText(buttonFourArray[arrayValue]);
            questionText.setText(questionArray[arrayValue]);
        }
        else {
            Count.cancel();
            displayWinner();
        }
    }

    //Check if answer is correct
    public void checkAnswer(boolean[] array) {
        //Incorrect answer, go to next answer
        if (!array[arrayValue]) {
            arrayValue++;
            displayNext();
        }
        //Correct answer, increase score, array index, and move to next question
        else {
            score++;
            arrayValue++;
            displayNext();
        }
    }

    //change to endgame view
    public void displayWinner() {
        //TODO
        buttonOne.setVisibility(View.INVISIBLE);
        buttonTwo.setVisibility(View.INVISIBLE);
        buttonThree.setVisibility(View.INVISIBLE);
        buttonFour.setVisibility(View.INVISIBLE);
        questionText.setText("Score:  " + score);
        titleText.setVisibility(View.INVISIBLE);
        timerText.setText("Game Over!");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startButton:
                if(startButton.getText().toString().equals("New Game"))
                    Count.cancel();
                Count = new CountDownTimer(15000, 1000) {
                    public void onTick(long millisUntilFinished) {
                        timerText.setText("" + millisUntilFinished / 1000);
                    }

                    public void onFinish() {
                        displayWinner();
                    }
                };

                Count.start();
                displayStart();
                break;
            case R.id.buttonOne:
                checkAnswer(buttonOneAnswer);
                break;
            case R.id.buttonTwo:
                checkAnswer(buttonTwoAnswer);
                break;
            case R.id.buttonThree:
                checkAnswer(buttonThreeAnswer);
                break;
            case R.id.buttonFour:
                checkAnswer(buttonFourAnswer);
                break;
        }
    }
}
