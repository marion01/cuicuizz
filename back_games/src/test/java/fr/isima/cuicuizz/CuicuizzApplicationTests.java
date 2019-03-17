package fr.isima.cuicuizz;

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
		
		assert(answer.getId() == am.getId());
		assert(answer.getAnswer() == am.getAnswer());
		assert(answer.isIsCorrect() == am.getIsCorrect());
	}
	
	@Test
	public void answerConvertMultipleTest() {
		assert(AnswerConverter.convert(Arrays.asList(new AnswerModel(), new AnswerModel(), new AnswerModel())).size() == 3);
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
		
		assert(question.getId() == qm.getId());
		assert(question.getTheme() == qm.getThemeId());
		assert(question.getValue() == qm.getQuestion());
		assert(question.getAnswers().size() == qm.getAnswers().size());
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
		
		assert(theme.getId() == tm.getId());
		assert(theme.getName() == tm.getName());
	}

	@Test
	public void themeConvertMultipleTest() {
		assert(ThemeConverter.convert(Arrays.asList(new ThemeModel(), new ThemeModel(), new ThemeModel())).size() == 3);
		
	}
	
	/*
	 * QuestionMapper tests
	 */
	
	@Test
	public void selectAllQuestionsTest() {
		List<QuestionModel> questions = questionMapper.selectAllQuestions(1);
		
		assert(questions.size() == questionMapper.getNbQuestionFromTheme(1));
		for (QuestionModel question : questions) assert(question.getThemeId() == 1);
	}
	
	@Test
	public void selectAllQuestionsFromNonExistingThemeTest() {
		assert(questionMapper.selectAllQuestions(0).size() == 0);
	}
	
	@Test
	public void getNbQuestionFromTheme() {
		assert(questionMapper.getNbQuestionFromTheme(1) == 7);
	}
	
	@Test
	public void getNbQuestionFromNonExistingTheme() {
		assert(questionMapper.getNbQuestionFromTheme(0) == 0);
	}

	@Test
	public void getThemesTest() {
		assert(questionMapper.getThemes().size() == 1);
	}
	
	/*
	 * IQuestionService tests
	 */
	
	@Test
	public void findQuestionTest() {
		List<Question> questions = questionService.findQuestion(1, 3);
		
		assert(questions.size() == 3);
		assert(questionService.findQuestion(1, 3) != questions);
	}

	@Test
	public void findQuestionRequestTooManyQuestionsTest() {
		assert(questionService.findQuestion(1, 99).size() == questionService.getNbQuestionFromTheme(1));
	}

}

