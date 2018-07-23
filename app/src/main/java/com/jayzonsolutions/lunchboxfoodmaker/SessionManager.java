package com.jayzonsolutions.lunchboxfoodmaker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.HashMap;

public class SessionManager {
    // User name (make variable public to access from outside)
    public static final String MAC_ADDRESS = "MAC_ADDRESS";
    // Email address (make variable public to access from outside)
    public static final String PASSWORD = "PASSWORD";
    // Sharedpref file name
    private static final String PREF_NAME = "JunaidPref";
    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    private static final String IS_ADMIN = "IS_ADMIN";

    private static final String USER_ID = "USER_ID";

    private static final String GROUP_ID = "GROUP_ID";

    private static final String USER_NAME = "USER_NAME";
    // Shared Preferences
    SharedPreferences pref;
    // Editor for Shared preferences
    Editor editor;
    // Context
    Context _context;
    // Shared pref mode
    int PRIVATE_MODE = 0;


    // Constructor
    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    /**
     * Create login session
     */
    public void createLoginSession(String MAC, String email, String admin, String user, String group, String name) {


        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref
        editor.putString(MAC_ADDRESS, MAC);

        // Storing email in pref
        editor.putString(PASSWORD, email);

        // IsAdmin in pref
        editor.putString(IS_ADMIN, admin);

        // IsAdmin in pref
        editor.putString(USER_ID, user);

        // IsAdmin in pref
        editor.putString(GROUP_ID, group);

        // IsAdmin in pref
        editor.putString(USER_NAME, name);


        // commit changes
        editor.commit();
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     */
    public void checkLogin() {
        // Check login status
        if (!this.isLoggedIn()) {
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, LoginActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }

    }


    /**
     * Get stored session data
     */
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(MAC_ADDRESS, pref.getString(MAC_ADDRESS, null));

        // user email id
        user.put(PASSWORD, pref.getString(PASSWORD, null));

        user.put(IS_ADMIN, pref.getString(IS_ADMIN, null));
        user.put(USER_ID, pref.getString(USER_ID, null));
        user.put(GROUP_ID, pref.getString(GROUP_ID, null));
        user.put(USER_NAME, pref.getString(USER_NAME, null));
        // return user
        return user;
    }

    /**
     * Clear session details
     */
    public void logoutUser() {
        // Clearing all data from Shared Preferences

        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, LoginActivity.class);
        // Closing all the Activities
        //  i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        //     i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        //Clear All Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
    }

    /**
     * Quick check for login
     **/
    // Get Login State
    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }
}
