package system_knowledge.coded;

import java.io.UnsupportedEncodingException;

public class Codec {


    /**
     *
     * 字符：  人们使用的记号，抽象意义上的一个符号。比如 "我"、"！！"、"A"、"B"。中文、符号、英文字母等都是字符
     *
     *
     *
     * 字符集：一个规则集合的名字、字符的集合。每个字符可能有自己规定的整数编号        （也叫字符码或者字码）
     *
     *        也就是说哪些汉字，字母和符号会被收入标准中。所包含“字符”的集合就叫做“字符集”
     *       （一套规定好的字符集合）
     *        例如：
     *        Unicode字符集：收录了中文、日语、韩语，标点符号，以及各国家的字符。
     *        ASCII字符集：只收录了英文字符。
     *
     *
     *
     *
     * 字符集编码：决定了如何将一个字符的整数编号对应到一个二进制的整数值。
     *            计算机中存储的字符到底使用什么二进制整数值来表示，就是字符集编码来决定的。
     *
     *            规定每个“字符”分别用一个字节还是多个字节存储，用哪些字节来存储，来表示，这个规定就叫做“编码”。
     *
     *            也可以说是字符集的实现方式，比如Unicode字符集，字符"我" 对应的编号为 25105（十进制），
     *            有UTF-8编码格式，可以把Unicode字符集的"我"编号为25105 对应映射到 E68891（十六进制），
     *            有UTF-16编码格式，可以把Unicode字符集的"我"编号为25105 对应映射到 FEFF6211 （十六进制）。
     *
     *            E68891和FEFF6211都可以用来表示抽象Unicode字符"我"。
     *
     *
     *            字符串（String）转换为字节串（byte[]）就是字符集编码的过程，反之同理
     *
     *      字符串→字节串 编码 （抽象表示字符转换到二进制实际存储值）
     *      字节串→字符串 解码 （二进制实际存储值转换到抽象表示字符串）
     *
     *
     *
     *      Java的字符串类 String 中的内容是 UNICODE 字符串；也就是存储了一组 UNICODE 整数编号  （char存储的是 UNICODE 整数编号，jdk1.9之后String里是byte数组，优化了存储空间）
     *
     */


    public static void main(String[] args) throws UnsupportedEncodingException {
        // UTF-8编码格式。（unicode字符集）

        // 客户端字符串
        String clientUTF8String = "我爱你";
        //编码，客户端字符串，使用UTF-8编码格式，转换为对应的原字节串
        byte[] clientUTF8StringBytes = clientUTF8String.getBytes("UTF-8");


        // ISO-8859-1编码格式。（ISO-8859-1字符集）

        //解码，把原字节串，使用ISO-8859-1编码格式，转换为对应的字符串
        String clientASCIIString = new String(clientUTF8StringBytes,"ISO-8859-1");
        //编码，把ISO-8859-1字符串，使用ISO-8859-1编码格式，转换为原字节串 （与上面的字节串同值！！）
        byte[] clientACSIIStringBytes = clientASCIIString.getBytes("ISO-8859-1");

        if (clientUTF8String.equals(clientASCIIString)){
            System.out.println("string equals ok");
        }

        String cUSB = "";
        for (byte b : clientUTF8StringBytes){
            cUSB += b;
        }
        String cASB = "";
        for (byte b : clientACSIIStringBytes){
            cASB += b;
        }

        if (cUSB.equals(cASB)){
            System.out.println("bytes equals ok");
        }


        // 解码，服务器接收原字节串，使用UTF-8编码格式，转换为对应的字符串
        String serverUTF8String = new String(clientACSIIStringBytes,"UTF-8");

        //字符串编码到字节串
        byte[] serverUTF8StringBytes = serverUTF8String.getBytes("UTF-8");
        //字节串解码到字符串
        String serverASCIIString = new String(serverUTF8StringBytes,"ISO-8859-1");
        //字符串编码到字节串
        byte[] serverACSSIIStringBytes = serverASCIIString.getBytes("ISO-8859-1");
        //解码得到数据
        String data = new String(serverACSSIIStringBytes,"UTF-8");
        System.out.println(data);
    }
}
