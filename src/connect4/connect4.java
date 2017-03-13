/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/**
 *
 * @author Khushali
 */
public class connect4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
       
        System.out.println("*****CONNECT 4 *****");
        System.out.println("Player 1 - Red");
        System.out.println("Player 2 - Yellow");
        board b=new board();
        b.inboard();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new referee(b));
        executorService.execute(new player(b,1));
        executorService.execute(new player(b,2));
        
    }
    
}
