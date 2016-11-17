/*
 * 
 * @author aaronjl2
 */
public class DecodeCaesar {

	public static void main(String[] args) {

		//Extra challenge (not graded - just for fun)
		//Create a new program DecodeCaesar.java that tries all 25 possible shift to automatically decrypt a message.
		//Either print all possible shifts, or only the ones where the frequency of the vowels a and e are above some threshold.

		String source = "";

		//Entering source text
		TextIO.putln("Please enter the source text (press enter to exit)");
		source = TextIO.getln();
		source = source.trim();

		//Checking if the user wants to exit
		if (source.equals("")) {
			TextIO.put("Bye.");
			System.exit(0);
		}
		//If there is input, print the source
		else {
			TextIO.putln("Cyphertext  : " + source);
		}
		source = source.toUpperCase();
		
		char []sourceText = new char[source.length()];
		int []unicode = new int[source.length()];
		int []unicodeCopy = new int[source.length()];
		String hex;
		int dec = 0;
		int count;

		//Moves source text into array
		for(count = 0; count < source.length(); count++) {
			sourceText[count] = source.charAt(count);
		}

		//Go through sourceText and translate each letter into unicode value.
		for(count = 0; count < sourceText.length; count++) {
			//Convert to hex and then to decimal representation of char
			hex = Integer.toHexString(sourceText[count]);
			dec = Integer.parseInt(hex, 16);
			unicode[count] = dec;
			unicodeCopy[count] = dec;
		}

		for (int e = 1; e <= 25; e++) {
			smartShift(e, unicode, unicodeCopy);
		}
	}

	public static void smartShift(int input, int[]unicode, int[]unicodeCopy) {

		//Create a new program DecodeCaesar.java that tries all 25 possible shift to automatically decrypt a message.
		//Either print all possible shifts, or only the ones where the frequency of the vowels a and e are above some threshold.

		//Copy over values to preserve them
		for (int x = 0; x <= unicode.length - 1; x++) {
			unicodeCopy[x] = unicode[x];
			//Shift and recovery
			if (unicode[x] >= 65 && unicode[x] <= 90) {
				unicodeCopy[x] += input;
				if (unicodeCopy[x] > 90) {
					unicodeCopy[x] -= 26;
				}
			}
		}
		String [] processed = new String[unicode.length];
		char [] finalProcess = new char[unicode.length];

		//Converts decimal to hex string
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

		//Analyzes array to count frequency of vowels
		for(int count = 0; count < finalProcess.length; count++) {
			if (finalProcess[count] == 'A') {
				aTotal++;
			}
			if (finalProcess[count] == 'E') {
				eTotal++;
			}
			if (finalProcess[count] == 'I') {
				iTotal++;
			}
			if (finalProcess[count] == 'O') {
				oTotal++;
			}
			if (finalProcess[count] == 'U') {
				uTotal++;
			}
			total++;
		}
		//Frequency calculations
		String finalCrypto = "";
		for (int z = 0; z < finalProcess.length; z++) {
			finalCrypto += finalProcess[z];
		}
		if (eTotal/total >= 0.05 || aTotal/total >= 0.05 || iTotal/total >= 0.05 || oTotal/total >= 0.05 || uTotal/total >= 0.05) {
			TextIO.putln();
			TextIO.putln("Potential: " + finalCrypto);
			TextIO.putln("A Pctg: " + aTotal/total);
			TextIO.putln("E Pctg: " + eTotal/total);
			TextIO.putln("I Pctg: " + iTotal/total);
			TextIO.putln("O Pctg: " + oTotal/total);
			TextIO.putln("U Pctg: " + uTotal/total);
		}
		else {
			TextIO.putln();
			TextIO.putln("NOT Potential: " + finalCrypto);
		}
	}
}