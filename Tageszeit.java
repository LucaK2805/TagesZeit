public class Tageszeit {
    private int stunden;
    private int minuten;
    private int sekunden;
    private static boolean am_pm_Format = false;

    // Konstruktor mit allen drei Parametern
    public Tageszeit(int stunden, int minuten, int sekunden) {
        this.stunden = stunden;
        this.minuten = minuten;
        this.sekunden = sekunden;
    }

    // Konstruktor mit zwei Parametern (Stunden und Minuten), Sekunden auf 0 setzen
    public Tageszeit(int stunden, int minuten) {
        this(stunden, minuten, 0);
    }

    // Objektmethode, um die Anzahl der Sekunden seit Mitternacht zu berechnen
    public int sekundenseitMitternacht() {
        return (stunden * 3600) + (minuten * 60) + sekunden;
    }

    // Objektmethode, um zu überprüfen, ob es Vormittag ist
    public boolean vormittags() {
        return stunden < 12;
    }

    // Objektmethode zur Umwandlung der Tageszeit in einen String im Format "hh:mm:ss"
    public String toString() {
        String zeitformat = am_pm_Format ? "%02d:%02d:%02d %s" : "%02d:%02d:%02d";
        if (am_pm_Format) {
            String am_pm = stunden < 12 ? "AM" : "PM";
            return String.format(zeitformat, stunden % 12, minuten, sekunden, am_pm);
        } else {
            return String.format(zeitformat, stunden, minuten, sekunden);
        }
    }


    // Objektmethode zur Anpassung der Uhrzeit um die angegebene Anzahl von Stunden, Minuten und Sekunden
    public void vorstellen(int stunden, int minuten, int sekunden) {
        this.sekunden += sekunden;
        this.minuten += minuten + this.sekunden / 60;
        this.stunden += stunden + this.minuten / 60;
        this.sekunden %= 60;
        this.minuten %= 60;
        this.stunden %= 24;
    }

    // Objektmethode zur Überprüfung, ob die Tageszeit früher als eine andere Tageszeit ist
    public boolean istFrüherAls(Tageszeit andereZeit) {
        return this.sekundenseitMitternacht() < andereZeit.sekundenseitMitternacht();
    }

    // Statisches Attribut zur Festlegung des Zeitformats (am/pm oder 24-Stunden-Format)
    public static void setzeZeitformat(boolean am_pm) {
        am_pm_Format = am_pm;
    }

    public static void main(String[] args) {
        Tageszeit zeit1 = new Tageszeit(10, 30, 45);
        Tageszeit zeit2 = new Tageszeit(15, 20);

        // Testen der Methoden
        System.out.println("Sekunden seit Mitternacht: " + zeit1.sekundenseitMitternacht());
        System.out.println("Ist es vormittags? " + zeit1.vormittags());
        System.out.println("Tageszeit 1: " + zeit1.toString());
        System.out.println("Tageszeit 2: " + zeit2.toString());
        zeit1.vorstellen(2, 15, 30);
        System.out.println("Nach Vorstellen: " + zeit1.toString());
        System.out.println("Ist Zeit 1 früher als Zeit 2? " + zeit1.istFrüherAls(zeit2));

        // Testen des Zeitformats
        setzeZeitformat(true); // am/pm Format
        System.out.println("Tageszeit 1 (am/pm Format): " + zeit1.toString());
        setzeZeitformat(false); // 24-Stunden-Format
        System.out.println("Tageszeit 1 (24-Stunden-Format): " + zeit1.toString());
    }
}
