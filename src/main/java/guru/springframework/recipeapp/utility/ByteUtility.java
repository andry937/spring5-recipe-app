package guru.springframework.recipeapp.utility;

import java.util.Arrays;

public final class ByteUtility {
    private ByteUtility(){

    }
    public static Byte[] toObjects(byte[] bytesPrim) {
        Byte[] bytes = new Byte[bytesPrim.length];
        Arrays.setAll(bytes, n -> bytesPrim[n]);
        return bytes;
    }
}
