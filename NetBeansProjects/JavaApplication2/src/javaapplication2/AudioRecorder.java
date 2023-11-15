/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication2;

import javax.sound.sampled.*;
import java.io.*;

public class AudioRecorder {

    public static void main(String[] args) {
        try {
            AudioFormat format = new AudioFormat(44100, 16, 2, true, false);
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

            if (!AudioSystem.isLineSupported(info)) {
                System.err.println("Line not supported");
                System.exit(1);
            }

            TargetDataLine line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();

            System.out.println("Recording...");

            AudioInputStream ais = new AudioInputStream(line);
            File audioFile = new File("output.wav");

            // Record for 30 seconds
            long startTime = System.currentTimeMillis();
            while (System.currentTimeMillis() - startTime < 30000) {
                AudioSystem.write(ais, AudioFileFormat.Type.WAVE, audioFile);
            }

            System.out.println("Recording finished.");

            line.stop();
            line.close();
        } catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }
}
