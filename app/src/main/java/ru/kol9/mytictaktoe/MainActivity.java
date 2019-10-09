package ru.kol9.mytictaktoe;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView mTextViewPlayerGo;
    GridLayout mGridLayout;

    boolean move = true;
    boolean win = false;
    int click=0;
    int[] resultArr = {-1, -1, -1, -1, -1, -1, -1, -1, -1};
    int [][] arrForCheck=new int[3][3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGridLayout = findViewById(R.id.grid);
        for (int i = 0; i < 9; i++) {
            mGridLayout.getChildAt(i).setId(i);
            mGridLayout.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (move) {
                        ((Button) v).setText(R.string.x);
                        resultArr[v.getId()] = 1;
                        mTextViewPlayerGo.setText(R.string.pl2);
                    }
                    if (!move) {
                        ((Button) v).setText(R.string.o);
                        resultArr[v.getId()] = 0;
                        mTextViewPlayerGo.setText(R.string.pl1);
                    }
                    ((Button) v).setEnabled(false);
                    checkWin();
                    move = !move;
                    click++;
                }
            });
        }
        mTextViewPlayerGo = findViewById(R.id.textView);
        mTextViewPlayerGo.setText(R.string.pl1);
    }

    public void checkWin() {
        int a=0;
        for (int i=0;i< 3;i++){
            for (int j=0;j<3;j++){
                arrForCheck[i][j]=resultArr[a];
                a++;
            }
        }
        int minusH=0,plusH=0;
        int minusV=0,plusV=0;
        for (int i=0;i< 3;i++){
            for (int j=0;j<3;j++){
                if (arrForCheck[i][j]==1)
                    plusH++;
                if (arrForCheck[i][j]==0)
                    minusH++;
                if (arrForCheck[j][i]==1)
                    plusV++;
                if (arrForCheck[j][i]==0)
                    minusV++;
            }
            if (plusH==3 || plusV==3){
                mTextViewPlayerGo.setText(R.string.pl1Won);
                win = true;
            }
            if (minusH == 3 || minusV==3) {
                mTextViewPlayerGo.setText(R.string.pl2Won);
                win = true;
            }
            plusH=0;
            minusH=0;
            plusV=0;
            minusV=0;
        }
        int minusDiagL=0,plusDiagL=0,minusdiagR=0,plusdiagR=0;
        for (int i=0;i<3;i++){
            if (arrForCheck[i][i]==1)
                plusDiagL++;
            if (arrForCheck[i][i]==0)
                minusDiagL++;
            if (arrForCheck[i][2-i]==0)
                minusdiagR++;
            if (arrForCheck[i][2-i]==1)
                plusdiagR++;
            
        }
        if (minusDiagL==3 || minusdiagR==3){
            mTextViewPlayerGo.setText(R.string.pl2Won);
            win = true;
        }
        if (plusDiagL==3 || plusdiagR==3){
            mTextViewPlayerGo.setText(R.string.pl1Won);
            win = true;
        }
        if (win) {
            for (int i = 0; i < 9; i++) {
                ((Button) mGridLayout.getChildAt(i)).setEnabled(false);
            }
            mTextViewPlayerGo.setBackgroundColor(Color.RED);
        }
       if(click == 8 && win!=true){
            mTextViewPlayerGo.setText(R.string.drawText);
            mTextViewPlayerGo.setBackgroundColor(Color.GREEN);
        }

    }

    public void onClickReset(View view) {
        win=false;
        click=0;
        for (int j = 0; j < 9; j++) {
            resultArr[j]=-1;
        }
        for (int i = 0; i < 9; i++) {
            ((Button) mGridLayout.getChildAt(i)).setEnabled(true);
            ((Button) mGridLayout.getChildAt(i)).setText("");
        }
        mTextViewPlayerGo.setText(R.string.pl1);
        mTextViewPlayerGo.setBackgroundColor(Color.WHITE);
    }
}
