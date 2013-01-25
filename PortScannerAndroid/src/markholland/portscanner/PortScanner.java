package markholland.portscanner;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;



public class PortScanner extends AsyncTask<Void, Void, String> {
    /** The system calls this to perform work in a worker thread and
      * delivers it the parameters given to AsyncTask.execute() */
    protected String doInBackground(Void... v) {
    	
    
    	
    Core.result = "Scanning...";
    String[] openPorts = new String[Core.finalPort - Core.initialPort + 1];
    int closedPorts = 0;
    int filteredPorts = 0;
    String result = "";
    String comparator = "";
    String response = "java.net.ConnectException: failed to connect to";
    
    for(int i = 0, port = Core.initialPort; i < openPorts.length; i++,port++){
    	
    	openPorts[i] = "";
    		
		try{      
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(Core.host, port),1000);
            socket.close();
            Log.e("MARK","Portopen: "+port);
            result +="Port "+(port++)+" is open\n";
		}catch(IOException e){
			comparator = ""+e;
			comparator = comparator.substring(0,47);
			Log.e("MARK","Portclosed/filtered: "+i);
			if(comparator.equals(response)){
			closedPorts++;
            }
            else
            	filteredPorts++;
		}
		
		
    }
        
    result += "Closed ports: "+closedPorts+"\nFiltered ports: "+filteredPorts;
        
    return result;
    }
    
    /** The system calls this to perform work in the UI thread and delivers
      * the result from doInBackground() */
    protected void onPostExecute(String result) {
      
       Core.result = result;
       
    }
}
