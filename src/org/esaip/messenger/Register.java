package org.esaip.messenger;

import org.esaip.messenger.rest.RestClient;
import org.esaip.messenger.util.MyPrefs_;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
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
 * Classe de l'activité Register.
 */
@EActivity(R.layout.register)
public class Register extends Activity {
	
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
	
	/** Champ de confirmation du mot de passe */
	@ViewById
	EditText editPassword2;
	
	/** L'affichage de l'erreur */
	@ViewById
	TextView error;
	
	/** Message d'erreur */
	String errormsg = "Erreur de login/mdp";
	
	/** La barre de progression*/
	@ViewById
	ProgressBar progressBar;
	
	/** booleen de gestion d'erreur */
	Boolean boolerror = false;

	/**
	 * Action lors du clique sur le bouton d'enregistrement
	 */
	@Click({ R.id.bEnvoyer })
	void envoyer() {
		if (TextUtils.isEmpty(editLogin.getText())
				|| TextUtils.isEmpty(editPassword.getText())||TextUtils.isEmpty(editPassword2.getText())) {
			boolerror = true;
			error.setText(errormsg);
		} else {
			if (editPassword.getText().toString()
					.equals(editPassword2.getText().toString())) {
				String passwordString = editPassword.getText().toString();
				String usernameString = editLogin.getText().toString();
				boolerror = false;
				progressBar.setVisibility(View.VISIBLE);
				register(usernameString, passwordString);
			} else {
				boolerror = true;
				error.setText(errormsg);
			}
		}
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
	 * Fonction en tâche de fond pour l'enregistrement d'un utilisateur.
	 *
	 * @param username le nom d'utilisateur
	 * @param password le mot de passe
	 */
	@Background
	void register(String username, String password) {
		String result = myRestClient.register(username, password).getBody();
		if (result.contains("Inscription réussie!")) {
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
	 * Affichage d'une erreur d'enregistrement.
	 */
	@UiThread
	void startError() {
		error.setText("Nom d'utilisateur non valide");
		progressBar.setVisibility(View.INVISIBLE);
	}
}
