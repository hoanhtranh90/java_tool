package org.me.word_pdf.utils;


import java.io.Closeable;

public class MethodUtils {

    public static void closeRessource(Closeable ressource) {
        try {
            if (ressource != null) {
                ressource.close();
                ressource = null;
            }
        } catch (final Exception e) {
        }
    }
}