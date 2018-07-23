package com.jayzonsolutions.lunchboxfoodmaker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {
    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 1000;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_splash);
/*appPermissions ap = new appPermissions(getApplicationContext());

        ap.requestStoragePermission();*/
        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

             /*   // Session class instance
                SessionManager session = new SessionManager(getApplicationContext());

                //        handleButtonClicked();


                //     session.checkLogin();

                if (session.isLoggedIn()) {
                    //   startActivity(new Intent(SplashScreen.this,
                    //           MainActivity.class));
                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(i);
                    //     startActivity(new Intent(SplashScreen.this,
                    //		                    MainActivity.class));
                } else {
                    Intent i = new Intent(SplashActivity.this, signin.class);
                    startActivity(i);
                }
                */

                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SplashActivity.this,LoginActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
/*    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    Intent welcomeIntent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(welcomeIntent);
                }
            }
        };
        thread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }*/
}
