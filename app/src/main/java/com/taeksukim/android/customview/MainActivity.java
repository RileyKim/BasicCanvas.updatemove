package com.taeksukim.android.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    CustomView view;

    //이동단위
    int unit = 0;

    //플레이어 정보
    int player_x = 0;
    int player_y = 0;
    int player_radius = unit / 2;

    private static final int GROUND_SIZE=10;

    final int map[][] = {
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,1,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},

    };



    Button btnUp, btnDown, btnRight, btnLeft;
    FrameLayout ground;
    RelativeLayout control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        unit = metrics.widthPixels / GROUND_SIZE;


        init();


        btnUp = (Button) findViewById(R.id.btnUp);
        btnDown = (Button) findViewById(R.id.btnDown);
        btnRight = (Button) findViewById(R.id.btnRight);
        btnLeft = (Button) findViewById(R.id.btnLeft);

        ground = (FrameLayout) findViewById(R.id.ground);
        control = (RelativeLayout) findViewById(R.id.control);

        btnDown.setOnClickListener(this);
        btnLeft.setOnClickListener(this);
        btnRight.setOnClickListener(this);
        btnUp.setOnClickListener(this);

        view = new CustomView(this);
        ground.addView(view);

    }

    private void init(){
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        unit = metrics.widthPixels / GROUND_SIZE;
        player_radius = unit/2;

        player_x = 0;
        player_y = 0;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnUp:
                if(player_y > 0 && !collisionCheck("up"))
                player_y = player_y - 1;
                break;

            case R.id.btnDown:
                if(player_y < GROUND_SIZE-1 && !collisionCheck("down"))
                player_y = player_y + 1;
                break;

            case R.id.btnLeft:
                if(player_x > 0 && !collisionCheck("left"))
                player_x = player_x - 1;
                break;

            case R.id.btnRight:
                if(player_x < GROUND_SIZE-1 && !collisionCheck("right"))
                player_x = player_x + 1;
                break;

        }
        //화면을 다시 그려주는 함수 -> 화면을 지운 후에 onDraw를 호출해준다.
        view.invalidate();
    }

    private boolean collisionCheck(String direction){
        if(direction.equals("up")){
            if(map[player_y-1][player_x] == 1){
                return true;
            }
        }else if(direction.equals("down")){
            if(map[player_y+1][player_x] == 1){
                return true;
            }
        }else if(direction.equals("left")){
            if(map[player_y][player_x-1] == 1){
                return true;
            }
        }else if(direction.equals("right")){
            if(map[player_y][player_x+1] == 1){
                return true;
            }
        }


        return false;
    }


    class CustomView extends View {

        public CustomView(Context context) {
            super(context);

        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            //#oNDraw 함수에서 그림그리기
            //1. 색상을 정의
            //Paint paint = new Paint();
            //paint.setColor(Color.CYAN);
            Paint gray = new Paint();
            gray.setColor(Color.GRAY);
            Paint magenta = new Paint();
            magenta.setColor(Color.MAGENTA);
            //2. canvas에 그림 그리기
            //canvas.drawRect(100,100,200,200,paint);
            //canvas.drawCircle(500,500,100,paint2);


            // 화면에 맵을 그린다.
            for(int i =0; i<map.length; i++){
                for(int j = 0; j <map[0].length; j++){
                    if(map[i][j] != 0) {
                        canvas.drawRect(
                                unit*j, // 왼쪽 위 x
                                unit*i, // 왼쪽 위 y
                                unit*j + unit, // 오른쪽 아래 x
                                unit*i + unit, // 오른쪽 아래 y
                                gray // BLACK

                        );
                    }
                }
            }

            // 플레이어를 화면에 그린다
            canvas.drawCircle(
                    player_x * unit + player_radius,
                    player_y * unit + player_radius,
                    player_radius, magenta );
        }
    }
}