package com.api.qlibrary.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.api.qlibrary.services.CurrentUserService;

/**
 * Clase principal de configuracion de acceso a la aplicacion
 * @author AAlejo
 * */
@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
   
	
	@Autowired
    private CurrentUserService currentUserService;
	
	 @Autowired
	 private JwtAuthenticationEntryPoint unauthorizedHandler;
	 
	 @Autowired
	 private JwtAuthenticationFilter jwtAuthenticationFilter;

	
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Bean
    /**
     * Crea el encriptador de contraseñas	
     * El numero 4 representa que tan fuerte quieres la encriptacion.
     * Se puede en un rango entre 4 y 31. 
     * Si no se coloca un numero el programa utilizara uno aleatoriamente cada vez
     * que inicies la aplicacion, por lo cual tus contrasenas encriptadas no funcionaran bien
     * 
     * @return bCryptPasswordEncoder
     */
    public BCryptPasswordEncoder passwordEncoder() {
		bCryptPasswordEncoder = new BCryptPasswordEncoder(4);

        return bCryptPasswordEncoder;
    }
    
    
    /**
     * 
     * Registra el service para usuarios y el encriptador de contrasena
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(currentUserService).passwordEncoder(bCryptPasswordEncoder);
    }
	
  
    /**
	 *  El metodo authorizeRequests() permite restringir y/o dar acceso request HTTP 
		antMatchers(): Lista de URL que corresponden a un RequestMapping como lo hacemos en los controladores.
		permitAll(): Especifica que estas URLs son accesibles por cualquiera.
		access(): permite el acceso cumpliendo la expresión, en este caso tenemos la expresion “hasRole()”. Donde verifica si el usuario tiene ese especifico Role.
		anyRequest(): Ya que la configuración es lineal poniendo este metodo al final interpreta los request a las URLs que no fueron descritos, y en conjunto con el metodo authenticated() permite y da acceso a cualquier usuario que este autenticado.
		
		El metodo fromLogin(). Permite personalizar el proceso de inicio de sesión
		loginPage(): indica la url de la pagina de inicio de sesión
		defaultSuccessUrl(): Indica a cual URL sera redirigido cuando el usuario inicie sesión.
		failureUrl(): Indica a cual URL sera redirigido cuando el inicio de sesión falla.
		usernameParameter() y passwordParameter(): Indica el nombre de los parámetros respectivamente.
		
		El metodo logout(): Personaliza el proceso de cierre de sesión.
		logoutSuccessUrl(): Indica la URL donde sera redirigido cuando el usuario cierre sesión.
	 */
    protected void configure(HttpSecurity http) throws Exception {
    	
    	
    	
    	 /**
         * Step adicional para callear al cors del Metodo Main
         */
        http = http.cors().and().csrf().disable();

        //origen de consulta de la documentacion
       // String swagerOrigin=env.getProperty("swagger-ui-host");
        
        http.authorizeRequests()
        .antMatchers("/qlibray/api/v1/auth/requestToken").permitAll()
        .antMatchers("/qlibray/api/v1/auth/login").permitAll()
        .antMatchers("/qlibray/api/v1/author/consult/getInfo").permitAll()
        .antMatchers("/qlibray/api/v1/book/consult/**").permitAll()
        .antMatchers("/qlibray/api/v1/category/consult/**").permitAll()
        .antMatchers("/swagger-ui/**").permitAll() //necesario para acceder a  swagger sin ninguna restriccion
        //.antMatchers("/swagger-ui/**").hasIpAddress(swagerOrigin) //necesario para swagger con restriccion
        .antMatchers("swagger-ui.html").permitAll() //necesario para swagger
        .antMatchers("/swagger-resources/**").permitAll() //necesario para swagger
        .antMatchers("/v2/api-docs/**").permitAll()//necesario para swagger
        .antMatchers("/v2/api-docs").permitAll()//necesario para swagger
        .antMatchers(HttpMethod.OPTIONS).permitAll()//necesario para que accedan a todos los componentes de las carpetas internas del app
     //   .antMatchers("/include/**","/css/**","/icons/**","/img/**","/js/**","/layer/**").permitAll()
        .anyRequest().authenticated()
        .and()
        .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); //filtro de acceso
  
}

    
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
	
}
