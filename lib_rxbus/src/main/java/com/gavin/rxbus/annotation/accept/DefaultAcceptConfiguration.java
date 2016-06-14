package com.gavin.rxbus.annotation.accept;

import android.os.Handler;

import java.util.concurrent.Executor;

/**
 * User: Gavin
 * E-mail: GavinChangCN@163.com
 * Desc:
 * Date: 2016-06-14
 * Time: 11:21
 */
public class DefaultAcceptConfiguration {

    protected static final String TAG = "DefaultAcceptConfiguration";

    public interface OnDefaultAcceptConfiguration {
        /**
         * 配置默认的Accept Executor
         *
         * @return
         */
        Executor applyAcceptExecutor();

        /**
         * 配置默认的Handler
         *
         * @return
         */
        Handler applyAcceptHandler();
    }

    private static DefaultAcceptConfiguration configuration;

    public static DefaultAcceptConfiguration getInstance() {
        if (null == configuration) {
            configuration = new DefaultAcceptConfiguration();
        }
        return configuration;
    }

    private OnDefaultAcceptConfiguration onDefaultAcceptConfiguration;

    public void registerAcceptConfiguration(OnDefaultAcceptConfiguration onDefaultAcceptConfiguration) {
        this.onDefaultAcceptConfiguration = onDefaultAcceptConfiguration;
    }

    private DefaultAcceptConfiguration() {
    }

    /**
     * 配置默认的Accept Executor
     *
     * @return
     */
    public Executor applyAcceptExecutor() {
        return null == onDefaultAcceptConfiguration ? null : onDefaultAcceptConfiguration.applyAcceptExecutor();
    }

    /**
     * 配置默认的Handler
     *
     * @return
     */
    public Handler applyAcceptHandler() {
        return null == onDefaultAcceptConfiguration ? null : onDefaultAcceptConfiguration.applyAcceptHandler();
    }

}
