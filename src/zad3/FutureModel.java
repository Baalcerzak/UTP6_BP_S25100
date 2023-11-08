package zad3;

import java.util.concurrent.Callable;

public class FutureModel implements Callable<Integer> {
    public String name = "";
    private boolean isInterrupted = false;

    public FutureModel(String name){
        this.name = name;
    }

    @Override
    public Integer call(){

        System.out.println("Start wątku: " + name);
        System.out.flush();

        while(!isInterrupted){
            if (Thread.currentThread().isInterrupted()){
                isInterrupted = true;
            }
        }

        System.out.println("Koniec wątku: " + name + " zwracam ");
        System.out.flush();

        return 0;
    }
}
