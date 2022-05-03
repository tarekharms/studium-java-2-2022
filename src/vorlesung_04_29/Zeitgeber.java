package vorlesung_04_29;

import java.io.IOException;
import java.util.Date;

public class Zeitgeber extends Thread {
    private boolean stopped;

    public Zeitgeber()
    {
        this.stopped = false;
    }

    public void stoppen()
    {
        this.stopped = true;
    }

    public void run()
    {
        while(!this.stopped)
        {
            System.out.println((new Date()).toString());
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

        System.out.println("Zeitgeber beendet");
    }
}
