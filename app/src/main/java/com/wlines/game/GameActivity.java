package com.wlines.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    final static int maxN=5;
    private Context context;
    private ImageView[][] ivCell = new ImageView[maxN][maxN];
    private Drawable[] drawCell = new Drawable[7];

    private Button bSave;
    private Button bExit;
    private Button bNewGame;

    private String[][] valueCell = new String[maxN][maxN];
    private int xMove = 0;
    private int yMove = 0;

    private Random rand = new Random();
    private boolean isSelected = false;
    private int selectedX = 0;
    private int selectedY = 0;
    private String selectedColor = "";

    public int pkt;
    private TextView points;
    public String N, lvlSelected;
    private TextView nick;

    private LinearLayout linearLayout;

    private static final String fileName = "rank.txt";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        context = this;
        lvlSelected = getIntent().getStringExtra("lvl");

        linearLayout = (LinearLayout) findViewById(R.id.winLine);

        nick = (TextView) findViewById(R.id.textViewNickGame);
        N = getIntent().getStringExtra("nick");
        nick.setText(N);
        setListen();
        loadResources();
        designBoardGame();
        initGame();
    }

    private void setListen() {

        bNewGame = (Button) findViewById(R.id.bNewGame);
        bNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               initGame();
            }
        });

        bSave = (Button) findViewById(R.id.bSave);
        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<User> users = new ArrayList<>();
                String zawartoscRank = null;
                String fileName = "rank.txt";
                String lineText = "";

                FileInputStream fis = null;
                FileOutputStream fos = null;

                try {
                    fis = openFileInput(fileName);
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader br = new BufferedReader(isr);
                    while ((zawartoscRank = br.readLine())!=null)
                    {
                        String[] line = zawartoscRank.split(" ");
                        User user = new User(line[0], Integer.parseInt(line[1]));
                        users.add(user);
                    }
                    users.add(new User(N,pkt));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if(fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }

                Collections.sort(users);

                try {
                    fos = openFileOutput(fileName, MODE_PRIVATE);
                    for(User u: users)
                    {
                        lineText = u.nick +" "+ u.points+"\n";
                        fos.write(lineText.getBytes());
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        });

        bExit = (Button) findViewById(R.id.bExit);
        bExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameActivity.this, GameMainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void newBall() {

        int x,y;
        int b = 0;

        do{
            x = rand.nextInt(maxN);
            y = rand.nextInt(maxN);
        }while(valueCell[x][y] != null);

        xMove=x;
        yMove=y;

        if(lvlSelected.equals("easy")) {
            b = rand.nextInt(3) + 1;
            if (b == 1) {
                valueCell[xMove][yMove] = "green";
                ivCell[xMove][yMove].setImageDrawable(drawCell[1]);
            }
            if (b == 2) {
                valueCell[xMove][yMove] = "blue";
                ivCell[xMove][yMove].setImageDrawable(drawCell[2]);
            }
            if (b == 3) {
                valueCell[xMove][yMove] = "yellow";
                ivCell[xMove][yMove].setImageDrawable(drawCell[3]);
            }

        }else if(lvlSelected.equals("medium")){
            b = rand.nextInt(4) + 1;
            if (b == 1) {
                valueCell[xMove][yMove] = "green";
                ivCell[xMove][yMove].setImageDrawable(drawCell[1]);
            }
            if (b == 2) {
                valueCell[xMove][yMove] = "blue";
                ivCell[xMove][yMove].setImageDrawable(drawCell[2]);
            }
            if (b == 3) {
                valueCell[xMove][yMove] = "yellow";
                ivCell[xMove][yMove].setImageDrawable(drawCell[3]);
            }
            if (b == 4) {
                valueCell[xMove][yMove] = "purple";
                ivCell[xMove][yMove].setImageDrawable(drawCell[4]);
            }
        }else if(lvlSelected.equals("hard")) {
            b = rand.nextInt(5) + 1;
            if (b == 1) {
                valueCell[xMove][yMove] = "green";
                ivCell[xMove][yMove].setImageDrawable(drawCell[1]);
            }
            if (b == 2) {
                valueCell[xMove][yMove] = "blue";
                ivCell[xMove][yMove].setImageDrawable(drawCell[2]);
            }
            if (b == 3) {
                valueCell[xMove][yMove] = "yellow";
                ivCell[xMove][yMove].setImageDrawable(drawCell[3]);
            }
            if (b == 4) {
                valueCell[xMove][yMove] = "purple";
                ivCell[xMove][yMove].setImageDrawable(drawCell[4]);
            }
            if (b == 5) {
                valueCell[xMove][yMove] = "red";
                ivCell[xMove][yMove].setImageDrawable(drawCell[5]);
            }
        }

    }


    private void initGame() {

        for(int i=0; i<maxN;i++){
            for(int j=0; j<maxN;j++){
                ivCell[i][j].setImageDrawable(drawCell[0]);
                valueCell[i][j]=null;
            }
        }
        pkt = 0;
        points = (TextView) findViewById(R.id.textViewPoints);
        points.setText(String.valueOf(pkt));
        newBall();
        newBall();
        newBall();
        newBall();
    }

    private void loadResources() {
        drawCell[6] = context.getResources().getDrawable(R.drawable.cell_bg);
        drawCell[0] = null;
        drawCell[1] = context.getResources().getDrawable(R.drawable.green);
        drawCell[2] = context.getResources().getDrawable(R.drawable.blue);
        drawCell[3] = context.getResources().getDrawable(R.drawable.yellow);
        drawCell[4] = context.getResources().getDrawable(R.drawable.purple);
        drawCell[5] = context.getResources().getDrawable(R.drawable.red);

    }

    private void drawBalls() {

            for (int i = 0; i < maxN; i++)
                for (int j = 0; j < maxN; j++) {
                    if (valueCell[i][j] == "green") ivCell[i][j].setImageDrawable(drawCell[1]);
                    if (valueCell[i][j] == "blue") ivCell[i][j].setImageDrawable(drawCell[2]);
                    if (valueCell[i][j] == "yellow") ivCell[i][j].setImageDrawable(drawCell[3]);
                    if (valueCell[i][j] == "purple") ivCell[i][j].setImageDrawable(drawCell[4]);
                    if (valueCell[i][j] == "red") ivCell[i][j].setImageDrawable(drawCell[5]);
                    if (valueCell[i][j] == null) ivCell[i][j].setImageDrawable(null);
                }
    }

    public boolean checkLine()
    {
        boolean value = false;
        for (int x=0; x<maxN; x++)
            for (int y=0; y<maxN; y++)
            {
                if (valueCell[x][y]!=null) if (lineH(x,y)) value=true;
                if (valueCell[x][y]!=null) if (lineV(x,y)) value=true;
            }
        return value;
    }

    private boolean lineH(int x, int y) {

        if (valueCell[x][y]==null) return false;
        int index = 0;
        String color = valueCell[x][y];
        for (int i=x; i<maxN; i++)
        {
            if (valueCell[i][y]==color) index++; else
            {
                if (index<4) return false;
                else
                {
                    for (int j=i-1; j>(x-1); j--) valueCell[j][y]=null;
                    resultPkt(index);
                    linearLayout.setVisibility(View.VISIBLE);
                    //Toast.makeText(this, "win line!!", Toast.LENGTH_SHORT).show();
                    return true;
                }
            }
            //проверка на удаление шариков
            // сделать при удалении win line
            if (i==maxN-1)
            {
                if (index<4) return false;
                else
                {
                    for (int j=i; j>(x-1); j--) valueCell[j][y]=null;
                    resultPkt(index);
                    linearLayout.setVisibility(View.VISIBLE);
                    //Toast.makeText(this, "win line!!", Toast.LENGTH_SHORT).show();
                    return true;

                }
            }
        }
        return false;
    }


    private boolean lineV(int x, int y) {
        if (valueCell[x][y]==null) return false;
        int index = 0;
        String color = valueCell[x][y];
        for (int i=y; i<maxN; i++)
        {
            if (valueCell[x][i]==color) index++; else
            {
                if (index<4) return false;
                else
                {
                    for (int j=i-1; j>(y-1); j--) valueCell[x][j]=null;
                    resultPkt(index);
                    linearLayout.setVisibility(View.VISIBLE);
                    //Toast.makeText(this, "win line!!", Toast.LENGTH_SHORT).show();
                    return true;
                }
            }
            if (i==maxN-1)
            {
                if (index<4) return false;
                else
                {
                    for (int j=i; j>(y-1); j--) valueCell[x][j]=null;
                    resultPkt(index);
                    linearLayout.setVisibility(View.VISIBLE);
                    //Toast.makeText(this, "win line!!", Toast.LENGTH_SHORT).show();
                    return true;
                }
            }
        }
        return false;
    }

    public void resultPkt(int p)
    {
        switch (p)
        {
            case 4: pkt+=5;
                break;
            case 5: pkt+=9;
                break;
            case 6: pkt+=15;
                break;
        }
    }


    private void designBoardGame() {
        int sizeofCell = Math.round(ScreenWidth()/maxN);
        LinearLayout.LayoutParams lpRow = new LinearLayout.LayoutParams(sizeofCell*maxN, sizeofCell);
        LinearLayout.LayoutParams lpCell = new LinearLayout.LayoutParams(sizeofCell,sizeofCell);

        LinearLayout linBoardGame = (LinearLayout) findViewById(R.id.boardGame);

        for(int i=0; i<maxN; i++) {
            LinearLayout linRow = new LinearLayout(context);
            for(int j=0; j<maxN; j++) {
                ivCell[i][j] = new ImageView(context);
                ivCell[i][j].setBackground(drawCell[6]);
                final int x=i;
                final int y=j;
                ivCell[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        xMove = x;
                        yMove = y;
                        //хуета
                        linearLayout.setVisibility(View.GONE);

                        int index = 0;
                        if(valueCell[xMove][yMove] == null){
                            if(isSelected == false){
                            }else{
                                valueCell[xMove][yMove] = selectedColor;
                                valueCell[selectedX][selectedY] = null;

                                for(int x=0;x<maxN;x++)
                                    for(int y =0;y<maxN;y++)
                                        if(valueCell[x][y] == null) index++;

                                if(index == 2){
                                    newBall();
                                    newBall();
                                    drawBalls();
                                } else if(index == 1){
                                    newBall();
                                    drawBalls();
                                }else {
                                    newBall();
                                    newBall();
                                    newBall();
                                    drawBalls();
                                }
                                isSelected = false;
                                selectedX = 0;
                                selectedY = 0;
                                checkLine();
                                points.setText(String.valueOf(pkt));
                                drawBalls();
                            }
                        }else if(valueCell[xMove][yMove] != null) {
                            if(isSelected == true){
                            }else {
                                selectedX = xMove;
                                selectedY = yMove;
                                selectedColor = valueCell[xMove][yMove];
                                isSelected = true;
                            }
                        }
                    }
                });
                linRow.addView(ivCell[i][j],lpCell);
            }
            linBoardGame.addView(linRow,lpRow);
        }
    }

    private float ScreenWidth() {
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }
}
