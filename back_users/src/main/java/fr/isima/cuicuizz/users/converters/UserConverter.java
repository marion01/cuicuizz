package fr.isima.cuicuizz.users.converters;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import fr.isima.cuicuizz.users.model.User;
import io.spring.guides.gs_producing_web_service.UserDto;

/**
 * Class used as a Factory pattern to transform dto and entity of users
 *
 */
public interface UserConverter {

	/**
	 * Transforms a dto user into a entity user
	 * @param dto the dto to transform
	 * @return the entity corresponding to the dto
	 */
	public static User convertDtoToEntity(UserDto dto) {
		final User entity = new User();
		
		if (dto == null) return null;

		entity.setId(dto.getId());
		entity.setPseudo(dto.getPseudo());

		final String password = dto.getPassword();
		// Hash password
		if (password != null && !password.isEmpty()) {
			try {
				MessageDigest md;
				md = MessageDigest.getInstance("SHA-256");
				md.update(dto.getPassword().getBytes());
				final byte byteData[] = md.digest();
				final StringBuffer sb = new StringBuffer();
				for (int i = 0; i < byteData.length; i++) {
					sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
				}
				// Set password to hashed value
				entity.setPassword(sb.toString());
			} catch (final NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		} else {
			entity.setPassword(null);
		}
		entity.setLastActionDate(null);
		
		return entity;
	}

	/**
	 * Transforms a entity user into a dto user
	 * @param entity the user entity to transform
	 * @return the user dto corresponding to the entity
	 */
	public static UserDto convertEntityToDto(User entity) {
		final UserDto dto = new UserDto();
		
		if (entity == null) return null;
		
		dto.setId(entity.getId());
		dto.setPseudo(entity.getPseudo());
		dto.setPassword(entity.getPassword());
		
		return dto;
	}
}
