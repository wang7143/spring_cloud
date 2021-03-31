import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Method;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;


import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;

public class pdfToImage {

    public static void main(String[] args) throws IOException {
        String instructiopath = "D://chome下载//pdftoimage//aaaa.pdf";
        String picturepath = "D://chome下载//aaaa";
//        System.out.println(changePdfToImg(instructiopath, picturepath));
        pdfToImage(instructiopath);
    }
    public static int changePdfToImg(String instructiopath,String picturepath) {
        int countpage =0;
        try {
            //instructiopath ="D:/instructio/2015-05-16/Android 4编程入门经典.pdf"  
            //picturepath = "D:/instructio/picture/2015-05-16/";  

            File file = new File(instructiopath);
            RandomAccessFile raf = new RandomAccessFile(file, "r");
            FileChannel channel = raf.getChannel();
            MappedByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY,
                    0, channel.size());
            PDFFile pdffile = new PDFFile(buf);

            //创建图片文件夹  
            File dirfile = new File(picturepath);
            if(!dirfile.exists()){
                dirfile.mkdirs();
            }
            //获得图片页数  
            countpage = pdffile.getNumPages();
            for (int i = 1; i <= pdffile.getNumPages(); i++) {
                PDFPage page = pdffile.getPage(i);
                Rectangle rect = new Rectangle(0, 0, ((int) page.getBBox()
                        .getWidth()), ((int) page.getBBox().getHeight()));
                int n = 2;
                /** 图片清晰度（n>0且n<7）【pdf放大参数】 */
                Image img = page.getImage(rect.width * n, rect.height * n,
                        rect, /** 放大pdf到n倍，创建图片。 */
                        null, /** null for the ImageObserver */
                        true, /** fill background with white */
                        true /** block until drawing is done */
                );
                BufferedImage tag = new BufferedImage(rect.width * n,
                        rect.height * n, BufferedImage.TYPE_INT_RGB);
                tag.getGraphics().drawImage(img, 0, 0, rect.width * n,
                        rect.height * n, null);
                /**
                 * File imgfile = new File("D:\\work\\mybook\\FilesNew\\img\\" + 
                 * i + ".jpg"); if(imgfile.exists()){
                 * if(imgfile.createNewFile()) { System.out.println("创建图片："+ 
                 * "D:\\work\\mybook\\FilesNew\\img\\" + i + ".jpg"); } else {
                 * System.out.println("创建图片失败！"); } }
                 */
                FileOutputStream out = new FileOutputStream(picturepath+"/" + i
                        + ".png");
                /** 输出到文件流 */
                JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
                JPEGEncodeParam param2 = encoder.getDefaultJPEGEncodeParam(tag);
                param2.setQuality(1f, true);
                /** 1f~0.01f是提高生成的图片质量 */
                encoder.setJPEGEncodeParam(param2);
                encoder.encode(tag);
                /** JPEG编码 */
                out.close();
            }
            channel.close();
            raf.close();
            unmap(buf);
            /** 如果要在转图片之后删除pdf，就必须要这个关闭流和清空缓冲的方法 */
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return countpage;

    }

    @SuppressWarnings("unchecked")
    public static void unmap(final Object buffer) {
        AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                try {
                    Method getCleanerMethod = buffer.getClass().getMethod(
                            "cleaner", new Class[0]);
                    getCleanerMethod.setAccessible(true);
                    sun.misc.Cleaner cleaner = (sun.misc.Cleaner) getCleanerMethod
                            .invoke(buffer, new Object[0]);
                    cleaner.clean();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
    }

    public static void pdfToImage(String pdfPath) throws IOException {
        try {
            /*图像合并使用参数*/
            // 定义宽度
            int width = 0;
            // 保存一张图片中的RGB数据
            int[] singleImgRGB;
            // 定义高度，后面用于叠加
            int shiftHeight = 0;
            //保存每张图片的像素值
            BufferedImage imageResult = null;
            // 利用PdfBox生成图像
            PDDocument pdDocument = PDDocument.load(new File(pdfPath));
            PDFRenderer renderer = new PDFRenderer(pdDocument);
            /*根据总页数, 按照50页生成一张长图片的逻辑, 进行拆分*/
            // 每50页转成1张图片
            int pageLength = 22;
            // 总计循环的次数
            int totalCount = pdDocument.getNumberOfPages() / pageLength + 1;
            for (int m = 0; m < totalCount; m++) {
                for (int i = 0; i < pageLength; i++) {
                    int pageIndex = i + (m * pageLength);
                    if (pageIndex == pdDocument.getNumberOfPages()) {
                        System.out.println("m = " + m);
                        break;
                    }
                    // 96为图片的dpi，dpi越大，则图片越清晰，图片越大，转换耗费的时间也越多
                    BufferedImage image = renderer.renderImageWithDPI(pageIndex, 230, ImageType.GRAY);
                    int imageHeight = image.getHeight();
                    int imageWidth = image.getWidth();
                    if (i == 0) {
                        //计算高度和偏移量
                        //使用第一张图片宽度;
                        width = imageWidth;
                        // 保存每页图片的像素值
                        // 加个判断：如果m次循环后所剩的图片总数小于pageLength，则图片高度按剩余的张数绘制，否则会出现长图片下面全是黑色的情况
                        if ((pdDocument.getNumberOfPages() - m * pageLength) < pageLength) {
                            imageResult = new BufferedImage(width, imageHeight * (pdDocument.getNumberOfPages() - m * pageLength), BufferedImage.TYPE_INT_RGB);
                        } else {
                            imageResult = new BufferedImage(width, imageHeight * pageLength, BufferedImage.TYPE_INT_RGB);
                        }
                    } else {
                        // 将高度不断累加
                        shiftHeight += imageHeight;
                    }
                    singleImgRGB = image.getRGB(0, 0, width, imageHeight, null, 0, width);
                    imageResult.setRGB(0, shiftHeight, width, imageHeight, singleImgRGB, 0, width);
                }
                System.out.println("m = " + m);
                File outFile = new File(pdfPath.replace(".pdf",  m + ".jpg"));
                System.out.println(outFile.getName());
                // 写图片
                ImageIO.write(imageResult, "jpg", outFile);
                // 这个很重要，下面会有说明
                shiftHeight = 0;
            }
            pdDocument.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}