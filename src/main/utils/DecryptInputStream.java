package main.utils;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DecryptInputStream extends FilterInputStream {
    private final int key;

    public DecryptInputStream(InputStream in, char key) {
        super(in);
        this.key = key;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int bytesRead = super.read(b, off, len);
        if (bytesRead == -1) {
            return -1;
        }
        for (int i = 0; i < bytesRead; i++) {
            b[off + i] = (byte) (b[off + i] - key);
        }
        return bytesRead;
    }
}
