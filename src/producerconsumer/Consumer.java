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
    
   Consumer( BlockingQueue q, AtomicInteger sum ) 
   { 
       this.q = q;
       this.sum = sum;
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
       System.out.println( "Consumer " + id + " consuming " + i + " Sum: " + sum );
   } 
}
