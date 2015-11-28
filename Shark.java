/**
 * Created by roni5_000 on 2015-11-23.
 */
public class Shark extends Fish
{
    final private static int maxEnergy = 100;
    private int energy = 50;

    public Shark(Ocean ocean, Coordinate coord)
    {
        super(ocean, coord);
    }

    @Override
    public void spawn()
    {
        Coordinate newCoord = this.ocean.getRandomEmptyAdjacentTile(this.coords);

        if(newCoord != null && this.nChrononsSinceSpawn%this.nChrononsToSpawn == 0 && this.energy > 30) {
            this.ocean.spawnShark(newCoord);
            this.nChrononsSinceSpawn = 0;
        }
    }

    @Override
    public void move(){
        Coordinate newCoord = this.ocean.getRandomAdjacentFishTile(this.coords);

        if (newCoord != null && this.energy <= 75) {
            this.ocean.despawnFish(newCoord);
            this.ocean.move(this.coords, newCoord);
            this.energy += 25;
            if(this.energy > maxEnergy)
                this.energy = maxEnergy;
            ++this.nChrononsSinceSpawn;
        }
        else
            super.move();

        this.energy -= 5;
    }

    @Override
    public boolean isDead(){
        return this.energy <= 20;
    }
}
