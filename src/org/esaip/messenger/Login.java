package org.esaip.messenger;

import org.esaip.messenger.rest.RestClient;
import org.esaip.messenger.util.MyPrefs_;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.rest.RestService;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;

/**
 * Classe de l'activité Login.
 */
@EActivity(R.layout.login)
public class Login extends Activity {
	
	/** Le client Rest */
	@RestService
	RestClient myRestClient;
	
	/** Les SharedPreferences */
	@Pref
	MyPrefs_ sprefs;
	
	/** Champ de saisi du nom d'utilisateur */
	@ViewById
	EditText editLogin;
	
	/** Champ de saisi du mot de passe */
	@ViewById
	EditText editPassword;
	
	/** Le Bouton envoyer */
	@ViewById
	Button bEnvoyer;
	
	/** Le bouton vider */
	@ViewById
	Button bVider;
	
	/** L'affichage de l'erreur */
	@ViewById
	TextView error;
	
	/** La barre de progression*/
	@ViewById
	ProgressBar progressBar;
	
	/** Message d'erreur */
	String errormsg = "Erreur de login/mdp";
	
	/** booleen de gestion d'erreur */
	Boolean boolerror = false;

	/**
	 * Action lors du clique sur le bouton de connection.
	 */
	@Click({ R.id.bEnvoyer })
	void envoyer() {
		if (TextUtils.isEmpty(editLogin.getText())
				|| TextUtils.isEmpty(editPassword.getText())) {
			boolerror = true;
			error.setText(errormsg);
		} else {
				String passwordString = editPassword.getText().toString();
				String usernameString = editLogin.getText().toString();
				boolerror = false;
				progressBar.setVisibility(View.VISIBLE);
				login(usernameString, passwordString);
	
		}
	}

	/**
	 * Fonction en tâche de fond pour la connection d'un utilisateur.
	 *
	 * @param username le nom d'utilisateur
	 * @param password le mot de passe
	 */
	@Background
	void login(String username, String password) {
		String result = myRestClient.login(username, password);
		if (result.contains("true")) {
			sprefs.edit().username().put(username).password().put(password)
					.apply();
			startConnected();
		} else {
			startError();
		}
	}

	/**
	 * Lancement de l'activité Connected.
	 */
	@UiThread
	void startConnected() {
		Intent activityHome = new Intent(getApplicationContext(), Home_.class);
		progressBar.setVisibility(View.INVISIBLE);
		startActivity(activityHome);
	}

	/**
	 * Affichage d'une erreur de connection.
	 */
	@UiThread
	void startError() {
		error.setText(errormsg);
		progressBar.setVisibility(View.INVISIBLE);
	}

	/**
	 * Vider les champs de saisie.
	 */
	@Click({ R.id.bVider })
	void vider() {
		editPassword.setText(null);
		editLogin.setText(null);
	}

	/**
	 * Lancement de l'activité d'enregistrement d'un utilisateur.
	 */
	@Click({ R.id.register })
	void register() {
		Intent activityRegister = new Intent(getApplicationContext(),
				Register_.class);
		startActivity(activityRegister);
	}
}
