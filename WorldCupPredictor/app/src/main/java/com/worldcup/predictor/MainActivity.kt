package com.worldcup.predictor

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var prefs: SharedPreferences
    private var pendingClearHistory = false

    private val homeUrl = "file:///android_asset/home.html"
    private val authUrl = "file:///android_asset/auth.html"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prefs = getSharedPreferences("wc_auth", Context.MODE_PRIVATE)

        webView = findViewById(R.id.webView)

        val settings: WebSettings = webView.settings
        settings.javaScriptEnabled = true
        settings.domStorageEnabled = true
        settings.databaseEnabled = true
        settings.allowFileAccess = true
        settings.allowContentAccess = true
        settings.allowFileAccessFromFileURLs = true
        settings.allowUniversalAccessFromFileURLs = true
        settings.loadWithOverviewMode = true
        settings.useWideViewPort = true
        settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW

        webView.addJavascriptInterface(AndroidBridge(), "Android")

        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                // Wipe the back stack after an auth transition so the user can never
                // navigate back into the login/home page they just left.
                if (pendingClearHistory) {
                    pendingClearHistory = false
                    view?.clearHistory()
                }
            }
        }

        // Native decides the entry point: logged in -> straight to home, else login.
        webView.loadUrl(if (isLoggedIn()) homeUrl else authUrl)
    }

    private fun isLoggedIn(): Boolean = prefs.getString("user", null) != null

    /** Bridge exposed to the WebView JS as `Android`. */
    inner class AndroidBridge {
        @JavascriptInterface
        fun login(email: String, name: String) {
            prefs.edit().putString("user", email).putString("name", name).apply()
            runOnUiThread {
                pendingClearHistory = true
                webView.loadUrl(homeUrl)
            }
        }

        @JavascriptInterface
        fun logout() {
            prefs.edit().clear().apply()
            runOnUiThread {
                pendingClearHistory = true
                webView.loadUrl(authUrl)
            }
        }

        @JavascriptInterface
        fun getName(): String = prefs.getString("name", "") ?: ""
    }

    override fun onBackPressed() {
        val url = webView.url
        // Home বা Auth স্ক্রিনে থাকলে অ্যাপ বন্ধ হবে (URL এ প্যারামিটার থাকলেও কাজ করবে)
        if (url != null && (url.contains("home.html") || url.contains("auth.html"))) {
            finish()
        } else if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
