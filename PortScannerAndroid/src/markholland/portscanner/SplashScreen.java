package markholland.portscanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
 

public class SplashScreen extends Activity {
 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        setContentView(R.layout.splash_screen);
 
        Handler handler = new Handler();
        final SplashScreen sPlashScreen = this;
        // run a thread after 2 seconds to start the home screen
        handler.postDelayed(new Runnable() {
 
        	
            @Override
            public void run() {
 
                // make sure we close the splash screen so the user won't come back when it presses back key
 
                finish();
                // start the home screen
 
                 
              //start a new activity
                Intent i = new Intent();
                i.setClass(sPlashScreen, MainActivity.class);
                        startActivity(i);
 
            }
 
        }, 1500); // time in milliseconds (1 second = 1000 milliseconds) until the run() method will be called
 
    }
 
}
