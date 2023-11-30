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
        
        
        String filePath = "/Users/darielbezerra/Downloads/Informatique pour les Systèmes d'Information/Cours/2eme Periode/Méthodologie de Développement/TP3/TP_Dariel_Hugo/src/main/java/org/centrale/medev/tp_dariel_hugo/baboon.pgm";
//      String filePath = "C:\\Users\\hugoc\\OneDrive\\Área de Trabalho\\exercicios\\MEDEV_TP3\\tp-pgm-hugo_dariel\\src\\main\\java\\org\\centrale\\medev\\tp_dariel_hugo\\baboon.pgm";

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
            String[] dimensions = line.split("  ");
            width = Integer.parseInt(dimensions[0]);
            height = Integer.parseInt(dimensions[1]);

            maxPixelValue = Integer.parseInt(br.readLine());

            // Ler os pixels da imagem
            int[][] pixels = new int[height][width];
            String[] pixel = new String[width];
            int[] line_matrix = new int[width];
            int j=0;
            
            
            for (int i = 0; i < height; i++) {
            
                while(j<width){


                    pixel = br.readLine().split("  ");
                    
                    for (int k=j;k<j+pixel.length;k++) {
                        line_matrix[k] = Integer.parseInt(pixel[k-j].trim());
                    }
                    
                    j = j+pixel.length;
                    
                }

                    pixels[i] = line_matrix;
            }
            
            int[] histogram = new int[256];

            for (int[] ligne : pixels){
                for(int pixelValue : ligne) {
                    histogram[pixelValue]++;
                }
            }
            
            for(int i = 0; i < histogram.length; i++){
                System.out.println(i + ": " + histogram[i]);
            }
            

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        

    }
    
    
    
    

  }