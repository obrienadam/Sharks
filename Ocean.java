/**
 * Created by roni5_000 on 2015-11-23.
 */

import java.util.ArrayList;
import java.util.Collections;

public class Ocean
{
    private Fish[][] tiles;
    private ArrayList<Fish> fishes = new ArrayList<Fish>();
    private int nTilesI, nTilesJ;
    private int nSharks, nFish;

    public Ocean(int nTilesI, int nTilesJ)
    {
        this.tiles = new Fish[nTilesI][nTilesJ];
        this.nTilesI = nTilesI;
        this.nTilesJ = nTilesJ;
    }

    public int getNFish()
    {
        return nFish;
    }

    public int getnSharks()
    {
        return nSharks;
    }

    public boolean isEmptyTile(int i, int j)
    {
        return this.tiles[i][j] == null;
    }

    public boolean isFish(int i, int j)
    {
        return this.tiles[i][j] instanceof Fish;
    }

    public void populate(double fishProbability, double sharkProbability)
    {
        for (int j = 0; j < nTilesJ; ++j)
        {
            for (int i = 0; i < nTilesI; ++i)
            {
                double rand = Math.random();

                if (rand < fishProbability) {
                    this.spawnFish(i, j);
                }
                else if (rand < fishProbability + sharkProbability) {
                    this.spawnShark(i, j);
                }
                else
                    tiles[i][j] = null;
            }
        }
    }

    public void spawnFish(int i, int j)
    {
        if (tiles[i][j] != null)
            return;

        tiles[i][j] = new Fish(this, i, j);
        fishes.add(tiles[i][j]);
        ++nFish;
    }

    public void spawnShark(int i, int j)
    {
        if (tiles[i][j] != null)
            return;

        tiles[i][j] = new Shark(this, i, j);
        fishes.add(tiles[i][j]);
        ++nSharks;
    }

    public void despawnFish(int i, int j)
    {
        if (!tiles[i][j].getClass().equals(Fish.class))
            return;

        fishes.remove(tiles[i][j]);
        tiles[i][j] = null;
        --nFish;
    }

    public void despawnShark(int i, int j)
    {
        if (!tiles[i][j].getClass().equals(Shark.class))
            return;

        fishes.remove(tiles[i][j]);
        tiles[i][j] = null;
        --nSharks;
    }

    public boolean move(int i, int j, int newI, int newJ)
    {
        if(newI < 0 || newI >= nTilesI || newJ < 0 || newJ >= nTilesJ || tiles[newI][newJ] != null)
            return false;

        tiles[newI][newJ] = tiles[i][j];
        tiles[i][j] = null;
        return true;
    }

    public void moveAll()
    {
        Collections.shuffle(fishes);

        for (Fish fish : fishes)
            fish.move();
    }

    public String getString()
    {
        String str = "";
        for (int j = 0; j < nTilesJ; ++j)
        {
            for (int i = 0; i < nTilesI; ++i)
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
