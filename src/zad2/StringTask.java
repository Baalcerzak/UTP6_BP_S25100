package zad2;

public class StringTask implements Runnable{
    private final String threadLetter;
    private volatile int numberOfDuplicates;
    private TaskState state;
    private volatile String concatString;
    private Thread thread;

    public StringTask(String threadLetter, int numberOfDuplicated) {
        this.threadLetter = threadLetter;
        this.numberOfDuplicates = numberOfDuplicated;
        state = TaskState.CREATED;
        concatString = "";
    }

    public void start() {
        thread = new Thread(this);
        thread.start();
        state = TaskState.RUNNING;
    }
    public void abort(){
        state = TaskState.ABORTED;
        thread.interrupt();
    }
    public TaskState getState() {
        return state;
    }

    public boolean isDone() {
        return state == TaskState.READY || state == TaskState.ABORTED;
    }

    public String getResult() {
        return concatString;
    }

    @Override
    public void run() {
        for (int i = 0; i < numberOfDuplicates; i++) {
            if (Thread.currentThread().isInterrupted()) {
                return;
            }
            concatString += threadLetter;
        }
        state = TaskState.READY;


    }
}
enum TaskState{
    CREATED, RUNNING, ABORTED, READY
}
