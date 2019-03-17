package fr.isima.cuicuizz.users.converters;

import fr.isima.cuicuizz.users.model.Score;
import io.spring.guides.gs_producing_web_service.ScoreDto;

public class ScoreConverter {
	public static Score convertDtoToEntity(ScoreDto dto) {
		final Score entity = new Score();

		entity.setId(dto.getId());
		entity.setMode(dto.getMode());
		entity.setTheme(dto.getTheme());
		entity.setUserId(dto.getUserId());
		entity.setNbQuestions(dto.getNbQuestions());
		entity.setNbSuccess(dto.getNbSuccess());
		entity.setValue(dto.getValue());
		return entity;

	}

	public static ScoreDto convertEntityToDto(Score entity) {
		final ScoreDto dto = new ScoreDto();
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
