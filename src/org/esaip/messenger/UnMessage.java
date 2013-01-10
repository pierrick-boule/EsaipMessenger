package org.esaip.messenger;

/**
 * La classe d'un Message.
 */
public class UnMessage {
	
	/** Pour définir la position du message. */
	public boolean left;
	
	/** Le texte du message */
	public String message;
	
	/** L'auteur du message */
	public String auteur;

	/**
	 * Constructeur d'un message.
	 *
	 * @param left pour la position
	 * @param message le message
	 * @param auteur l'auteur
	 */
	public UnMessage(boolean left, String message,String auteur) {
		super();
		this.left = left;
		this.message = message;
		this.auteur = auteur;
	}

}