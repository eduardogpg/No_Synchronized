
package no_synchronized;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eduardo
 */
public class Main_Thread extends Thread{
   
    private static int SleepTime = 1000;
    private int Number= 0;
    private String Name= "";
    private String State= "";
    
    private boolean status;
    public boolean Spleep;
    private boolean CriticalZone= false;
  
    Random random = new Random();
   
    public Main_Thread(String nameThread, int num){
     
        status= false;
        Number = num;
        Spleep= false;
        Name = nameThread;
        
    }
    public void StopThread(){
        this.status = true;
    }
    public void run(){
        while(!status){
            if(!Data_Shared.getBusy()){
               CriticalZone= true;
                Data_Shared.ChangeBusy(this);
                               
            }else{
                Spleep= true;
                SleepThread();
            }   
    
        }
    }
    public void changegetCriticalZone(){
        CriticalZone= false;
    }
    public void changeSleep(){
        Spleep= false;
    }
    public boolean getCriticalZone(){
        return this.CriticalZone;
    }
   public void SleepThread(){
         try {  
                
                Thread.sleep(SleepTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(Main_Thread.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    public void behavior(){
            
                if(Name.equals(Data_Shared.NameThread())){
                    Operation();
                       this.SleepThread();  
                }
    }
    private void Operation(){
         int n = random.nextInt(4);
         int nu = Data_Shared.State ;
         
        if (n==0)
            addition(nu);
        else if(n == 1)
            subtract(nu);
        else if(n==2)
            division(nu);
        else if(n==3)
            multiplication(nu);
        
       
        setNumber(Number);
        System.out.println("I am the Thread :"+ Name + " the Status is : "+Data_Shared.getState() +" current Status "+ State);
   
    }
    private void addition(int nu){
        State =" Addition";
        this.Number= nu+10;
    }
    private void subtract(int nu){
        State =" Subtract";
        this.Number= nu-10;
    }
    private void multiplication(int nu){
         State =" Multiplication";
         this.Number= nu*2;
    }
    private void division(int nu){
        
        if(Data_Shared.getState()==0){
            nu= 50;
            System.err.println("restart for 50");
        }else
            nu = nu /2;
        this.Number= nu;
        State =" Division";
    }
    private void setNumber(int num){
        Data_Shared.setState(num); 
    }
    public boolean getSleep(){
        return this.Spleep;
    }
    
    public String getThisName(){
        return Name;
    }
    public boolean getLife(){
        return this.status;
    }
}
