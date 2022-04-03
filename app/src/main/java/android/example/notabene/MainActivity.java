package android.example.notabene;

import androidx.annotation.NonNull;
<<<<<<< HEAD
import androidx.appcompat.app.AlertDialog;
=======
>>>>>>> 35a7748f08c7c4b7dac3d1f124450a9e291319e6
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
<<<<<<< HEAD
import android.content.DialogInterface;
=======
>>>>>>> 35a7748f08c7c4b7dac3d1f124450a9e291319e6
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // Constants be used by handler
    final public static int STATUS_SENT = 2;
    final public static int STATUS_ERROR = 3;
    final public static int STATUS_NO_CLIENT = 4;
    final public static int STATUS_SENTBYCLIENT = 5;

    private SharedPreferences prefs; // preferenses will be used for storing email subject, 'to' address, 'from' address, password to mailbox
    public static final String PREF = "myprefs";
    SharedPreferences.Editor editor;
    public static Handler myHandler;
    boolean micIsActive = false;   // Microphone activation flag
    SpeechRecognizer speechRecognizer;
    TextView textViewMessage;
    String mEmail, mSubject, mMessage, mFrom, mPassword, isDarkTheme;
    Button buttonMic, buttonSend;
    View parentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parentLayout = findViewById(android.R.id.content);
        textViewMessage = findViewById(R.id.txt);
        buttonSend =findViewById(R.id.btnSend);
        buttonMic = findViewById(R.id.btnMic);
        prefs = getSharedPreferences(PREF, Context.MODE_PRIVATE);
        mEmail = prefs.getString("to","");
        mSubject =prefs.getString("subject","");
        isDarkTheme = prefs.getString("isDarkTheme","");

<<<<<<< HEAD


        // In case of application starts first time show warning than redirect to initial setup screen
        if (mEmail.length() == 0 || mSubject.length() == 0) {
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setTitle(R.string.warning);
            alertDialog.setMessage(getResources().getString(R.string.initialSetup));
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getResources().getString(R.string.agree),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            // Set default subject
                            editor = prefs.edit();
                            editor.putString("subject", "NotaBene");
                            editor.commit();
                            // redirect to setup screed
                            callSetupMenu();
                        }
                    });
            alertDialog.show();
        }

        // check audio permissions for speech recogniser
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 1);
=======
        // Ask user for initial setup in case of application starts first time
        if (mEmail.length() == 0 || mSubject.length() == 0) {
            Toast.makeText(this, R.string.initialSetup,
                    Toast.LENGTH_LONG).show();
            // Set default subject
            editor = prefs.edit();
            editor.putString("subject","NotaBene");
            editor.commit();
            // call setup menu
            Intent intent = new Intent(this, Setup.class);
            this.startActivity(intent);
>>>>>>> 35a7748f08c7c4b7dac3d1f124450a9e291319e6
        }

        // switch Theme according to preferences
        if (Boolean.parseBoolean(isDarkTheme)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

<<<<<<< HEAD
=======
        // check audio permissions for speech recogniser
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 1);
        }

>>>>>>> 35a7748f08c7c4b7dac3d1f124450a9e291319e6
        // handler will processed feedback from JavaMailAPI thread
        myHandler = new Handler(Looper.getMainLooper()) {
            public void handleMessage(android.os.Message msg) {
                switch (msg.what) {
<<<<<<< HEAD
                    case STATUS_SENT:
                        // message has been sent successfully by JavaMailAPI
=======

                    case STATUS_SENT:
>>>>>>> 35a7748f08c7c4b7dac3d1f124450a9e291319e6
                        Snackbar.make(parentLayout, R.string.success, Snackbar.LENGTH_INDEFINITE).show();
                        textViewMessage.setText("");
                        buttonSend.setEnabled(true);
                        break;
                    case STATUS_ERROR:
<<<<<<< HEAD
                        // attempt of emailing by JavaMailAPI is failed. Message will be send with intent by default email application
                        emailIntent();
                        break;
                    case STATUS_NO_CLIENT:
                        // no default email application
=======
                        // this code will perform in case direct emailing by JavaMailAPI is impossible (for example in case of no internet)
                        // so message will be send with intent and default email application of android system
                        emailIntent();
                        break;
                    case STATUS_NO_CLIENT:
>>>>>>> 35a7748f08c7c4b7dac3d1f124450a9e291319e6
                        Snackbar.make(parentLayout, R.string.noclient, Snackbar.LENGTH_LONG).show();
                        break;
                    case STATUS_SENTBYCLIENT:
                        textViewMessage.setText("");
                        buttonSend.setEnabled(true);
                        break;
                    default:
                        break;
                }
            }
        };

        // Speech Recognizer will use system language
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        Intent speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        buttonMic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!micIsActive) {
<<<<<<< HEAD
                    // internet connection is required for speech recognition
=======
                    // check internet connection required for speech recognition
>>>>>>> 35a7748f08c7c4b7dac3d1f124450a9e291319e6
                    if (!isOnline(getApplicationContext())) {
                        Snackbar.make(parentLayout, R.string.noInternet, Snackbar.LENGTH_LONG).show();
                        return;
                    }
                    // start listening
                    micIsActive = true;
                    Snackbar.make(parentLayout, R.string.listening, Snackbar.LENGTH_INDEFINITE)
                            .setAction(R.string.stop, new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    // stop listening
                                    micIsActive = false;
                                    speechRecognizer.stopListening();
<<<<<<< HEAD
                                    Snackbar.make(parentLayout, R.string.listeningStopped, Snackbar.LENGTH_SHORT).show();
=======
                                    Snackbar.make(parentLayout, R.string.listeningStopped, Snackbar.LENGTH_SHORT)
                                            .show();
>>>>>>> 35a7748f08c7c4b7dac3d1f124450a9e291319e6
                                }
                            })
                            .show();
                    speechRecognizer.startListening(speechRecognizerIntent);
                } else {
                    // stop listening
                    micIsActive = false;
                    speechRecognizer.stopListening();
                    Snackbar.make(parentLayout, R.string.listeningStopped, Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
<<<<<<< HEAD
            public void onReadyForSpeech(Bundle bundle) { }

            @Override
            public void onBeginningOfSpeech() { }

            @Override
            public void onRmsChanged(float v) { }

            @Override
            public void onBufferReceived(byte[] bytes) { }

            @Override
            public void onEndOfSpeech() { }

            @Override
            public void onError(int i) { }
=======
            public void onReadyForSpeech(Bundle bundle) {            }

            @Override
            public void onBeginningOfSpeech() {            }

            @Override
            public void onRmsChanged(float v) {            }

            @Override
            public void onBufferReceived(byte[] bytes) {            }

            @Override
            public void onEndOfSpeech() {            }

            @Override
            public void onError(int i) {            }
>>>>>>> 35a7748f08c7c4b7dac3d1f124450a9e291319e6

            @Override
            public void onResults(Bundle results) {
                ArrayList<String> data =results.getStringArrayList(speechRecognizer.RESULTS_RECOGNITION);
                textViewMessage.setText(data.get(0));
                speechRecognizer.stopListening();
                micIsActive = false;
                Snackbar.make(parentLayout, R.string.listeningStopped, Snackbar.LENGTH_SHORT).show();
            }

            @Override
<<<<<<< HEAD
            public void onPartialResults(Bundle bundle) { }

            @Override
            public void onEvent(int i, Bundle bundle) { }
=======
            public void onPartialResults(Bundle bundle) {            }

            @Override
            public void onEvent(int i, Bundle bundle) {            }
>>>>>>> 35a7748f08c7c4b7dac3d1f124450a9e291319e6
        });

        // sending message
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // let's try to send message with JavaMailAPI
                // in case of it is impossible JavaMailAPI throws an error then
                // handler send the message by intent with default email client
                mMessage = textViewMessage.getText().toString();
<<<<<<< HEAD
                mEmail = prefs.getString("to","");
                if (mMessage.length() == 0){
                    // user forget create message
                    Snackbar.make(parentLayout, R.string.empty, Snackbar.LENGTH_SHORT).show();
                } else if (mEmail.length() == 0) {
                    // lack of "email to"
                    Snackbar.make(parentLayout, R.string.requiredField, Snackbar.LENGTH_SHORT).show();
                } else {
                    buttonSend.setEnabled(false);
                    // load data from preferences
                    prefs = getSharedPreferences(PREF, Context.MODE_PRIVATE);
=======
                if (mMessage.length()>0) {
                    buttonSend.setEnabled(false);
                    // load data rom preferences
                    prefs = getSharedPreferences(PREF, Context.MODE_PRIVATE);
                    mEmail = prefs.getString("to","");
>>>>>>> 35a7748f08c7c4b7dac3d1f124450a9e291319e6
                    mSubject =prefs.getString("subject","");
                    mFrom = prefs.getString("from","");
                    mPassword = prefs.getString("password", "");

                    // send message by JavaMAilAPI in new thread
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            JavaMailAPI javaMailAPI = new JavaMailAPI(MainActivity.this, mEmail, mSubject, mMessage, mFrom, mPassword);
                            javaMailAPI.send();
                        }
                    };
                    Thread thread = new Thread(runnable);
                    thread.start();
<<<<<<< HEAD
=======
                } else {
                    // in case of user forget create message
                    Snackbar.make(parentLayout, R.string.empty, Snackbar.LENGTH_SHORT).show();
>>>>>>> 35a7748f08c7c4b7dac3d1f124450a9e291319e6
                }
            }
        });
    }

<<<<<<< HEAD
    // call setup menu
    protected  void callSetupMenu() {
        Intent intent = new Intent(this, Setup.class);
        this.startActivity(intent);
    }

=======
>>>>>>> 35a7748f08c7c4b7dac3d1f124450a9e291319e6
    // sending email by intent with default email client
    protected void emailIntent() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{mEmail,});
        intent.putExtra(Intent.EXTRA_SUBJECT,mSubject);
        intent.putExtra(Intent.EXTRA_TEXT,mMessage);
        intent.setData(Uri.parse("mailto:"));

        try {
            startActivity(intent);
            myHandler.sendEmptyMessage(STATUS_SENTBYCLIENT);
        } catch (ActivityNotFoundException e) {
            myHandler.sendEmptyMessage(STATUS_NO_CLIENT);
        }
    }

<<<<<<< HEAD
    // inflating menu
=======
    // to inflate menu
>>>>>>> 35a7748f08c7c4b7dac3d1f124450a9e291319e6
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

<<<<<<< HEAD
    // processing menu item selected
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        callSetupMenu();
=======
    // to process menu item selected
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(this, Setup.class);
        this.startActivity(intent);
>>>>>>> 35a7748f08c7c4b7dac3d1f124450a9e291319e6
        return true;
    }

    // checking permission for microphone
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, R.string.permissionGranted, Toast.LENGTH_SHORT).show();
                buttonMic.setEnabled(true);
            } else {
                Toast.makeText(this, R.string.permissionDenied, Toast.LENGTH_SHORT).show();
                buttonMic.setEnabled(false);
            }
        }
    }

    // checking internet connection
    public static boolean isOnline(Context context)
    {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting())
        {
            return true;
        }
        return false;
    }
}