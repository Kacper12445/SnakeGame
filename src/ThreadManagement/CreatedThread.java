package ThreadManagement;

public class CreatedThread extends Thread{
    private static IDSetter threadID = new IDSetter(1);
    private ThreadPool threadPool;

    public CreatedThread(ThreadPool threadPoolParam)
    {
        /**Allocating new thread*/
        super(threadPoolParam, "PooledThread: " + threadID.getNextThreadID());
        this.threadPool = threadPoolParam;
    }

    @Override
    public void run()
    {
        /**Checking if thread is interrupted*/
        while(!isInterrupted())
        {
            Runnable threadTask = null;
            try
            {
                threadTask = threadPool.getThreadTask();
            }
            catch(InterruptedException exception)
            {
                exception.printStackTrace();
            }
            if(threadTask == null)
            {
                return;
            }
            try
            {
                threadTask.run();
            }
            catch (Throwable throwable)
            {
                threadPool.uncaughtException(this, throwable);
            }
        }
    }
}
