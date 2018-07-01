package ua.mk.par.elibrary.utils;

import java.io.Serializable;

public class RandomName implements Serializable {
    public static String getRandCapitalLetter() {
        return String.valueOf((char) (65 + 25 * Math.random()));
    }

    public static String getRandLetter() {
        return String.valueOf((char) (97 + 25 * Math.random()));
    }

    public static String getRandName(int chars) {
        int ln = (int) (1 + chars * Math.random());
        String s = getRandCapitalLetter();
        for (int i = 1; i <= ln; i++) {
            s = s + getRandLetter();
        }
        return s;
    }

    public static String getRandTitle(int chars, int words) {
        int lt = (int) (1 + words * Math.random());
        String s = "";
        for (int i = 1; i <= lt; i++) {
            s = s + getRandName(chars) + " ";
        }
        return s;
    }

    public static int getRandScore() {
        return (int) (9 * Math.random() + 1);
    }

}
