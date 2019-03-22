package com.hepengju.java11.new11_keyagreement;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.spec.NamedParameterSpec;
import java.security.spec.XECPublicKeySpec;

import javax.crypto.KeyAgreement;

/**
 * JEP 324: Key Agreement with Curve25519 and Curve448
 * Curve25519 和 Curve448 算法的密钥协议
 * 
 * <pre>
 *  目的：
 *        * 密码学要求使用 Curve25519 和Curve448 是因为它们的安全性和性能。
 *        * JDK会增加两个新的接口XECPublicKey 和 XECPrivateKey。
 *        
 *  @See <a href="https://www.jianshu.com/p/ae60abb6752d">   
 *  @See <a href="http://openjdk.java.net/jeps/331">    
 *   
 * </pre>
 * @author WGR
 *
 */
public class _KeyAgreement {
    
    /**
     * 示例代码
     * @throws Exception
     */
    public void testKeyAgreement() throws Exception {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("XDH");
        NamedParameterSpec paramSpec = new NamedParameterSpec("X25519");
        kpg.initialize(paramSpec); // equivalent to kpg.initialize(255)
        // alternatively: kpg = KeyPairGenerator.getInstance("X25519")
        KeyPair kp = kpg.generateKeyPair();

        KeyFactory kf = KeyFactory.getInstance("XDH");
        BigInteger u = new BigInteger("9999999999999999999999999999999999999999999999999");
        XECPublicKeySpec pubSpec = new XECPublicKeySpec(paramSpec, u);
        PublicKey pubKey = kf.generatePublic(pubSpec);

        KeyAgreement ka = KeyAgreement.getInstance("XDH");
        ka.init(kp.getPrivate());
        ka.doPhase(pubKey, true);
        @SuppressWarnings("unused")
        byte[] secret = ka.generateSecret();
    }

}
