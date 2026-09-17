/*
 *  __      __  _______ 
 *  \ \    / / |  ___  |
 *   \ \  / /  | |___| |
 *    \ \/ /   |  ___  |   VORTEX
 *     \__/    |_|   |_|   ARONIX
 * ----------------------------------------------------------------------------
 *  @Copyright: (c) 2026 Vortex Aronix.
 *  @License  : Open Source (Licencia Permisiva / Código Abierto)
 *  @Terms    : Se permite el uso, modificación y distribución de este código.
 *              Debe incluirse este aviso de derechos de autor original y 
 *              acreditar a Vortex Aronix como desarrollador original.
 *              EL SOFTWARE SE PROPORCIONA "TAL CUAL", SIN GARANTÍAS DE NINGÚN 
 *              TIPO. La compañía no se hace responsable de daños, fallos o 
 *              reclamaciones derivadas de su uso.
 *              Para mas informacion en https://vortexaronix.com/FAQs/App/Escritorio/Modificaciones.
 * ----------------------------------------------------------------------------
 */
package com.aronixcompany.veteninaria.Modelo;

/**
 * ----------------------------------------------------------------------------
 * [ DESCRIPCIÓN TÉCNICA ]
 * ----------------------------------------------------------------------------
 * Descripción : Clase de plantilla para guardar datos del animal
 * Módulos : Ninguno
 * Dependencias: Ninguno
 *
 * @author Aaron Cisneros (VA Developer)
 * @version 1.0
 * @since 9 jul 2026
 * ----------------------------------------------------------------------------
 */
public class Animal {
    
    /**
     * Nombre del animal
     */
    protected String nombre;
    
    /**
     * Edad del animal
     */
    protected int edad;
    
    /**
     * Peso de la mascota
     */
    protected double peso;

    /**
     * Constructor para guardar los datos
     * @param nombre recibe su nombre
     * @param edad recibe su edad
     * @param peso recibe su peso
     */
    public Animal(String nombre, int edad, double peso) {
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
    }

    /**
     * Funcion que obtiene el nombre de la mascota
     * @return devuelve su nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo para asignar nuevo nombre
     * @param nombre recibe su nuevo nombre nuevo para asignar
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Funcion para obtener su edad
     * @return devuelde la edad de la mascota
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Metodo para cambiar la edad del animal
     * @param edad recibe como paramerto la edad nueva asignada
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * Funcion para obtener su peso
     * @return devuelve el peso del animal
     */
    public double getPeso() {
        return peso;
    }

    /**
     * Metodo para asignar nuevo peso
     * @param peso recibe el peso para asignar a la mascota
     */
    public void setPeso(double peso) {
        this.peso = peso;
    }

    /**
     * Funcion para obtener su informacion
     * @return devuelve la informacion del animal
     */
    public String mostrarInformacion() {
        return "Nombre: " + nombre + ", Edad: " + edad + " años, Peso: " + peso + " kg";
    }

}
