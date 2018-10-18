package com.example.jiangxinwei.pacman;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

// The author of this class file is Wenjing Xue
public class Instruction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_instruction);

        TextView instructionText = (TextView)findViewById(R.id.textView_instruction);
        String instruction;

        // Explain the rule and control of the game
        instruction = "This is the instruction of the game:\n\n";
        instruction += "The player is playing with a computer\n\n";
        instruction += "Control:\n Four button in the left and right side makes player to control the move direction.\n";
        instruction += "The direction of the four button are U: up, D: down, L: left, R: right.\n\n";
        instruction += "Game Rule: The player has to eat all the beans and not catch by the chasers, \n otherwise the player lose the game. \n";
        instruction += "When the computer been caught, it just disappear and the game continues.";


        instructionText.setText(instruction);
    }

    // after review the instruction, return to the welcome activity
    public void backHome(View view){
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }
}
