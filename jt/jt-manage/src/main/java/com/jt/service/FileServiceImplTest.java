package com.jt.service;

import com.jt.vo.ImageVO;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


public class FileServiceImplTest implements FileService{
    private String rootDirPath = "D:/JT-SOFT/images";

    //1.2准备图片的集合    包含了所有的图片类型
    private static Set<String> imageTypeSet;

    static {
        imageTypeSet = new HashSet<>();
        imageTypeSet.add(".jpg");
        imageTypeSet.add(".png");
        imageTypeSet.add(".gif");


    }

    /**
     * 完善的校验的过程
     * 1. 校验是否为图片
     * 2. 校验是否为恶意程序
     * 3. 防止文件数量太多,分目录存储.
     * 4. 防止文件重名
     * 5. 实现文件上传.
     *
     * @param uploadFile
     * @return
     */
    public ImageVO upload(MultipartFile uploadFile) {
        //校验图片类型
        //1.1 获取当前图片的名称 之后截取其中的类型
        String fileName = uploadFile.getOriginalFilename();
        int index = fileName.lastIndexOf(".");
        String fileType = fileName.substring(index);
        //将数据转化为小写
        fileType = fileType.toLowerCase();
        //1.3   判断图片类型是否正确
        if (!imageTypeSet.contains(fileType)) {
            //图片类型不匹配
            return ImageVO.fail();
        }
        //2.校验是否为恶意程序 根据宽度|高度进行判断
        try {
            //2,1 理工工具API对象 读取字节信息，获取图片对象类型
            BufferedImage bufferedImage = ImageIO.read(uploadFile.getInputStream());
            //2.2 校验是否有宽度和高度
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();
            if (width == 0 || height == 0) {
                return ImageVO.fail();
            }
            //3. 分目录存储  yyyy/MM/dd  分隔
            //3.1 将时间按照指定的格式进行转为为字符串
            String dateDir = new SimpleDateFormat("/yyyy/MM/dd/")
                    .format(new Date());
            //3.2 拼接文件存储的目录对象
            String fileDirPath=rootDirPath+dateDir;
            File dirFile = new File(fileDirPath);
            //3.3 动态创建目录
            if(!dirFile.exists()){
                dirFile.mkdirs();
            }

            //4.防止文件重名  uuid.jpg    动态拼接
            //4.1 动态生成uuid  实现文件名称拼接    名.后缀
            String uuid=UUID.randomUUID().toString().replace("-", ".");
            String realFileName=uuid+fileType;

            //5. 实现文件上传
            //5.1 拼接文件真实路径 dir/文件名称
            String realFilePath=fileDirPath+realFileName;
            //5.2封装对象   实现上传
            File realFile=new File(realFilePath);
            uploadFile.transferTo(realFile);

            //实现文件上传成功！
            String url="https://img14.360buyimg.com/n0/jfs/t1/45882/22/7027/53284/5d49358aE9c25c1bd/fb7365463f6a1a7b.jpg";
            return ImageVO.success(url,width, height);
        } catch (IOException e) {
            e.printStackTrace();
            return ImageVO.fail();
        }


    }
}

