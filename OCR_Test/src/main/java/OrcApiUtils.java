

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

/**
 * 调用api接口工具类
 */
public class OrcApiUtils {

    // 封装身份证文字识别请求报文体
    public static String reqIdCardBody(String path, String side) throws Exception {
        // 获取base64数据
        String imgBase64 = FileUtils.img_base64(path);
        if (StringUtils.isEmpty(imgBase64)) {
            return "";
        }

        // configure配置
        JSONObject configObj = new JSONObject();
        // 身份证正面信息
        configObj.put("side", side);

        String config_str = configObj.toString();

        // 拼装请求body的json字符串
        JSONObject requestObj = new JSONObject();
        requestObj.put("image", imgBase64);
        if (configObj != null) {
            requestObj.put("configure", config_str);
        }
        String bodys = requestObj.toString();
        return bodys;
    }

}
