/**
 * Created by roni5_000 on 2015-11-23.
 */
public class WaTor
{
    public static void main(String[] args)
    {
        Ocean ocean = new Ocean(80, 80);
        ocean.populate(0.9, 0.01);

        int nFish = ocean.getNFish(), nSharks = ocean.getnSharks();

        for(int i = 0; i < 400; ++i) {
            ocean.moveAll();
            ocean.removeDeadFish();
            System.out.println(ocean.getString());

            if(ocean.getNFish() == 0)
            {
                System.out.println("All fish are gone!");
                //break;
            }
            if(ocean.getnSharks() == 0)
            {
                System.out.println("All sharks are gone!");
                //break;
            }
        }

        System.out.printf("%d %d\n%d %d\n", nFish, nSharks, ocean.getNFish(), ocean.getnSharks());
    }
}
