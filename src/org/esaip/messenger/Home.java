package org.esaip.messenger;

import org.esaip.messenger.util.MyPrefs_;

import android.app.Activity;
import android.content.Intent;
import android.widget.Button;

import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.OptionsItem;
import com.googlecode.androidannotations.annotations.OptionsMenu;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;

/**
 * La classe de l'actvité Home.
 */
@EActivity(R.layout.home)
@OptionsMenu(R.menu.menuconnected)
public class Home extends Activity{
	
	/** Bouton permettant de lancer la liste des messages */
	@ViewById
	Button buttonList;
	
	/** Bouton permettant de lancer l'activité d'envoi de nouveau message */
	@ViewById
	Button buttonEnvoi;
	
	/** Les SharedPreferences */
	@Pref
	MyPrefs_ sprefs;
	
	/**
	 * Si l'utilisateur appui sur le bouton list ont lance l'activité Liste.
	 */
	@Click({R.id.buttonList})
	void liste(){
		Intent activitylist = new Intent(this, Liste_.class);
		startActivity(activitylist);
	}
	
	/**
	 * Si l'utilisateur appui sur le bouton envoi ont lance l'activité Envoi.
	 */
	@Click({R.id.buttonEnvoi})
	void envoi(){
		Intent activityenvoi = new Intent(this, Envoi_.class);
		startActivity(activityenvoi);
	}
	
	/**
	 * Déconnection via le menu.
	 */
	@OptionsItem
	void decoSelected() {
		sprefs.edit().clear().apply();
		Intent activitylog = new Intent(this, Login_.class);
		startActivity(activitylog);
		finish();
	}
}
