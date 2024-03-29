//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations.
//


package org.esaip.messenger.util;

import android.content.Context;
import android.content.SharedPreferences;
import com.googlecode.androidannotations.api.sharedpreferences.EditorHelper;
import com.googlecode.androidannotations.api.sharedpreferences.SharedPreferencesHelper;
import com.googlecode.androidannotations.api.sharedpreferences.StringPrefEditorField;
import com.googlecode.androidannotations.api.sharedpreferences.StringPrefField;

public final class MyPrefs_
    extends SharedPreferencesHelper
{


    public MyPrefs_(Context context) {
        super(context.getSharedPreferences("MyPrefs", 0));
    }

    public MyPrefs_.MyPrefsEditor_ edit() {
        return new MyPrefs_.MyPrefsEditor_(getSharedPreferences());
    }

    public StringPrefField password() {
        return stringField("password", "");
    }

    public StringPrefField username() {
        return stringField("username", "");
    }

    public final static class MyPrefsEditor_
        extends EditorHelper<MyPrefs_.MyPrefsEditor_>
    {


        MyPrefsEditor_(SharedPreferences sharedPreferences) {
            super(sharedPreferences);
        }

        public StringPrefEditorField<MyPrefs_.MyPrefsEditor_> password() {
            return stringField("password");
        }

        public StringPrefEditorField<MyPrefs_.MyPrefsEditor_> username() {
            return stringField("username");
        }

    }

}
