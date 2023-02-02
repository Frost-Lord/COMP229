package main;

import  java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class InputHandler implements KeyListener {

    public boolean UpActivated, DownActivated, LeftActivated, RightActivated;

    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {
        int EVT = e.getKeyCode();

        if (EVT == KeyEvent.VK_W){
            UpActivated = true;
        }
        if (EVT == KeyEvent.VK_S){
            DownActivated = true;
        }
        if (EVT == KeyEvent.VK_A){
            LeftActivated = true;
        }
        if (EVT == KeyEvent.VK_D){
            RightActivated = true;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        int EVT = e.getKeyCode();

        if (EVT == KeyEvent.VK_W){
            UpActivated = false;
        }
        if (EVT == KeyEvent.VK_S){
            DownActivated = false;
        }
        if (EVT == KeyEvent.VK_A){
            LeftActivated = false;
        }
        if (EVT == KeyEvent.VK_D){
            RightActivated = false;
        }
    }
}