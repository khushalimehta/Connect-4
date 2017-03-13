/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4;

import java.security.SecureRandom;
import static java.lang.System.exit;
/**
 *
 * @author Khushali
 */
public class board {
    private static  SecureRandom generator=new SecureRandom();
    private int cboard[][]= new int[6][7];
    private  boolean gamefinish=false;
    private  boolean moveplayer=false;
    public board()
    {
        this.setboard(cboard);
    }
    
    public void inboard()
    {
     int i,j;
     for(i=0;i<6;i++)
     {
    	 for(j=0;j<7;j++)
    	 {
    		 cboard[i][j] = 0;
    	 }
     }
    }
    public void pboard()
    {
     int i,j;
     for(i=0;i<6;i++)
     {
    	 for(j=0;j<7;j++)
    	 {
    		 System.out.printf(" %d ", cboard[i][j]);
    	 }
    	 System.out.println("");
     }  
    }
    public int[][] getboard()
    {
    	return cboard;
    }
    public boolean getgamefinish()
    {
        return gamefinish;
    }
    public boolean getmoveplayer()
    {
        return moveplayer;
    }
    public void setboard(int[][] cboard)
    {
    	this.cboard=cboard;	
    }
    public void setgamefinish(boolean gamefinish)
    {
        this.gamefinish=gamefinish;
    }
    public void setmoveplayer(boolean moveplayer)
    {
        this.moveplayer=moveplayer;
    }
  public synchronized void moveplayer(int playervalue) throws InterruptedException
    {
         int i,colno,newcolno=0;
         for(;;)
         {
             cboard=getboard();
             try
             {
                 while(getmoveplayer() == false)
                 wait();
                 setmoveplayer(false);
                 if(getgamefinish())
                 {
                    notifyAll();
                    exit(0);
                 }
            
             else
                 {
                  System.out.printf("\nPlayer %d is Playing\n", playervalue);     
                     do
                     {
                    
                      colno = generator.nextInt(7) + 1;
                      System.out.printf("\nSelected Column is %d\n",colno);
                      for(i=5;i>=0;i--)
                      {
                          if(cboard[i][colno-1]== 0)
                          {
                                cboard[i][colno - 1] = playervalue;
                                setboard(cboard);
                                pboard();
                                System.out.printf("\nChecking for winner\n");
                                //setmoveplayer(false);
                                newcolno = 0;
                                notifyAll();
                                break;
                          }
                          else
                          {
                              newcolno=1;
                          }
                      }
                     }
                     while(newcolno==1);                 
               }
             }
             catch(Exception e)
             {
                 System.out.println("Exception in player move");
             }
    
          }   
    }
    public synchronized void monitor() throws InterruptedException
    {
       pboard();
        for(;;)
        {
       
                 try
                {
                    while(getmoveplayer() == true)
                    {
                        wait();
                    }
                    setmoveplayer(true);
                    cboard=getboard();
                    if(checkwinner()==0 && tiecheck()==0)
                    {
                       setmoveplayer(true);
                        notifyAll();
                    }
                    else
                    {
                        setgamefinish(true);
                        System.out.println("Game Finished");
                        notifyAll();
                        return;
                    }
                }
        
                catch(Exception e)
                {
                    System.out.println("Exception in monitor");
                }
            
        }
                
    }
      
      private int checkwinner()
      {
          try
          {
          if(vcheck()==1 || dcheck()==1)
              { 
              return(1);
              }
          }
          catch(ArrayIndexOutOfBoundsException e)
          {
              System.out.printf("Exception in chechwinnner\n");
          }
          return(0);
      }
      /* Vertical Check */
        private int vcheck() throws ArrayIndexOutOfBoundsException
        {
          int i,j;
          for(i=0;i<6;i++)
          {
            for(j=0;j<7;j++)
            {
                if((i+1)<6 && (i+2)<6 && (i+3)<6)
                {
                    if(cboard[i][j]==cboard[i+1][j] && cboard[i][j]==cboard[i+2][j] && cboard[i][j]==cboard[i+3][j] &&  cboard[i][j] != 0)
                    {
                        System.out.println("Vertical match");
                        System.out.printf("\nThe winner is Player\n ", +cboard[i][j]);
                        return(1);
                    }
                }
               
            }
        }
        return(0);

      }
        /* Diagonal Check*/
      private int dcheck() throws ArrayIndexOutOfBoundsException
      {
          int i,j;
          for(i=0;i<6;i++)
          {
            for(j=0;j<7;j++)
                {
                     if(j+1 < 7 && j+2 < 7 && j+3 < 7 && i+1 < 6 && i+2 < 6 && i+3 < 6)
                    {
                        if (( cboard[i][j] == cboard[i+1][j+1] &&  cboard[i][j] == cboard[i+2][j+2] && cboard[i][j] == cboard[i+3][j+3]) && cboard[i][j] != 0)
                        {
                            System.out.println("Diagonal match");
                            System.out.println("The Winner is Player"+ cboard[i][j]);
                            return(1);
                        }
                }
                        
                    if (j-1 > -1 && j-2 > -1 && j-3 > -1 && i+1 < 6 &&  i+2 < 6 &&  i+3 < 6)
                    {
                        if ((cboard[i][j] == cboard[i+1][j-1] && cboard[i][j] == cboard[i+2][j-2] && cboard[i][j] == cboard[i+3][j-3]) && cboard[i][j] != 0)
                        {
                            System.out.println("Diagonal match");
                            System.out.println("The Winner is Player %d "+ cboard[i][j]);
                            return(1);
                        }
                    }
                }               
            }
         return(0);

      }
      public synchronized int tiecheck()
              
      {
          cboard=getboard();
          int i,j;
          for(i=0;i<6;i++)
          {
              for(j=0;j<7;j++)
              {
                  if(cboard[i][j]==0)
                  {
                      notifyAll();
                      return(0);
                  }
              }
          System.out.println("Tie");
          notifyAll();
          
          }
         return(0); 
       }
}
