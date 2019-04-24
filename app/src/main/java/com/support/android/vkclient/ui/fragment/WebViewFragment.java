package com.support.android.vkclient.ui.fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.support.android.vkclient.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class WebViewFragment extends Fragment {

    public interface WebViewCallback {
        void handleUrl(String url);
    }

    private String url;
    @Nullable
    private WebViewCallback callback;

    public static WebViewFragment newInstance(String url) {
        WebViewFragment webViewFragment = new WebViewFragment();

        Bundle args = new Bundle();
        args.putString("url", url);
        webViewFragment.setArguments(args);

        return webViewFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (WebViewCallback) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity doesn't implement interface " + WebViewCallback.class.getSimpleName());
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            url = getArguments().getString("url", "");
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_web_view, container, false);

        WebView webView = v.findViewById(R.id.auth_web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.clearCache(true);
        webView.setWebViewClient(new MyWebViewClient());

        webView.loadUrl(url);

        return v;
    }

    @Override
    public void onDetach() {
        callback = null;
        super.onDetach();
    }

    class MyWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            if (callback != null) {
                callback.handleUrl(url);
            }
        }
    }
}
