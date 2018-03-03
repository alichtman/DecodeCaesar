import java.util.Scanner;

public class DecodeCaesar {

	public static void main(String[] args) {

		/*
		  Tries all 25 possible shifts to produce a list of likely inputs to a caesar-cipher encoded message.
		  Displays the decryption possibility only when the frequency of any vowel is above 5%.
		 */

		//Entering source text
		System.out.println("Please enter caesar-cipher encoded text (press enter to exit)");
		System.out.println("List of decryption possibilities will be printed.\n-----");

		Scanner scanner = new Scanner(System.in);
		String source = scanner.nextLine().trim().toUpperCase();

		//Checking if the user wants to exit
		if (source.equals("")) {
			System.out.println("Bye.");
			System.exit(0);
		}

		char []sourceText = new char[source.length()];
		int []unicode = new int[source.length()];
		int []unicodeCopy = new int[source.length()];

		//Moves source text into array
		for (int count = 0; count < source.length(); count++) {
			sourceText[count] = source.charAt(count);
		}

		String hex;
		int dec;

		//Go through sourceText and translate each letter into unicode value.
		for (int count = 0; count < sourceText.length; count++) {
			//Convert to hex and then to decimal representation of char
			hex = Integer.toHexString(sourceText[count]);
			dec = Integer.parseInt(hex, 16);
			unicode[count] = dec;
			unicodeCopy[count] = dec;
		}

		for (int shift = 1; shift <= 25; shift++) {
			smartShift(shift, unicode, unicodeCopy);
		}
	}

	private static void smartShift(int shift, int[] unicode, int[] unicodeCopy) {

		// Preserve values
		for (int x = 0; x <= unicode.length - 1; x++) {
			unicodeCopy[x] = unicode[x];

			//Shift and recovery
			if (unicode[x] >= 65 && unicode[x] <= 90) {
				unicodeCopy[x] += shift;

				if (unicodeCopy[x] > 90) {
					unicodeCopy[x] -= 26;
				}
			}
		}

		String [] processed = new String[unicode.length];
		char [] finalProcess = new char[unicode.length];

		//Converts dec to hex string
		for (int count = 0; count < processed.length; count++) {
			processed[count] = Integer.toHexString(unicodeCopy[count]);

			//Convert the hex string into char:
			int hexToInt = Integer.parseInt(processed[count], 16);
			char intToChar = (char)hexToInt;
			finalProcess[count] = intToChar;
		}

		//Basic frequency calculations
		double total = 0;
		double aTotal = 0;
		double eTotal = 0;
		double iTotal = 0;
		double oTotal = 0;
		double uTotal = 0;

		//Analyze array to count frequency of vowels
		for (char c : finalProcess) {

			total++;

			switch(c) {
				case 'A':
					aTotal++;
					break;
				case 'E':
					eTotal++;
					break;
				case 'I':
					iTotal++;
					break;
				case 'O':
					oTotal++;
					break;
				case 'U':
					uTotal++;
					break;
				default:
					break;
			}
		}

		//Frequency calculations
		StringBuilder finalCrypto = new StringBuilder();

		for (char c : finalProcess) {
			finalCrypto.append(c);
		}

		if (eTotal / total >= 0.05 || aTotal / total >= 0.05 || iTotal / total >= 0.05 || oTotal / total >= 0.05 || uTotal / total >= 0.05) {
			System.out.println();
			System.out.println("\t" + finalCrypto);
			System.out.println("\t\tA Pct: " + aTotal / total);
			System.out.println("\t\tE Pct: " + eTotal / total);
			System.out.println("\t\tI Pct: " + iTotal / total);
			System.out.println("\t\tO Pct: " + oTotal / total);
			System.out.println("\t\tU Pct: " + uTotal / total);
//		} else {
//			System.out.println();
//			System.out.println("NOT Potential: " + finalCrypto);
		}
	}
}
