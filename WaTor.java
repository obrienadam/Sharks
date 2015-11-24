/**
 * Created by roni5_000 on 2015-11-23.
 */
public class WaTor
{
    public static void main(String[] args)
    {
        Ocean ocean = new Ocean(80, 80);
        ocean.populate(0.3, 0.05);

        int nFish = ocean.getNFish(), nSharks = ocean.getnSharks();

        for(int i = 0; i < 100; ++i) {
            ocean.moveAll();
            System.out.println(ocean.getString());
        }

        System.out.printf("%d %d\n%d %d\n", nFish, nSharks, ocean.getNFish(), ocean.getnSharks());
    }
}
