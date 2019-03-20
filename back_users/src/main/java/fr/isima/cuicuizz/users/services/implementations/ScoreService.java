package fr.isima.cuicuizz.users.services.implementations;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fr.isima.cuicuizz.users.converters.ScoreConverter;
import fr.isima.cuicuizz.users.dbaccess.mybatis.dao.ScoreMapper;
import fr.isima.cuicuizz.users.model.Score;
import fr.isima.cuicuizz.users.services.interfaces.IScoreService;
import io.spring.guides.gs_producing_web_service.ScoreDto;

/**
 * The service implementation class for scores
 *
 */
@Service
public class ScoreService implements IScoreService{
	
	/**
	 * The score mapper
	 */
	@Autowired
	private ScoreMapper scoreMapper;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ScoreDto> getUserScores(int userId) {
		return scoreMapper.getUserScores(userId).stream().map((s) -> ScoreConverter.convertEntityToDto(s)).collect(Collectors.toList());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ScoreDto addScore(ScoreDto score) {
		Score entityToAdd = ScoreConverter.convertDtoToEntity(score);
		Score oldEntity = scoreMapper.getUserScore(entityToAdd.getUserId(), entityToAdd.getMode(), entityToAdd.getTheme());
		// Score not already exists
		if(oldEntity == null) {
			scoreMapper.addScore(entityToAdd);
		}else {
			//score exists, need to update the value
			entityToAdd.setId(oldEntity.getId());
			scoreMapper.updateScore(entityToAdd);	
		}
		return ScoreConverter.convertEntityToDto(scoreMapper.getUserScore(score.getUserId(), score.getMode(), score.getTheme()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ScoreDto> getUserModeScores(int userId, String mode) {
		return scoreMapper.getUserModeScores(userId, mode).stream().map((s) -> ScoreConverter.convertEntityToDto(s)).collect(Collectors.toList());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ScoreDto> getUserThemeScores(int userId, String theme) {
		return scoreMapper.getUserThemeScores(userId, theme).stream().map((s) -> ScoreConverter.convertEntityToDto(s)).collect(Collectors.toList());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ScoreDto getUserScore(int userId, String mode, String theme) {
		return ScoreConverter.convertEntityToDto(scoreMapper.getUserScore(userId, mode, theme));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ScoreDto> getAllScores() {
		return scoreMapper.getAllScores().stream().map((s) -> ScoreConverter.convertEntityToDto(s)).collect(Collectors.toList());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ScoreDto> getAllModeScores(String mode) {
		return scoreMapper.getAllModeScores(mode).stream().map((s) -> ScoreConverter.convertEntityToDto(s)).collect(Collectors.toList());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ScoreDto> getAllThemeScores(String theme) {
		return scoreMapper.getAllThemeScores(theme).stream().map((s) -> ScoreConverter.convertEntityToDto(s)).collect(Collectors.toList());
	}
}
