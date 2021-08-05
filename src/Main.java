/**
 * Program that generates random genomes of desired length and
 * finds the most frequent nucleotides in them
 * @author Arda Serdar Pektezol
 * @since 05/08/2021
 */

import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<String> genomesList = new ArrayList<>();
        System.out.println("These are random genomes generated:\n");
        for (int i = 0; i < 5; i++) {
            genomesList.add(produceRandomGenome(10));
            System.out.println(genomesList.get(i));
        }
        System.out.println("\nThis is the dictionary containing all of the random genomes along with the most frequent nucleotides in them:");
        System.out.println(frequentNucleotidesInGenomes(genomesList, 3));
    }

    /**
     *
     * @param genomesList List of string with genomes
     * @param lengthOfNucleotide How long the genome is
     * @return HashMap dictionary of the most frequent nucleotides with the same value over the entire genome list
     */
    private static HashMap<String, HashMap<String, Integer>> frequentNucleotidesInGenomes (List<String> genomesList, int lengthOfNucleotide) {
        HashMap<String, HashMap<String, Integer>> dictionary = new HashMap<>();
        for (String genome : genomesList) {
            dictionary.put(genome, frequentNucleotides(genome, lengthOfNucleotide));
        }
        return dictionary;
    }

    /**
     * Iterates over the nucleotide dictionary in order to get the maximum value
     * Removes all keys that do not have the maximum value
     * @param genome Pure string of undivided genome
     * @param lengthOfNucleotide How long the genome is
     * @return HashMap dictionary of the most frequent nucleotides with the same value
     */
    private static HashMap<String, Integer> frequentNucleotides (String genome, int lengthOfNucleotide) {
        HashMap<String, Integer> dictionary = prevalenceDictionary(genome, lengthOfNucleotide);
        int maxValue = 0;
        for (HashMap.Entry<String, Integer> pair : dictionary.entrySet()) {
            int value = pair.getValue();
            if (value > maxValue) {
                maxValue = value;
            }
        }
        int finalMaxValue = maxValue;
        dictionary.entrySet().removeIf(pair -> !pair.getValue().equals(finalMaxValue));
        return dictionary;
    }

    /**
     * Iterates over the entire genome, with the length of nucleotide in mind,
     * to put each nucleotide into dictionary OR increases the value if said
     * nucleotide is already in the dictionary
     * @param genome Pure string of undivided genome
     * @param lengthOfNucleotide How long the genome is
     * @return HashMap dictionary of all divided nucleotides from genome
     */
    private static HashMap<String, Integer> prevalenceDictionary(String genome, int lengthOfNucleotide) {
        HashMap<String, Integer> dictionary = new HashMap<>();
        for (int i = 0; i < genome.length() - lengthOfNucleotide + 1; i++) {
            String currentNucleotide = genome.substring(i, i + lengthOfNucleotide);
            int value = dictionary.getOrDefault(currentNucleotide, 0);
            dictionary.put(currentNucleotide, value + 1);
        }
        return dictionary;
    }

    /**
     * Adds a random base to the genome string until desired length is reached
     * @param lengthOfGenome How long the genome will be
     * @return String genome
     */
    private static String produceRandomGenome(int lengthOfGenome) {
        Random rng = new Random();
        char[] bases = {'A', 'T', 'C', 'G'};
        StringBuilder genome = new StringBuilder();
        for (int i = 0; i < lengthOfGenome; i++) {
            genome.append(bases[rng.nextInt(4)]);
        }
        return genome.toString();
    }

}