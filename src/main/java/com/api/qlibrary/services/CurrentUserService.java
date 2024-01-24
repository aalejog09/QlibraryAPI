package com.api.qlibrary.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.qlibrary.auxiliar.appUser.AppuserCurrentDTO;
import com.api.qlibrary.models.Appuser;
import com.api.qlibrary.repositories.IAppuserRepository;

@Service
public class CurrentUserService implements UserDetailsService {
    
	
	private final IAppuserRepository appuserRepository;

    @Autowired
    public CurrentUserService(IAppuserRepository appuserRepository) {
        this.appuserRepository = appuserRepository;
    }

    
    /**
     * Servicio para consultar un usuario por su nombre
     *  @param username
     *  @return currentUser
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Appuser user = appuserRepository.findByUsername(username);
        if (user != null) {
            final AppuserCurrentDTO currentUser = new AppuserCurrentDTO();
            currentUser.setUsername(user.getUsername());
            currentUser.setPassword(user.getAccessCode());
            return currentUser;
        }
        throw new UsernameNotFoundException("Error, usuario invalido. " + username);
    }
}
