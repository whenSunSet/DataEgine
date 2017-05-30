package com.example.administrator.policemap.dataEngin.requestAndResponse;

/**
 * Created by Administrator on 2017/5/23 0023.
 */
public interface RequestFlag{
    RequestType getRequestType();
    RequestLevel getRequestLevel();

    enum NONE implements RequestFlag{
        patrolAccountAction_login(RequestLevel.to_network),
        imageAction_updateApp(RequestLevel.to_network);

        RequestType mRequestType;
        RequestLevel mRequestLevel;
        NONE(RequestLevel requestLevel) {
            mRequestType = RequestType.NONE;
            mRequestLevel=requestLevel;
        }
        public RequestType getRequestType() {
            return mRequestType;
        }
        public RequestLevel getRequestLevel() {
            return mRequestLevel;
        }
    }

    enum GET implements RequestFlag{
        getAppConfig(RequestLevel.to_local),
        getUserConfig(RequestLevel.to_local),
        initAppData(RequestLevel.to_network);

        RequestType mRequestType;
        RequestLevel mRequestLevel;
        GET(RequestLevel requestLevel) {
            mRequestType = RequestType.GET;
            mRequestLevel=requestLevel;
        }
        public RequestType getRequestType() {
            return mRequestType;
        }
        public RequestLevel getRequestLevel() {
            return mRequestLevel;
        }
    }

    enum INSERT implements RequestFlag{
        patrolAccountAction_register(RequestLevel.to_network),
        patrolRecordAction_signIn(RequestLevel.to_network),
        updateAndGetAppConfig(RequestLevel.to_local),
        updateAndGetUserConfig(RequestLevel.to_local);

        RequestType mRequestType;
        RequestLevel mRequestLevel;
        INSERT(RequestLevel requestLevel) {
            mRequestType = RequestType.INSERT;
            mRequestLevel=requestLevel;
        }
        public RequestType getRequestType() {
            return mRequestType;
        }
        public RequestLevel getRequestLevel() {
            return mRequestLevel;
        }
    }
}