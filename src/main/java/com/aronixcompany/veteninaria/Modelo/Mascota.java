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
 * Descripción : Clase base que es la pnatilla de mascotas
 * Módulos : Animal
 * Dependencias: Ninguno
 *
 * @author Aaron Cisneros (VA Developer)
 * @version 1.0
 * @since 9 jul 2026
 * ----------------------------------------------------------------------------
 */
public class Mascota extends Animal {

    private String especie;
    private String raza;
    private Propietario propietario;

    /**
     * Constructor para crear el objeto de mascota
     * @param nombre recibe su nombre
     * @param edad recibe la edad 
     * @param peso recibe el peso
     * @param especie recibe su tipo de especie
     * @param raza recibe su raza
     * @param propietario recibe el objeto propietario
     */
    public Mascota(String nombre, int edad, double peso, String especie, String raza, Propietario propietario) {
        super(nombre, edad, peso);
        this.especie = especie;
        this.raza = raza;
        this.propietario = propietario;
    }

    /**
     * Funcion para obtener la especie
     * @return devuelve la especie de la mascota
     */
    public String getEspecie() {
        return especie;
    }

    /**
     * Metodo para asignar la nueva especie a la mascota
     * @param especie recibe la especie que se adignara
     */
    public void setEspecie(String especie) {
        this.especie = especie;
    }

    /**
     * Funcion para obtner su raza
     * @return devuelve la nueva raza para mascota
     */
    public String getRaza() {
        return raza;
    }

    /**
     * Metod para asignar la nueva raza
     * @param raza recibe como parametro la raza de la mascota
     */
    public void setRaza(String raza) {
        this.raza = raza;
    }

    /**
     * Funcion para obtener su propietario
     * @return devuelve el propietario de la mascota
    */
    public Propietario getPropietario() {
        return propietario;
    }

    /**
     * Metodo para asignar el propietario
     * @param propietario recibe como parameto el propietario de la mascota
     */
    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    /**
     * Funcion para obtener la informacion de la mascota
     * @return devuelve los datos de su informacion
     */
    @Override
    public String mostrarInformacion() {
        return super.mostrarInformacion() + ", Especie: " + especie + ", Raza: " + raza + " -> [" + propietario.mostrarInformacion() + "]";
    }

}
