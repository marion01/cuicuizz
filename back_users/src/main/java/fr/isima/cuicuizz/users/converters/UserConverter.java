package fr.isima.cuicuizz.users.converters;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import io.spring.guides.gs_producing_web_service.UserDto;
import fr.isima.cuicuizz.users.model.User;

public class UserConverter {

	public static User convertDtoToEntity(UserDto dto) {
		User entity = new User();
		
		entity.setId(dto.getId());
		entity.setPseudo(dto.getPseudo());
		
		String password = dto.getPassword();
		// Hash password
		if(password != null && !password.isEmpty())
		{
			try {
				MessageDigest md;
				md = MessageDigest.getInstance("SHA-256");
				md.update(dto.getPassword().getBytes());
		        byte byteData[] = md.digest();
		        StringBuffer sb = new StringBuffer();
		        for (int i = 0; i < byteData.length; i++) {
		        	sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		        }
				entity.setPassword(sb.toString());
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}else {
			entity.setPassword(null);
		}
		entity.setLastActionDate(null);
		return entity;
	}
	
	public static UserDto convertEntityToDto(User entity) {
		UserDto dto = new UserDto();
		dto.setId(entity.getId());
		dto.setPseudo(entity.getPseudo());
		dto.setPassword(null);
		return dto;
	}
}
