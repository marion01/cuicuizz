package fr.isima.cuicuizz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.isima.cuicuizz.model.AnswerModel;
import fr.isima.cuicuizz.model.QuestionModel;

public class QuestionDbAccess {

	/**
	 * Connect to the test.db database
	 * 
	 * @return the Connection object
	 */
	private Connection connect() {
		// SQLite connection string
		String url = "jdbc:sqlite:" + ClassLoader.getSystemClassLoader().getResource("database.db").getFile();
		url = url.replace("/", "\\");
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
		} catch (final SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

	/**
	 * select all rows in the warehouses table
	 */
	public Map<Integer, QuestionModel> selectAll() {
		final String sql = "SELECT Id, Question, ThemeId FROM Question";
		final Map<Integer, QuestionModel> results = new HashMap<>();
		try (Connection conn = this.connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			// loop through the result set
			while (rs.next()) {
				System.out.println(rs.getInt("Id") + "\t" + rs.getString("Question") + "\t" + rs.getDouble("ThemeId"));
				final QuestionModel qm = new QuestionModel();
				qm.setId(rs.getInt("Id"));
				qm.setQuestion(rs.getString("Question"));
				results.put(rs.getInt("Id"), qm);
			}
			return results;
		} catch (final SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	/**
	 * select all rows in the warehouses table
	 */
	public List<QuestionModel> selectQuestionsFromTheme(int themeId, int nb) {

		final String sql = String.format(
				"SELECT Id, Question, ThemeId, Answer, IsCorrect FROM Question Join Answer on Answer.QuestionId = Question.Id where ThemeId=%s",
				themeId);
		System.out.println(sql);
		final List<QuestionModel> results = new ArrayList<>();
		try (Connection conn = this.connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			double questionId = -1;
			AnswerModel am;
			QuestionModel qm = new QuestionModel();
			while (rs.next()) {
				System.out.println(rs.getInt("Id") + "\t" + rs.getString("Question") + "\t" + rs.getDouble("ThemeId")
						+ "\t" + rs.getString("Answer"));
				if (questionId == rs.getInt("id")) {
					am = new AnswerModel();
					am.setAnswer(rs.getString("Answer"));
					am.setIsCorrect(rs.getBoolean("IsCorrect"));
					qm.getAnswers().add(am);
				} else {
					if (questionId != -1)
						results.add(qm);
					questionId = rs.getInt("Id");
					qm = new QuestionModel();
					qm.setId(rs.getInt("Id"));
					qm.setQuestion(rs.getString("Question"));

					am = new AnswerModel();
					am.setAnswer(rs.getString("Answer"));
					am.setIsCorrect(rs.getBoolean("IsCorrect"));
					qm.getAnswers().add(am);
				}

			}
			if (questionId != -1)
				results.add(qm);
			System.out.println(results.size());
			return (results.size() > nb ? results.subList(0, nb) : results);
		} catch (final SQLException e) {
			System.out.println(e.getMessage());
		}
		return new ArrayList<>();
	}
}
