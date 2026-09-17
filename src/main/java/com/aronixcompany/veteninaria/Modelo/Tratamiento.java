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
 * Descripción : Clase que contiene los tratamientos del sistema
 * Módulos : Niguno
 * Dependencias: Ninguno
 *
 * @author Aaron Cisneros (VA Developer)
 * @version 1.0
 * @since 9 jul 2026
 * ----------------------------------------------------------------------------
 */
public class Tratamiento {

    private String medicamento;
    private String dosis;
    private String duracion;
    private String observaciones;

    /**
     * Constructor para crear el objeto
     * @param medicamento recibe el nombre del tratamiento
     * @param dosis recibe los datos de su dosis
     * @param duracion recibe el tiempo de su curacion
     * @param observaciones recibe sus observaciones
     */
    public Tratamiento(String medicamento, String dosis, String duracion, String observaciones) {
        this.medicamento = medicamento;
        this.dosis = dosis;
        this.duracion = duracion;
        this.observaciones = observaciones;
    }

    /**
     * Funcion para obtener los medicamentos
     * @return devuelve sus meditamentos de su tratamiento
     */
    public String getMedicamento() {
        return medicamento;
    }

    /**
     * Funcion para obtener su dosis
     * @return devuelve su dosis de su tratamiento
     */
    public String getDosis() {
        return dosis;
    }

    /**
     * Funcion para obtener la duracion del trratamiento
     * @return devuelve la duracion de su tratamiento
     */
    public String getDuracion() {
        return duracion;
    }

    /**
     * Metodo para obtener sus observaciones
     * @return devuelve sus observaciones de su tratamiento
     */
    public String getObservaciones() {
        return observaciones;
    }

    @Override
    public String toString() {
        return medicamento + " (Dosis: " + dosis + ", Duración: " + duracion + ")";
    }

}
