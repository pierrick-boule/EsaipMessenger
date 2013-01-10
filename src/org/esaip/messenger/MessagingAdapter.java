package org.esaip.messenger;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * La classe de l'adapter MessagingAdapter.
 */
public class MessagingAdapter extends ArrayAdapter<UnMessage> {

	/** Le texte d'un message */
	private TextView textMessage;
	
	/** Une liste de messages. */
	private List<UnMessage> messages = new ArrayList<UnMessage>();
	
	/** affichage de la liste */
	private LinearLayout wrapper;

	/* redéfinition de la fonction d'ajout
	 * @see android.widget.ArrayAdapter#add(java.lang.Object)
	 */
	@Override
	public void add(UnMessage object) {
		messages.add(object);
		super.add(object);
	}

	/**
	 * Instantiates a new messaging adapter.
	 *
	 * @param context the context
	 * @param textViewResourceId the text view resource id
	 */
	public MessagingAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
	}

	/* redéfinition de la fonction permettant de compter le nombre d'éléments
	 * @see android.widget.ArrayAdapter#getCount()
	 */
	public int getCount() {
		return this.messages.size();
	}

	/* redéfinition de la fonction retournant un élément
	 * @see android.widget.ArrayAdapter#getItem(int)
	 */
	public UnMessage getItem(int index) {
		return this.messages.get(index);
	}

	/* redéfinition de la fonction définissant le comportement visuel de la liste
	 * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		if (row == null) {
			LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.message_bulle, parent, false);
		}

		wrapper = (LinearLayout) row.findViewById(R.id.wrapper);

		UnMessage message = getItem(position);

		textMessage = (TextView) row.findViewById(R.id.text_bulle_message);

		textMessage.setText(Html.fromHtml("<b>"+message.auteur + " dit :</b> <br />" + message.message));

		textMessage.setBackgroundResource(message.left ? R.drawable.bubbleyellow : R.drawable.bubblegreen);
		wrapper.setGravity(message.left ? Gravity.LEFT : Gravity.RIGHT);

		return row;
	}

	/**
	 * Decode to bitmap.
	 *
	 * @param decodedByte the decoded byte
	 * @return the bitmap
	 */
	public Bitmap decodeToBitmap(byte[] decodedByte) {
		return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
	}

}