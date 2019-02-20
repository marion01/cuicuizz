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
				"SELECT Id,Question,IsCorrect,Answer FROM Question q Join Answer a on a.QuestionId=q.Id where ThemeId=%s;",
				themeId);
		final List<QuestionModel> results = new ArrayList<>();
		try (Connection conn = this.connect();
				Statement stmt = conn.createStatement();

				ResultSet rs = stmt.executeQuery(sql)) {

			// loop through the result set
			while (rs.next()) {
				System.out.println(rs.getInt("Id") + "\t" + rs.getString("Question") + "\t" + rs.getDouble("ThemeId"));
				final QuestionModel qm = new QuestionModel();
				qm.setId(rs.getInt("Id"));
				qm.setQuestion(rs.getString("Question"));
				qm.setAnswer(new AnswerModel());
				qm.getAnswer().setAnswer(rs.getString("Answer"));
				System.out.println("test");
				qm.getAnswer().setIsCorrect(rs.getBoolean("IsCorrect"));
				System.out.println("test2");
				results.add(qm);
				System.out.println(qm.getQuestion());
			}
			return (results.size() > nb ? results.subList(0, nb) : results);
		} catch (final SQLException e) {
			System.out.println(e.getMessage());
		}
		return new ArrayList<>();
	}
}
