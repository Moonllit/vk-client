package com.support.android.vkclient.domain.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.support.android.vkclient.BuildConfig;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class VkEndpoint {
    private static final String BASE_URL = "https://api.vk.com/method/";
    private final VkApi api;
    private final SharedPreferences preferences;

    @Inject
    public VkEndpoint(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(chain -> {
                    Request request = chain.request()
                            .newBuilder()
                            .url(chain.request().url().newBuilder()
                                    .addQueryParameter("access_token", preferences.getString("vk_token", ""))
                                    .addQueryParameter("v", BuildConfig.API_VERSION)
                                    .build())
                            .build();

                    return chain.proceed(request);
                })
                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        api = retrofit.create(VkApi.class);
    }

    public VkApi getApi() {
        return api;
    }
}
