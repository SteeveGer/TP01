package outils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * Lecture d'un fichier texte dans un String[]
 * Ecriture d'un String[] dans un fichier texte
 *
 * @author Peter DAEHNE - HEG-Genève
 * @version Version 1.0 - 01.10.2009
*/
public class FileStr {

  private static final int EOF = -1;

  /** Lecture d'un fichier texte dans un String[].
      @param fileName Le nom du fichier texte.
      @return le String[] contenant les lignes du fichier texte. */
  public static String[] read (String fileName) {
    try {
      FileInputStream f = new FileInputStream(fileName);
      StringBuffer b = new StringBuffer(f.available());
      int c = f.read();
      while (c != EOF) {
        b.append((char)c);
        c = f.read();
      }
      f.close();
      StringTokenizer sT = new StringTokenizer(b.toString(), "\r\n");
      int nbLig = sT.countTokens();
      String[] res = new String[nbLig];
      for (int k = 0; k < nbLig; k++) {res[k] = sT.nextToken();}
      return res;
    }
    catch (FileNotFoundException e0) {e0.printStackTrace(); return null;}
    catch (IOException e1) {e1.printStackTrace(); return null;}
  } // read

  /** Ecriture d'un String[] dans un fichier texte.
      @param fileName Le nom du fichier texte.
      @param str le String[] contenant les lignes à écrire. */
    public static void write (String fileName, String[] str) {
    try {
      OutputStreamWriter w = new OutputStreamWriter(new FileOutputStream(fileName), java.nio.charset.Charset.forName("ISO-8859-1"));
      for (int k = 0; k < str.length; k++) {
        w.write(str[k], 0, str[k].length());
        w.write("\r\n", 0, 2);
      }
      w.close();
    }
    catch (FileNotFoundException e0) {e0.printStackTrace();}
    catch (IOException e1) {e1.printStackTrace();}
  } // write

} // FileStr
