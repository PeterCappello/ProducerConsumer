package producerconsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Peter Cappello
 */
public class ProducerConsumer 
{
    /**
     * @param args the command line arguments - unused
     */
    public static void main(String[] args) 
    {
        BlockingQueue q = new LinkedBlockingQueue();
        AtomicInteger sum = new AtomicInteger();
        Integer total = 0;
        Producer p1 = new Producer( q );
        Producer p2 = new Producer( q );
        Consumer c1 = new Consumer( q, sum, total );
        Consumer c2 = new Consumer( q, sum, total );
        Consumer c3 = new Consumer( q, sum, total );
        new Thread( c1 ).start();
        
        // the extra Consumer threads illustate a race condition on Integer total.
        new Thread( c2 ).start(); 
        new Thread( c3 ).start();
        new Thread( p1 ).start();
        new Thread( p2 ).start(); 
    }
}
