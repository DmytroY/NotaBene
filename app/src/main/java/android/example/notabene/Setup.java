package android.example.notabene;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class Setup extends AppCompatActivity {
    Button btnSave;
    Switch swDarkTheme;
    TextView textViewSubj, textViewFrom, textViewTo, textViewPassw;
    View parentLayout;
    private SharedPreferences prefs;
    SharedPreferences.Editor editor;
    public static final String PREF = "myprefs";
    String isDarkTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup);

        parentLayout = findViewById(android.R.id.content);
        textViewSubj = findViewById(R.id.editTextSubj);
        textViewFrom = findViewById(R.id.editTextFrom);
        textViewTo = findViewById(R.id.editTextTo);
        textViewPassw = findViewById(R.id.editTextPassw);
        btnSave = findViewById(R.id.btn_save);
        swDarkTheme = findViewById(R.id.swDarkTheme);

        prefs = getSharedPreferences(PREF, Context.MODE_PRIVATE);
        editor = prefs.edit();

        textViewSubj.setText(getPref("subject"));
        textViewFrom.setText(getPref("from"));
        textViewTo.setText(getPref("to"));
        textViewPassw.setText(getPref("password"));
<<<<<<< HEAD
        isDarkTheme = getPref("isDarkTheme");
        if (Boolean.parseBoolean(isDarkTheme)){
            swDarkTheme.setChecked(true);
        }

        //
        String to = textViewTo.getText().toString();
        if (to.length() == 0) {
            Snackbar.make(parentLayout, R.string.requiredField, Snackbar.LENGTH_LONG).show();
            textViewTo.requestFocus();
        }

=======
        if (Boolean.parseBoolean(getPref("isDarkTheme"))){
            swDarkTheme.setChecked(true);
        }

>>>>>>> 35a7748f08c7c4b7dac3d1f124450a9e291319e6
        // if dark/light Theme is Checked
        swDarkTheme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                isDarkTheme = new Boolean(isChecked).toString();
            }
        });

        // if 'Save' preferences button clicked
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject = textViewSubj.getText().toString();
                String from = textViewFrom.getText().toString();
                String to = textViewTo.getText().toString();
                String passw = textViewPassw.getText().toString();

<<<<<<< HEAD
                boolean validEmail = to.matches("^[a-zA-Z0-9@.]+$");

                // check fields entry and validity of email format
                if (subject.length()>0 && to.indexOf("@") != -1 && to.indexOf(".") != -1 && validEmail) {
                    if ( setPref("subject", subject) && setPref("from", from) && setPref("to", to) && setPref("password", passw) && setPref("isDarkTheme", isDarkTheme)) {
                        Snackbar.make(parentLayout, R.string.prefSaved, Snackbar.LENGTH_INDEFINITE).show();
                    } else {
                        Snackbar.make(parentLayout, R.string.prefSaveError, Snackbar.LENGTH_INDEFINITE).show();
                    }
                } else {
                    //Toast.makeText(getApplicationContext(),"Please imput valid emails", Toast.LENGTH_SHORT).show();
                    Snackbar.make(parentLayout, R.string.setupFieldError, Snackbar.LENGTH_INDEFINITE).show();
=======

                // check fields entry and validity of email format
                if (subject.length()>0 && to.indexOf("@") != -1 && to.indexOf(".") != -1) {
                    if ( setPref("subject", subject) && setPref("from", from) && setPref("to", to) && setPref("password", passw) && setPref("isDarkTheme", isDarkTheme)) {
                        Snackbar.make(parentLayout, R.string.prefSaved, Snackbar.LENGTH_INDEFINITE)
                                .show();
                    } else {
                        Snackbar.make(parentLayout, R.string.prefSaveError, Snackbar.LENGTH_INDEFINITE)
                                .show();
                    }
                } else {
                    //Toast.makeText(getApplicationContext(),"Please imput valid emails", Toast.LENGTH_SHORT).show();
                    Snackbar.make(parentLayout, R.string.setupFieldError, Snackbar.LENGTH_INDEFINITE)
                            .show();
>>>>>>> 35a7748f08c7c4b7dac3d1f124450a9e291319e6
                }
            }
        });
    }

    // save preferences
    public boolean setPref(String key, String value) {
        editor.putString(key, value);
        if (editor.commit()) {
            return true;
        } else {
            return false;
        }
    }

    // load preferences
    public String getPref(String key) {
        return prefs.getString(key,"");
    }

}
