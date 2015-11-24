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
    private int nChrononsToSpawn;
    private int iCoord, jCoord;

    public Fish(Ocean ocean, int iCoord, int jCoord)
    {
        this.ocean = ocean;
        this.iCoord = iCoord;
        this.jCoord = jCoord;
        this.nChrononsToSpawn = 100;
    }

    public void move() {
        ArrayList<Integer> iCoords = new ArrayList<Integer>(), jCoords = new ArrayList<Integer>();

        for (int i = -1; i <= 1; ++i) {
            iCoords.add(this.iCoord + i);
            jCoords.add(this.jCoord + i);
        }

        Collections.shuffle(iCoords);
        Collections.shuffle(jCoords);

        for(Integer newJ: jCoords)
        {
            for(Integer newI: iCoords)
            {
                if(ocean.move(this.iCoord, this.jCoord, newI, newJ))
                {
                    this.iCoord = newI;
                    this.jCoord = newJ;
                    return;
                }
            }
        }
    }
}
