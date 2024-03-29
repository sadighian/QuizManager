package fr.epita.quiz.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

import fr.epita.quiz.datamodel.MultipleChoice;
import fr.epita.quiz.datamodel.Open;
import fr.epita.quiz.datamodel.Question;


/** Class to export a quiz to plain text
 * @author Jonathan Sadighian and Rhea Moubarak
 *
 */
public class Exporter {

	private PrintWriter writer;
	private Scanner scanner;
	private File file;

	/** Default constructor. Create a new Exporter instance.
	 * @param path (String) file path to the file being exported, including the filename itself
	 * @throws IOException Unable to find file
	 */
	public Exporter(String path) {
		try {
			File file = new File(path);
			if (!file.exists()) {
				file.createNewFile();
			}
			this.setFile(file);
			this.writer = new PrintWriter(new FileOutputStream(this.getFile(), false));
			this.setScanner(new Scanner(this.getFile()));
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/** Export a completed quiz to plain text. Implemented in the GUI.
	 * @param quiz (Quiz) Completed quiz
	 * @param includeSummary (Boolean) If TRUE, output will include student name and grade
	 */
	public void exportAll(Quiz quiz, Boolean includeSummary) {
		if (includeSummary.booleanValue()) {
			this.write("Student", quiz.getStudent().getName());
			this.write("Correct", quiz.getGrade());
			this.write("Total Questions", quiz.getTotalQuestions());
			this.write("Topics", quiz.getTopics().toString());
		}
		
		for (Question question : quiz.getUsedQuestions()) {
			this.writeQuestion(question);
		}
	}

	/** Write a single question to a file
	 * @param question (MultipleChoice) question to write to text
	 */
	public void writeQuestion(Question question) {
		
		if (question.getClass() == MultipleChoice.class) {
			writer.println("-------Question #" + question.getNumber() + "-------");
			writer.println(question.getQuestion());
			writer.println("");
			writer.println("A) " + question.getOptions().get(0));
			writer.println("B) " + question.getOptions().get(1));
			writer.println("C) " + question.getOptions().get(2));
			writer.println("D) " + question.getOptions().get(3));
			writer.println("");
			writer.flush();
		}
		else if (question.getClass() == Open.class) {
			writer.println("-------Question #" + question.getNumber() + "-------");
			writer.println(question.getQuestion());
			writer.println("");
			writer.println("(write your response below)");
			writer.println("   ");
			writer.println("   ");
			writer.println("   ");
			writer.println("");
			writer.flush();
		}
	}
	
	/** Write a key-value styled comment in plain text export
	 * @param msg (String) key label
	 * @param num (int) value label
	 */
	public void write(String msg, int num) {
		writer.println("");
		writer.println(msg + " : " + num);
		writer.flush();
	}
	
	/** Write a key-value styled comment in plain text export
	 * @param msg (String) key label
	 * @param num (String) value label
	 */
	public void write(String msg, String num) {
		writer.println("");
		writer.println(msg + " : " + num);
		writer.flush();
	}
	
	/** Write a key-value styled comment in plain text export
	 * @param msg (String) key label
	 * @param num (double) value label
	 */
	public void write(String msg, double num) {
		writer.println("");
		writer.println(msg + " : " + num);
		writer.flush();
	}

	/** Getter for Scanner object used by class
	 * @return (Scanner) Scanner object used by class
	 */
	public Scanner getScanner() {
		return scanner;
	}

	/** Setter for Scanner object used by class
	 * @param scanner (Scanner) Scanner object to store in class
	 */
	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

	/** Getter for File object used by class
	 * @return (File) File object used by class
	 */
	public File getFile() {
		return file;
	}

	/** Setter for File object used by class
	 * @param file (File) File object to be used by class
	 */
	public void setFile(File file) {
		this.file = file;
	}

}
