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
 * Descripción : Clase para guardar en memoria la mascota
 * Módulos : Macota
 * Dependencias: ninguno
 *
 * @author Aaron Cisneros (VA Developer)
 * @version 1.0
 * @since 9 jul 2026
 * ----------------------------------------------------------------------------
 */
public class Gato extends Mascota {

    private final boolean esIndependiente;

    /**
     * Constructor para iniciar el objeto de gato
     * @param nombre recibe su nombre
     * @param edad recibe su edad
     * @param peso recibe su peso
     * @param raza recibe su raza
     * @param propietario recibe el objeto propietario
     * @param esIndependiente recibe boleano si esta es hogareño o no
     */
    public Gato(String nombre, int edad, double peso, String raza, Propietario propietario, boolean esIndependiente) {
        super(nombre, edad, peso, "Gato", raza, propietario);
        this.esIndependiente = esIndependiente;
    }

    /**
     * Funcion para obtener la informacion del gato
     * @return Devuelve la informacion de la mascota
     */
    @Override
    public String mostrarInformacion() {
        return super.mostrarInformacion() + " | Atributo Gato [Es Hogareño/Independiente: " + (esIndependiente ? "Sí" : "No") + "]";
    }

}
