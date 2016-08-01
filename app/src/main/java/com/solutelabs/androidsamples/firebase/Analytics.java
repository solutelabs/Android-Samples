package com.solutelabs.androidsamples.firebase;

import android.content.Context;
import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;

/**
 * Created by stllpt038 on 1/8/16.
 */

public class Analytics {

    /**
     * Custom Event
     */
    interface Event {
        String ADD_ITEM = "add_item";
        String UPDATE_ITEM = "update_item";
        String DELETE_ITEM = "delete_item";
    }

    /**
     * Custom Param
     */
    interface Param {
        String ACTION = "action";
        String LABEL = "label";

        interface Action {
            String AUTO = "auto";
            String CLICK = "click";
            String SWIPE = "swipe";
        }
    }

    /**
     * Custom User Properties
     */
    interface UserProperties {
        String PREFERENCE = "preference";
    }


    private static Analytics analytics;
    private FirebaseAnalytics firebaseAnalytics;

    /**
     * Initialize firebase analytics instance
     *
     * @param context App Context
     * @return {{@link Analytics}}
     */
    public static Analytics with(Context context) {
        if (analytics == null) {
            analytics = new Analytics(context.getApplicationContext());
        }
        return analytics;
    }

    /**
     * Get analytics event for send events
     *
     * @return {{@link Analytics}}
     */
    public static Analytics get() {
        if (analytics == null)
            throw new UnsupportedOperationException("Unable to get instance of Analytics - Did you missed to call Analytics.with(context) in you application class or before using this method.");
        return analytics;
    }


    /**
     * Initialization of Analytics
     *
     * @param context Application Context
     */
    private Analytics(Context context) {
        firebaseAnalytics = FirebaseAnalytics.getInstance(context);
    }

    /**
     * Set user properties for detailed firebaseAnalytics
     *
     * @param preference Preference set by user
     */
    public void setPreference(String preference) {
        firebaseAnalytics.setUserProperty(UserProperties.PREFERENCE, preference);
    }

    /**
     * Send event in Firebase analytics
     *
     * @param event Name of event
     * @param param Extra param for detailed event
     */
    public void event(String event, Bundle param) {
        firebaseAnalytics.logEvent(event, param);
    }

    /**
     * Helper class to call analytics events for Tutorial
     */
    public static final class Tutorial {
        /**
         * Helper method to call begin tutorial event
         */
        public static void begin() {
            get().event(FirebaseAnalytics.Event.TUTORIAL_BEGIN, null);
        }

        /**
         * Helper method to call compete tutorial event
         */
        public static void complete() {
            get().event(FirebaseAnalytics.Event.TUTORIAL_COMPLETE, null);
        }
    }

    public static final class Account {

        public static final String ACCOUNT = "account";

        public static void loginView() {
            Bundle bundle = new Bundle();
            bundle.putString(Param.ACTION, Param.Action.AUTO);
            bundle.putString(Param.LABEL, "Login View");
            get().event(FirebaseAnalytics.Event.LOGIN, bundle);
        }

        public static void loginStart() {
            Bundle bundle = new Bundle();
            bundle.putString(Param.ACTION, Param.Action.CLICK);
            bundle.putString(Param.LABEL, "Start Process");
            get().event(FirebaseAnalytics.Event.LOGIN, bundle);
        }

        public static void loginSuccess() {
            Bundle bundle = new Bundle();
            bundle.putString(Param.ACTION, Param.Action.AUTO);
            bundle.putString(Param.LABEL, "Success Response");
            get().event(FirebaseAnalytics.Event.LOGIN, bundle);
        }

        public static void loginFailed() {
            Bundle bundle = new Bundle();
            bundle.putString(Param.ACTION, Param.Action.AUTO);
            bundle.putString(Param.LABEL, "Failed Response");
            get().event(FirebaseAnalytics.Event.LOGIN, bundle);
        }

        public static void selectProfile() {
            Bundle bundle = new Bundle();
            bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, ACCOUNT);
            bundle.putString(Param.LABEL, "Profile");
            get().event(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
        }

        public static void logout() {
            Bundle bundle = new Bundle();
            bundle.putString(Param.ACTION, Param.Action.CLICK);
            bundle.putString(Param.LABEL, "Logout");
            get().event(FirebaseAnalytics.Event.LOGIN, bundle);
        }

    }

}
