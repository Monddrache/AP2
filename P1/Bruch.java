/**
 * Die Klasse implementiert unveraenderliche Brueche.
 * <p>
 * Brueche sind in gekuerzter
 * Form gespeichert. Nur der Zaehler kann 0 oder negativ sein.
 * <p>
 * Die Aufrufe sind von der Form
 * 
 * <pre>
 * linkerOperand.Operator(rechterOperand)
 * bruch.Operation()
 * </pre>
 * 
 * Bruch Objekte werden niemals veraendert.
 * <p>
 * Beispiele:
 * <pre>
 * Bruch r0 = new Bruch(2);
 * Bruch r1 = new Bruch(3, 4).addiere(r0);
 * Bruch r2 = r1.invertiere();
 * if (r1.equals(r2)) ...
 * if (r1.compareTo(r2) < 0) ...
 * System.out.println(r2);
 * </pre>
 * 
 */
public final class Bruch implements Comparable<Bruch> {
    private final long zaehler;
    private final long nenner;
    
    /**
     * Die Konstante 0.
     */
    public static final Bruch ZERO = new Bruch(0);
    
    /**
     * Die Konstante 1.
     */
    public static final Bruch ONE = new Bruch(1, 1);

    /**
     * Diese Methode ueberschreibt die entsprechende Methode aus
     * <code>java.lang.Object</code> indem sie die Werte zweier Brueche
     * vergleicht. In dem Vergleich <code>a.equals(b)</code> kann <code>b</code>
     * fuer eine beliebige Objektreferenz stehen. Das Ergebnis ist genau dann
     * <code>true</code> wenn das Argument einen Bruch mit gleichem Wert
     * darstellt.
     * 
     * @param einObjekt das Objekt mit dem <code>this</code> verglichen wird.
     * @return <code>true</code> wenn die Werte gleich sind.
     */
    @Override
    public boolean equals(Object einObjekt) {
        if (einObjekt instanceof Bruch) {
        	Bruch einBruch = (Bruch) einObjekt; // Was bedeutet das??
        	// Das bedeutet: Wenn das übergeben Onjekt eine Instanz der Klasse Bruch ist,
        	// wird ein expliziter TypeCast "Bruch" auf das Object ausgeführt
        	if((this.zaehler == einBruch.zaehler) && (this.nenner == einBruch.nenner)){
        		return true;
        	};
        }
        return false;
    }

    /**
     * Erzeugt aus einem String den dadurch beschriebenen Bruch.
     * Es wird vorausgesetzt, dass der Bruch formal korrekt geschrieben ist.
     * Insbesondere sind innerhalb des Bruchs nur Ziffern, Vorzeichen und
     * der Bruchstrich erlaubt. Wenn der Bruchstrich fehlt, wird von einer
     * ganzen Zahl ausgegangen
     * <p>
     * Die Ausgabe von <code>toString</code> ist in jedem Fall eine erlaubte
     * Eingabe.
     * 
     * @param s Stringdarstellung des Bruches
     * @return erzeugter Bruch
     * @throws NumberFormatException wenn der String keinen Bruch darstellt
     */
    public static Bruch parseBruch(String s) {
    	String[] parts = s.split("/");
    	return new Bruch(
        		Long.parseLong(parts[0]),
        		parts.length == 1 ? 1 : Long.parseLong(parts[1]));
    	
    }

    /**
     * Erzeugt aus einer ganzen Zahl einen Bruch.
     * 
     * @param zahl als Bruch darzustellende Zahl
     * 
     * oder
     * 
     * @param zaehler des Bruchs
     * @param nenner des Bruchs
     */
    public Bruch(long zahl) {
        this.zaehler = zahl;
        this.nenner = 1;
    }
    
    public Bruch(long zaehler, long nenner) {
    	if (nenner == 0){
    		throw new ArithmeticException();
    	};
    	long teiler = ggt(zaehler, nenner);
    	long nen = (long)Math.sqrt((nenner/teiler)*(nenner/teiler));
    	long zae = (long)Math.sqrt((zaehler/teiler)*(zaehler/teiler));
    	if((zaehler < 0 && nenner < 0)||(zaehler > 0 && nenner > 0)){
    		this.zaehler = zae;
            this.nenner = nen;
    	}else if (zaehler > 0 && nenner < 0){
    		this.zaehler = zae * -1;
    		this.nenner = nen;
    	}else{
    		this.zaehler = zae * -1;
            this.nenner = nen;
    	};
    }

    /**
     * Berechnet den groessten gemeinsamen Teiler zweier <b>positiver</b>
     * Zahlen.
     * 
     * @param a 1. Zahl
     * @param b 2. Zahl
     * @return groesster gemeinsamer Teiler
     */
    private static long ggt(long a, long b) {
        return b != 0 ? ggt(b, a % b) : a;
    }
        
    /**
     * Addiert zwei Brueche.
     * 
     * @param b zweiter Operand
     * @return Ergebnisbruch
     */
    public Bruch addiere(Bruch b) {
        return new Bruch(zaehler * b.nenner + nenner * b.zaehler,
                nenner * b.nenner);
    }

    /**
     * Subtrahiert zwei Brueche.
     * 
     * @param b zweiter Operand
     * @return Ergebnisbruch
     */
    public Bruch subtrahiere(Bruch b) {
        return new Bruch(zaehler * b.nenner - nenner * b.zaehler,
                nenner * b.nenner);
    }

    /* UNBEDINGT: auch JavaDoc schreiben --- das gilt ueberall! */
    /**
     * Multipliziert zwei Brueche.
     * 
     * @param b zweiter Operand
     * @return Ergenbisbruch
     */
    public Bruch multipliziere(Bruch b) {
    	return new Bruch(zaehler * b.zaehler, nenner * b.nenner);
    }
    
    /**
     * Dividiert zwei Brueche.
     * 
     * @param bruch2 zweiter Operand
     * @return Ergenbisbruch
     */
    public Bruch dividiere(Bruch bruch2) {
        return new Bruch(zaehler * bruch2.nenner, bruch2.zaehler * nenner);
    }
    
    /**
     * Gibt den Kehrwert des Bruchs zurück.
     * 
     * @return Kehrwert
     */
    public Bruch kehrwert() {
    	return new Bruch(nenner, zaehler);
    }

    /**
     * Gibt den Zaehler des Bruchs in gekuerzter Form zurueck. Wenn der Bruch
     * negativ ist, ist auch der Zaehler negativ.
     * 
     * @return Wert des Zaehlers
     */
    public long zaehler() {
        return zaehler;
    }

    /**
     * Gibt den Nenner des Bruchs in gekuerzter Form zurueck. Der Nenner ist
     * immer positiv.
     * 
     * @return Wert des Nenners
     */
    public long nenner() {
        return nenner;
    }

    /**
     * Gibt den Wert des Bruchs als Gleitkommazahl zurueck.
     * 
     * @return Wert des Bruchs
     */
    public double doubleValue() {
        return (double) zaehler / (double) nenner;
    }

    /**
     * Gibt eine Stringdarstellung des Bruchs in gekuerzter Form zurueck. Das
     * negative Vorzeichen steht vor dem Zaehler. Ganze Zahlen werden ohne
     * Bruchstrich zurueckgegeben.
     * <p>
     * Beispiele:
     * 
     * <pre>
     * 2/3, -2/3, 0, 10
     * </pre>
     */
    @Override
    public String toString() {
        return (nenner != 1)? zaehler + "/" + nenner: String.valueOf(zaehler);
    }

    @Override
    public int hashCode() {
        return (int)(zaehler + nenner);
    }

    /**
     * Diese Methode implementiert den von der Schnittstelle
     * <code>java.lang.Comparable</code> geforderten Vergleich der Groesse
     * zweier Brueche. Der Aufruf:
     * 
     * <pre>
     * a.compareTo(b)
     * </pre>
     * 
     * ist definiert fuer zwei Brueche <code>a</code> und <code>b</code>. Das
     * Ergebnis ist positiv, wenn <code>a > b</code> gilt, negativ wenn
     * <code>a < b</code> und 0, wenn die beiden Brueche gleich sind.
     * <p>
     * Aufrufe koennen wie folgt aussehen:
     * 
     * <pre>
     * if (a.compareTo(b) >= 0) // wenn a >= b
     * if (a.compareTo(b) == 0) // wenn a == b
     * </pre>
     * 
     * @param einBruch der zweite Bruch
     * @return positive, negative Zahl oder 0 wenn das aufrufende Objekt
     *         groesser, kleiner oder gleich dem Argument ist
     */
    public int compareTo(Bruch einBruch) {
    	double ai = doubleValue();
        double bi = (double)einBruch.zaehler / (double)einBruch.nenner;
        if(ai > bi){
        	return 1;
        }else if(ai < bi){
        	return -1;
        }else if(ai == bi){
    		return 0;
        }else{
        	return 0;
        }
    }
}