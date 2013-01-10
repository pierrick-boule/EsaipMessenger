package org.esaip.messenger.rest;

import org.springframework.http.ResponseEntity;

import com.googlecode.androidannotations.annotations.rest.Accept;
import com.googlecode.androidannotations.annotations.rest.Get;
import com.googlecode.androidannotations.annotations.rest.Post;
import com.googlecode.androidannotations.annotations.rest.Rest;
import com.googlecode.androidannotations.api.rest.MediaType;

/**
 * Une interface RestClient.
 */
@Rest("http://parlezvous.herokuapp.com")
public interface RestClient {

	/**
	 * Fonction de login Rest.
	 *
	 * @param username le nom d'utilisateur
	 * @param password le mot de passe
	 * @return chaine de caractere vrai ou faux
	 */
	@Get("/connect/{username}/{password}")
	String login(String username, String password);

	/**
	 * Obtenir les messages.
	 *
	 * @param username le nom d'utilisateur
	 * @param password le mot de passe
	 * @return les messages
	 */
	@Get("/messages/{username}/{password}")
	String getMessages(String username, String password);
	
	/**
	 * Fonction d'envoi Rest.
	 *
	 * @param username le nom d'utilisateur
	 * @param password le mot de passe
	 * @param message un message
	 * @return une chaine de caractere
	 */
	@Get("/message/{username}/{password}/{message}")
	String postMessage(String username, String password,String message);

	/**
	 * Fonction d'enregistrement Rest.
	 *
	 * @param username le nom d'utilisateur
	 * @param password le mot de passe
	 * @return la page web html
	 */
	@Post("/subscribe?username={username}&password={password}")
	@Accept(MediaType.APPLICATION_XHTML_XML)
	ResponseEntity<String> register(String username, String password);
}
