package com.gavin.rxkotlin.model;

import android.text.TextUtils;

import com.gavin.rxkotlin.R;
import com.gavin.rxkotlin.app.GavinApplication;
import com.gavin.rxkotlin.model.bean.ErrorInfo;
import com.gavin.rxkotlin.model.listener.OnDataListener;
import com.gavin.rxkotlin.model.listener.OnJsonDataListener;
import com.gavin.rxkotlin.model.listener.OnNoDataListener;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.request.RequestCall;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.net.SocketTimeoutException;
import java.util.Map;

import okhttp3.Call;

/**
 * User: Gavin
 * E-mail: GavinChangCN@163.com
 * Desc:
 * Date: 2016-05-30
 * Time: 16:32
 */
public class BaseApi {

    protected static final String TAG = "BaseApi";

    protected static final String URL_BASE = "http://192.168.0.12:8080/";

    public static final int METHOD_GET = 1;
    public static final int METHOD_POST = 2;
    public static final String PARAM_EQUAL = "=";
    public static final String PARAM_AND = "&";

    public static Map<String, String> sLoginParams;

    public static class AutoLoginRequest {
        public Type classType;
        public RequestCall requestCall;
        public OnDataListener onDataListener;
        public OnNoDataListener onNoDataListener;
        public OnJsonDataListener onJsonDataListener;
    }


    public StringCallback createCallback(final OnDataListener listener, final Type classOfT) {
        return new StringCallback() {

            @Override
            public void onError(Call call, Exception e) {
                ErrorInfo _error = null;
                if (e != null) {
                    if (e instanceof SocketTimeoutException && GavinApplication.Companion.getSContext() != null) {
                        _error = new ErrorInfo();
                        _error.setErrorMsg(GavinApplication.Companion.getSContext().getString(R.string.request_timeout));
                    } else if (!TextUtils.isEmpty(e.getMessage())) {
                        Gson _gson = new Gson();
                        try {
                            _error = _gson.fromJson(e.getMessage(), ErrorInfo.class);
                        } catch (JsonParseException jpe) {
                            jpe.printStackTrace();
                        }
                    }
                }
                listener.onError(_error);
            }

            @Override
            public void onResponse(String response) {
                ErrorInfo _error = null;
                try {
                    Gson _gson = new Gson();
                    JSONObject _jo = new JSONObject(response);
                    if (_jo.getBoolean("success")) {
                        String _data = _jo.has("data") ? _jo.getString("data") : null;
                        if (!TextUtils.isEmpty(_data)) {
                            listener.onSuccess(_gson.fromJson(_data, classOfT));
                            return;
                        }
                    } else {
                        _error = _gson.fromJson(_jo.toString(), ErrorInfo.class);
                        if (_error != null && _error.getErrorCode() != null && _error.getErrorCode().equals("0001")) {
                            return;
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                listener.onError(_error);
            }
        };
    }

    public StringCallback createCallback(final OnNoDataListener listener) {
        return new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                ErrorInfo _error = null;
                if (e != null) {
                    if (e instanceof SocketTimeoutException && GavinApplication.Companion.getSContext() != null) {
                        _error = new ErrorInfo();
                        _error.setErrorMsg(GavinApplication.Companion.getSContext().getString(R.string.request_timeout));
                    } else if (!TextUtils.isEmpty(e.getMessage())) {
                        Gson _gson = new Gson();
                        try {
                            _error = _gson.fromJson(e.getMessage(), ErrorInfo.class);
                        } catch (JsonParseException jpe) {
                            jpe.printStackTrace();
                        }
                    }
                }
                listener.onError(_error);
            }

            @Override
            public void onResponse(String response) {
                ErrorInfo _error = null;
                try {
                    JSONObject _jo = new JSONObject(response);
                    if (_jo.getBoolean("success")) {
                        listener.onSuccess();
                        return;
                    } else {
                        Gson _gson = new Gson();
                        _error = _gson.fromJson(_jo.toString(), ErrorInfo.class);
                        if (_error != null && _error.getErrorCode() != null && _error.getErrorCode().equals("0001")) {
                            return;
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                listener.onError(_error);
            }
        };
    }

    public StringCallback createCallback(final OnJsonDataListener listener) {
        return new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                ErrorInfo _error = null;
                if (e != null) {
                    if (e instanceof SocketTimeoutException && GavinApplication.Companion.getSContext() != null) {
                        _error = new ErrorInfo();
                        _error.setErrorMsg(GavinApplication.Companion.getSContext().getString(R.string.request_timeout));
                    } else if (!TextUtils.isEmpty(e.getMessage())) {
                        Gson _gson = new Gson();
                        try {
                            _error = _gson.fromJson(e.getMessage(), ErrorInfo.class);
                        } catch (JsonParseException jpe) {
                            jpe.printStackTrace();
                        }
                    }
                }
                listener.onError(_error);
            }

            @Override
            public void onResponse(String response) {
                ErrorInfo _error = null;
                try {
                    JSONObject _jo = new JSONObject(response);
                    if (_jo.getBoolean("success")) {
                        listener.onSuccess(_jo.getString("data"));
                        return;
                    } else {
                        Gson _gson = new Gson();
                        _error = _gson.fromJson(_jo.toString(), ErrorInfo.class);
                        if (_error != null && _error.getErrorCode() != null && _error.getErrorCode().equals("0001")) {
                            return;
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                listener.onError(_error);
            }
        };
    }

}
