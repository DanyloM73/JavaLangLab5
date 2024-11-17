package main.utils;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class EncryptOutputStream extends FilterOutputStream {
    private final int key;

    public EncryptOutputStream(OutputStream out, char key) {
        super(out);
        this.key = key;
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        byte[] encrypted = new byte[len];
        for (int i = 0; i < len; i++) {
            encrypted[i] = (byte) (b[off + i] + key);
        }
        super.write(encrypted, 0, len);
    }
}
