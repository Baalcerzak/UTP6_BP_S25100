package zad3;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Frame extends JFrame {
    private ExecutorService executorService = Executors.newCachedThreadPool();

    Map<FutureModel, Future<Integer>> map = new HashMap<>();
    public Frame(){
        JPanel panel = new JPanel();
        JLabel l= new JLabel("Manage your tasks");

        JButton startButton1 = new JButton("Task 1");
        JButton startButton2 = new JButton("Task 2");
        JButton startButton3 = new JButton("Task 3");
        String [] buttons = {startButton1.getText(), startButton2.getText(), startButton3.getText()};
        JList list = new JList(buttons);
        list.setSelectedIndex(1);

        panel.add(l);

        panel.add(startButton1);
        panel.add(startButton2);
        panel.add(startButton3);
        panel.add(list);
        add(panel);

        startButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startThread(startButton1.getText());
                startButton1.setEnabled(false);
            }
        });
        startButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startThread(startButton2.getText());
                startButton2.setEnabled(false);
            }
        });
        startButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startThread(startButton3.getText());
                startButton3.setEnabled(false);
            }
        });
        list.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {

                stopThread((String)list.getModel().getElementAt(list.locationToIndex(e.getPoint())));
            }
        });
        setSize(500,500);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
    public void startThread(String text){
        FutureModel fr = new FutureModel(text);
        Future<Integer> future = executorService.submit(new FutureModel(text));
        map.put(fr, future);
    }
    public void stopThread(String name1){
        for (Map.Entry<FutureModel, Future<Integer>> entry : map.entrySet()) {
            if (entry.getKey().name.equals(name1)) {
                entry.getValue().cancel(true);
            }
        }
    }

}
