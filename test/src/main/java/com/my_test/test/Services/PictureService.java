package com.my_test.test.Services;

import com.my_test.test.Entities.CommentEntity;
import com.my_test.test.Entities.PictureEntity;
import com.my_test.test.Entities.UserEntity;
import org.apache.commons.io.FilenameUtils;
import com.my_test.test.Repositories.CommentRepository;
import com.my_test.test.Repositories.PictureRepository;
import com.my_test.test.Repositories.UserRepository;
import com.my_test.test.Request.PictureGetByCategoryRequest;
import com.my_test.test.Request.PictureGetInfoRequest;
import com.my_test.test.Request.PictureUploadRequest;
import com.my_test.test.Response.PictureGetByCategoryResponse;
import com.my_test.test.Response.PictureGetInfoResponse;
import com.my_test.test.Response.PictureUploadResponse;
import com.obs.services.ObsClient;
import com.obs.services.model.PutObjectRequest;
import com.obs.services.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class PictureService {

    @Autowired
    private PictureRepository pictureRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Value("${obs.endpoint}")
    private String obsEndpoint;

    @Value("${obs.ak}")
    private String obsAk;

    @Value("${obs.sk}")
    private String obsSk;

    public PictureUploadResponse uploadImage(PictureUploadRequest request) {
        MultipartFile imageFile = request.getMultipartFile();
        try {
            if (imageFile.isEmpty()) {
                return new PictureUploadResponse(null,null,false,"上传失败，图片文件为空！");
            }

            // 将图片文件保存到OBS，并返回图片URL
            String imageUrl = saveImageToObs(imageFile);

            // 创建并保存图片信息到数据库
            PictureEntity pictureEntity = new PictureEntity();
            pictureEntity.setUser(request.getUserEntity());
            pictureEntity.setUrl(imageUrl);
            pictureEntity.setUploadTime(request.getUploadTime() != null ? request.getUploadTime() : Instant.from(LocalDateTime.now()));
            pictureEntity.setCreationIntent(request.getCreationIntent());
            pictureEntity.setCategory(request.getCategory());
            pictureRepository.save(pictureEntity);

            // 返回成功响应
            return new PictureUploadResponse(request.getUserEntity(),pictureEntity, true, "图片上传成功！");
        } catch (IOException e) {
            return new PictureUploadResponse(null,null,false,"上传失败: " + e.getMessage());
        }
    }

    private String saveImageToObs(MultipartFile imageFile) throws IOException {
        String fileName = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(imageFile.getOriginalFilename());
        ObsClient obsClient = new ObsClient(obsAk, obsSk, obsEndpoint);
        String folderName = "picture/"; // 文件夹名称
        String objectKey = folderName + fileName; // 完整的对象键，包含文件夹路径
        PutObjectRequest request = new PutObjectRequest("mydemo2", objectKey, imageFile.getInputStream());
        PutObjectResult result = obsClient.putObject(request);
        if (result.getStatusCode() != 200) {
            throw new IOException("存储失败: " + result.getStatusCode());
        }

        // 请替换为实际的桶名和域名
        return "https://mydemo2.obs.cn-north-4.myhuaweicloud.com/" + objectKey;
    }


    // 图像的主页展示逻辑
    public PictureGetByCategoryResponse getPictureByCategory(PictureGetByCategoryRequest request) {
        List<PictureEntity> pictures = pictureRepository.findByCategory(request.getPictureCategory());
        // 打乱图片列表以随机化结果
        Collections.shuffle(pictures, new Random());
        // 如果图片数量超过10张，则只选取前10张图片展示
        pictures = pictures.size() <= 10 ? pictures : pictures.subList(0, 10);
        return new PictureGetByCategoryResponse(pictures, true, "获取分类图片成功");
    }


    // 图像的详情页面逻辑
    public PictureGetInfoResponse getPictureInfo(PictureGetInfoRequest request) {
        Optional<PictureEntity> pictureOptional = pictureRepository.findById(request.getImageId());
        if (pictureOptional.isPresent()) {
            PictureEntity picture = pictureOptional.get();
            UserEntity user=userRepository.findByAccountName(picture.getUser().getAccountName());
            List<CommentEntity> comments = commentRepository.findByPictureImageId(picture.getImageId());
            return new PictureGetInfoResponse(user, picture,comments, true, "获取图片详情成功");
        } else {
            return new PictureGetInfoResponse(null, null,null ,false, "图片不存在");
        }
    }

    public PictureEntity likePicture(Long imageId) {
        Optional<PictureEntity> pictureOpt = pictureRepository.findById(imageId);
        if (pictureOpt.isPresent()) {
            PictureEntity picture = pictureOpt.get();
            picture.setLikesCount(picture.getLikesCount() + 1);
            return pictureRepository.save(picture);
        } else {
            throw new IllegalArgumentException("图片不存在");
        }
    }
}
