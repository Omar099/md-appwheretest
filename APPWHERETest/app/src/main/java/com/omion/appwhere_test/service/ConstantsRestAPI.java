package com.omion.appwhere_test.service;

class ConstantsRestAPI {
    static final String URL_SERVER = "http://166.62.33.53:4590/";

    private static final String KEY_API = "api/";
    private static final String KEY_CONTROLLER_SESSION = "session/";

    static final String KEY_GET_LOGIN = KEY_API + KEY_CONTROLLER_SESSION + "login";
    static final String KEY_POST_REGISTER = KEY_API + KEY_CONTROLLER_SESSION + "register";

    static final String KEY_GET_MERCHANTS = "get-merchants";
    static final String KEY_POST_MERCHANTS = "register-merchant";

    static final String QUERY_EMAIL = "email";
    static final String QUERY_PASSWORD = "password";


}
