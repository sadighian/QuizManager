package fr.epita.quiz.tests;


import java.util.LinkedList;
import java.util.List;

import fr.epita.quiz.datamodel.MultipleChoice;
import fr.epita.quiz.services.QuestionJDBCDAO;


public class TestDAO {

	public static void main(String[] args) {
		
		// Instantiate data access object
		QuestionJDBCDAO dao = new QuestionJDBCDAO();
		
		/*	CREATE method Test 		*/
		// Create question to insert
		MultipleChoice question = new MultipleChoice();
		question.setQuestion("What is my favorite color?");
		question.setDifficulty(3);
		LinkedList<String> topicsList = new LinkedList<String>();
		topicsList.add("France");
		topicsList.add("Informatique");
		topicsList.add("Unix");
		topicsList.add("Java");
		question.setTopics(topicsList);
		question.addOption("Red");
		question.addOption("Blue");
		question.addOption("Green");
		question.addOption("Yellow");
		question.setAnswer(1);
		
		// Insert question into database
		dao.create(question);
		
		/*	READ method Test 		*/
		List<MultipleChoice> results = dao.read();
		
	}  // end of main()

}  // end of TestDAO()
