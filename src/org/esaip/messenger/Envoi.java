package org.esaip.messenger;

import org.esaip.messenger.rest.RestClient;
import org.esaip.messenger.util.MyPrefs_;

import android.app.Activity;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;

import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.OptionsItem;
import com.googlecode.androidannotations.annotations.OptionsMenu;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.rest.RestService;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;

/**
 * Classe de l'activité Envoi.
 */
@EActivity(R.layout.envoi)
@OptionsMenu(R.menu.menuconnected)
public class Envoi extends Activity{
	
	/** Les SharedPreferences */
	@Pref
	MyPrefs_ sprefs;
	
	/** Le message a envoyer. */
	@ViewById
	EditText messagetosend;
	
	/** Le bouton permettant d'envoyer un message */
	@ViewById
	Button bEnvoyerMsg;
	
	/** Le client Rest */
	@RestService
	RestClient myRestClient;
	
	/**
	 * Lorsque l'on click sur le bouton on lance une tache de fond d'envoi du message.
	 */
	@Click(R.id.bEnvoyerMsg)
	void send() {
		sendmessage();
	}
	
	/**
	 * Envoy du message via le client Rest.
	 */
	@Background
	void sendmessage(){
		myRestClient.postMessage(sprefs.username().get(), sprefs.password().get(), messagetosend.getText().toString());
		updateui();
	}
	
	/**
	 * Mise à jour de l'interface.
	 */
	@UiThread
	void updateui(){
		messagetosend.setText("");
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
