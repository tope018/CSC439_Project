package de.fhdw.ergoholics.brainphaser.activities.main;

import de.fhdw.ergoholics.brainphaser.R;
import de.fhdw.ergoholics.brainphaser.utility.ChallengeQuestion;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class PhaseoutActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    // Widgets
    private Button startButton;
    private TextView questionText;
    private TextView gameMode;
    private TextView instruction;
    private TextView phaseCountText;
    private Button buttonA;
    private Button buttonB;
    private Button buttonC;
    private Button buttonD;

    // SharedPreferences object
    private SharedPreferences savedValues;

    // Instance variables
    private int currentArrayValue = 0;
    private String questionTextString = "";
    private String phaseCountTextString = "";
    private int currentPhase = 0;
    private int currentQ = 0;
    private int currentCorrectQ = 0;
    private String aString = "";
    private String bString = "";
    private String cString = "";
    private String dString = "";

    // Counter values
    int arrayValue = 0;
    int phaseCount = 0;
    int qCount = 0;
    int correctQCount = 0;
    boolean questionsExhausted = false;

    //define questions and answers in separate arrays - will replace with xml parser methods

    ArrayList<ChallengeQuestion> list = new ArrayList<ChallengeQuestion>();

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phaseout);

        gameMode = (TextView) findViewById(R.id.phaseoutMode);
        startButton = (Button) findViewById(R.id.startButton);
        questionText = (TextView) findViewById(R.id.questionText);
        phaseCountText = (TextView) findViewById(R.id.phaseCountTextView);
        buttonA = (Button) findViewById(R.id.buttonA);
        buttonB = (Button) findViewById(R.id.buttonB);
        buttonC = (Button) findViewById(R.id.buttonC);
        buttonD = (Button) findViewById(R.id.buttonD);

        instruction = (TextView) findViewById(R.id.instruction);

        startButton.setOnClickListener(this);
        buttonA.setOnClickListener(this);
        buttonB.setOnClickListener(this);
        buttonC.setOnClickListener(this);
        buttonD.setOnClickListener(this);

        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);

        //Create question arrayList
        for (int i = 0; i < questionArray.length; i++) {
            ChallengeQuestion question = new ChallengeQuestion();
            question.setQuestion(questionArray[i]);
            question.setButtonOneAnswer(buttonOneArray[i]);
            question.setButtonOneBoolean(buttonOneAnswer[i]);
            question.setButtonTwoAnswer(buttonTwoArray[i]);
            question.setButtonTwoBoolean(buttonTwoAnswer[i]);
            question.setButtonThreeAnswer(buttonThreeArray[i]);
            question.setButtonThreeBoolean(buttonThreeAnswer[i]);
            question.setButtonFourAnswer(buttonFourArray[i]);
            question.setButtonFourBoolean(buttonFourAnswer[i]);
            list.add(question);
        }

        shuffleArray(list);
    }

    @Override
    public void onPause() {
        SharedPreferences.Editor editor = savedValues.edit();
        editor.putInt("currentArrayValue", arrayValue);
        editor.putString("questionTextString", questionText.getText().toString());

        editor.putString("phaseCountTextString", phaseCountText.getText().toString());
        editor.putInt("currentPhase", phaseCount);
        editor.putInt("currentQ", qCount);
        editor.putInt("currentCorrectQ", correctQCount);

        editor.putString("aString", buttonA.getText().toString());
        editor.putString("bString", buttonB.getText().toString());
        editor.putString("cString", buttonC.getText().toString());
        editor.putString("dString", buttonD.getText().toString());
        editor.commit();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();

        // Retrieve
        currentArrayValue = savedValues.getInt("currentArrayValue", 0);
        questionTextString = savedValues.getString("questionTextString", "");
        currentPhase = savedValues.getInt("currentPhase", 0);
        currentQ = savedValues.getInt("currentQ", 0);
        currentCorrectQ = savedValues.getInt("currentCorrectQ", 0);
        phaseCountTextString = savedValues.getString("phaseCountTextString", "");
        aString = savedValues.getString("aString", "");
        bString = savedValues.getString("bString", "");
        cString = savedValues.getString("cString", "");
        dString = savedValues.getString("dString", "");

        // Set
        arrayValue = currentArrayValue;
        questionText.setText(questionTextString);
        phaseCountText.setText(phaseCountTextString);
        phaseCount = currentPhase;
        qCount = currentQ;
        correctQCount = currentCorrectQ;
        buttonA.setText(aString);
        buttonB.setText(bString);
        buttonC.setText(cString);
        buttonD.setText(dString);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            // New game
            case R.id.startButton:
                displayStart();
                break;
            // Check answers; check for end of game or advance question
            case R.id.buttonA:
                checkAnswer(list, "buttonOne");
                if (phasesExhausted() || questionsExhausted) {
                    endGame();
                    break;
                }
                arrayValue++;
                displayNext();
                break;
            case R.id.buttonB:
                checkAnswer(list, "buttonTwo");
                if (phasesExhausted() || questionsExhausted) {
                    endGame();
                    break;
                }
                arrayValue++;
                displayNext();
                break;
            case R.id.buttonC:
                checkAnswer(list, "buttonThree");
                if (phasesExhausted() || questionsExhausted) {
                    endGame();
                    break;
                }
                arrayValue++;
                displayNext();
                break;
            case R.id.buttonD:
                checkAnswer(list, "buttonFour");
                if (phasesExhausted() || questionsExhausted) {
                    endGame();
                    break;
                }
                arrayValue++;
                displayNext();
                break;
        }
    }

    // New game
    public void displayStart() {
        // Widget visibility updated
        gameMode.setVisibility(View.INVISIBLE);
        instruction.setVisibility(View.INVISIBLE);
        questionText.setVisibility(View.VISIBLE);
        phaseCountText.setVisibility(View.VISIBLE);
        buttonA.setVisibility(View.VISIBLE);
        buttonB.setVisibility(View.VISIBLE);
        buttonC.setVisibility(View.VISIBLE);
        buttonD.setVisibility(View.VISIBLE);

        //randomize array list
        shuffleArray(list);

        // Widget text updated
        startButton.setText("New Game");
        questionText.setText(list.get(0).getQuestion());
        phaseCountText.setText("Phases Incurred: 0");
        buttonA.setText(list.get(0).getButtonOneAnswer());
        buttonB.setText(list.get(0).getButtonTwoAnswer());
        buttonC.setText(list.get(0).getButtonThreeAnswer());
        buttonD.setText(list.get(0).getButtonFourAnswer());

        //Counters set to default
        resetValues();
    }

    // Present next question
    public void displayNext() {
        // Question, answer options, and phase count display updated
        questionText.setText(list.get(arrayValue).getQuestion());
        phaseCountText.setText("Phases Incurred: " + phaseCount);
        buttonA.setText(list.get(arrayValue).getButtonOneAnswer());
        buttonB.setText(list.get(arrayValue).getButtonTwoAnswer());
        buttonC.setText(list.get(arrayValue).getButtonThreeAnswer());
        buttonD.setText(list.get(arrayValue).getButtonFourAnswer());

        // Question total updated
        qCount++;
    }

    // Update game counters
    public void checkAnswer(ArrayList<ChallengeQuestion> array, String string) {
        // Incorrect answer records phase, correct answer adds to correct total
        switch (string) {
            case "buttonOne":
                if (!array.get(arrayValue).isButtonOneBoolean()) {
                    phaseCount++;
                    break;
                }
                else {
                    correctQCount++;
                    break;
                }
            case "buttonTwo":
                if (!array.get(arrayValue).isButtonTwoBoolean()) {
                    phaseCount++;
                    break;
                }
                else {
                    correctQCount++;
                    break;
                }
            case "buttonThree":
                if (!array.get(arrayValue).isButtonThreeBoolean()) {
                    phaseCount++;
                    break;
                }
                else {
                    correctQCount++;
                    break;
                }
            case "buttonFour":
                if (!array.get(arrayValue).isButtonFourBoolean()) {
                    phaseCount++;
                    break;
                }
                else {
                    correctQCount++;
                    break;
                }
        }
        questionsExhausted();
    }

    // Check for third phase/strike
    public boolean phasesExhausted() {
        if (phaseCount == 3)
            return true;
        return false;
    }

    // Determine if current question is last in category
    public void questionsExhausted() {
        if (questionArray.length - 1 == arrayValue)
            questionsExhausted = true;
    }

    // Game over, phases or questions exhausted
    public void endGame() {
        // Widget visibility updated
        questionText.setVisibility(View.INVISIBLE);
        gameMode.setVisibility(View.INVISIBLE);
        buttonA.setVisibility(View.INVISIBLE);
        buttonB.setVisibility(View.INVISIBLE);
        buttonC.setVisibility(View.INVISIBLE);
        buttonD.setVisibility(View.INVISIBLE);

        // Display final game summary
        if (phaseCount == 1)
            phaseCountText.setText(phaseCount + " Phase!\n" + correctQCount + " of " + qCount + " correct");
        else
            phaseCountText.setText(phaseCount + " Phases!\n" + correctQCount + " of " + qCount + " correct");
        phaseCountText.setVisibility(View.VISIBLE);
        resetValues();
    }

    // Game over, all counters reset
    public void resetValues() {
        arrayValue = 0;
        qCount = 1;
        phaseCount = 0;
        correctQCount = 0;
        questionsExhausted = false;
    }

    //Randomized the question array list
    private static void shuffleArray(ArrayList<ChallengeQuestion> array)
    {
        int index;
        ChallengeQuestion temp;
        Random random = new Random();
        for (int i = array.size() - 1; i > 0; i--)
        {
            index = random.nextInt(i + 1);
            temp = array.get(i);
            array.set(i, array.get(index));
            array.set(index, temp);
        }
    }
}