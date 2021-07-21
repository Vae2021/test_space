package com.alipay.demo.service.rpc;

import com.alipay.demo.facade.rpc.SampleService;
import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.alipay.sofa.runtime.api.annotation.SofaReferenceBinding;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 本接口用于演示RPC服务调用
 */
@RestController
@RequestMapping("/rpc")
public class RpcSampleController {

    @SofaReference(binding = @SofaReferenceBinding(bindingType = "tr"))
    private SampleService sampleService;

    @GetMapping
    public String helloRpc() {
        return sampleService.hello();
    }
}
