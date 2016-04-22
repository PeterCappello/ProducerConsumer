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
    private Integer total;
    
   Consumer( BlockingQueue q, AtomicInteger sum, Integer total ) 
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
       /** 
        * synchronized necessary to ensure that println executes before another 
        * consumer thread modifies sum. 
        * (Run it w/o synchronized to illustrate race.)
        */
       synchronized ( sum )
       {
           sum.addAndGet( i );    // sum will appear monotonically nondecreasing
           System.out.println( "Consumer " + id + " consuming " + i + " sum: " + sum );
       }
       total += i;           // total may not appear monotonically nondecreasing
       System.out.println( "Consumer " + id + " consuming " + i + " total: "  + total );
   } 
}
