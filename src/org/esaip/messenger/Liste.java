package org.esaip.messenger;

import org.esaip.messenger.rest.RestClient;
import org.esaip.messenger.util.MyPrefs_;

import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.ListView;

import com.googlecode.androidannotations.annotations.AfterViews;
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
 * Classe de l'activité Liste.
 */
@EActivity(R.layout.liste)
@OptionsMenu(R.menu.menuconnected)
public class Liste extends Activity {

	/** L'adapter pour la liste de message. */
	private MessagingAdapter adapter;
	
	/** La vue qui affichera les messages. */
	@ViewById
	ListView listViewMessages;
	
	/** Un nouveau message. */
	@ViewById
	EditText newMessage;
	
	/** Le client Rest. */
	@RestService
	RestClient myRestClient;
	
	/** Les SharedPreferences */
	@Pref
	MyPrefs_ sprefs;
	
	/**
	 * Après l'affichage de l'activité.
	 */
	@AfterViews
	void after() {
		newMessage.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
					adapter.add(new UnMessage(false, newMessage.getText().toString(),sprefs.username().get()));
					sendmessage();
					return true;
				}
				return false;
			}
		});
		listViewMessages.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
		listViewMessages.setStackFromBottom(true);
		getmessages();
	}
	
	/**
	 * Obtenir la liste de messages dans l'adapter.
	 * et de mettre lancer une mise a jour de la vue
	 */
	@Background
	void getmessages(){
		adapter = new MessagingAdapter(getApplicationContext(),
				R.layout.message_bulle);
		String messages = myRestClient.getMessages(sprefs.username().get(), sprefs.password().get());
		String messagesArray[]=messages.split(";");
		for (String message : messagesArray) {
			String unMessageArray[] = message.split(":");
			if(unMessageArray[0].contains(sprefs.username().get()))	{
				adapter.add(new UnMessage(false, unMessageArray[1],unMessageArray[0]));
			} else {
				adapter.add(new UnMessage(true, unMessageArray[1],unMessageArray[0]));
			}
		}
		updateui();
	}
	
	/**
	 * Mise à jour de l'affichage de la liste à l'écran.
	 */
	@UiThread
	void updateui(){
		listViewMessages.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		listViewMessages.refreshDrawableState();
		listViewMessages.invalidate();
	}
	
	/**
	 * Tâche de fond permettant d'envoyer un message.
	 */
	@Background
	void sendmessage(){
		myRestClient.postMessage(sprefs.username().get(), sprefs.password().get(), newMessage.getText().toString());
		newMessage.setText("");
		updateui();
	}
	
	/**
	 * Lorsque l'on appui sur le bouton refresh on récupere la liste de message et MAJ liste.
	 */
	@Click(R.id.refresh)
	void send() {
		getmessages();
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
