package Bierkopf;

import java.io.*;

//1 zu 1 vom Herold übernommen, jedoch alle Funktionen als static versehen
class Eingabe {
  public static char readChar(String ausgabe) {
    char c = 0;
    String zeile;
    boolean fehler = true;

    InputStreamReader input = new InputStreamReader(System.in);
    BufferedReader buffin = new BufferedReader(input);
    while (fehler) {
      try {
        System.out.print(ausgabe);
        zeile = buffin.readLine();
        c = zeile.charAt(0);
        fehler = false;
      } catch (IOException ioex) {
        System.err.println("Fehler bei der Eingabe");
      } catch (NumberFormatException nfex) {
        System.err.println("Fehler bei der Konvertierung");
      }
    }
    return c;
  }

  public static int readInt(String ausgabe) {
    int i = 0;
    String zeile;
    boolean fehler = true;

    InputStreamReader input = new InputStreamReader(System.in);
    BufferedReader buffin = new BufferedReader(input);
    while (fehler) {
      try {
        System.out.print(ausgabe);
        zeile = buffin.readLine();
        i = Integer.parseInt(zeile);
        fehler = false;
      } catch (IOException ioex) {
        System.err.println("Fehler bei der Eingabe");
      } catch (NumberFormatException nfex) {
        System.err.println("Fehler bei der Konvertierung");
      }
    }
    return i;
  }

  public static float readFloat(String ausgabe) {
    float f = 0;
    String zeile;
    boolean fehler = true;

    InputStreamReader input = new InputStreamReader(System.in);
    BufferedReader buffin = new BufferedReader(input);
    while (fehler) {
      try {
        System.out.print(ausgabe);
        zeile = buffin.readLine();
        f = Float.parseFloat(zeile);
        fehler = false;
      } catch (IOException ioex) {
        System.err.println("Fehler bei der Eingabe");
      } catch (NumberFormatException nfex) {
        System.err.println("Fehler bei der Konvertierung");
      }
    }
    return f;
  }

  public static double readDouble(String ausgabe) {
    double d = 0;
    String zeile;
    boolean fehler = true;

    InputStreamReader input = new InputStreamReader(System.in);
    BufferedReader buffin = new BufferedReader(input);
    while (fehler) {
      try {
        System.out.print(ausgabe);
        zeile = buffin.readLine();
        d = Double.parseDouble(zeile);
        fehler = false;
      } catch (IOException ioex) {
        System.err.println("Fehler bei der Eingabe");
      } catch (NumberFormatException nfex) {
        System.err.println("Fehler bei der Konvertierung");
      }
    }
    return d;
  }

  public static String readLine(String ausgabe) {
    String zeile = "";
    boolean fehler = true;

    InputStreamReader input = new InputStreamReader(System.in);
    BufferedReader buffin = new BufferedReader(input);
    while (fehler) {
      try {
        System.out.print(ausgabe);
        zeile = buffin.readLine();
        fehler = false;
      } catch (IOException ioex) {
        System.err.println("Fehler bei der Eingabe");
      } catch (NumberFormatException nfex) {
        System.err.println("Fehler bei der Konvertierung");
      }
    }
    return zeile;
  }

}
