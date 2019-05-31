package Model.Highway;

//Autostrada - sklada się z dwoch jezdni

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Highway {
    public Road[] roads;
    private int numberOfSegments = 17;

    public static List<Integer> carsOnSegment = new ArrayList<>(Collections.nCopies(17, 0));
    public static List<Integer> segmentsLen = Arrays.asList(191, 434, 371, 481, 611, 271, 776, 454, 345, 413, 532, 612, 666, 626, 665, 306, 599);
    public static ArrayList<Integer> segmentsByCell = new ArrayList<>(8353);
    public static ArrayList<Integer> startOfSegments = new ArrayList<>(Collections.nCopies(17, 0));
    public static String[] segmentsNames = {"Balice", "Balice2", "Modlniczka", "Modlnica", "Zielonki", "Węgrzce", "Kr. Mistrzejowice", "Kr. Grębałów", "Kr. Nowa Huta", "Kr. Przewóz", "Kr. Bieżanów", "Kr. Wieliczka", "Kr. Łagiewniki", "Kr. Południe", "Kr. Skawina", "Kr. Tyniec", "Kr. Bielany"};

    public Highway(int highwayWidth) {
        roads = new Road[highwayWidth];
        for (int i = 0; i < highwayWidth; i++) roads[i] = new Road(3);
        setupSegments();
        setupSegmentsStarts();
    }

    public void setupHighway() {
        //Ustawianie wjazdow i zjazdow
        roads[0].road[2].setupEntryOneWay();
        roads[0].road[2].setupExitOneWay();
        roads[1].road[2].setupEntryOtherWay();
        roads[1].road[2].setupExitOtherWay();
        //Ustawianie wylaczonych z ruchu kratek pomiedzy zjazdami
        roads[0].road[2].setupDisabled();
        roads[1].road[2].setupDisabled();
        //Ustawianie normalnych kratek na wewnetrznych pasach
        roads[0].road[1].setupNormal();
        roads[0].road[0].setupNormal();
        roads[1].road[1].setupNormal();
        roads[1].road[0].setupNormal();
    }

    public void setupSegments() {
        for (int i = 0; i < segmentsLen.size(); ++i) {
            for (int j = 0; j < segmentsLen.get(i); ++j) {
                segmentsByCell.add(i);
            }
        }
    }

    public void setupSegmentsStarts() {
        for (int i = 1; i < segmentsLen.size(); ++i) {
            startOfSegments.set(i, startOfSegments.get(i - 1) + segmentsLen.get(i-1));
        }
        System.out.print(startOfSegments);
    }

    public static void resetNumbersOfCarOnSegments() {
        for (int number = 0; number < carsOnSegment.size(); ++number) {
            carsOnSegment.set(number, 0);
        }
    }
}