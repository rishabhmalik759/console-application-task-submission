package com.rm.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class TripletDecoder implements Decoder {

	private boolean isFileProcessed = false;
	private Map<String, Triplet> mapTriplets;
	
	/*
	 * This methods generates the message from triplets loaded into memory. 
	 * It requires an initial text to start the processing.
	 * Message will not be extracted in case of any error 
	 * while processing a file, so error will be printed.
	 */
	@Override
	public String decode(String initial) {
		
		if (!isFileProcessed) {
			System.out.println("Error : File processing terminated abnormally, Unable to decode the message");
			return null;
		}

		if (initial == null) {
			System.out.println("Error : Initial is null, Unable to decode the message");
			return null;
		}

		String next = initial;
		if (!mapTriplets.containsKey(next)) {
			System.out.println("Error : Initial not found, Unable to decode the message");
			return null;
		}
		StringBuilder sb = new StringBuilder();

		while (mapTriplets.containsKey(next)) {
		
			Triplet triplet = mapTriplets.get(next);
			sb.append(triplet.getMessage());
			next = triplet.getNext();
		}
		
		return sb.toString();
	}
	
	/*
	 * Private parameterized constructor used 
	 * by launcher method (main()) only.
	 * It accepts File as an input, reads the file 
	 * and loads triplets into memory to extract the message.
	 * Message will not be extracted in case of 
	 * any error while processing a file.
	 */
	public TripletDecoder(File file) {

		mapTriplets = new HashMap<>();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));

			String line;
			while ((line = reader.readLine()) != null) {
				Triplet triplet = new Triplet(line);
				mapTriplets.put(triplet.getLeft(), triplet);
			}
			reader.close();
			isFileProcessed = true;
		} catch (Exception ex) {
			System.out.println("Error : "+ex.getMessage());
		}
	}

	/*
	 * Prints a help about the program
	 */
	private static void printUsage() {
		
		StringBuilder sb = new StringBuilder();

		sb.append(Constants.NEW_LINE);
		sb
			.append("--------------------------------------------------------------")
			.append(Constants.NEW_LINE);
		sb
			.append("Usage :").append(Constants.SPACE)
			.append(Thread.currentThread().getStackTrace()[1].getClassName()).append(Constants.SPACE)
			.append("<FILENAME>").append(Constants.SPACE)
			.append("<INITIAL>").append(Constants.NEW_LINE);
		sb
			.append("--------------------------------------------------------------")
			.append(Constants.NEW_LINE);			
		sb
			.append("<FILENAME>").append(Constants.TAB).append("File with full path containing decoded message")
			.append(Constants.NEW_LINE);
		sb
			.append("<INITIAL>").append(Constants.TAB).append("Initial triplet (3 characters text)").append(Constants.NEW_LINE);
		sb
			.append("--------------------------------------------------------------")
			.append(Constants.NEW_LINE);			
		sb.append(Constants.NEW_LINE);
		
		System.out.println(sb.toString());
	}
	
	/*
	 * Validates the provided arguments and 
	 * terminate the program with a message
	 * in case of invalid inputs
	 */
	private static void validateInputs(String... args) {

		int noOfArgs = args.length;
		
		System.out.println();
		if (noOfArgs != 2) {
			if (args.length < 2) {
				System.out.println("Error : Not enough arguments");
			} else if (args.length > 2) {
				System.out.println("Error : Too many arguments");
			}
			printUsage();
			System.exit(1);
		}

		String filename = args[0];
		File file = new File(filename);
		if (!file.exists()) {
			System.out.println("Error : File ("+filename+") doesn't exist");
			System.exit(1);
		}
		
		if (!file.isFile()) {
			System.out.println("Error : File ("+filename+") is a directory");
			System.exit(1);
		}
		
		String initial = args[1].trim();
		if (Constants.EMPTY.equals(initial) || initial.length() !=3 ) {
			System.out.println("Error : Invalid triplet, Triplet must be of 3 characters");
			System.exit(1);
		}
	}
	
	/*
	 * Launcher method - Entry point to the program
	 */
	public static void main(String... args) {

		validateInputs(args);
		
		String filename = args[0];
		System.out.println("Filename : "+filename);
		
		String initial = args[1].trim();
		System.out.println("Initial  : "+initial);
		
		File file = new File(args[0]);
		Decoder decoder = new TripletDecoder(file);
		String decodedText = decoder.decode(initial);
		
		System.out.println();
		
		System.out.println("Message : "+decodedText);
		System.out.println();
	}
}
