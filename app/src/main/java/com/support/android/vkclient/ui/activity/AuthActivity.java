package com.support.android.vkclient.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import com.support.android.vkclient.BuildConfig;
import com.support.android.vkclient.R;
import com.support.android.vkclient.domain.dto.Token;
import com.support.android.vkclient.ui.fragment.WebViewFragment;

import java.net.URLEncoder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class AuthActivity extends AppCompatActivity implements WebViewFragment.WebViewCallback {

    private static final String TAG = AuthActivity.class.getSimpleName();
    public static String redirect_url = "https://oauth.vk.com/blank.html";
    private SharedPreferences preferences;

    public static String getUrl(String api_id, String scope) {
        String url = "https://oauth.vk.com/authorize?client_id=" + api_id;

        url = url + "&display=mobile&scope="
                + scope + "&redirect_uri=" + URLEncoder.encode(redirect_url) + "&response_type=token"
                + "&v=" + URLEncoder.encode(BuildConfig.API_VERSION);

        return url;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        WebViewFragment fragment = WebViewFragment
                .newInstance(getUrl(BuildConfig.VK_APP_ID,
                        "friends,photos,status,wall,offline"));

        fragmentManager.beginTransaction()
                .add(R.id.vk_auth_container, fragment)
                .commit();
    }

    @Override
    public void handleUrl(String url) {
        try {
            if (url == null) {
                return;
            }

            if (url.startsWith(AuthActivity.redirect_url)) {
                if (!url.contains("error=")) {

                    try {
                        Token token = tryExtractAccessTokens(url);
                        saveToken(token);
                    } catch (Exception e) {
                        Log.d(TAG, "error on tryExtractAccessTokens: " + e);
                    }

                }
                Intent intent = new Intent(this, UserAccountActivity.class);
                startActivity(intent);
                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Token tryExtractAccessTokens(String url) throws Exception {
        Token accessToken = null;

        String correctUrl = url.replace("#", "?");
        Uri uri = Uri.parse(correctUrl);
        String userId = uri.getQueryParameter("user_id");
        String token = uri.getQueryParameter("access_token");

        if (userId != null && token != null) {
            accessToken = new Token(Long.parseLong(userId), token);
        }

        if (accessToken == null) {
            throw new Exception("Failed to parse redirect url " + url);
        }

        return accessToken;
    }

    private void saveToken(Token token) {
        preferences.edit()
                .putString("vk_token", token.getAccessToken())
                .putLong("vk_user_id", token.getOwnerId())
                .apply();
    }
}
