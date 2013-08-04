package com.peak.android;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class MainActivity extends Activity {

    SlidingMenu menu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //setBehindContentView(R.layout.main);

        /*
        SlidingMenu menu = new SlidingMenu(this);
        menu.setMode
        menu.setMode(SlidingMenu.LEFT);
        menu.setBehindScrollScale(0.0f);
        menu.setFadeDegree(0.0f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        menu.setMenu(R.layout.menu);
        */

        menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT); // Menu only appears from left.
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN); // Can display menu from dragging anywhere.
        menu.setTouchModeBehind(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setBehindOffset(300);
        menu.setFadeDegree(0.3f);
        menu.setShadowWidth(5);
        //menu.setOnClickListener(this);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        menu.setMenu(R.layout.menu);

        Button btn = (Button) findViewById(R.id.menuButton1);
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "sliding menu button click", Toast.LENGTH_LONG).show();
            }
        });

        //menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        //menu.setShadowWidthRes(R.dimen.shadow_width);
        //menu.setShadowDrawable(R.drawable.shadow);
        //menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        //menu.setFadeDegree(0.35f);
        //menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        //menu.setMenu(R.layout.login);

        /*

        Log.d("Peak", "MainActivity onCreate()");

        try {
            Intent intent = getIntent();
            String userObjStr = intent.getStringExtra("userObjStr");
            JSONObject userObj = new JSONObject(userObjStr);

            TextView tv = (TextView) findViewById(R.id.tv_temp);
            tv.setText(userObj.toString(4));
        }

        catch(Exception e) {
            Log.e("Peak", e.toString());
        }

        */
    }

    public void toggle(View v) {
        menu.toggle(true);
    }
}
