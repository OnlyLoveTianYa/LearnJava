package com.onlylovetianya.encrypt;

public class TestEncrypt {

    public static void main(String[] args) {

        System.out.println(parseByte2HexStr("sso_cs_mstsc.exe-_-192.168.0.131-_-Administrator-_-bamboocloud@2017-_-3389+_+1920+_+1080".getBytes()));
        System.out.println(parseByte2HexStr("sso_cs_common.exe-_-C:\\Program Files (x86)\\KeePass Password Safe 2\\KeePass.exe-_-OnlyLoveTianYa-_-950120-_-[TITLE:Open Database - NewDatabase.kdbx]+_++_+[Name:m_tbPassword]+_+[Name:m_btnOK]".getBytes()));
        System.out.println(parseByte2HexStr("sso_cs_common.exe-_-C:\\Program Files (x86)\\Tencent\\QQ\\Bin\\QQScLauncher.exe-_-1169975059-_-950120ZHIwei-_-[CLASS:TXGuiFoundation; Title:QQ]+_+6+_+1+_+0".getBytes()));
        System.out.println(parseByte2HexStr("sso_bs_common.exe-_-https://passport.csdn.net-_-763149560@qq.com-_-950120-_-formId:fm1+_+id:username+_+id:password".getBytes()));
        System.out.println(parseByte2HexStr("sso_bs_common.exe-_-https://pan.baidu.com-_-18581518120-_-950120ZHIwei-_-formId:TANGRAM__PSP_4__formbuttonId:TANGRAM__PSP_4__submit+_+id:TANGRAM__PSP_4__userName+_+id:TANGRAM__PSP_4__password".getBytes()));
        System.out.println(parseByte2HexStr("sso_bs_common.exe-_-https://github.com/login-_-OnlyLoveTianYa-_-950120ZHIwei-_-0+_+id:login_field+_+id:password".getBytes()));
    }

    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     *
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
}
