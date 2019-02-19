package fr.epita.quiz.tests;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import fr.epita.quiz.datamodel.MultipleChoice;
import fr.epita.quiz.datamodel.Quiz;
import fr.epita.quiz.datamodel.Student;
import fr.epita.quiz.services.Exporter;

public class TestExporter {
	
	public static void main(String[] args) throws IOException {
		// Instantiate Quiz class
		Exporter exporter = new Exporter("testExporter.txt");
		
		Student student = new Student("Thibault", "123");
		List<String> topics = new LinkedList<String>();
		topics.add("France");
		Quiz quiz = new Quiz(student, topics, 5);
		quiz.loadNewQuiz();
		
		// Take the quiz
		for (int i=0; i<quiz.getTotalQuestions(); i++) {
			MultipleChoice question = quiz.getNewQuestion(1);
			System.out.println("New Question: " + question.getQuestion());
			question.setChoice(quiz.getRandom().nextInt(4));
			quiz.processNewQuestion(question);
			
			exporter.writeQuestion(question);
			
			System.out.println("Progress ...  " + quiz.getProgress());
		}
		
		System.out.println("Quiz completed.");
		System.out.println("Score: " + quiz.getGrade());
		exporter.write("Score", quiz.getGrade());
		exporter.write("Percentage complete", quiz.getProgress());
	}
}