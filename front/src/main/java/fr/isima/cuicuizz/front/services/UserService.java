package fr.isima.cuicuizz.front.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.isima.cuicuizz.front.webservices.IUserClient;

@Service
public class UserService implements IUserService{
	
	@Autowired
	IUserClient userClient;

}
