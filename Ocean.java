/**
 * Created by roni5_000 on 2015-11-23.
 */

import sun.plugin.dom.core.CoreConstants;

import java.util.ArrayList;
import java.util.Collections;

public class Ocean
{
    private Fish[][] tiles;
    private ArrayList<Fish> fishes = new ArrayList<Fish>();
    private Coordinate lowerCoord, upperCoord;
    private int nSharks, nFish;

    public Ocean(int nTilesI, int nTilesJ)
    {
        this.tiles = new Fish[nTilesI][nTilesJ];
        this.lowerCoord = new Coordinate(0, 0);
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

    public boolean isEmptyTile(Coordinate coord) {
        return coord.isBoundedBy(this.lowerCoord, this.upperCoord) && this.tiles[coord.i][coord.j] == null;
    }

    public boolean isFishTile(Coordinate coord) {
        return coord.isBoundedBy(this.lowerCoord, this.upperCoord)
                && this.tiles[coord.i][coord.j] != null
                && this.tiles[coord.i][coord.j].getClass().equals(Fish.class);
    }

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

    public void despawn(Coordinate coord){
        if(this.isEmptyTile(coord))
            return;
        else if (this.isSharkTile(coord))
            this.despawnShark(coord);
        else if (this.isFishTile(coord))
            this.despawnFish(coord);
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
        else if (Math.abs(newCoord.i - coord.i) > 1 || Math.abs(newCoord.j - coord.j) > 1)
            return false;

        tiles[newCoord.i][newCoord.j] = tiles[coord.i][coord.j];
        tiles[coord.i][coord.j] = null;
        tiles[newCoord.i][newCoord.j].coords = newCoord;

        return true;
    }

    public void moveAll() {
        Collections.shuffle(fishes); // randomize the order in which fish move

        ArrayList<Fish> currentFishes = new ArrayList<Fish>(this.fishes);

        for (Fish fish : currentFishes)
            fish.move();
    }

    public void removeDeadFish(){
        ArrayList<Fish> deadFishes = new ArrayList<Fish>();

        for(Fish fish: fishes){
            if(fish.isDead())
                deadFishes.add(fish);
        }

        for(Fish deadFish: deadFishes)
            this.despawn(deadFish.coords);
    }

    public Coordinate getRandomEmptyAdjacentTile(Coordinate coord)
    {
        ArrayList<Coordinate> adjCoords = new ArrayList<Coordinate>();

        for (int i = -1; i <= 1; ++i)
            for(int j = -1; j <= 1; ++j)
                adjCoords.add(new Coordinate(coord.i + i, coord.j + j));

        Collections.shuffle(adjCoords);

        for(Coordinate adjCoord: adjCoords)
        {
            if(this.isEmptyTile(adjCoord))
            {
                return adjCoord;
            }
        }

        return null;
    }

    public Coordinate getRandomAdjacentFishTile(Coordinate coord)
    {
        ArrayList<Coordinate> adjCoords = new ArrayList<Coordinate>();

        for (int i = -1; i <= 1; ++i)
            for(int j = -1; j <= 1; ++j)
                adjCoords.add(new Coordinate(coord.i + i, coord.j + j));

        Collections.shuffle(adjCoords);

        for(Coordinate adjCoord: adjCoords)
        {
            if(this.isFishTile(adjCoord))
            {
                return adjCoord;
            }
        }

        return null;
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
