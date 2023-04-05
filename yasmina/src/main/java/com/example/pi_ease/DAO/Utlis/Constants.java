package com.example.pi_ease.DAO.Utlis;

public class Constants {
    public static final String APPLICATION_NAME = "LENDRES";
    public static final String APPLICATION_BODY = "Incoming notification";
    public static final String DEFAULT_PASSWORD = "LENDRES2023%!";
    // Application media context
    public static final String ADD_USER_PICTURE_CONTEXT = "userPhoto";
    // Application emails subject
    public static final String MAIL_SUBJECT_USER_RESET_PASSWORD = "LENDRES - Reset your password";

    // push notification action
    public static final String RECEIVE_COMMUNICATION_ACTION = "RECEIVE_COMMUNICATION";
    public static final String CANCEL_NOTIFICATION_ACTION = "CANCEL_NOTIFICATION";
    public static final String CONFIRM_NOTIFICATION_ACTION = "CONFIRM_NOTIFICATION";
    public static final String DECLINE_NOTIFICATION_ACTION = "DECLINE_NOTIFICATION";
    public static final String LEAVE_NOTIFICATION_ACTION = "LEAVE_NOTIFICATION";
    public static final String NOTIF_ACTION_NAME = "ACTION";
    public static final String NOTIF_TOKEN_NAME = "TWILIO_TOKEN";
    public static final String NOTIF_TITLE_NAME = "title";
    public static final String NOTIF_BODY_NAME = "body";
    public static final String NOTIF_COMMUNICATION_TIME_NAME = "communicationDuration";
    public static final String NOTIF_COMMUNICATION_TYPE = "communicationType";

    private Constants() {}
}

