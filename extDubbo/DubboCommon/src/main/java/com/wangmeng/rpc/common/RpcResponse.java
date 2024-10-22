package com.wangmeng.rpc.common;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
//RPC通信消息的响应数据规则
public class RpcResponse {
    //响应的消息id
    private String responseId;
    //请求的消息id
    private String requestId;
    // 响应的消息是否成功
    private boolean success;
    // 响应的数据结果
    private Object result;
    // 如果有异常信息,在该对象中记录异常信息
    private Throwable throwable;
}
