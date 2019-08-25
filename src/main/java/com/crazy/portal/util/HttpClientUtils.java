package com.crazy.portal.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.helpers.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import javax.net.ssl.*;
import java.io.*;
import java.net.SocketTimeoutException;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
/**
 * Created by weiying on 2019/7/29.
 */
@Slf4j
public class HttpClientUtils {

    public static final int CONNECT_TIMEOUT=10000;
    public static final int READ_TIMEOUT=10000;
    public static final String CHARSET="UTF-8";
    public static final String PROTOCOL="TLS";
    private static HttpClient client = null;

    private static final String authString = "Basic UzAwMjA0NjkwMzM6Q1JNQFVuaXNvYw==";
    public static final String MIMETYPE_JSON = "application/json";

    static {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(128);
        cm.setDefaultMaxPerRoute(128);
        client = HttpClients.custom().setConnectionManager(cm).build();
    }

    public static String get(String url) throws IOException {
        return get(url, CHARSET,authString, null, null);
    }
    public static String post(String url, String body) throws IOException{
        return post(url, body, null, "application/json", "utf-8", CONNECT_TIMEOUT, READ_TIMEOUT);
    }
    public static String postHeader(String url, String params) throws IOException {
        return post(url, params, authString, MIMETYPE_JSON, null, null, null);
    }

    /**
     * 发送一个 Post 请求, 使用指定的字符集编码.
     *
     * @param url
     * @param body RequestBody
     * @param mimeType 例如 application/xml "application/x-www-form-urlencoded" a=1&b=2&c=3
     * @param charset 编码
     * @param connTimeout 建立链接超时时间,毫秒.
     * @param readTimeout 响应超时时间,毫秒.
     * @return ResponseBody, 使用指定的字符集编码.
     * @throws ConnectTimeoutException 建立链接超时异常
     * @throws SocketTimeoutException  响应超时
     * @throws Exception
     */
    public static String post(String url, String body,String header, String mimeType,String charset, Integer connTimeout, Integer readTimeout) throws IOException {
        HttpClient client = null;
        HttpPost post = new HttpPost(url);
        String result = "";
        try {
            if (StringUtils.isNotBlank(body)) {
                HttpEntity entity = new StringEntity(body, ContentType.create(mimeType, charset));
                post.setEntity(entity);
            }
            // 设置参数
            RequestConfig.Builder customReqConf = RequestConfig.custom();
            if (connTimeout != null) {
                customReqConf.setConnectTimeout(connTimeout);
            }
            if (readTimeout != null) {
                customReqConf.setSocketTimeout(readTimeout);
            }
            post.setConfig(customReqConf.build());
            if(header != null) {
                post.setHeader("Authorization", header);
            }
            HttpResponse res;
            if (url.startsWith("https")) {
                // 执行 Https 请求.
                client = createSSLInsecureClient();
                res = client.execute(post);
            } else {
                // 执行 Http 请求.
                client = HttpClientUtils.client;
                res = client.execute(post);
            }
            result = IOUtils.toString(res.getEntity().getContent(), charset);
        } catch (GeneralSecurityException e) {
            log.error("",e);
        } catch (ClientProtocolException ex) {
            log.error("",ex);
        } catch (IOException ec) {
            log.error("",ec);
        } finally {
            post.releaseConnection();
            if (url.startsWith("https") && client != null&& client instanceof CloseableHttpClient) {
                ((CloseableHttpClient) client).close();
            }
        }
        return result;
    }

    /**
     * 发送一个 GET 请求
     *
     * @param url
     * @param charset
     * @param connTimeout  建立链接超时时间,毫秒.
     * @param readTimeout  响应超时时间,毫秒.
     * @return
     * @throws ConnectTimeoutException   建立链接超时
     * @throws SocketTimeoutException   响应超时
     * @throws Exception
     */
    public static String get(String url, String charset, String header, Integer connTimeout,Integer readTimeout) throws IOException {

        HttpClient client = null;
        HttpGet get = new HttpGet(url);
        String result = "";
        try {
            // 设置参数
            RequestConfig.Builder customReqConf = RequestConfig.custom();
            if (connTimeout != null) {
                customReqConf.setConnectTimeout(connTimeout);
            }
            if (readTimeout != null) {
                customReqConf.setSocketTimeout(readTimeout);
            }
            get.setConfig(customReqConf.build());
            if(StringUtil.isNotEmpty(header)){
                get.setHeader("Authorization",header);
            }
            HttpResponse res = null;
            if (url.startsWith("https")) {
                // 执行 Https 请求.
                client = createSSLInsecureClient();
                if (null!=client) {
                    res = client.execute(get);
                }
            } else {
                //conn.setRequestProperty("Authorization", "Basic " + authStringEnc);
                // 执行 Http 请求.
                client = HttpClientUtils.client;
                res = client.execute(get);
            }
            if (null!=res) {
                result = IOUtils.toString(res.getEntity().getContent(), charset);
            }
        } catch (ClientProtocolException cpe) {
            throw cpe;
        } catch (IOException e) {
            log.error("",e);
        } catch (GeneralSecurityException se) {
            log.error("",se);
        } finally {
            get.releaseConnection();
            if (url.startsWith("https") && client != null && client instanceof CloseableHttpClient) {
                ((CloseableHttpClient) client).close();
            }
        }
        return result;
    }

    /**
     * 创建 SSL连接
     * @return
     * @throws GeneralSecurityException
     */
    private static CloseableHttpClient createSSLInsecureClient() throws GeneralSecurityException {
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                public boolean isTrusted(X509Certificate[] chain,String authType) throws CertificateException {
                    return true;
                }
            }).build();

            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new X509HostnameVerifier() {
                public void verify(String host, SSLSocket ssl) throws IOException {
                }

                public void verify(String host, X509Certificate cert)
                        throws SSLException {
                }

                public void verify(String host, String[] cns,
                                   String[] subjectAlts) throws SSLException {
                }

                public boolean verify(String arg0, SSLSession arg1) {
                    return arg0.equalsIgnoreCase(arg1.getPeerHost());
                }

            });

            return HttpClients.custom().setSSLSocketFactory(sslsf).build();

        } catch (GeneralSecurityException e) {
            throw e;
        }
    }
}
