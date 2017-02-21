package com.taeksukim.android.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    CustomView view;
    int player_x = 0;
    int player_y = 0;
    int player_size = 50;

    //이동단위
    int unit = 50;

    Button btnUp, btnDown, btnRight, btnLeft;
    FrameLayout ground;
    RelativeLayout control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnUp:
                player_y = player_y - unit;
                break;

            case R.id.btnDown:
                player_y = player_y + unit;
                break;

            case R.id.btnLeft:
                player_x = player_x - unit;
                break;

            case R.id.btnRight:
                player_x = player_x + unit;
                break;

        }
        //화면을 다시 그려주는 함수 -> 화면을 지운 후에 onDraw를 호출해준다.
        view.invalidate();
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

            Paint paint2 = new Paint();
            paint2.setColor(Color.MAGENTA);
            //2. canvas에 그림 그리기
            //canvas.drawRect(100,100,200,200,paint);
            //canvas.drawCircle(500,500,100,paint2);

            canvas.drawCircle(player_x, player_y, player_size, paint2);
        }
    }
}