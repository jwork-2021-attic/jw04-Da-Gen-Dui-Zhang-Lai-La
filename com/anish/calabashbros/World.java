package com.anish.calabashbros;
public class World {

    public static final int WIDTH = 30;
    public static final int HEIGHT = 30;
    public static final int mazeSize=30;
    private Tile<Thing>[][] tiles;
    public MazeGenerator maze;
    public World() {
        maze = new MazeGenerator(mazeSize);
        maze.generateMaze();
            int[][] getMaze=maze.getMaze();
        if (tiles == null) {
            tiles = new Tile[WIDTH][HEIGHT];
        }

        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                tiles[i][j] = new Tile<>(i, j);
                if(getMaze[i][j]==1){
                    tiles[i][j].setThing(new Floor(this));
                }
                else{
                    tiles[i][j].setThing(new Wall(this));
                }
            }
        }
    }

    public Thing get(int x, int y) {
        return this.tiles[x][y].getThing();
    }

    public void put(Thing t, int x, int y) {
        this.tiles[x][y].setThing(t);
    }

}
