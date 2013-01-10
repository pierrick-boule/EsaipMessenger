//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations.
//


package org.esaip.messenger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import com.googlecode.androidannotations.api.SdkVersionHelper;
import org.esaip.messenger.R.id;
import org.esaip.messenger.R.layout;
import org.esaip.messenger.util.MyPrefs_;

public final class Home_
    extends Home
{


    @Override
    public void onCreate(Bundle savedInstanceState) {
        init_(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(layout.home);
    }

    private void init_(Bundle savedInstanceState) {
        sprefs = new MyPrefs_(this);
    }

    private void afterSetContentView_() {
        buttonEnvoi = ((Button) findViewById(id.buttonEnvoi));
        buttonList = ((Button) findViewById(id.buttonList));
        {
            View view = findViewById(id.buttonEnvoi);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        Home_.this.envoi();
                    }

                }
                );
            }
        }
        {
            View view = findViewById(id.buttonList);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        Home_.this.liste();
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

    public static Home_.IntentBuilder_ intent(Context context) {
        return new Home_.IntentBuilder_(context);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(org.esaip.messenger.R.menu.menuconnected, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean handled = super.onOptionsItemSelected(item);
        if (handled) {
            return true;
        }
        switch (item.getItemId()) {
            case id.deco:
                decoSelected();
                return true;
            default:
                return false;
        }
    }

    public static class IntentBuilder_ {

        private Context context_;
        private final Intent intent_;

        public IntentBuilder_(Context context) {
            context_ = context;
            intent_ = new Intent(context, Home_.class);
        }

        public Intent get() {
            return intent_;
        }

        public Home_.IntentBuilder_ flags(int flags) {
            intent_.setFlags(flags);
            return this;
        }

        public void start() {
            context_.startActivity(intent_);
        }

    }

}
