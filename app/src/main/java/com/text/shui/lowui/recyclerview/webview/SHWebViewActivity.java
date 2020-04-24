package com.text.shui.lowui.recyclerview.webview;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.text.shui.R;
import com.text.shui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * webview
 * https://www.jianshu.com/p/3c94ae673e2a
 */
public class SHWebViewActivity extends BaseActivity {

    @BindView(R.id.lay_webview)
    LinearLayout layWebview;

    WebView webview;
    public String url = "http://www.baidu.com";

    @Override
    public int getlayout() {
        return R.layout.activity_webview;
    }

    @Override
    public void initView() {
        setTitle("WebView");

//        防止内存泄露1
//        不在xml中定义 Webview ，而是在需要的时候在Activity中创建，并且Context使用 getApplicationgContext()
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        webview = new WebView(getApplicationContext());
        webview.setLayoutParams(params);
        layWebview.addView(webview);

        //方式1. 加载一个网页：
        webview.loadUrl("http://www.google.com/");

        //方式2：加载apk包中的html页面
        webview.loadUrl("file:///android_asset/test.html");

        //方式3：加载手机本地的html页面
        webview.loadUrl("content://com.android.htmlfileprovider/sdcard/test.html");

        // 方式4： 加载 HTML 页面的一小段内容
        // 参数说明：
        // 参数1：需要截取展示的内容
        // 内容里不能出现 ’#’, ‘%’, ‘\’ , ‘?’ 这四个字符，若出现了需用 %23, %25, %27, %3f 对应来替代，否则会出现异常
        // 参数2：展示内容的类型
        // 参数3：字节码
//        WebView.loadData(String data, String mimeType, String encoding)

        initSettings();
        initWebClient();
        initWebChromeClient();
    }

    /**
     * 辅助 WebView 处理 Javascript 的对话框,网站图标,网站标题等等。
     */
    private void initWebChromeClient() {
        webview.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                //获得网页的加载进度并显示
                if (newProgress < 100) {
                    String progress = newProgress + "%";
                    Log.e("webview",progress);
                } else {
                }
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                //获取Web页中的标题
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                //支持javascript的警告框
                new AlertDialog.Builder(SHWebViewActivity.this)
                        .setTitle("JsAlert")
                        .setMessage(message)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                result.confirm();
                            }
                        })
                        .setCancelable(false)
                        .show();
                return true;
            }

            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                //支持javascript的确认框
                new AlertDialog.Builder(SHWebViewActivity.this)
                        .setTitle("JsConfirm")
                        .setMessage(message)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                result.confirm();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                result.cancel();
                            }
                        })
                        .setCancelable(false)
                        .show();
// 返回布尔值：判断点击时确认还是取消
// true表示点击了确认；false表示点击了取消；
                return true;
            }

            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                //支持javascript输入框
                final EditText et = new EditText(SHWebViewActivity.this);
                et.setText(defaultValue);
                new AlertDialog.Builder(SHWebViewActivity.this)
                        .setTitle(message)
                        .setView(et)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                result.confirm(et.getText().toString());
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                result.cancel();
                            }
                        })
                        .setCancelable(false)
                        .show();

                return true;
            }
        });
    }

    /**
     * 处理各种通知 & 请求事件
     */
    private void initWebClient() {

        webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                //1.打开网页时不调用系统浏览器， 而是在本WebView中显示；
                // 在网页上的所有加载都经过这个方法,这个函数我们可以做很多操作。
                webview.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                //开始载入页面调用的，我们可以设定一个loading的页面，告诉用户程序在等待网络响应。
                Log.e("webview","加载中");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //在页面加载结束时调用。我们可以关闭loading 条，切换程序动作。
                Log.e("webview","加载结束");
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
                //在加载页面资源时会调用，每一个资源（比如图片）的加载都会调用一次。
            }

            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                super.onReceivedHttpError(view, request, errorResponse);
                // 这个方法在6.0才出现
                //6.0之前 我们可以在WebChromeClient()子类中可以重写他的onReceivedTitle()方法
                int statusCode = 0;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    statusCode = errorResponse.getStatusCode();
                    System.out.println("onReceivedHttpError code = " + statusCode);
                    if (404 == statusCode || 500 == statusCode) {
                        view.loadUrl("about:blank");// 避免出现默认的错误界面
                        view.loadUrl(url);
                    }
                }
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                //super.onReceivedSslError(view, handler, error);
                //默认的处理方式handler.cancel();，WebView变成空白页

                handler.proceed();//接受证书
            }
        });


    }

    private void initSettings() {
        //声明WebSettings子类
        WebSettings webSettings = webview.getSettings();

//如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
// 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）
// 在 onStop 和 onResume 里分别把 setJavaScriptEnabled() 给设置成 false 和 true 即可

//支持插件
//        webSettings.setPluginsEnabled(true);

//设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

//缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

//其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式


        // 特别注意：5.1以上默认禁止了https和http混用，以下方式是开启
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (webview != null && webview.canGoBack()) {
                webview.goBack();
                return true;
            }
        } else {
            if (webview != null) {
                layWebview.removeView(webview);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 防止内存泄漏
     * 在 Activity 销毁（ WebView ）的时候，
     * 先让 WebView 加载null内容，
     * 然后移除 WebView，
     * 再销毁 WebView，最后置空。
     */
    @Override
    protected void onDestroy() {
        if (webview != null) {
            webview.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            webview.clearHistory();

            ((ViewGroup) webview.getParent()).removeView(webview);
            webview.destroy();
            webview = null;
        }
        super.onDestroy();
    }
}
