package com.hc.config;

import org.bouncycastle.asn1.gm.GMNamedCurves;
import org.bouncycastle.asn1.gm.GMObjectIdentifiers;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.SM2Engine;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.*;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.jce.spec.ECPrivateKeySpec;
import org.bouncycastle.jce.spec.ECPublicKeySpec;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.encoders.Hex;

import java.math.BigInteger;
import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class Sm2Utils {


	 public static BouncyCastleProvider provider;
	// 椭圆曲线参数规格
	public static ECParameterSpec ecParameterSpec;
	// 获取椭圆曲线KEY生成器
	public static KeyFactory keyFactory;
	// 获取一条SM2曲线参数
	public static X9ECParameters sm2ECParameters = GMNamedCurves.getByName("sm2p256v1");
	public static String privateKey="a2081b5b81fbea0b6b973a3ab6dbbbc65b1164488bf22d8ae2ff0b8260f64853";
	public static String publicKey="04813d4d97ad31bd9d18d785f337f683233099d5abed09cb397152d50ac28cc0ba43711960e811d90453db5f5a9518d660858a8d0c57e359a8bf83427760ebcbba";

	 static{
	        try {
	            provider = new BouncyCastleProvider();
				sm2ECParameters = GMNamedCurves.getByName("sm2p256v1");
	            ecParameterSpec = new ECParameterSpec(sm2ECParameters.getCurve(),
						sm2ECParameters.getG(), sm2ECParameters.getN(), sm2ECParameters.getH());
	            keyFactory = KeyFactory.getInstance("EC", provider);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	
	private static void test() throws NoSuchAlgorithmException {
		//生成密钥对
		ECDomainParameters domainParameters = new ECDomainParameters(sm2ECParameters.getCurve(), sm2ECParameters.getG(), sm2ECParameters.getN());
		ECKeyPairGenerator keyPairGenerator = new ECKeyPairGenerator();
		keyPairGenerator.init(new ECKeyGenerationParameters(domainParameters, SecureRandom.getInstance("SHA1PRNG")));
		AsymmetricCipherKeyPair asymmetricCipherKeyPair = keyPairGenerator.generateKeyPair();

		//私钥，16进制格式，自己保存，格式如a2081b5b81fbea0b6b973a3ab6dbbbc65b1164488bf22d8ae2ff0b8260f64853
		BigInteger privatekey = ((ECPrivateKeyParameters) asymmetricCipherKeyPair.getPrivate()).getD();
		String privateKeyHex = privatekey.toString(16);
		System.out.println("私钥"+privateKeyHex);
		//公钥，16进制格式，发给前端，格式如04813d4d97ad31bd9d18d785f337f683233099d5abed09cb397152d50ac28cc0ba43711960e811d90453db5f5a9518d660858a8d0c57e359a8bf83427760ebcbba
		ECPoint ecPoint = ((ECPublicKeyParameters) asymmetricCipherKeyPair.getPublic()).getQ();
		String publicKeyHex = Hex.toHexString(ecPoint.getEncoded(false));
		System.out.println("公钥"+publicKeyHex);
	}

	public static String getInfoBySM2SM3(String password) throws InvalidCipherTextException {
		String temp=Sm2Utils.decryptData(password);
		String info=Sm2Utils.getBeforeStr(temp);
		String sm3=Sm2Utils.getAfterStr(temp);
		String sm3ymm = Sm3Utils.encrypt(info);
		return info;
	}

	//解密
	public static String decryptData(String cipherData) throws InvalidCipherTextException {
		//JS加密产生的密文
		//String cipherData = "043b698d696924034465b76f02a634b7e8d528eb013a6e23bf45eacdaf3a2e22a1dd9978da77fd287a837fc541a56358370bd975b338304e896846be008e0aa9860ed0da90e384fccd7b18e36e9dc62904ad95b1c51a17a580d1062f62bbb67f9d7bd398aa9b3fc3f0c657c62e6aef060580a29e05f46bb492c333853aab1ecdb132a5db70a8cb810afab581b5b4e04ccf83728fe3b0f4245e71d17f4a23794919b67ed6a2be3f67cd1c39d5e8d049c07a4052a670c75a69ee6e14b8956bed2f3abe03b4dc";
		byte[] cipherDataByte = Util.hexStringToBytes(cipherData);//Hex.decode(cipherData);
		//ECDomainParameters
		//刚才的私钥Hex，先还原私钥
		//String privateKey = "";//"a2081b5b81fbea0b6b973a3ab6dbbbc65b1164488bf22d8ae2ff0b8260f64853";
		BigInteger privateKeyD = new BigInteger(privateKey, 16);
		ECDomainParameters domainParameters = new ECDomainParameters(sm2ECParameters.getCurve(), sm2ECParameters.getG(), sm2ECParameters.getN());
		ECPrivateKeyParameters privateKeyParameters = new ECPrivateKeyParameters(privateKeyD, domainParameters);
		//用私钥解密
		SM2Engine sm2Engine = new SM2Engine();
		sm2Engine.init(false, privateKeyParameters);
		//processBlock得到Base64格式，记得解码
		byte[] arrayOfBytes = Base64.getDecoder().decode(sm2Engine.processBlock(cipherDataByte, 0, cipherDataByte.length));
		//System.out.println(Hex.toHexString(arrayOfBytes));
		String data = new String(arrayOfBytes);//4e617269403230323123
		return data;
	}

	//加密
	public static String encryptData(String cipherData) throws InvalidCipherTextException, NoSuchAlgorithmException {
		// 构造ECC算法参数，曲线方程、椭圆曲线G点、大整数N
		ECDomainParameters domainParameters = new ECDomainParameters(sm2ECParameters.getCurve(), sm2ECParameters.getG(), sm2ECParameters.getN());
		//提取公钥点
		ECPoint pukPoint = sm2ECParameters.getCurve().decodePoint(Hex.decode(publicKey));
		ECPublicKeyParameters publicKeyParameters = new ECPublicKeyParameters(pukPoint, domainParameters);
		SM2Engine sm2Engine = new SM2Engine();
		sm2Engine.init(true, new ParametersWithRandom(publicKeyParameters, SecureRandom.getInstance("SHA1PRNG")));
		byte[] arrayOfBytes = null;
		try {
			byte[] in = Base64.getEncoder().encode(cipherData.getBytes());
			arrayOfBytes = sm2Engine.processBlock(in, 0, in.length);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Hex.toHexString(arrayOfBytes);
	}


    /**
     * SM2算法生成密钥对
     *
     * @return 密钥对信息
     */
    public static KeyPair generateSm2KeyPair() throws InvalidAlgorithmParameterException, NoSuchAlgorithmException {
        final ECGenParameterSpec sm2Spec = new ECGenParameterSpec("sm2p256v1");
        // 获取一个椭圆曲线类型的密钥对生成器
        final KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC", provider);
        SecureRandom random = new SecureRandom();
        // 使用SM2的算法区域初始化密钥生成器
        kpg.initialize(sm2Spec, random);
        // 获取密钥对
        KeyPair keyPair = kpg.generateKeyPair();
        return keyPair;
    }
	
    /**
     * 签名
     *
     * @param plainText 待签名文本
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws InvalidKeyException
     * @throws SignatureException
     */
    public static String sign(String plainText) throws NoSuchAlgorithmException, InvalidKeySpecException,
            InvalidKeyException, SignatureException {
        // 创建签名对象
        Signature signature = Signature.getInstance(GMObjectIdentifiers.sm2sign_with_sm3.toString(), provider);
        // 将私钥HEX字符串转换为X值
        BigInteger bigInteger = new BigInteger(privateKey, 16);
        BCECPrivateKey privateKey = (BCECPrivateKey) keyFactory.generatePrivate(new ECPrivateKeySpec(bigInteger,
                ecParameterSpec));
        // 初始化为签名状态
        signature.initSign(privateKey);
        // 传入签名字节
        signature.update(plainText.getBytes());
        // 签名
        return Base64.getEncoder().encodeToString(signature.sign());
    }
    
    public static boolean verify(String plainText, String signatureValue) throws NoSuchAlgorithmException, InvalidKeySpecException,
    InvalidKeyException, SignatureException {
		// 创建签名对象
		Signature signature = Signature.getInstance(GMObjectIdentifiers.sm2sign_with_sm3.toString(), provider);
		// 将公钥HEX字符串转换为椭圆曲线对应的点
		ECPoint ecPoint = sm2ECParameters.getCurve().decodePoint(Hex.decode(publicKey));
		BCECPublicKey key = (BCECPublicKey) keyFactory.generatePublic(new ECPublicKeySpec(ecPoint, ecParameterSpec));
		// 初始化为验签状态
		signature.initVerify(key);
		signature.update(plainText.getBytes());
		return signature.verify(Base64.getDecoder().decode(signatureValue));
		}
    
	  public static void main(String[] args) throws Exception {

		  String doce=encryptData("{\"Datas\":[{\"station_name\":\"500kV星城变电站\",\"device_name\":\"星城#4T\",\"key_id\":\"41414671572B41456B41414F51592F414179\",\"warn_time\":\"2021-06-01 02:30:00\",\"warn_level\":\"预警\",\"device_type\":\"主变压器\",\"warn_source\":\"变压器-油温计异常告警\",\"warn_message\":\"油温2,(AB/AC/BC)相间温差绝对值超过15℃。主变最高油温:42.81。\",\"conf_mode\":1}]}");
		  System.out.println(doce);
          System.out.println(decryptData("049a58f45c3506b517eae3b2bc79aecb11e46db7b21bf61f7922884578d717dbea9487a00f3d03c0325384a274e2fb03a99dc891e4294af250de7b109a0891144bc3d51b1c98e979770d57377da6e9ffa12ddc41d65ae7030a6033b56ff9256530f8e5185b5e787742b7b154db6ba4f24bda448160112270fcd757a9c6204851e8180ae0adbc922df91c1f9e7ac5084f90bcc2d21957a087d9c81f3ddf198c084984d6235a4172503e49afbada2e534430f4f083a5719e8405f8269501977d17a741dafee678ce55e94fb779d727b1fec1fadaccdb048ea56059f102c6900d0e2e3c490f6eef186f1ce345992cf7d56a6593273cf1d30745cf9d0425960f27538abc88b1801eb0848106db26464664e930aae2e882a5171a62b2d334d150e6f280fa7b00eb73d6130650871205bfea881a4634969397201801742812f7e63a06ad4fccadbb730738ca8ec7cf79568dccaaf33a1af9266771d882b4c778c5553b7dfdd0cbbfcd98151b9d4b48114282a34e6ffa7cf471d848350229103e659976408a94ec1026ea07ea6d616e3f34107945fd25512ef95a72a9e13b865d485d70f0ab76c0271856373a515d3a97dce75d5273fda1f78b80bb3fdae6a7c716fb5b797c349e51ca972bd4d9917db1cefe6db0a616b343fb900e77d2d77a7db44b24ecf6ebb059a2cc67c2aafe9763ac9e94c0dc8fab2c17ebf7878361d94b2b5c4abc6dc2b5a2791056698b623854c912841a0ede1d4ec2d3d7b77288abf9d1bd5a2b0c4c4a2f65d982b7f975053a"));
//		  String aaa=decryptData("04236b9e1ccc619cbea912494d81fb9debee4066154863b1b91d15b7bac1a4f1c3ef8f690f7fa5e6058e4e051b976f8d468f37f7d5e830cce6fb6aec8c750d6f191d27a336c1814142a62a18db51a96f00114e734e0660e9acb54a798b006b4c881299e21dfdbd101d22192a77b95aa4c6133a1644b07bea1fe697668156b975e96da2571941cb117e57f929ed684ef62bd6c888cfa65800ff4443542d6c2970b6f41663e90a35c4e3062f8beecdb99dbe0be29b09f20f7cbb3eb5666f25801da7d1fd5711");
//		  System.out.println(aaa);
//		  String test=getInfoBySM2SM3("04236b9e1ccc619cbea912494d81fb9debee4066154863b1b91d15b7bac1a4f1c3ef8f690f7fa5e6058e4e051b976f8d468f37f7d5e830cce6fb6aec8c750d6f191d27a336c1814142a62a18db51a96f00114e734e0660e9acb54a798b006b4c881299e21dfdbd101d22192a77b95aa4c6133a1644b07bea1fe697668156b975e96da2571941cb117e57f929ed684ef62bd6c888cfa65800ff4443542d6c2970b6f41663e90a35c4e3062f8beecdb99dbe0be29b09f20f7cbb3eb5666f25801da7d1fd5711");
//		  System.out.println(test);
		  /* System.out.println("签名源数据：" + "蔡霸霸");
          String signStr = sign("蔡霸霸", "a2081b5b81fbea0b6b973a3ab6dbbbc65b1164488bf22d8ae2ff0b8260f64853");
          System.out.println("签名后数据：" + signStr);
          boolean verify = verify("蔡霸霸", signStr, "04813d4d97ad31bd9d18d785f337f683233099d5abed09cb397152d50ac28cc0ba43711960e811d90453db5f5a9518d660858a8d0c57e359a8bf83427760ebcbba");
          System.out.println("签名验证结果：" + verify);*/
		  /*String info="Nari@2021# 962f3a572a1e24badbaed662a8b3dd1d18e2058184dc8aa8eaa20526985feb63";
		  System.out.println(getAfterStr(info));
		  System.out.println(getBeforeStr(info).length());*/
	  }


	public static String getAfterStr(String str) {
		int pos = str.lastIndexOf("&");
		String extName = str.substring(pos+1);
		return extName;
	}

	public static String getBeforeStr(String str) {
		int pos = str.lastIndexOf("&");
		String extName = str.substring(0,pos);
		return extName;
	}

}
