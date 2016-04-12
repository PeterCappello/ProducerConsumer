package producerconsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Peter Cappello
 */
public class Producer implements Runnable
{
    private static final AtomicInteger PRODUCER_ID = new AtomicInteger();

    private final int id = PRODUCER_ID.getAndIncrement();
    private final BlockingQueue<Integer> q;
        
    Producer( BlockingQueue q ) { this.q = q; }

    @Override
    public void run() 
    {
      try 
      {
          while ( true ) 
          { 
              q.put( produce() ); 
              Thread.sleep( (int) ( 1000 * Math.random() ) );
          }
      } 
      catch ( InterruptedException ignore ) {}
    }

    Integer produce() 
    { 
        int i = (int) ( 10 * Math.random() );
        System.out.println( "Producer " + id + " producing " + i );
        return i; 
    }
}
