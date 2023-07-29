package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Let's say
    //x=0
    //o=1
    //null=9

    //Hi,I'm Shubha

    Button reset;
    Vibrator vibrator;


    int active=0;   //to check that who's turn is it
    int[] state = {9,9,9,9,9,9,9,9,9};
    boolean over=false; //for check the game is over or not
    int[] [] winpositions=  {{0,1,2} , {3,4,5} , {6,7,8},       //positions where x or o will win
                            {0,3,6} , {1,4,7} , {2,5,8},
                            {0,4,8} , {2,4,6}};
    boolean tie=false;

    public void tap(View view)      //function to play the game
    {
        vibrator.vibrate(100);
        ImageView image= (ImageView) view;
        int tappedImage= Integer.parseInt(image.getTag().toString());

        if(over)
        {
            reset(view);
            TextView status=findViewById(R.id.textView5);
            status.setText("TAP TO PLAY");
            return;


        }

        if(state[tappedImage]==9)
        {
            state[tappedImage]=active;
            for(int i=0;i<9;i++)
            {
                if(state[i]==9)
                {
                   tie=false;
                   break;
                }
                else
                {
                    tie=true;
                    continue;
                }

            }
            //image.animate().translationYBy(-1000f);
            if(active==0 && !tie)
            {
                //Toast.makeText(this, "0's Turn...", Toast.LENGTH_SHORT).show();
                image.setImageResource(R.drawable.x_);
                active=1;
                TextView status=findViewById(R.id.textView5);
                status.setText("0's Turn");
            }
            else if(active==1 && !tie)
            {
                //Toast.makeText(this, "X's Turn...", Toast.LENGTH_SHORT).show();
                image.setImageResource(R.drawable._o);
                active=0;
                TextView status=findViewById(R.id.textView5);
                status.setText("X's Turn");
            }
            else if(tie)
            {
                image.setImageResource(R.drawable.x_);
                ImageView img = (ImageView)findViewById(R.id.imageView10);
                img.setImageResource(R.drawable.gameover);
                TextView status=findViewById(R.id.textView5);
                status.setText("TIE..!");
            }
        }
        //image.animate().translationYBy(1000f).setDuration(300);
        //hi,SHUBHAJIT RANA

        for(int[] win:winpositions)
        {
            if (state[win[0]] == state[win[1]] && state[win[1]] == state[win[2]] && state[win[0]] != 9)
            {
                ImageView img = (ImageView)findViewById(R.id.imageView10);
                img.setImageResource(R.drawable.gameover);
                // Somebody has won! - Find out who!
                String winner = null;
                over = true;
                if (state[win[0]] == 0)
                {
                    winner_sound(view);
                    winner = "X HAS WON";
                }
                else if(state[win[0]] == 1)
                {
                    winner_sound(view);
                    winner = "O HAS WON";
                }

                // Update the status bar for winner announcement
                TextView status = findViewById(R.id.textView5);
                status.setText(winner);
            }
        }
    }



    public void reset(View view)    //function to reset the game
    {
        over=false;
        vibrator.vibrate(100);
        TextView status=findViewById(R.id.textView5);
        status.setText("TAP TO PLAY");
        active=0;
        for(int i=0;i<state.length;i++)
        {
            state[i]=9;
        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);


        ((ImageView)findViewById(R.id.imageView10)).setImageResource(0);

    }

    public void winner_sound(View view)
    {
        final MediaPlayer mediaPlayer=MediaPlayer.create(this,R.raw.sound_winner);
        mediaPlayer.start();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reset=findViewById(R.id.reset);
        vibrator=(Vibrator) getSystemService(VIBRATOR_SERVICE);
    }
}