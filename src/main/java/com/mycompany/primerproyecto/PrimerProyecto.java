package com.mycompany.primerproyecto;

import controlador.Prueba;
import controlador.Test;

/**
 *
 * @author matprog06
 */
public class PrimerProyecto {

    public static void main(String[] args) {
        Prueba prueba=new Prueba();
        Test test =new Test();
        prueba.saludo();
        prueba.saludo();
        prueba.contar();
        prueba.contar2();
        prueba.despedida();
        prueba.despedida();
        
        test.alfabeto();
    }
}
