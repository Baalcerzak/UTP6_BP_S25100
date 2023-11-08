package zad1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Letters{
    private List<Thread> threadList;

    public Letters(String threadNames) {
        threadList = new ArrayList<>();
        String[] list = threadNames.split("");
        for (String s : list) {

            Thread thread = new Thread(
                    ()->{
                        try {
                            while (!Thread.currentThread().isInterrupted()) {
                                Thread.sleep(1000);
                                System.out.print(s);
                            }
                        }catch (InterruptedException e){}
                    }
            );
            threadList.add(thread);
            thread.setName("Thread "+ s);
        }
    }

    public List<Thread> getThreads() {

        return threadList;
    }



}
