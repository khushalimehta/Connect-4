/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4;
import static java.lang.System.exit;
import java.security.SecureRandom;
/**
 *
 * @author Khushali
 */

public class player implements Runnable {
    
     private board b;
     private int cboard[][];
     private int playervalue;
    
     
    public player(board b,int mvalue)
    {
        this.setplayervalue(mvalue);
        this.b=b;
        cboard= new int[6][7];
    }
    public int getplayervalue() {
        return playervalue;
    }

    public void setplayervalue(int mvalue) {
        this.playervalue = mvalue;
    }

    
    @Override
    public void run()
    {
        if(b.getgamefinish())
        {
            exit(0);
        }
        else
        {
                try
                {
                    b.moveplayer(playervalue);	
                }
                catch(InterruptedException exception)
                {   
                    System.out.printf("\nexception in player \n"+exception);

                }

        }
    }
}

    
