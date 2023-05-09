### 摘要算法

**MD5信息摘要算法**(MD5 Message-Digest Algorithm)，一种被广泛使用的密码散列函数，可以产生出一个128位(16字节的散列值（hash value），用于确保信息传输完整一致。MD5由美国密码学家罗纳德·李维斯特Ronald Linn Rivest设计，于1992年公开，用以取代MD4算法。这套算法的程序在 RFC 1321 标准中被加以规范。1996年后该算法被证实存在弱点，可以被加以破解，对于需要高度安全性的数据，2004年，证实MD5算法无法防止碰撞，因此不适用于安全性认证，如SSL公开密钥认证或是数字签名等用途。

**安全散列算法**(Secure Hash Algorithm，缩写为SHA, 是一个密码散列函数家族，是FIPS联邦资料处理标准)所认证的安全散列算法。能计算出一个数字消息所对应到的，长度固定的字符串, 又称消息摘要的算法。且若输入的消息不同，它们对应到不同字符串的几率很高。
SHA家族的算法，由美国国家安全局NSA所设计，并由美国国家标准与技术研究院NIST发布，是美国的政府标准，其分别是：

- **[SHA-0](https://zh.wikipedia.org/wiki/SHA-0)**：1993年发布，当时称做安全散列标准(Secure Hash Standard)，发布之后很快就被NSA撤回，是SHA-1的前身。

- **[SHA-1](https://zh.wikipedia.org/wiki/SHA-1)**：1995年发布，SHA-1在许多安全协议中广为使用，包括TLS、GnuPG、SSH、S/MIME和IPsec，是MD5的后继者。但SHA-1的安全性在2010年以后已经不被大多数的加密场景所接受。2017年荷兰密码学研究小组CWI和Google正式宣布攻破了SHA-1。

- **[SHA-2](https://zh.wikipedia.org/wiki/SHA-2)**：2001年发布，包括SHA-224、SHA-256、SHA-384、SHA-512、SHA-512/224、SHA-512/256。SHA-2目前没有出现明显的弱点。虽然至今尚未出现对SHA-2有效的攻击，但它的算法跟SHA-1基本上仍然相似。

- **[SHA-3](https://zh.wikipedia.org/wiki/SHA-3)**：2015年正式发布，由于对MD5出现成功的破解，以及对SHA-0和SHA-1出现理论上破解的方法，国家标准暨技术研究院NIST感觉需要一个与之前算法不同的，可替换的加密散列算法，也就是现在的SHA-3。

目前来说，SHA-256和Md5摘要算法比较流行，SHA-256比Md5安全，但是目前两者都还是比较流行的，二者都是信息摘要算法，不是加密解密算法，这点需要注意。

### 常见用途
在我们普通的开发之中，MD5和SHA256是两种常用的算法， 主要用于方法的验签，比如我们的一个系统，提供了对外的API，但是要保证不是所有的人都可以访问或者是使用这个API，所以我们需要有一定的规则去限制，比如说我这个api是`getUserInfo`，方法的签名是`getUserInfo(String appId, String appKey, Integer uid， Integer localId, String requestTime, String sign )`，假如我们要面对的一个外部系统提供给我们的方法是这样的，而最后一个是sign，也就是方法需要的签名，一般而言api提供方会告诉你这个sign的签名的方法，比如，sign=Md5(appId + appkey.substring(2,18) + uid + localId + uid + localId + requestTime)，也就是按照这个方式去生成sign签名，也就是说我们在请求方法的时候要先自己按照他规定的方式去生成sign，然后再去请求，对方接收到请求，也会按照同样的方式去校验，看是否能够通过校验，否则就会返回签名失败的提示。至于sign的生成方式，没有统一的方式，按照接口提供方的要求即可。

### 在Java之中使用

我们一般是使用，有比较多的工具可以供我们使用，比如hutool工具，还有就是apache的commons-codec工具包，如果在spring的环境下面，我们还可以使用spring提供的DigestUtils，当然我们还可以自己去生成，一般而言，这种方式的重点就是比如我们要操作一个字符串，然后把它作为输入，然后计算取的它的信息摘要，然后把它转化成16进制即可。就使用而言是没有什么难度的，下面是代码层面的一些例子。

#### MD5生成的方式
```java
import org.apache.commons.codec.digest.DigestUtils;
/**
 * commons-codec
 */
public class CommonMd5Util {

    public static String md5String(String str) {
        String md5Str = DigestUtils.md5Hex(str);
        return md5Str;
    }
}
```

```java
import cn.hutool.crypto.SecureUtil;
/**
 * hutool的SecureUtil
 */
public class HutoolMd5Util {

    public static String md5String16(String str) {
        String md5Str = SecureUtil.md5().digestHex16(str);
        return md5Str;
    }

    public static String md5String32(String str) {
        String md5Str = SecureUtil.md5().digestHex(str);
        return md5Str;
    }
}
```

```java
import org.springframework.util.DigestUtils;
/**
 * spring的DigestUtils
 */
public class SpringMd5Util {

    public static String md5String(String str) {
        String md5Str = DigestUtils.md5DigestAsHex(str.getBytes());
        return md5Str;
    }
}

```

```java
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * 自己生成
 */
public class OwnMd5Util {
    public static String md5String(String str) {
        try {
            // 摘要算法
            MessageDigest md5 = MessageDigest.getInstance("md5");
            // 准备数据
            byte[] bytes = str.getBytes();
            // 计算
            byte[] digest = md5.digest(bytes);
            // 十六进制的字符
            char[] chars = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
            StringBuffer sb = new StringBuffer();
            // 处理成十六进制的字符串
            for (byte b : digest) {
                sb.append(chars[(b >> 4) & 15]);
                sb.append(chars[b & 15]);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
```

#### sha256生成的方式

```java
import org.apache.commons.codec.digest.DigestUtils;
/**
 * commons-codec
 */
public class CommonSha256Util {
    public static String sha256String(String str) {
        String md5Str = DigestUtils.sha256Hex(str);
        return md5Str;
    }
}
```

```java
import cn.hutool.crypto.SecureUtil;
/**
 * hutool的SecureUtil
 */
public class HutoolSha256Util {

    public static String sha256String(String str) {
        String sha256Str = SecureUtil.sha256(str);
        return sha256Str;
    }
}
```

```java
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * 自己生成
 */
public class OwnerSha256Util {

    public static String sha256String(String str) {
        MessageDigest messageDigest;
        String encodeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            encodeStr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodeStr;
    }

    private static String byte2Hex(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                // 1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }
}
```

#### 摘要算法与输出

```java
public class Md5ShaApplication {

    public static void main(String[] args) {
        System.out.println(HutoolMd5Util.md5String16("hello"));
        System.out.println(HutoolMd5Util.md5String32("hello"));
        System.out.println(SpringMd5Util.md5String("hello"));
        System.out.println(CommonMd5Util.md5String("hello"));
        System.out.println(OwnMd5Util.md5String("hello"));
        System.out.println(HutoolSha256Util.sha256String("hello"));
        System.out.println(OwnerSha256Util.sha256String("hello"));
        System.out.println(CommonSha256Util.sha256String("hello"));
    }
}
```

>bc4b2a76b9719d91
>5d41402abc4b2a76b9719d911017c592
>5d41402abc4b2a76b9719d911017c592
>5d41402abc4b2a76b9719d911017c592
>5D41402ABC4B2A76B9719D911017C592
>2cf24dba5fb0a30e26e83b2ac5b9e29e1b161e5c1fa7425e73043362938b9824
>2cf24dba5fb0a30e26e83b2ac5b9e29e1b161e5c1fa7425e73043362938b9824
>2cf24dba5fb0a30e26e83b2ac5b9e29e1b161e5c1fa7425e73043362938b9824





