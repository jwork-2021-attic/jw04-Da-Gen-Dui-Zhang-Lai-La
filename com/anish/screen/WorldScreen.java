package com.anish.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;

import com.anish.calabashbros.BubbleSorter;
import com.anish.calabashbros.Calabash;
import com.anish.calabashbros.World;
import com.anish.calabashbros.Thing;

import asciiPanel.AsciiPanel;

public class WorldScreen implements Screen {

    private World world;
    private Thing bros;
    String[] sortSteps;
    public WorldScreen() {
        world = new World();
        bros = new Thing(new Color(204, 0, 0),(char)1, world);
        world.put(bros, 0, 0);

    }
    private String[] parsePlan(String plan) {
        return plan.split("\n");
    }
    
    private void execute(Calabash[] bros, String step) {
        String[] couple = step.split("<->");
        getBroByRank(bros, Integer.parseInt(couple[0])).swap(getBroByRank(bros, Integer.parseInt(couple[1])));
    }
 
    private Calabash getBroByRank(Calabash[] bros, int rank) {
        for (Calabash bro : bros) {
            if (bro.getRank() == rank) {
                return bro;
            }
        }
        return null;
    }

    @Override
    public void displayOutput(AsciiPanel terminal) {

        for (int x = 0; x < World.WIDTH; x++) {
            for (int y = 0; y < World.HEIGHT; y++) {

                terminal.write(world.get(x, y).getGlyph(), x, y, world.get(x, y).getColor());

            }
        }
    }

    int i = 0;

    @Override
    public Screen respondToUserInput(KeyEvent key) {
        int[][] getMaze=this.world.maze.getMaze();
        if(key.getKeyCode()== 0x25){

            if(getMaze[bros.getX()-1][bros.getY()]==1&&(bros.getX()-1)>=0){
            Thing bro;
            bro = new Thing(new Color(255, 165, 0),(char)27, this.world);
                bros.moveTo(bro,bros.getX(), bros.getY(),bros.getX()-1, bros.getY());
            }

        }
        else if(key.getKeyCode()== 0x26){

            if(getMaze[bros.getX()][bros.getY()-1]==1&&(bros.getY()-1)>=0){
                Thing bro;
                bro = new Thing(new Color(255, 165, 0),(char)24, this.world);
            bros.moveTo(bro,bros.getX(), bros.getY(),bros.getX(), bros.getY()-1);
            }
        }
        else if(key.getKeyCode()== 0x27){

            if(getMaze[bros.getX()+1][bros.getY()]==1&&(bros.getX()+1)>=0){
            Thing bro;
            bro = new Thing(new Color(255, 165, 0),(char)26, this.world);
            bros.moveTo(bro,bros.getX(), bros.getY(),bros.getX()+1, bros.getY());
            }
        }
        else if(key.getKeyCode()== 0x28){

            if(getMaze[bros.getX()][bros.getY()+1]==1&&(bros.getY()+1)>=0){
                Thing bro;
                bro = new Thing(new Color(255, 165, 0),(char)25, this.world);
            bros.moveTo(bro,bros.getX(), bros.getY(),bros.getX(), bros.getY()+1);
            }
        }
        if(bros.getX()==30&&bros.getY()==30)
        System.out.println("you won");
        return this;
    }

}
