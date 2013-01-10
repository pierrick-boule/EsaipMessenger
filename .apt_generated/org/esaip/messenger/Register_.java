//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations.
//


package org.esaip.messenger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.googlecode.androidannotations.api.BackgroundExecutor;
import com.googlecode.androidannotations.api.SdkVersionHelper;
import org.esaip.messenger.R.id;
import org.esaip.messenger.R.layout;
import org.esaip.messenger.rest.RestClient_;
import org.esaip.messenger.util.MyPrefs_;

public final class Register_
    extends Register
{

    private Handler handler_ = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        init_(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(layout.register);
    }

    private void init_(Bundle savedInstanceState) {
        sprefs = new MyPrefs_(this);
        myRestClient = new RestClient_();
    }

    private void afterSetContentView_() {
        progressBar = ((ProgressBar) findViewById(id.progressBar));
        editPassword = ((EditText) findViewById(id.editPassword));
        error = ((TextView) findViewById(id.error));
        editPassword2 = ((EditText) findViewById(id.editPassword2));
        editLogin = ((EditText) findViewById(id.editLogin));
        {
            View view = findViewById(id.bEnvoyer);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        Register_.this.envoyer();
                    }

                }
                );
            }
        }
        {
            View view = findViewById(id.bVider);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        Register_.this.vider();
                    }

                }
                );
            }
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        afterSetContentView_();
    }

    @Override
    public void setContentView(View view, LayoutParams params) {
        super.setContentView(view, params);
        afterSetContentView_();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        afterSetContentView_();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (((SdkVersionHelper.getSdkInt()< 5)&&(keyCode == KeyEvent.KEYCODE_BACK))&&(event.getRepeatCount() == 0)) {
            onBackPressed();
        }
        return super.onKeyDown(keyCode, event);
    }

    public static Register_.IntentBuilder_ intent(Context context) {
        return new Register_.IntentBuilder_(context);
    }

    @Override
    public void startError() {
        handler_.post(new Runnable() {


            @Override
            public void run() {
                try {
                    Register_.super.startError();
                } catch (RuntimeException e) {
                    Log.e("Register_", "A runtime exception was thrown while executing code in a runnable", e);
                }
            }

        }
        );
    }

    @Override
    public void startConnected() {
        handler_.post(new Runnable() {


            @Override
            public void run() {
                try {
                    Register_.super.startConnected();
                } catch (RuntimeException e) {
                    Log.e("Register_", "A runtime exception was thrown while executing code in a runnable", e);
                }
            }

        }
        );
    }

    @Override
    public void register(final String username, final String password) {
        BackgroundExecutor.execute(new Runnable() {


            @Override
            public void run() {
                try {
                    Register_.super.register(username, password);
                } catch (RuntimeException e) {
                    Log.e("Register_", "A runtime exception was thrown while executing code in a runnable", e);
                }
            }

        }
        );
    }

    public static class IntentBuilder_ {

        private Context context_;
        private final Intent intent_;

        public IntentBuilder_(Context context) {
            context_ = context;
            intent_ = new Intent(context, Register_.class);
        }

        public Intent get() {
            return intent_;
        }

        public Register_.IntentBuilder_ flags(int flags) {
            intent_.setFlags(flags);
            return this;
        }

        public void start() {
            context_.startActivity(intent_);
        }

    }

}
