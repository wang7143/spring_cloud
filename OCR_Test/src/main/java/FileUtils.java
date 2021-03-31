

import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 关于文件处理的工具类
 */
public class FileUtils {

    /**
     * 将图片文件转为base64位的字符串
     * @param path
     * @return
     */
    public static String img_base64(String path) throws IOException {
        FileInputStream finputstream = null;
        /**
         *  对path进行判断，如果是本地文件就二进制读取并base64编码，如果是url,则返回
         */
        String imgBase64="";
        if (path.startsWith("http")){
            imgBase64 = path;
        }else {
            try {
                File file = new File(path);
                byte[] content = new byte[(int) file.length()];
                finputstream = new FileInputStream(file);
                finputstream.read(content);
                imgBase64 = new String(Base64.encodeBase64(content));
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(finputstream!=null){
                    finputstream.close();
                }
            }
        }
        return imgBase64;
    }

    public static void main(String[] args) throws IOException {
        String s = img_base64("D://chome下载//pdftoimage//aaaa0.jpg");
        System.out.println(s.length());
    }
}
