package cn.java68.util;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.Base64;
import com.qiniu.util.StringMap;
import com.qiniu.util.UrlSafeBase64;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.io.File;
import java.io.IOException;

/**
 * Author: wty
 * date: 2018/8/3 23:41
 */
public class QiniuCloudUtil {

    // 设置需要操作的账号的AK和SK
    private static final String ACCESS_KEY = "eGhj-pHW_daavswNDqnbNER3tOs8hEKejTofVvnr";
    private static final String SECRET_KEY = "c2LdKG3AvBn0UhYg7PFdBswVz6uG_oikmrvr99cl";

    // 要上传的空间
    private static final String bucketname = "picture";

    // 密钥
    private static final Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

    private static final String DOMAIN = "http://pdx11avb0.bkt.clouddn.com/";

    private static final String style = "imageView2/0/q/75|watermark/2/text/d3d3LmphdmE2OC5jbg==/font/5b6u6L2v6ZuF6buR/fontsize/500/fill/IzIzRDhDQQ==/dissolve/100/gravity/NorthWest/dx/10/dy/10";
    public static String getUpToken() {
        return auth.uploadToken(bucketname, null, 3600, new StringMap().put("insertOnly", 1));
    }

    // 普通上传
    public static String upload(File file, String fileName) throws IOException {
        // 创建上传对象
        UploadManager uploadManager = new UploadManager();
        try {
            // 调用put方法上传
            String token = auth.uploadToken(bucketname);
            if(StringUtil.isEmpty(token)) {
                System.out.println("未获取到token，请重试！");
                return null;
            }
            Response res = uploadManager.put(file, fileName, token);
            // 打印返回的信息
            System.out.println(res.bodyString());
            if (res.isOK()) {
                Ret ret = res.jsonToObject(Ret.class);
                //如果不需要对图片进行样式处理，则使用以下方式即可
                //return DOMAIN + ret.key;
                return DOMAIN + ret.key + "?" + style;
            }
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            System.out.println(r.toString());
            try {
                // 响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                // ignore
            }
        }
        return null;
    }
    // http://pd5cw3qos.bkt.clouddn.com/f1389362-b751-4287-b880-56edc6b96461

    //base64方式上传
    public static String put64image(byte[] base64, String key) throws Exception{
        String file64 = Base64.encodeToString(base64, 0);
        Integer l = base64.length;
        String url = "http://upload.qiniu.com/putb64/" + l + "/key/"+ UrlSafeBase64.encodeToString(key);
        //非华东空间需要根据注意事项 1 修改上传域名
        RequestBody rb = RequestBody.create(null, file64);
        Request request = new Request.Builder().
                url(url).
                addHeader("Content-Type", "application/octet-stream")
                .addHeader("Authorization", "UpToken " + getUpToken())
                .post(rb).build();
        //System.out.println(request.headers());
        OkHttpClient client = new OkHttpClient();
        okhttp3.Response response = client.newCall(request).execute();
        System.out.println(response);
        //如果不需要添加图片样式，使用以下方式
        //return DOMAIN + key;
        return DOMAIN + key + "?" + style;
    }


    // 普通删除(暂未使用以下方法，未测试)
    public void delete(String key) throws IOException {
        // 实例化一个BucketManager对象
        BucketManager bucketManager = new BucketManager(auth);
        // 此处的33是去掉：http://ongsua0j7.bkt.clouddn.com/,剩下的key就是图片在七牛云的名称
        key = key.substring(33);
        try {
            // 调用delete方法移动文件
            bucketManager.delete(bucketname, key);
        } catch (QiniuException e) {
            // 捕获异常信息
            Response r = e.response;
            System.out.println(r.toString());
        }
    }

    class Ret {
        public long fsize;
        public String key;
        public String hash;
        public int width;
        public int height;
    }
}