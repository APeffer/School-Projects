package com.project1.example;

import javax.swing.SwingUtilities;

import com.project1.example.GUI.MainFrame;

public class Main {

    
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run(){
                MainFrame main = new MainFrame();
                main.show();
            }
        });

    }
    
}