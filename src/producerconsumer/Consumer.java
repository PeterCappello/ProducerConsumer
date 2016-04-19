package producerconsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Peter Cappello
 */
public class Consumer implements Runnable
{
    private static int CONSUMER_ID;

    private final int id = CONSUMER_ID++;
    private final BlockingQueue<Integer> q;
    private final AtomicInteger sum;
    private int total;
    
   Consumer( BlockingQueue q, AtomicInteger sum, int total ) 
   { 
       this.q = q;
       this.sum = sum;
       this.total = total;
   }
   
    @Override
   public void run() 
   {
     try 
     { 
         while ( true ) 
         { 
             consume( q.take() ); 
         } 
     } 
     catch (InterruptedException ignore ) {}
   }
   
   void consume( Integer i ) 
   { 
       sum.addAndGet( i ) ; 
       total += i;
       System.out.println( "Consumer " + id + " consuming " + i + " Sum: " + sum + " total: "  + total );
   } 
}
