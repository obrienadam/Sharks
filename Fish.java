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
    private Ocean ocean;
    final private int nChrononsToSpawn = 100;
    private int nChrononsSinceSpawn = 0;
    public Coordinate coords;

    public Fish(Ocean ocean, Coordinate coords)
    {
        this.ocean = ocean;
        this.coords = coords;
    }

    public void move() {
        ArrayList<Coordinate> adjCoords = new ArrayList<Coordinate>();

        for (int i = -1; i <= 1; ++i)
            for(int j = -1; j <= 1; ++j)
                adjCoords.add(new Coordinate(this.coords.i + i, this.coords.j + j));

        Collections.shuffle(adjCoords);

        this.nChrononsSinceSpawn++;

        for(Coordinate coord: adjCoords)
        {
            if(this.ocean.move(this.coords, coord))
            {
                return;
            }
        }
    }
}
