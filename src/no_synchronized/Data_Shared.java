
package no_synchronized;

/**
 *
 * @author Eduardo
 */
public class Data_Shared {
    static int State = 0;
   static boolean busy = false;
    static String nameThr = "";
    
    static void setState(int number){
        Data_Shared.State= number;
        
    }
    static int getState(){
        return Data_Shared.State;
    }
    
    static void ChangeBusy(Main_Thread th){        
        if(!Data_Shared.busy){
           //System.err.println("_______________");
            Data_Shared.busy= true;
            Data_Shared.nameThr= th.getThisName();
            th.behavior();
            th.changegetCriticalZone();
            th.changeSleep();
            Data_Shared.ChengeBusyLiberation();
            th.SleepThread();
        }else{
           
            th.SleepThread();
        }
    }
   
    static String NameThread(){
        return Data_Shared.nameThr;
    }
    static void ChengeBusyLiberation(){
      
        Data_Shared.busy = false;
    }
    static boolean getBusy(){
        return Data_Shared.busy;
    }
    
}