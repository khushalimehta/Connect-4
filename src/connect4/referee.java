/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4;



/**
 *
 * @author Khushali
 */
public class referee implements Runnable {
     private int cboard[][]= new int[6][7];
     private board b;
     public referee(board  b)
     {
         this.b=b;
         
     }
     @Override
      public void run()
        {
            try
            {
              b.monitor();
            }
            catch(Exception e)
            {
                System.out.println("Error");
            }
        }
   
      
}
