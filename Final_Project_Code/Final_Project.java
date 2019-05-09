package finalProjectTest;


/* 
* Author: Renee Linford
* Date: 5-6-19
* Intro to Programming Final Project: RNA strand to amino acid sequence, & RNA strand mutation.
* 
*/


import java.util.Scanner;


/** <h1> CSCI 1105 Final Project </h1>
* <p> In this program, the user inputs a string of RNA nucleotide bases and the program returns the original codon sequence, a randomly mutated codon sequence, and then prints each corresponding amino acid sequence.  Also prints total codons, number of point mutations, and amino acids changes. </p>
*
* <p> Created: 5-6-19 </p>
* 
* @author Renee Linford
*/


public class Final_Project {
	
	
	public static void main(String[] args) {
	/* User enters a string of at least 3 characters (A, C, G, U).  
	* Program calls method to convert to amino acid string.
	* Program calls method to introduce mutations to original string.
	* Prints number of point mutations and amino acids changes.
	* Prints original string & corresponding amino acid sequence, and
	* prints mutated string & corresponding amino acid sequence.
	* Program will not print amino acids after a STOP codon.
	*/
	
	
		// Prompt user for characters to be put into string array.
		System.out.print("Enter at least 3 nucleotide bases with spaces (i.e., A C G U C U): ");
		Scanner input = new Scanner(System.in);
		String line = input.nextLine();
		line = line.trim(); // Trim whitespace.
		line = line.toUpperCase(); // Change string to uppercase.	
		
		
		// Separate each character into base sequence array. 
		String [] baseSeq = line.split(" "); // Assign line to array "base sequence."
	
	
		// If line length is less than 3, prompt user for another entry.
		while (baseSeq.length < 3) {
			System.out.print("Enter more bases: ");
			String line2 = input.nextLine();
			line2 = line2.trim(); // Trim whitespace.
			line = line + " " + line2; // Concat new line with previous line.
			line = line.toUpperCase(); // Change string to uppercase.
			baseSeq= line.split(" "); // Assign characters to array.
		}
	
	
		// Check if sequence has characters other than A C G or U.  
		for (int c = 0; c < baseSeq.length; c++) {
			baseSeq[c] = baseSeq[c].toUpperCase();
			if (baseSeq[c].equals("A") || baseSeq[c].equals("C") || baseSeq[c].equals("G") || baseSeq[c].equals("U")) {
				continue;
			}
			else { // If invalid characters present, error displays and system exit.
				System.out.println();
				System.out.print("Error: invalid characters");
				System.out.println();
				System.exit(1);
			}
		}
			
			
		// Convert base sequence into codons.
		String [] codonArray = new String[baseSeq.length / 3];
		codonArray = toCodon(baseSeq); // Returns original codon array.
		System.out.println();
		System.out.println("Total codons: " + codonArray.length);
		System.out.println();

		
		// Call method to convert codons to amino acids. 
		String [] aminoAcidArray = new String[codonArray.length];
		aminoAcidArray = toAminoAcid(codonArray);
				
	
		// Call method to introduce mutations. Use user input base sequence.
		String [] mutatedSeq = new String [baseSeq.length];
		mutatedSeq = mutate(baseSeq); // Returns mutated base sequence.
		
	
		// Convert new mutated sequence into codons.
		String [] mutatedCodon = new String [mutatedSeq.length / 3];
		mutatedCodon = toCodon(mutatedSeq); // Returns mutated codon sequence.
		
			
		// Convert mutated codons to amino acids. 
		String [] mutatedAA = new String [mutatedCodon.length];
		mutatedAA = toAminoAcid(mutatedCodon);	// Returns mutated amino acid sequence.
		
		
		// Print original codon sequence.
		System.out.print("Original Codons seq:  ");
		for (int i = 0; i < codonArray.length; i++) {
			System.out.print(codonArray[i] + " ");
		}
		System.out.println();
		
		
		// Print mutated codon sequence.
		System.out.print("Mutated Codons seq:   ");
		for (int k = 0; k < mutatedCodon.length; k++) {
			System.out.print(mutatedCodon[k] + " ");
		}
		System.out.println();
		
		
		// Print original amino acids. Stop printing if find STOP codon.
		System.out.println();
		System.out.print("Original Amino Acids: ");
		for (int j = 0; j < aminoAcidArray.length; j++) {
			if (aminoAcidArray[j].equals("STOP")) { // Prints STOP codon but nothing after.
				System.out.print(aminoAcidArray[j] + " ");
				break;
			}
			else // Print amino acid.
				System.out.print(aminoAcidArray[j] + " ");
		}
		
		
		// Print amino acids in mutated array.
		System.out.println();
		System.out.print("Mutated Amino Acids:  ");
		for (int l = 0; l < mutatedAA.length; l++) {
			if (mutatedAA[l].equals("STOP")) { // Prints STOP codon but nothing after.
				System.out.print(mutatedAA[l] + " ");
				break;
			}
			else // Print amino acid.
				System.out.print(mutatedAA[l] + " ");
		}
		System.out.println();
		
		
		// Display number of point mutations by comparing original base seq to mutated seq.
		int pointMutations = 0;
		for (int m = 0; m < (mutatedSeq.length); m++) { // Do not compare extra codons.
			if (!baseSeq[m].equals(mutatedSeq[m])){ 
				pointMutations++; // If amino acid in original array and mutated array are different, count increases.
			}
		}
		System.out.println();
		System.out.println("Number of point mutations: " + pointMutations); // Print number of point mutations.
		
		
		// Display number of amino acid changes.
		int aaChanges = 0;
		for (int n = 0; n < (codonArray.length); n++) {
			if (aminoAcidArray[n] != mutatedAA[n]){ 
				aaChanges++; // If amino acid in original array and mutated array are different, count++.
			}
		}
		System.out.println("Number of amino acid changes: " + aaChanges); // Print number of amino acid changes.
		
	}
	
	
	
	/** This method is used to concat strings in an array. Method concats 3 indices at a time (i.e., array with index values of "A A A A A A" becomes "AAA AAA").  Best for changing a string array of RNA characters to an array of codons.
	* <pre>Examples: 
	* {@code	toCodon(baseSequence[]) returns codonSequence[]
	*} </pre>
	*
	* @param baseSequence (String []; an array of strings where each index is one character such as A, C, G, or U)
	* @return codonSequence (String []; an array of strings where each index is three characters such as AAA)
	*/
	
	
	public static String [] toCodon(String [] baseSequence) {
	/* Method takes array of single character strings and converts it to new string array where each index has three characters (i.e. a codon). */
		
		
		// Assign base sequence length to codonSeq array.
		int arrayLength = ((baseSequence.length)/3);
		String [] codonSequence = new String[arrayLength]; 
		
				
		// Loop assigns 3 characters of 3 indices in base sequence to one index in a new codon array.
		for (int i = 0, j = 0; i < codonSequence.length; i++, j+=3) { // Where i = codonSeq array index, & j = sequence string index.
			if (baseSequence[j] != "" && baseSequence[j+1] != "" && baseSequence[j+2] != "") { 
				// Only concat 3 character codon if there are 3 characters in sequence to use.
				codonSequence[i] = baseSequence[j] + baseSequence[j+1] + baseSequence[j+2];
			}
		}

		
		return codonSequence;
	
	}
	
	
	
	/**
	* This method is used to change an array of codons to an array of amino acids. Each index of the codon array is matched to its corresponding amino acid, and that amino acid is copied to a new array. Strings should be in uppercase.
	*
	* <pre>Example:
	* {@code	toAminoAcid(codonSequence[]) returns aminoAcidSequence[]
	*}</pre>
	*
	* @param codonSeq (String []; a string array where each index is three characters long, such as AAA or UGC.)
	* @return aaSeq (String []; a string array where each index is a string representing an amino acid, such as LYS for the codon AAA, or CYS for the codon UGC.)
	*/
	
	
	public static String [] toAminoAcid(String [] codonSeq) {
	/* Method takes array of codons (indices of 3 characters) and compares to table of amino acids.
	* Returns string array with indices of amino acids (i.e., UCC returns SER, UAA returns STOP). */
	
	
		// Create amino acid table. Includes Stop codons.
		String [][] aaTable = { // [64][2]
			{"UUU", "PHE"}, {"UUC", "PHE"}, {"UUA", "LEU"}, {"UUG", "LEU"}, {"CUU", "LEU"}, {"CUC", "LEU"}, {"CUA", "LEU"}, {"CUG", "LEU"}, 
			{"AUU", "ILE"}, {"AUC", "ILE"}, {"AUA", "ILE"}, {"AUG", "MET"}, {"GUU", "VAL"}, {"GUC", "VAL"}, {"GUA", "VAL"}, {"GUG", "VAL"},
			{"UCU", "SER"}, {"UCC", "SER"}, {"UCA", "SER"}, {"UCG", "SER"}, {"CCU", "PRO"}, {"CCC", "PRO"}, {"CCA", "PRO"}, {"CCG", "PRO"}, 
			{"ACU", "THR"}, {"ACC", "THR"}, {"ACA", "THR"}, {"ACG", "THR"}, {"GCU", "ALA"}, {"GCC", "ALA"}, {"GCA", "ALA"}, {"GCG", "ALA"},
			{"UAU", "TYR"}, {"UAC", "TYR"}, {"UAA", "STOP"}, {"UAG", "STOP"}, {"CAU", "HIS"}, {"CAC", "HIS"}, {"CAA", "GLN"}, {"CAG", "GLN"}, 
			{"AAU", "ASN"}, {"AAC", "ASN"}, {"AAA", "LYS"}, {"AAG", "LYS"}, {"GAU", "ASP"}, {"GAC", "ASP"}, {"GAA", "GLU"}, {"GAG", "GLU"},
			{"UGU", "CYS"}, {"UGC", "CYS"}, {"UGA", "STOP"}, {"UGG", "TRP"}, {"CGU", "ARG"}, {"CGC", "ARG"}, {"CGA", "ARG"}, {"CGG", "ARG"}, 
			{"AGU", "SER"}, {"AGC", "SER"}, {"AGA", "ARG"}, {"AGG", "ARG"}, {"GGU", "GLY"}, {"GGC", "GLY"}, {"GGA", "GLY"}, {"GGG", "GLY"},
				};
		
		
		// Compare codon sequence to amino acid table. Place matching amino acids into new array.
		
		String [] aaSeq = new String [codonSeq.length]; // New amino acid sequence array.
		
		for (int i = 0; i < aaSeq.length; i++) { // Loop compares codon to aaTable.
			for (int j = 0; j < 64; j++) { 
				if (codonSeq[i].equals(aaTable[j][0])) {  
					// If find matching codon, put corresponding amino acid into new array.
					aaSeq[i] = aaTable [j][1];
				}	
			}
		}
		
		// Return amino acid sequence array. (Includes STOP codons.)
		return aaSeq;
	
	}
	
	
	
	/** This method takes a string array containing indices of single characters (A, C, G, or U), and randomly selects a random number of indices to change.  It will change the index character to a randomly selected A, C, G or U character.  Returns mutated array containgin indices of single characters (A, C, G, or U).
	*
	* <pre>Example:
	*{@code	mutate(sequence[]) returns mutatedSequence[]
	*}</pre>
	*
	* @param sequence (String []; a string array of single base characters to be randomly altered.)
	* @return mutatedSeq (String []; a string array of single base characters with indices randomly changed.) 
	*/
	
	
	public static String [] mutate(String [] sequence) {
	/* Method will randomly mutate contents of a random number of indices in a string array, and return a randomly mutated string array.
	* Needs single-dimension array containing one character per index. */	
		
		
		// Copy original sequence to new array.
		String [] mutatedSeq = new String [sequence.length];
		for (int i = 0; i < mutatedSeq.length; i++) {
			mutatedSeq[i] = sequence[i];
		}
		
		
		// Randomly selects total number of mutations.
		int numberOfMutations = (int)(Math.random() * sequence.length);
	

		// Randomly select index from new array.
		for (int j = 0; j < numberOfMutations; j++) {
			int randomIndex = (int)(Math.random() * sequence.length);
			//System.out.println("j is " + j + " and randomIndex is " + randomIndex);
			
			// Select random base for replacement.
			String randomBase = Integer.toString((int)(Math.random() * 4));
			
			if (randomBase.equals("0")) { // If random number is 0, mutation is base A.
				randomBase = "A";
			}
			if (randomBase.equals("1")) { // If random number is 1, mutation is base C.
				randomBase = "C";
			}
			if (randomBase.equals("2")) { // If random number is 2, mutation is base G.
				randomBase = "G";
			}
			if (randomBase.equals("3")) { // If random number is 3, mutation is base U.
				randomBase = "U";
			}
			
			// Mutate random index to random nucleotide base (A, C, G, U).
			mutatedSeq[randomIndex] = randomBase;
		}
			
		
		// Return mutated array. 
		return mutatedSeq;
		
	}
	
	
}