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
 * Descripción : Clase para guardar los datos en memoria la ave
 * Módulos : Mascota
 * Dependencias: ninguno
 *
 * @author Aaron Cisneros (VA Developer)
 * @version 1.0
 * @since 9 jul 2026
 * ----------------------------------------------------------------------------
 */
public class Ave extends Mascota {

    private final boolean canta;

    /**
     * Constructor para iniciar el objeto de la ave
     * @param nombre recibe el nombre
     * @param edad recibe su edad
     * @param peso recibe su peso
     * @param raza recibe su raza
     * @param propietario recibe el objeto del propietario 
     * @param canta recibe boleano si puede cantar
     */
    public Ave(String nombre, int edad, double peso, String raza, Propietario propietario, boolean canta) {
        super(nombre, edad, peso, "Ave", raza, propietario);
        this.canta = canta;
    }

    /**
     * Funcion para obtener la informacion
     * @return devueve la informacion de la mascota
     */
    @Override
    public String mostrarInformacion() {
        return super.mostrarInformacion() + " | Atributo Ave [Canta: " + (canta ? "Sí" : "No") + "]";
    }

}
