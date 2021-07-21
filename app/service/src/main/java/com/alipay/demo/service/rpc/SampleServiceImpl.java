package com.alipay.demo.service.rpc;

import com.alipay.demo.facade.rpc.SampleService;
import com.alipay.sofa.runtime.api.annotation.SofaService;
import com.alipay.sofa.runtime.api.annotation.SofaServiceBinding;
import org.springframework.stereotype.Component;

/**
 * 通过 {@link SofaService} 注解发布 RPC 服务
 */
@SofaService(bindings = { @SofaServiceBinding(bindingType = "tr") })
@Component
public class SampleServiceImpl implements SampleService {

    @Override
    public String hello() {
        return "欢迎使用 SOFABoot!";
    }
}
