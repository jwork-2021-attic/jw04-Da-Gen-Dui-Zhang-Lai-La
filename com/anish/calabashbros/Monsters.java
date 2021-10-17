package com.anish.calabashbros;

import java.awt.Color;

public class Monsters extends Creature implements Comparable<Monsters> {

    private int rank;

    public Monsters(Color color, int rank, World world) {
        super(color, (char) 2, world);
        this.rank = rank;
    }

    public int getRank() {
        return this.rank;
    }

    @Override
    public String toString() {
        return String.valueOf(this.rank);
    }

    @Override
    public int compareTo(Monsters o) {
        return Integer.valueOf(this.rank).compareTo(Integer.valueOf(o.rank));
    }

    public void swap(Monsters another) {
        int x = this.getX();
        int y = this.getY();
        this.moveTo(another.getX(), another.getY());
        another.moveTo(x, y);
    }

}
