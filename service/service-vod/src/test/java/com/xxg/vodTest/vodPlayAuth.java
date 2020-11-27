package com.xxg.vodTest;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.junit.Test;

import java.util.List;

/**
 * @author xxg
 * @version 1.0
 * @date 2020/8/31 15:14
 * @Description:根据视频id，获取视频播放凭证，可以播放加密地址
 * @Params:
 */
public class vodPlayAuth {

    @Test
    public void test(){
        try {
            //1、根据视频id获取视频播放地址（进行初始化）
            DefaultAcsClient client = initSDK.initVodClient("LTAI4GEffgH19uMPoLig3D7f", "FhNgsJUYcajP7RRrpxAIk9m93YaY7f");
            //2、创建获取视频地址request和response
            GetVideoPlayAuthRequest request=new GetVideoPlayAuthRequest();
            GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
            //3、向request中设置视频id
            request.setVideoId("ad71ad19a17a425fa03285a190f22e17");
            //4、调用初始化对象里面的方法，传递request，获取数据
            response = client.getAcsResponse(request);
            //播放凭证
            System.out.print("PlayAuth = " + response.getPlayAuth() + "\n");
            //VideoMeta信息
            System.out.print("VideoMeta.Title = " + response.getVideoMeta().getTitle() + "\n");
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
