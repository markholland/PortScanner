package markholland.portscanner;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	Handler h = new Handler();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.config);
		//setButtonEvents();
		
	}
	
	@Override
	protected void onStart(){
    	super.onStart();
    	
    	// Update the UI each second 
    	h.post(new Runnable() {
        	@Override
        	public void run() {
        		loadData();
        		getDataFromView();
        		
        		h.postDelayed(this, 1000);
        	}
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	
	private void setButtonEvents() {
        Button buttonStart = (Button) findViewById(R.id.buttonStart);
        buttonStart.setOnClickListener(new OnClickListener() {
        	public void onClick(View view) {
        		getDataFromView();
        		
        		
        	        		
        	}
        	});
        	
        
	}
	
	private void getDataFromView() {
        // Init form values
		
		Button buttonStart = (Button) findViewById(R.id.buttonStart);
        buttonStart.setOnClickListener(new OnClickListener() {
        	@Override
 		    public void onClick(View v) {
        		EditText editText;
        		boolean valid = true;
        	
        		editText = (EditText) findViewById(R.id.editTextHost);
        		String host = editText.getText().toString();
        		if(TextUtils.isEmpty(host)){
        			editText.setError("Must enter a host");
        			valid = false;
        		}
        		else
        			Core.host = host;
		
        		editText = (EditText) findViewById(R.id.editTextInitialPort);
        		        		
        		if((editText.getText().toString()).length() == 0){
        			editText.setError("Must enter a port");
        			valid = false;
        			Log.e("MARK","here");
        		}
        		else if(Integer.valueOf(editText.getText().toString()).intValue() > Core.finalPort){
        			
        			editText.setError("Initial port above final port");
        			valid = false;
        		}
        		else if(!((editText.getText().toString()).length() == 0)){
        			Core.initialPort = Integer.valueOf(editText.getText().toString()).intValue();
        		}
			
		
        		editText = (EditText) findViewById(R.id.editTextFinalPort);
        		
        		
        		if((editText.getText().toString()).length() == 0){
        			editText.setError("Must enter a port");
        			valid = false;
        			Log.e("MARK","here");
        		}
        		else if(Integer.valueOf(editText.getText().toString()).intValue() < Core.initialPort){
        			
        			editText.setError("Final port below initial port");
        			valid = false;
        		}
        		else if(!((editText.getText().toString()).length() == 0)){
        			Core.finalPort = Integer.valueOf(editText.getText().toString()).intValue();
        		}
        		
        		
        		if(valid){
        			Toast toast = Toast.makeText(getApplicationContext(),
	        	       		"starting...",
	        	       		Toast.LENGTH_SHORT);
	        	    	toast.show();
        			new PortScanner().execute();
        			
        			
        			
        		}
        		//Hide keyboard on pressinstart
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE); 

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                           InputMethodManager.HIDE_NOT_ALWAYS);
        }	
     });
        
        
		
		/*
		editText = (EditText) findViewById(R.id.editTextTimeout);
		Core.timeout = Integer.valueOf(editText.getText().toString()).intValue();
		
		editText = (EditText) findViewById(R.id.editTextMaxThreads);
		Core.maxThreads = Integer.valueOf(editText.getText().toString()).intValue();
		*/
	}
	
	private void loadData() {
		TextView textView;
		
		textView = (TextView) findViewById(R.id.textViewResult);
		textView.setText(Core.result);
		
		
	}
		
		
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
