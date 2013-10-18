package no_synchronized;

/**
 *
 * @author Eduardo
 */
public class Clock extends Thread{
    private int segundos=0;
    private int minutos=0;
    private boolean Game= true;
    
    public void Clock(){
    
    }
    public void run(){
        while(Game){
            try{                    
                Thread.sleep(1000);
                this.SetTime();
            }catch(Exception e){                    
                System.out.println("Problem with "+  e);                
            }    

        }
    }
    public void Restart(){
        this.segundos=0;
    
    }
    private void SetTime(){
        if(segundos< 300){
            segundos++;
        }else{
            segundos=0;
            minutos++;
        }
    }
    public void StopClock(){
        this.Game= false;
    }
    public int getSecond(){
        return this.segundos;
    }
    public int getMinutos(){
        return this.minutos;
    }
}
