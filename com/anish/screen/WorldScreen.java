package com.anish.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;

import com.anish.calabashbros.BubbleSorter;
import com.anish.calabashbros.Monsters;
import com.anish.calabashbros.World;

import asciiPanel.AsciiPanel;

public class WorldScreen implements Screen {

    private World world;
    private Monsters[][] monsters;
    private Monsters[] monster;
    String[] sortSteps;
    int xNum=8;
    int yNum=8;
    int xStart=10;
    int yStart=10;
    int r,g,b;
    boolean[] randomData;
    public WorldScreen() {
        world = new World();
        randomData=new boolean[xNum*yNum];
        monsters = new Monsters[xNum][yNum];
        for(int i=0;i<xNum;i++){
            for(int j=0;j<yNum;j++){
                 int rank=makeRandom();

                 r=(int)(Math.random()*255);
                 g=(int)(Math.random()*255);
                 b=(int)(Math.random()*255);
                 monsters[i][j] = new Monsters(new Color(r,g,b),rank, world);
                world.put(monsters[i][j],j*2+yStart,i*2+xStart);
            }
        }

        loadMonsters(monsters);
        
        BubbleSorter<Monsters> b = new BubbleSorter<>();
        b.load(monster);
        b.sort();

        sortSteps = this.parsePlan(b.getPlan());
    }

    private String[] parsePlan(String plan) {
        return plan.split("\n");
    }

    private void execute(Monsters[][] bros, String step) {
        String[] couple = step.split("<->");
        getBroByRank(bros, Integer.parseInt(couple[0])).swap(getBroByRank(bros, Integer.parseInt(couple[1])));
    }

    private Monsters getBroByRank(Monsters[][] bros, int rank) {
        for (Monsters[] bro1 : bros) {
            for(Monsters bro2 : bro1)
            if (bro2.getRank() == rank) {
                return bro2;
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

        if (i < this.sortSteps.length) {
            this.execute(monsters, sortSteps[i]);
            i++;
        }

        return this;
    }

    public void loadMonsters(Monsters[][] monsters){
        monster=new Monsters[xNum*yNum];
        int i=0;
        for(Monsters[] monster1:monsters){
            for(Monsters monster2:monster1){
                monster[i]=monster2;
                i++;
            }
        }
    }
    public int makeRandom(){
        int rank=(int)(Math.random()*(xNum*yNum));
        while(randomData[rank]==true){
            rank=(int)(Math.random()*(xNum*yNum));
        }
        randomData[rank]=true;
        return rank;
    }
}
