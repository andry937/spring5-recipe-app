package guru.springframework.recipeapp.utility;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Base64;

public final class ByteUtility {
    private ByteUtility(){

    }
    public static Byte[] toObjects(byte[] bytesPrim) {
        Byte[] bytes = new Byte[bytesPrim.length];
        Arrays.setAll(bytes, n -> bytesPrim[n]);
        return bytes;
    }

    public static byte[] toPrimitives(Byte[] oBytes)
    {
        byte[] bytes = new byte[oBytes.length];
        for(int i = 0; i < oBytes.length; i++) {
            bytes[i] = oBytes[i];
        }
        return bytes;
    }

    public static String toBase64(Byte[] bytes) throws UnsupportedEncodingException {
        byte[] primitiveByte = toPrimitives(bytes);
        byte[] encodeBase64 = Base64.getEncoder().encode(primitiveByte);
        return new String(encodeBase64, "UTF-8");
    }
}
