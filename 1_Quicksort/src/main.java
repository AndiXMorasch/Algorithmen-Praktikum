import java.io.IOException;

public class main {

    public static void main(String args[]) throws IOException {

        Zufallszahlen zufallszahlen = new Zufallszahlen();
        zufallszahlen.hunderttausendZahlen();
        zufallszahlen.leseHunderttausendZahlenInArrayEin();
        zufallszahlen.eineMillionZahlen();
        zufallszahlen.leseEineMillionZahlenInArrayEin();
        zufallszahlen.fuenfMillionZahlen();
        zufallszahlen.leseFuenfMillionenZahlenInArrayEin();
        
        int[] array1 = zufallszahlen.arr1;
        long startzeit1 = zufallszahlen.aktuelleZeit();
        zufallszahlen.quickSort(array1, 0, (array1.length - 1));
        long endzeit1 = zufallszahlen.aktuelleZeit();
        long sortierdauer1 = zufallszahlen.sortierdauer(startzeit1, endzeit1);
        System.out.println("Die Sortierung von 100.000 Elementen hat " + sortierdauer1 + " Millisekunden gedauert.");
        //zufallszahlen.printArray(array1);

        int[] array2 = zufallszahlen.arr2;
        long startzeit2 = zufallszahlen.aktuelleZeit();
        zufallszahlen.quickSort(array2, 0, (array2.length - 1));
        long endzeit2 = zufallszahlen.aktuelleZeit();
        long sortierdauer2 = zufallszahlen.sortierdauer(startzeit2, endzeit2);
        System.out.println("Die Sortierung von 1.000.000 Elementen hat " + sortierdauer2 + " Millisekunden gedauert.");
        // zufallszahlen.printArray(array2);

        int[] array3 = zufallszahlen.arr3;
        long startzeit3 = zufallszahlen.aktuelleZeit();
        zufallszahlen.quickSort(array3, 0, (array3.length - 1));
        long endzeit3 = zufallszahlen.aktuelleZeit();
        long sortierdauer3 = zufallszahlen.sortierdauer(startzeit3, endzeit3);
        System.out.println("Die Sortierung von 5.000.000 Elementen hat " + sortierdauer3 + " Millisekunden gedauert.");
        // zufallszahlen.printArray(array3);

        long startzeit4 = zufallszahlen.aktuelleZeit();
        zufallszahlen.quickSort(array1, 0, (array1.length - 1));
        long endzeit4 = zufallszahlen.aktuelleZeit();
        long sortierdauer4 = zufallszahlen.sortierdauer(startzeit4, endzeit4);
        System.out.println("Die Sortierung von 100.000 [bereits sortierten] Elementen hat " + sortierdauer4 + " Millisekunden gedauert.");
        //zufallszahlen.printArray(array1);

        int[] array4 = zufallszahlen.arr1;
        long startzeit5 = zufallszahlen.aktuelleZeit();
        zufallszahlen.quickSortMedianOfThree(array4, 0, (array4.length - 1));
        long endzeit5 = zufallszahlen.aktuelleZeit();
        long sortierdauer5 = zufallszahlen.sortierdauer(startzeit5, endzeit5);
        System.out.println("Die Sortierung von 100.000 Elementen (mithilfe Median-Of-Three) hat " + sortierdauer5 + " Millisekunden gedauert.");
        // zufallszahlen.printArray(array4);

        int[] array5 = zufallszahlen.arr2;
        long startzeit6 = zufallszahlen.aktuelleZeit();
        zufallszahlen.quickSortMedianOfThree(array5, 0, (array5.length - 1));
        long endzeit6 = zufallszahlen.aktuelleZeit();
        long sortierdauer6 = zufallszahlen.sortierdauer(startzeit6, endzeit6);
        System.out.println("Die Sortierung von 1.000.000 Elementen (mithilfe Median-Of-Three) hat " + sortierdauer6 + " Millisekunden gedauert.");
        // zufallszahlen.printArray(array5);

        int[] array6 = zufallszahlen.arr3;
        long startzeit7 = zufallszahlen.aktuelleZeit();
        zufallszahlen.quickSortMedianOfThree(array6, 0, (array6.length-1));
        long endzeit7 = zufallszahlen.aktuelleZeit();
        long sortierdauer7 = zufallszahlen.sortierdauer(startzeit7, endzeit7);
        System.out.println("Die Sortierung von 5.000.000 Elementen (mithilfe Median-Of-Three) hat " + sortierdauer7 + " Millisekunden gedauert.");
        // zufallszahlen.printArray(array6);

        long startzeit8 = zufallszahlen.aktuelleZeit();
        zufallszahlen.quickSortMedianOfThree(array1, 0, (array1.length-1));
        long endzeit8 = zufallszahlen.aktuelleZeit();
        long sortierdauer8 = zufallszahlen.sortierdauer(startzeit8, endzeit8);
        System.out.println("Die Sortierung von 100.000 [bereits sortierten Elementen] (mithilfe Median-Of-Three) hat " + sortierdauer8 + " Millisekunden gedauert.");
        // zufallszahlen.printArray(array1);
    }
}
