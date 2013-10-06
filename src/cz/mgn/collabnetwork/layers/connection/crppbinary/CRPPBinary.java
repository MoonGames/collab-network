package cz.mgn.collabnetwork.layers.connection.crppbinary;

import cz.mgn.collabnetwork.layers.connection.Connection;
import cz.mgn.collabnetwork.layers.connection.DataListener;
import cz.mgn.collabnetwork.utils.BinaryUtil;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martin Indra <aktive at seznam.cz>
 */
public class CRPPBinary implements Connection {

    protected DataListener dataListener;
    protected InputStream inputStream;
    protected OutputStream outputStream;
    protected int lastID = 0;

    public CRPPBinary(InputStream inputStream, OutputStream outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        init();
    }
    
    private void init() {
        listen();
    }

    @Override
    public void setDataListener(DataListener dataListener) {
        this.dataListener = dataListener;
    }

    @Override
    public void sendData(byte[] data) {
        byte[] toSend = new byte[data.length + 8];

        byte[] bytes = BinaryUtil.intToByteArray(generateID());
        System.arraycopy(bytes, 0, toSend, 0, 4);

        bytes = BinaryUtil.intToByteArray(data.length);
        System.arraycopy(bytes, 0, toSend, 4, 4);

        System.arraycopy(data, 0, toSend, 8, data.length);

        sendByStream(data);
    }

    protected void sendByStream(byte[] data) {
        try {
            outputStream.write(data);
            outputStream.flush();
        } catch (IOException ex) {
            Logger.getLogger(CRPPBinary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected int generateID() {
        lastID++;
        return lastID;
    }

    protected void listen() {
        Thread thread = new Thread() {

            @Override
            public void run() {
                boolean running = true;
                try {
                    while (running) {
                        readNext();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(CRPPBinary.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    Logger.getLogger(CRPPBinary.class.getName()).log(Level.INFO, "Input stream reading ended.");
                }

            }
        };
        
        thread.start();
    }

    protected void readNext() throws IOException {
        byte[] idBytes = new byte[4];
        inputStream.read(idBytes);
        //int id = BinaryUtil.byteArrayToInt(idBytes);

        byte[] lengthBytes = new byte[4];
        inputStream.read(lengthBytes);
        int length = BinaryUtil.byteArrayToInt(lengthBytes);

        byte[] data = new byte[length];
        inputStream.read(data);

        if (dataListener != null) {
            dataListener.dataReceived(data);
        }
    }
}
