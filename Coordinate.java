/**
 * Created by roni5_000 on 2015-11-28.
 */
public class Coordinate {
    public int i, j;

    public Coordinate(int i, int j){
        this.i = i;
        this.j = j;
    }

    public boolean equals(Coordinate other){
        return this.i == other.i && this.j == other.j;
    }

    public boolean isBoundedBy(Coordinate lowerCoord, Coordinate upperCoord){
        return this.i >= lowerCoord.i && this.j >= lowerCoord.j && this.i <= upperCoord.i && this.j <= upperCoord.j;
    }
}
