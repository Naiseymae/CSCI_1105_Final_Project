# CSCI_1105_Final_Project

## Synopsis

This is the final project code for the Intro to Programming course.  With it a user inputs RNA nucleotide base characters and the program will show the corresponding amino acid sequence.  Then that base sequence is randomly mutated, and a comparision between the original base and amino acid sequences and the newly mutated base and amino acid sequences is displayed.  

## Code Example

The following code will concat 3 bases from the original sequence to make a codon. The loop assigns 3 characters of 3 indices in base sequence to one index in a new codon array, where i = codonSequence index and j = baseSequence index. It will only create a codon if there are 3 bases available to concat from the original sequence.

```java
		for (int i = 0, j = 0; i < codonSequence.length; i++, j+=3) {  
			if (baseSequence[j] != "" && baseSequence[j+1] != "" && baseSequence[j+2] != "") { 
				codonSequence[i] = baseSequence[j] + baseSequence[j+1] + baseSequence[j+2];
			}
		}
   ```
   
## Motivation

This program was made to show how random mutations in the RNA base sequence can affect the amino acid protein sequence. This program allows the user to see a side-by-side comparison of the original and mutated base sequences as well as the original and mutated amino acid sequences. 

## Tests

I ran tests for this program on Eclipse using JUnit4, which included tests on the following methods: toCodon(), toAminoAcid(), and mutate().

