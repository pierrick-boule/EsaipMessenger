package org.esaip.messenger;

import org.esaip.messenger.rest.RestClient;
import org.esaip.messenger.util.MyPrefs_;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ProgressBar;

import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.rest.RestService;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;

/**
 * La Classe Dashboard, une activitée permettant d'effectuer un auto-login.
 */
@EActivity(R.layout.dashboard)
public class Dashboard extends Activity {

	/** La progress bar */
	@ViewById
	ProgressBar progressBarDash;
	
	/** Les SharedPreferences */
	@Pref
	MyPrefs_ sprefs;
	
	/** Le Client Rest */
	@RestService
	RestClient myRestClient;

	/**
	 * Après l'affichage
	 */
	@AfterViews
	void afterViews() {
		progressBarDash.setVisibility(View.VISIBLE);
		if (sprefs.username().exists()) {
			login(sprefs.username().get(),sprefs.password().get());
		} else {
			Intent activitylog = new Intent(this, Login_.class);
			progressBarDash.setVisibility(View.INVISIBLE);
			startActivity(activitylog);
			finish();
		}
	}

	/**
	 * Login. 
	 * Fonction de login et de mémorisation des identifiants
	 *
	 * @param username Le nom d'utilisateur
	 * @param password Le mot de passe
	 */
	@Background
	void login(String username, String password) {
		String result = myRestClient.login(username, password);
		if (result.contains("true")) {
			sprefs.edit().username().put(username).password().put(password).apply();
			startConnected();
		} else {
			startError();
		}
	}
	
	/**
	 * Lancement de l'activité Connected
	 */
	@UiThread
	void startConnected(){
		Intent activityconnected = new Intent(getApplicationContext(), Home_.class);
		progressBarDash.setVisibility(View.INVISIBLE);
		startActivity(activityconnected);
		finish();
	}
	
	/**
	 * Lancement de l'activité de Login
	 */
	@UiThread
	void startError(){
		Intent activityconnected = new Intent(getApplicationContext(), Login_.class);
		progressBarDash.setVisibility(View.INVISIBLE);
		startActivity(activityconnected);
		finish();
	}

}
