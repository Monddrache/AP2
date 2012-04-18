import java.io.*;


/**
 * Programm zum einlesen eines Arrays und ausgeben der Summe.
 * @author Olli van der Buerie
 * @version 510811bla
 *
 */
public class Arraysum {
	private static Bruch[] brArr;
	private static Bruch sum = new Bruch(0);
	private static int count;
	
	public int count(){
		return count;
	}
	
	/**
	 * Konstruktor
	 * 
	 * @param init Number of Bruch Elements
	 */
	public Arraysum(int init){
		count = init;
	};
	
	/**
	 * Reads a Line from Standard input
	 * 
	 * @param info
	 * @return String from Stdio
	 * @throws IOException
	 */

	public static String readSin(String info)throws IOException{
		InputStreamReader converter = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(converter);
		System.out.println(info);
		return in.readLine();
	}
	/**
	 * Reads an Integer from Stdio
	 * 
	 * @param info
	 * @return
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public static int readInt(String info) throws NumberFormatException, IOException{
		return Integer.parseInt(readSin(info));
	}

	/**
	 * Creates a new Array,<br>
	 * Reads string values from stdio,<br>
	 * Converts string into Bruch,<br>
	 * Adds Bruch to Array<br>
	 * 
	 * 
	 * @param ic ArrayList InitialLength
	 * @throws IOException 
	 * @throws NumberFormatException
	 * @return ArrayList
	 */
	
	public static Bruch[] createAl(int ic) throws IOException{
		Bruch bru[] = new Bruch[ic];
		for (int i = 0;i < ic;i++){
			String zw = readSin("Bitte geben Sie den naechsten Bruch ein: ");
			bru[i]=(Bruch.parseBruch(zw));
		}
		return bru;
	}
	/**
	 * Funktion um Brueche einzulesen und sortiert auszugeben
	 * 
	 * @param args
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public static void main(String[] args) throws NumberFormatException, IOException{
		// TODO: 0 Werte abfangen, Bruch exceptions abfangen
		if(count < 1)
			count = readInt("Wieviele Werte wollen Sie eingeben?: ");
		brArr = createAl(count);
		System.out.println("\nUnsortiertes Array: " +  java.util.Arrays.toString(brArr));
		java.util.Arrays.sort(brArr);
		System.out.println("Sortiertes Array: " + java.util.Arrays.toString(brArr) );
		for (Bruch bru: brArr){
			sum = sum.addiere(bru);
		}
		System.out.println("Summe: " + sum.toString());
		System.out.println("Mittelwert: " + sum.dividiere(new Bruch(count)));
	}

}
