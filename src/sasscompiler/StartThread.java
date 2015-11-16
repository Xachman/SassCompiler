/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sasscompiler;

/**
 *
 * @author ziron_000
 */
public class StartThread extends Thread {
    
    private final SassWatcher _SassWatcher;
    //private final Thread _thread = new Thread();
      
    
    public StartThread(SassWatcher sassWatcher ) {
        _SassWatcher = sassWatcher;
    }
    
    @Override
    public void run() {
        while(true) {
            // do stuff with input
            //System.out.println("No change was detected. ");
            try {
                this.sleep(1000);                 //1000 milliseconds is one second.
             } catch(InterruptedException ex) {
                this.currentThread().interrupt();
             }
            if (_SassWatcher.checkFileDates()){
              System.out.println("A change was detected. ");
              _SassWatcher.compile();
            }
        }
    }
    
}
