import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class test {

    public static void main(String[] args) throws IOException {
        String host = "https://ocrapi-advanced.taobao.com";
        String path = "/ocrservice/advanced";
        String method = "POST";
        String appcode = "ab2f6a7977284ec783512b140ca439e9";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/json; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        File file = new File("a.json");

        File filer = new File("D://a.txt");
        PrintStream ps = new PrintStream(new FileOutputStream(filer));
        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            for (int j = 1; j < 397; j++) {
                String s = FileUtils.img_base64("D://chome下载//aaaa//"+ j + ".png");
                JSONObject requestObj = new JSONObject();
                requestObj.put("img",s);
                String bodys = requestObj.toString();
                HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);

                String s1 = EntityUtils.toString(response.getEntity());
//
                JSONObject a1 = JSONObject.parseObject(s1);
                String content = (String)a1.get("content");
//
                ps.append(content);// 在已有的基础上添加字符串
                System.out.println(j);
            }
//            String bodys = "{img:" + " D://chome下载//aaaa//" + 7 + ".png}";
//            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
//            System.out.println(response.toString());
//            //获取response的body
//            System.out.println("      ");
//            System.out.println(EntityUtils.toString(response.getEntity()));


        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            ps.close();
        }

    }

    public static String ConvertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error=" + e.toString());
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                System.out.println("Error=" + e.toString());
            }
        }
        return sb.toString();
    }
}
