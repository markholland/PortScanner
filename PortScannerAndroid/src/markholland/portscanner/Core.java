package markholland.portscanner;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

public class Core {
	
	static volatile public String defaultHost;
	static volatile public int defaultInitialPort;
	static volatile public int defaultFinalPort = 65000;
	static volatile public int defaultTimeout;
	static volatile public int defaultMaxThreads;
	static volatile public String defaultResult = "";
	
	
	static volatile public String host = defaultHost;
	static volatile public int initialPort = defaultInitialPort;
	static volatile public int finalPort = defaultFinalPort;
	static volatile public int timeout = defaultTimeout;
	static volatile public int maxThreads = defaultMaxThreads;
	static volatile public String result = defaultResult;
	
	
	
	
	
	
	
	

}
