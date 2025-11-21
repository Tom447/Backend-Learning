package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperateLog {

    private Long id; //ID, 主键
    private String url; //请求URL地址
    private String targetClass; //目标类类名
    private String targetMethod; //目标方法名
    private String methodParams; //方法运行参数
    private String methodReturn; //方法运行返回值, json格式字符串
    private String clientBrowser; //客户端浏览器信息
    private String clientPlatform; //客户端操作系统名称
    private LocalDateTime operateTime; //操作时间
    private Long costTime; //操作耗时

}
