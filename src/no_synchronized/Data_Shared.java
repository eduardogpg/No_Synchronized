
package no_synchronized;

/**
 *
 * @author Eduardo
 */
public class Data_Shared {
    static volatile int  State = 0;
   static volatile boolean busy = false;
    static String nameThr = "";
    
    static void setState(int number){
        Data_Shared.State= number;
        
    }
    static int getState(){
        return Data_Shared.State;
    }
    
    static void ChangeBusy(Main_Thread th){        
        if(!Data_Shared.busy){
            
            Data_Shared.busy= true;
            Data_Shared.nameThr= th.getThisName();
            th.setCiticalZone();
            th.behavior();
            th.SleepThread();//Me ayuda a poder ver en la tabla los estados
            th.ChangeCriticalZone();
            th.changeSleep();
            Data_Shared.ChangeBusyLiberation();
            th.SleepThread();
        }else{
           th.setSleep();
           th.SleepThread();
        }
    }
   
    static String NameThread(){
        return Data_Shared.nameThr;
    }
    static void ChangeBusyLiberation(){
      
        Data_Shared.busy = false;
    }
    static boolean getBusy(){
        return Data_Shared.busy;
    }
    
}
