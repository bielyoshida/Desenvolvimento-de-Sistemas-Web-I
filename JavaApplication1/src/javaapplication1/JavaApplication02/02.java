e/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.util.Scanner;

/**
 *
 * @author User
 */
public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        pessoa individuo = new pessoa();
        System.out.println("Informe seu nome");    
        individuo.nome = ler.next();
        individuo.apresentação();
        
        
    }
    
}
