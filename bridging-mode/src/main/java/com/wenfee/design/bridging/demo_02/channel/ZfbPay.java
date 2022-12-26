package com.wenfee.design.bridging.demo_02.channel;

import com.wenfee.design.bridging.demo_02.mode.IPayMode;

import java.math.BigDecimal;

/**
 * @author Wenfee
 * @date 2022/12/26
 */
public class ZfbPay extends Pay {

    public ZfbPay(IPayMode payMode) {
        super(payMode);
    }

    @Override
    public String transfer(String uId, String tradeId, BigDecimal amount) {
        logger.info("模拟支付宝渠道支付划账 <开始> uId: {}; tradeId: {}; amount: {}", uId, tradeId, amount);
        boolean security = payMode.security(uId);
        logger.info("模拟支付宝渠道支付风控 <校验>. uId: {}; tradeId: {}; security: {}", uId, tradeId, security);
        if (!security) {
            logger.info("模拟支付宝渠道支付划账 <拦截>. uId: {}; tradeId: {}; amount: {}", uId, tradeId, amount);
            return "0001";
        }
        logger.info("模拟支付宝渠道支付划账<成功>. uId: {}; tradeId: {}; amount: {}", uId, tradeId, amount);
        return "0000";
    }

}
