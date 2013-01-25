//import java.net.*;
//import java.io.*;
//import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 
 * @author Alvaro Ponce
 *
 */
public class portDisplay{

    AtomicIntegerArray opennedPorts;
    AtomicIntegerArray opennedPorts2;
    AtomicIntegerArray portchecker;
    AtomicInteger totalPorts;
    AtomicInteger closedPorts;
    AtomicInteger filteredPorts;
    AtomicInteger openPortscounter;
    AtomicInteger openPortscounter2;
    AtomicInteger checkerPortscounter;
    AtomicInteger percentage;
    
    public portDisplay(){
        opennedPorts = new AtomicIntegerArray(65535);
        opennedPorts2 = new AtomicIntegerArray(65535);
        portchecker = new AtomicIntegerArray(65535);
        totalPorts = new AtomicInteger(0);
        closedPorts = new AtomicInteger(0);
        filteredPorts = new AtomicInteger(0);
        openPortscounter = new AtomicInteger(0);
        openPortscounter2 = new AtomicInteger(0);
        checkerPortscounter = new AtomicInteger(0);
        percentage = new AtomicInteger(0);
    }
   
    public void incrementTotalPorts(){
        totalPorts.incrementAndGet();
    }
    
    public int incrementClosed(){
        return closedPorts.incrementAndGet();
    }
    
    public int incrementFiltered(){
        return filteredPorts.incrementAndGet();
    }

    public void setPercentage(int i){
        percentage.set(i);
    }     
    
    public int getPercentage(){
        return percentage.get();
    }
    
    public int getTotalPorts(){
        return totalPorts.get();
    }
    
    public int getChecked(int counter){
        return portchecker.get(counter);
    }
    
    public int getcheckedPorts(){
        return checkerPortscounter.get();
    }
    
    public int getOpenned(){
        return openPortscounter.get();
    }
    
    public int getClosed(){
        return closedPorts.get();
    }
    
    public int getFiltered(){
        return filteredPorts.get();
    }
    
    public void insertPort(int port){
        opennedPorts.addAndGet(openPortscounter.get(),port);
        openPortscounter.incrementAndGet();
    }
    
    public void insertPort2(int port){
        opennedPorts2.addAndGet(openPortscounter2.get(),port);
        openPortscounter2.incrementAndGet();
    }
    
    public void insertChecker(int port){
        portchecker.addAndGet(checkerPortscounter.get(),port);
        checkerPortscounter.incrementAndGet();
    }
    
    public int getOpennedPort(int counter){
        return opennedPorts.get(counter);
    }

    
    public String toString(){
        boolean frag = false;
        boolean frag2 = false;
        String res = "Succesful connections: \n";
        for(int i=0; opennedPorts.get(i) != 0 ;i++){
            res+= "Port " + opennedPorts.get(i) + " connected in first try \n";
        }
        for(int j=0; j < openPortscounter2.get() ;j++){
           for(int n=0; n < openPortscounter.get() ;n++){
               if(opennedPorts2.get(j)==opennedPorts.get(n)) {
                   filteredPorts.incrementAndGet();
                   n = openPortscounter.get();
                   frag = true;
               }
               
            }if(frag==false)res+= "Port " + opennedPorts2.get(j) + " connected in second try \n";
        }
        for(int p=0; p < openPortscounter2.get() ;p++){
            for(int o=0; o < openPortscounter2.get() ;o++){
                if(opennedPorts2.get(p)==opennedPorts2.get(o)) {
                    o = openPortscounter.get();
                    frag2 = true;
                }
                
            }if(frag2==false) res+= "Port " + opennedPorts2.get(p) + " connected in second try \n";
        }
        res+= "Ports closed " + closedPorts.get() + "\n";
        res+= "Ports filtered " + filteredPorts.get() + "\n";
        return res;
    }
}
    
    
    

