/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.centrale.medev.tp_dariel_hugo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;



/**
 *
 * @author darielbezerra
 */
public class TP_Dariel_Hugo {

    public static void main(String[] args) {
        
        /**
         * Lecture des images PGM
         */
        
        
      String filePath = "/baboon.pgm";

        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));

            // Ler o cabeçalho do arquivo PGM
            String magicNumber = br.readLine();
            if (!magicNumber.equals("P2")) {
                System.out.println("Format invalide");
                br.close();
                return;
            }

            int width = 0, height = 0, maxPixelValue = 0;

            // Ignores les lignes des commentaires
            String line;
            while ((line = br.readLine()) != null && line.startsWith("#")) {
            }

            // Ler largura, altura e valor máximo do pixel
            String[] dimensions = line.split("\\s");
            width = Integer.parseInt(dimensions[0]);
            height = Integer.parseInt(dimensions[1]);

            maxPixelValue = Integer.parseInt(br.readLine());

            // Ler os pixels da imagem
            int[][] pixels = new int[height][width];
            String[] pixel = new String[width];
            
            for (int i = 0; i < height; i++) {
                br.readLine();
                pixel = line.split("\\s");
                for (int j = 0; j < width; j++) {
                    pixels[i][j] = Integer.parseInt(pixel[j]);
                }
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
    

  }