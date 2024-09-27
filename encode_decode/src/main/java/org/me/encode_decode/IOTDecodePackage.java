package org.me.encode_decode;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import jakarta.xml.bind.DatatypeConverter;

import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author sangnk
 * @Created 26/09/2024 - 10:06 SA
 * @project = java_tool
 * @_ Mô tả:
 */
public class IOTDecodePackage {
    public void decodeTest() {
        String raw = "283530303130303031393379a47d65799a7daa868681a47d867caf7da0696f7e827d6281a277b379a279a97d8977b1869e6d7683808e5c8e9675a67f8b80a481807aa57d976d6d7d8479607d9a71ab798479a97f29";
        ByteBuf buf = Unpooled.wrappedBuffer(ByteBufUtil.decodeHexDump(raw));
        String textRemovedStart = ByteBufUtil.hexDump(buf.readSlice(11));
        //remove end
        int readableBytes = buf.readableBytes();
        buf = buf.readSlice(readableBytes - 1);

        String keyHex = "4D5449304D6A457A4D544D784D5449794D673D3D";
        byte[] key = DatatypeConverter.parseHexBinary(keyHex);


        buf = decryptByteBuf(buf,key);

        //thêm lại  start và end
        buf = Unpooled.wrappedBuffer(ByteBufUtil.decodeHexDump(textRemovedStart + ByteBufUtil.hexDump(buf) + "29"));

        String sentence = buf.toString(StandardCharsets.US_ASCII);
        System.out.println(sentence);


        String regex = "^\\(([^,]+),([^,]+),([^,]+),([^,]+),([^,]+),([^,]+),([^,]+),([^,]+),([^,]+),([^,]+),([^,]+),([^,]+),([^,]+),([^,]+),([^,]+),([^,]+),([^,]+)\\)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(sentence);

    }


    public static ByteBuf decryptByteBuf(ByteBuf buf, byte[] key) {
        /**
         * example
         * org	01 11 00 34 16 01 24 16 28 49 21 00 25 64 10 54 66 31 1F 01 00 00 00 00 9D 09 00 00 00 00 20 C0 37 00 00 00 00 0D 00 1A 0E E0 08 69 52 30 57 92 04 81 00 00 00 00 00 AC
         * byte key	4d 54 49 30 4d 6a 45 7a 4d 54 4d 78 4d 54 49 79 4d 67 3d 3d 4d 54 49 30 4d 6a 45 7a 4d 54 4d 78 4d 54 49 79 4d 67 3d 3d 4d 54 49 30 4d 6a 45 7a 4d 54 4d 78 4d 54 49 79 4d 67 3d 3d
         * encode msg	4E 65 49 64 63 6B 69 90 75 9D 6E 78 72 B8 59 CD B3 98 5C 3E 4D 54 49 30 EA 73 45 7A 4D 54 6D 38 84 54 49 79 4D 74 3D 57 5B 34 51 99 9F 9A 9C 0C 51 D5 4D 78 4D 54 49 25
         * encode = org + key
         */
        if (buf.readableBytes() == 0 || key.length == 0) {
            return buf;
        }

        // Create a new ByteBuf for the decrypted data
        ByteBuf decryptedBuf = buf.alloc().buffer(buf.readableBytes());

        // Iterate over the buffer and decrypt each byte
        int keyIndex = 0;
        while (buf.isReadable()) {
            byte encryptedByte = buf.readByte();
            byte decryptedByte = (byte) (encryptedByte - key[keyIndex]);

            // Add the decrypted byte to the new buffer
            decryptedBuf.writeByte(decryptedByte);

            // Update the key index for the next byte
            keyIndex = (keyIndex + 1) % key.length;
        }

        return decryptedBuf;
    }

    public static void main(String[] args) {
        IOTDecodePackage iotDecodePackage = new IOTDecodePackage();
        iotDecodePackage.decodeTest();
    }
}
