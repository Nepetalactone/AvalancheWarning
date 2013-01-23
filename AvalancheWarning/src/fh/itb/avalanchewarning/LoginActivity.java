package fh.itb.avalanchewarning;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class LoginActivity extends Activity {

	Button _btnLogin;
	Button _btnAbort;

	EditText _tfUser;
	EditText _tfPw;

	RadioGroup _rgLanguage;
	RadioButton _rbEnglish;
	RadioButton _rbGerman;

	String[] _languageGerman;
	String[] _languageEnglish;

	// Initialisierungsblock soll im optimalfall später mit Textfile ausgelagert
	// werden!

	// 0.Username, 1.German, 2.English, 3.Login, 4.Abort
	{
		_languageGerman = new String[] { "Benutzername", "Deutsch", "Englisch",
				"Login", "Beenden" };
		_languageEnglish = new String[] { "Username", "German", "English",
				"Login", "Abort" };
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		// Init
		_btnLogin = (Button) findViewById(R.id.btnLogin);
		_btnAbort = (Button) findViewById(R.id.btnAbort);

		_tfUser = (EditText) findViewById(R.id.txtUsername);
		_tfPw = (EditText) findViewById(R.id.pwdUserpassword);

		_rgLanguage = (RadioGroup) findViewById(R.id.radioLanguage);
		_rbEnglish = (RadioButton) findViewById(R.id.rdoLanguageEnglish);
		_rbGerman = (RadioButton) findViewById(R.id.rdoLanguageGerman);

		// Listener
		_btnLogin.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// LOGIN Button clicked!

				// TODO: Validätsprüfung
				// TODO: Ausgabe Fehlermeldung
				// TODO: Weiterleitung auf Andere Form

				String enteredUsername = _tfUser.getText().toString();
				String enteredPassword = _tfPw.getText().toString();

			}
		});

		// ABORT Button clicked!
		_btnAbort.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				finish();
				System.exit(0);
			}
		});

		// RadioButtonListener
		_rgLanguage.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == _rbGerman.getId())
					germanRBClicked();
				else
					englishRBClicked();
			}
		});
	}

	/**
	 * Methode wird aufgerufen, falls der Radiobutton der Deutschen Sprache
	 * ausgewählt wird.
	 * 
	 * Hier werden alle Elemente auf die deutsche Sprache umgestellt.
	 */
	private void germanRBClicked() {
		// falls noch kein Benutzername eingetragen wurde:
		if (_tfUser.getText().toString().equals(_languageEnglish[0]))
			_tfUser.setText(_languageGerman[0]);

		_rbGerman.setText(_languageGerman[1]);
		_rbEnglish.setText(_languageGerman[2]);
		_btnLogin.setText(_languageGerman[3]);
		_btnAbort.setText(_languageGerman[4]);
	}

	/**
	 * Methode wird aufgerufen, falls der Radiobutton der Englischen Sprache
	 * ausgewählt wird.
	 * 
	 * Hier werden alle Elemente auf die englische Sprache umgestellt.
	 */
	private void englishRBClicked() {
		// fals noch kein Benutzername eingetragen wurde:
		if (_tfUser.getText().toString().equals(_languageGerman[0]))
			_tfUser.setText(_languageEnglish[0]);

		_rbGerman.setText(_languageEnglish[1]);
		_rbEnglish.setText(_languageEnglish[2]);
		_btnLogin.setText(_languageEnglish[3]);
		_btnAbort.setText(_languageEnglish[4]);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_login, menu);
		return true;
	}

}
