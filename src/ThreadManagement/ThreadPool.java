package ThreadManagement;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool extends ThreadGroup{

    private final int threadNumber;
    private boolean isCreated;
    private final List<Runnable> taskList;

    public ThreadPool(int threadNumberParam)
    {
        super("ThreadPool");
        /**Number of threads*/
        this.threadNumber = threadNumberParam;
        /**Actualizing status*/
        this.isCreated = true;
        /**List if tasks*/
        this.taskList = new LinkedList<Runnable>();
        createThreads();
    }

    private void createThreads()
    {
        for(int i = 0; i < this.threadNumber; i++)
        {
            /**thread begins to execute
            Throws IllegalStateException*/
            new CreatedThread(this).start();
        }
    }

    /**Running thread*/
    public synchronized void startThreadTask(Runnable threadTask)
    {
        /**Checking is thread has been created*/
        if(!this.isCreated)
        {
            /**Showing message*/
            throw new IllegalStateException("ThreadPool dead");
        }
        /**If threadTask exists, add to list*/
        if(threadTask != null)
        {
            /**Adding thread task to list*/
            taskList.add(threadTask);
            //Waking up waiting thread
            notify();
        }
    }

    protected  synchronized Runnable getThreadTask() throws  InterruptedException
    {
        /**Checking if there are tasks in list*/
        while(this.taskList.size() == 0)
        {
            if(!this.isCreated)
            {
                return null;
            }
            /**Current thread wait until it is awakened*/
            wait();
        }
        return this.taskList.remove(0);
    }
}
