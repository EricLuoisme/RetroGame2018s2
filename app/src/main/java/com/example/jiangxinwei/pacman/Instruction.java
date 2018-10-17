package com.example.jiangxinwei.pacman;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

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
        instruction = "This game has two mode: Practice and Compete \n\n";
        instruction += "The Practice mode allow play to play alone\n";
        instruction += "The Compete mode makes the player to compete with a computer\n\n";
        instruction += "The four button in the left and right side makes player to control the move direction.\n";
        instruction += "The direction of the four button are U: up, D: down, L: left, R: right.\n\n";
        instruction += "The player has to eat all the beans and not catch by the chasers, \n otherwise the player lose the game. \n";
        instruction += "When the computer been caught in the Compete mode, \n it just disappear and the game continues.";


        instructionText.setText(instruction);
        //instructionText.setMaxLines(6);
    }

    public void backHome(View view){
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }
}
