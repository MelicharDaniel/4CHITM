package melichar.old;

/**
 * Main-Klasse, führt das Lager aus.
 *
 * @author Daniel Melichar
 * @version 15.10.2014
 */
public class TestLager {

    public static void main(String[] args) {
        Lager[] lager = new Lager[3];

        for (int x = 0; x < lager.length; x++) {
            lager[x] = new Lager();
            new ErzeugerThread(lager[x]).start();
            new VerbraucherThread(lager[x]).start();
            new VerbraucherThread(lager[x]).start();
            new StatusThread(lager[x]).start();
        }
        lager[0].setProdukt("Redbull");
        lager[1].setProdukt("Tschick");
        lager[2].setProdukt("Taschentuch");
    }
}