package com.controller.test;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/test/alipay"})
public class AlipayTestController
{

  @Value("${alipayPublicKey}")
  private String alipayPublicKey;

  @Value("${alipayPrivateKey}")
  private String alipayPrivateKey;

  @Value("${alipayAppId}")
  private String alipayAppId;

  @Value("${alipayUrl}")
  private String alipayUrl;

  @RequestMapping({"toOneToOne"})
  public String toOneToOne()
  {
    return "/test/alipayTest";
  }
  @RequestMapping({"/oneToOne"})
  @ResponseBody
  public Map<String, Object> oneToOne(Long amount) { 
	Map<String, Object> map = new HashMap<String, Object>();
    StringBuffer logInfo = new StringBuffer();
    try {
      if (amount == null) {
        amount = Long.valueOf(100L);
      }
      AlipayClient alipayClient = new DefaultAlipayClient(this.alipayUrl, this.alipayAppId, this.alipayPrivateKey, "json", "utf8", this.alipayPublicKey, "RSA2");
      AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();
      request.setBizContent("{    \"out_biz_no\":\"" + 
        System.currentTimeMillis() + "\"," + 
        "    \"payee_type\":\"ALIPAY_LOGONID\"," + 
        "    \"payee_account\":\"ebajcd9192@sandbox.com\"," + 
        "    \"amount\":\"" + amount + "\"," + 
        "    \"payer_show_name\":\"上海交通卡退款测试\"," + 
        "    \"payee_real_name\":\"沙箱环境\"," + 
        "    \"remark\":\"转账备注\"," + 
        "  }");
      AlipayFundTransToaccountTransferResponse response = (AlipayFundTransToaccountTransferResponse)alipayClient.execute(request);
      String code = response.getCode();
      String msg = response.getSubMsg();
      if (response.isSuccess())
        logInfo.append("调用成功:" + amount + "元。。。XXXXXXXXX");
      else {
        logInfo.append("调用失败");
      }
      map.put("msg", logInfo + ";Code:" + code + ";msg:" + msg);
    } catch (Exception e) {
      e.printStackTrace();
      logInfo.append(e.toString());
    }
    return map;
  }
}