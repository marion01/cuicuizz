package fr.isima.cuicuizz;

<<<<<<< HEAD
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

=======
>>>>>>> UserService
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
		AnswerModel am = new AnswerModel();
		Answer answer;
		
		am.setId(1);
		am.setAnswer("answer");
		am.setIsCorrect(true);
		
		answer = AnswerConverter.convert(am);
<<<<<<< HEAD

		assertEquals(am.getId(), answer.getId());
		assertEquals(am.getAnswer(), answer.getAnswer());
		assertEquals(am.getIsCorrect(), answer.isIsCorrect());
=======
		
		assert(answer.getId() == am.getId());
		assert(answer.getAnswer() == am.getAnswer());
		assert(answer.isIsCorrect() == am.getIsCorrect());
>>>>>>> UserService
	}
	
	@Test
	public void answerConvertMultipleTest() {
<<<<<<< HEAD
		assertEquals(3, AnswerConverter.convert(Arrays.asList(new AnswerModel(), new AnswerModel(), new AnswerModel())).size());
=======
		assert(AnswerConverter.convert(Arrays.asList(new AnswerModel(), new AnswerModel(), new AnswerModel())).size() == 3);
>>>>>>> UserService
	}

	/*
	 * QuestionConverter tests
	 */
	
	@Test
	public void questionConvertTest() {
		QuestionModel qm = new QuestionModel();
		Question question;
		
		qm.setId(1);
		qm.setThemeId(2);
		qm.setQuestion("question");
		qm.setAnswers(Arrays.asList(new AnswerModel(), new AnswerModel(), new AnswerModel()));
		
		question = QuestionConverter.convert(qm);
		
<<<<<<< HEAD
		assertEquals(qm.getId(), question.getId());
		assertEquals(qm.getThemeId(), question.getTheme());
		assertEquals(qm.getQuestion(), question.getValue());
		assertEquals(qm.getAnswers().size(), question.getAnswers().size());
=======
		assert(question.getId() == qm.getId());
		assert(question.getTheme() == qm.getThemeId());
		assert(question.getValue() == qm.getQuestion());
		assert(question.getAnswers().size() == qm.getAnswers().size());
>>>>>>> UserService
	}

	@Test
	public void questionConvertMultipleTest() {
		assert(QuestionConverter.convert(Arrays.asList(new QuestionModel(), new QuestionModel(), new QuestionModel())).size() == 3);
		
	}

	/*
	 * ThemeConverter tests
	 */
	
	@Test
	public void themeConvertTest() {
		ThemeModel tm = new ThemeModel();
		Theme theme;
		
		tm.setId(1);
		tm.setName("Theme");
		
		theme = ThemeConverter.convert(tm);
		
<<<<<<< HEAD
		assertEquals(tm.getId(), theme.getId());
		assertEquals(tm.getName(), theme.getName());
=======
		assert(theme.getId() == tm.getId());
		assert(theme.getName() == tm.getName());
>>>>>>> UserService
	}

	@Test
	public void themeConvertMultipleTest() {
<<<<<<< HEAD
		assertEquals(3, ThemeConverter.convert(Arrays.asList(new ThemeModel(), new ThemeModel(), new ThemeModel())).size());
=======
		assert(ThemeConverter.convert(Arrays.asList(new ThemeModel(), new ThemeModel(), new ThemeModel())).size() == 3);
>>>>>>> UserService
		
	}
	
	/*
	 * QuestionMapper tests
	 */
	
	@Test
	public void selectAllQuestionsTest() {
		List<QuestionModel> questions = questionMapper.selectAllQuestions(1);
		
<<<<<<< HEAD
		assertEquals((int)questionMapper.getNbQuestionFromTheme(1), questions.size());
		for (QuestionModel question : questions) assertEquals(1, question.getThemeId());
=======
		assert(questions.size() == questionMapper.getNbQuestionFromTheme(1));
		for (QuestionModel question : questions) assert(question.getThemeId() == 1);
>>>>>>> UserService
	}
	
	@Test
	public void selectAllQuestionsFromNonExistingThemeTest() {
<<<<<<< HEAD
		assertEquals(0, questionMapper.selectAllQuestions(0).size());
=======
		assert(questionMapper.selectAllQuestions(0).size() == 0);
>>>>>>> UserService
	}
	
	@Test
	public void getNbQuestionFromTheme() {
<<<<<<< HEAD
		assertEquals(7, (int)questionMapper.getNbQuestionFromTheme(1));
=======
		assert(questionMapper.getNbQuestionFromTheme(1) == 7);
>>>>>>> UserService
	}
	
	@Test
	public void getNbQuestionFromNonExistingTheme() {
<<<<<<< HEAD
		assertEquals(0, (int)questionMapper.getNbQuestionFromTheme(0));
=======
		assert(questionMapper.getNbQuestionFromTheme(0) == 0);
>>>>>>> UserService
	}

	@Test
	public void getThemesTest() {
<<<<<<< HEAD
		assertEquals(1, questionMapper.getThemes().size());
=======
		assert(questionMapper.getThemes().size() == 1);
>>>>>>> UserService
	}
	
	/*
	 * IQuestionService tests
	 */
	
	@Test
	public void findQuestionTest() {
		List<Question> questions = questionService.findQuestion(1, 3);
		
<<<<<<< HEAD
		assertEquals(3, questions.size());
		assertNotEquals(questions, questionService.findQuestion(1, 3));
=======
		assert(questions.size() == 3);
		assert(questionService.findQuestion(1, 3) != questions);
>>>>>>> UserService
	}

	@Test
	public void findQuestionRequestTooManyQuestionsTest() {
<<<<<<< HEAD
		assertEquals((int)questionService.getNbQuestionFromTheme(1), questionService.findQuestion(1, 99).size());
=======
		assert(questionService.findQuestion(1, 99).size() == questionService.getNbQuestionFromTheme(1));
>>>>>>> UserService
	}

}

