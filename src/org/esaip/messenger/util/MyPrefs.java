package org.esaip.messenger.util;

import com.googlecode.androidannotations.annotations.sharedpreferences.SharedPref;
import com.googlecode.androidannotations.annotations.sharedpreferences.SharedPref.Scope;

/**
 * l'interface MyPrefs pour les SharedPreferences.
 */
@SharedPref(value=Scope.UNIQUE)
public interface MyPrefs {
	
	/**
	 * nom d'utilisateur.
	 *
	 * @return une chaine de charactere
	 */
	String username();
	
	/**
	 * Mot de passe
	 *
	 * @return une chaine de charactere
	 */
	String password();
}
