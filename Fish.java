import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.regex.Matcher;

/**
 * Created by roni5_000 on 2015-11-23.
 */
public class Fish
{
    protected Ocean ocean;
    final protected int nChrononsToSpawn = 8;
    protected int nChrononsSinceSpawn = 0;
    public Coordinate coords;

    private int age = 0;
    private int maxAge = 17;

    public Fish(Ocean ocean, Coordinate coords)
    {
        this.ocean = ocean;
        this.coords = coords;
    }

    public void move() {

        Coordinate newCoord = this.ocean.getRandomEmptyAdjacentTile(this.coords);

        if(newCoord != null)
            this.ocean.move(this.coords, newCoord);

        ++this.nChrononsSinceSpawn;
        ++this.age;
        this.spawn();
    }

    public void spawn(){
        Coordinate newCoord = this.ocean.getRandomEmptyAdjacentTile(this.coords);

        if(newCoord != null && this.nChrononsSinceSpawn%this.nChrononsToSpawn == 0) {
            this.ocean.spawnFish(newCoord);
            this.nChrononsSinceSpawn = 0;
        }
    }

    public boolean isDead(){
        return this.age > this.maxAge;
    }
}
