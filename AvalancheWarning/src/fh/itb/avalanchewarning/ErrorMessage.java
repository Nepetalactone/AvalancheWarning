package fh.itb.avalanchewarning;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class ErrorMessage {

	/**
	 * Verwendung: 
	 * 
	 * ErrorMessage.getErrorMessage("Username und Passwort falsch", "Ok", v.getContext());
	 *
	 * 
	 * Durch diese Methode wird eine Fehlermeldung angezeigt.
	 */
	public static void showErrorMessage(String infoText, String confirmText,
			Context context) {
		AlertDialog.Builder alertbox = new AlertDialog.Builder(context);
		alertbox.setMessage(infoText);
		alertbox.setNeutralButton(confirmText, new OnClickListener() {
			public void onClick(DialogInterface di, int i) {
			}
		});
		alertbox.show();
	}
}
