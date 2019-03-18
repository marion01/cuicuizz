package fr.isima.cuicuizz.users.converters;

import fr.isima.cuicuizz.users.model.Score;
import io.spring.guides.gs_producing_web_service.ScoreDto;

/**
 * Class used as a Factory pattern to transform entity and dto Score
 *
 */
public interface ScoreConverter {
	
	/**
	 * Transforms a Dto score into a entity score
	 * @param dto the dto to transform
	 * @return the entity corresponding to the dto
	 */
	public static Score convertDtoToEntity(ScoreDto dto) {
		final Score entity = new Score();
		
		if(dto == null) return null;
		
		entity.setId(dto.getId());
		entity.setMode(dto.getMode());
		entity.setTheme(dto.getTheme());
		entity.setUserId(dto.getUserId());
		entity.setNbQuestions(dto.getNbQuestions());
		entity.setNbSuccess(dto.getNbSuccess());
		entity.setValue(dto.getValue());
		
		return entity;

	}

	/**
	 * Transforms a entity score into a dto
	 * @param entity the entity score to transform
	 * @return the dto score corresponding to the entity
	 */
	public static ScoreDto convertEntityToDto(Score entity) {
		final ScoreDto dto = new ScoreDto();
		
		if(entity == null) return null;
		
		dto.setId(entity.getId());
		dto.setTheme(entity.getTheme());
		dto.setMode(entity.getMode());
		dto.setUserId(entity.getUserId());
		dto.setNbQuestions(entity.getNbQuestions());
		dto.setNbSuccess(entity.getNbSuccess());
		dto.setValue(entity.getValue());
		
		return dto;
	}
}
