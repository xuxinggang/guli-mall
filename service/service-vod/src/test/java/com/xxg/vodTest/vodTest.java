package com.xxg.vodTest;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetAppInfosResponse;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import org.junit.Test;

import java.util.List;

/**
 * @author xxg
 * @version 1.0
 * @date 2020/8/31 14:11
 * @Description:  根据视频id，获取视频播放地址
 * @Params:
 */

public class vodTest {
    @Test
    public void test(){
        try {
            //1、根据视频id获取视频播放地址（进行初始化）
            DefaultAcsClient client = initSDK.initVodClient("LTAI4GEffgH19uMPoLig3D7f", "FhNgsJUYcajP7RRrpxAIk9m93YaY7f");
            //2、创建获取视频地址request和response
            GetPlayInfoRequest request = new GetPlayInfoRequest();
            GetPlayInfoResponse response = new GetPlayInfoResponse();
            //3、向request中设置视频id
            request.setVideoId("10976e7bf39e42b29d73d8799699ad0d");
            //4、调用初始化对象里面的方法，传递request，获取数据
            response = client.getAcsResponse(request);
            List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
            //播放地址
            for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
                System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
            }
        } catch (ClientException e) {
            e.printStackTrace();
        }


    }
}
