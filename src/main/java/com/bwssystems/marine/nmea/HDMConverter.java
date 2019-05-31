package com.bwssystems.marine.nmea;

import java.io.*;
import java.net.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HDMConverter {

    public static void main(String argv[]) throws Exception {
        String clientSentence;
        String targetSentence;
        ServerSocket welcomeSocket = new ServerSocket(6789);
        Logger log = LoggerFactory.getLogger(HDMConverter.class);

        log.info("Starting HDMConverter....");

        log.info("Waiting for connection....");
        Socket connectionSocket = welcomeSocket.accept();
        log.info("Socket connected....");
        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
        log.info("Start read loop....");
        boolean running = true;
        while (running) {
            try {
                clientSentence = inFromClient.readLine();
                if (clientSentence != null && clientSentence.length() > 0) {
                    targetSentence = SentenceConvert.doReplace(clientSentence, "$AP", "$HC");
                    // log.info("Debug client sentence: " + clientSentence);
                    // log.info("Debug target sentence: " + targetSentence);
                    if (!targetSentence.equals(clientSentence)) {
                        targetSentence = targetSentence + "\r\n";
                        // log.info("Debug output for converted sentence: " + targetSentence);
                        outToClient.writeBytes(targetSentence);

                    } else {
                        // log.info("Debug Not sending converted message for: " + clientSentence);
                        // log.info(" with sentence: " + targetSentence);
                    }
                } else if(clientSentence == null) {
                    log.warn("client sentence is null, resetting connection");
                    inFromClient.close();
                    outToClient.close();
                    connectionSocket.close();
                    throw(new IOException("We must have lost connection..."));
                }
            } catch (IOException e) {
                log.error("Lost TCP connection, {}, resetting....", e.getMessage());
                log.info("Waiting for TCP connection....");
                connectionSocket = welcomeSocket.accept();
                log.info("Socket connected....");
                inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            }
        }

        log.info("HDMConverter shutting down....");
        welcomeSocket.close();
        System.exit(0);
    }
}
