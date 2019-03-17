package fr.isima.cuicuizz;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.isima.cuicuizz.converters.AnswerConverter;
import fr.isima.cuicuizz.converters.QuestionConverter;
import fr.isima.cuicuizz.converters.ThemeConverter;
import fr.isima.cuicuizz.dbaccess.mybatis.dao.QuestionMapper;
import fr.isima.cuicuizz.model.AnswerModel;
import fr.isima.cuicuizz.model.QuestionModel;
import fr.isima.cuicuizz.model.ThemeModel;
import fr.isima.cuicuizz.services.interfaces.IQuestionService;
import io.spring.guides.gs_producing_web_service.Answer;
import io.spring.guides.gs_producing_web_service.Question;
import io.spring.guides.gs_producing_web_service.Theme;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CuicuizzApplicationTests {

	@Autowired
	private QuestionMapper questionMapper;

	@Autowired
	private IQuestionService questionService;

	/*
	 * AnswerConverter tests
	 */

	@Test
	public void answerConvertTest() {
		final AnswerModel am = new AnswerModel();
		Answer answer;

		am.setId(1);
		am.setAnswer("answer");
		am.setIsCorrect(true);

		answer = AnswerConverter.convert(am);

		assertEquals(am.getId(), answer.getId());
		assertEquals(am.getAnswer(), answer.getAnswer());
		assertEquals(am.getIsCorrect(), answer.isIsCorrect());

	}

	@Test
	public void answerConvertMultipleTest() {

		assertEquals(3,
				AnswerConverter.convert(Arrays.asList(new AnswerModel(), new AnswerModel(), new AnswerModel())).size());

	}

	/*
	 * QuestionConverter tests
	 */

	@Test
	public void questionConvertTest() {
		final QuestionModel qm = new QuestionModel();
		Question question;

		qm.setId(1);
		qm.setThemeId(2);
		qm.setQuestion("question");
		qm.setAnswers(Arrays.asList(new AnswerModel(), new AnswerModel(), new AnswerModel()));

		question = QuestionConverter.convert(qm);

		assertEquals(qm.getId(), question.getId());
		assertEquals(qm.getThemeId(), question.getTheme());
		assertEquals(qm.getQuestion(), question.getValue());
		assertEquals(qm.getAnswers().size(), question.getAnswers().size());

	}

	@Test
	public void questionConvertMultipleTest() {
		assert (QuestionConverter.convert(Arrays.asList(new QuestionModel(), new QuestionModel(), new QuestionModel()))
				.size() == 3);

	}

	/*
	 * ThemeConverter tests
	 */

	@Test
	public void themeConvertTest() {
		final ThemeModel tm = new ThemeModel();
		Theme theme;

		tm.setId(1);
		tm.setName("Theme");

		theme = ThemeConverter.convert(tm);

		assertEquals(tm.getId(), theme.getId());
		assertEquals(tm.getName(), theme.getName());

	}

	@Test
	public void themeConvertMultipleTest() {

		assertEquals(3,
				ThemeConverter.convert(Arrays.asList(new ThemeModel(), new ThemeModel(), new ThemeModel())).size());

	}

	/*
	 * QuestionMapper tests
	 */

	@Test
	public void selectAllQuestionsTest() {
		final List<QuestionModel> questions = questionMapper.selectAllQuestions(1);

		assertEquals((int) questionMapper.getNbQuestionFromTheme(1), questions.size());
		for (final QuestionModel question : questions)
			assertEquals(1, question.getThemeId());

	}

	@Test
	public void selectAllQuestionsFromNonExistingThemeTest() {

		assertEquals(0, questionMapper.selectAllQuestions(0).size());

	}

	@Test
	public void getNbQuestionFromTheme() {

		assertEquals(7, (int) questionMapper.getNbQuestionFromTheme(1));

	}

	@Test
	public void getNbQuestionFromNonExistingTheme() {

		assertEquals(0, (int) questionMapper.getNbQuestionFromTheme(0));

	}

	@Test
	public void getThemesTest() {

		assertEquals(1, questionMapper.getThemes().size());

	}

	/*
	 * IQuestionService tests
	 */

	@Test
	public void findQuestionTest() {
		final List<Question> questions = questionService.findQuestion(1, 3);
		assertEquals(3, questions.size());
		assertNotEquals(questions, questionService.findQuestion(1, 3));

	}

	@Test
	public void findQuestionRequestTooManyQuestionsTest() {

		assertEquals((int) questionService.getNbQuestionFromTheme(1), questionService.findQuestion(1, 99).size());

	}

}
