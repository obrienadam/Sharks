/**
 * Created by roni5_000 on 2015-11-23.
 */

import java.util.ArrayList;
import java.util.Collections;

public class Ocean
{
    private Fish[][] tiles;
    private ArrayList<Fish> fishes = new ArrayList<Fish>();
    private Coordinate upperCoord;
    private int nSharks, nFish;

    public Ocean(int nTilesI, int nTilesJ)
    {
        this.tiles = new Fish[nTilesI][nTilesJ];
        this.upperCoord = new Coordinate(nTilesI - 1, nTilesJ - 1);
    }

    public int getNFish()
    {
        return nFish;
    }

    public int getnSharks()
    {
        return nSharks;
    }

    public boolean isEmptyTile(Coordinate coord)
    {
        return this.tiles[coord.i][coord.j] == null;
    }

    public boolean isFishTile(Coordinate coord) { return this.tiles[coord.i][coord.j].getClass().equals(Fish.class); }

    public boolean isSharkTile(Coordinate coord) { return this.tiles[coord.i][coord.j].getClass().equals(Shark.class); }

    public void populate(double fishProbability, double sharkProbability)
    {
        for (int j = 0; j <= upperCoord.j; ++j)
        {
            for (int i = 0; i <= upperCoord.i; ++i)
            {
                double rand = Math.random();

                if (rand < fishProbability) {
                    this.spawnFish(new Coordinate(i, j));
                }
                else if (rand < fishProbability + sharkProbability) {
                    this.spawnShark(new Coordinate(i, j));
                }
                else
                    tiles[i][j] = null;
            }
        }
    }

    public void spawnFish(Coordinate coord)
    {
        if (!this.isEmptyTile(coord))
            return;

        tiles[coord.i][coord.j] = new Fish(this, coord);
        fishes.add(tiles[coord.i][coord.j]);
        ++nFish;
    }

    public void spawnShark(Coordinate coord)
    {
        if (!this.isEmptyTile(coord))
            return;

        tiles[coord.i][coord.j] = new Shark(this, coord);
        fishes.add(tiles[coord.i][coord.j]);
        ++nSharks;
    }

    public void despawnFish(Coordinate coord)
    {
        if (!this.isFishTile(coord))
            return;

        fishes.remove(tiles[coord.i][coord.j]);
        tiles[coord.i][coord.j] = null;
        --nFish;
    }

    public void despawnShark(Coordinate coord)
    {
        if (!this.isSharkTile(coord))
            return;

        fishes.remove(tiles[coord.i][coord.j]);
        tiles[coord.i][coord.j] = null;
        --nSharks;
    }

    public boolean move(Coordinate coord, Coordinate newCoord)
    {
        if(!newCoord.isBoundedBy(new Coordinate(0, 0), this.upperCoord) || !this.isEmptyTile(newCoord))
            return false;

        tiles[newCoord.i][newCoord.j] = tiles[coord.i][coord.j];
        tiles[coord.i][coord.j] = null;
        tiles[newCoord.i][newCoord.j].coords = newCoord;

        return true;
    }

    public void moveAll()
    {
        Collections.shuffle(fishes); // randomize the order in which fish move

        for (Fish fish : fishes)
            fish.move();
    }

    public String getString()
    {
        String str = "";
        for (int j = 0; j <= upperCoord.j; ++j)
        {
            for (int i = 0; i <= upperCoord.i; ++i)
            {
                if (tiles[i][j] == null)
                    str += "| ";
                else if(tiles[i][j].getClass().equals(Fish.class))
                    str += "|F";
                else if (tiles[i][j].getClass().equals(Shark.class))
                    str += "|S";
            }
            str += "|\n";
        }

        return str;
    }
}
