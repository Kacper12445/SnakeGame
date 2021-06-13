package ThreadManagement;

public class IDSetter {
    private int threadID;

    //Creating ID for every creating thread
    public IDSetter(int ID){
        this.threadID = ID;
    }

    //Getting id of next created thread
    public int getNextThreadID(){
        return threadID++;
    }
}
