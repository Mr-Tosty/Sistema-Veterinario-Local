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
 * Descripción : Clase que guarda los datos de la consulta
 * Módulos : ninguno
 * Dependencias: ninguno
 *
 * @author Aaron Cisneros (VA Developer)
 * @version 1.0
 * @since 9 jul 2026
 * ----------------------------------------------------------------------------
 */
public class Consulta {

    private Mascota mascota;
    private Veterinario veterinarian;
    private String fecha; // Formato AAAA-MM-DD
    private String diagnostico;
    private Tratamiento tratamiento;
    private double costo;

    /**
     * Constructor para crear los objetos de consulta
     * @param mascota recibe la mascota
     * @param veterinarian recibe su veterinaria
     * @param fecha recibe la fecha
     * @param diagnostico recibe el diagnostico
     * @param tratamiento recibe su tratamiento
     * @param costo recibe su costo
     */
    public Consulta(Mascota mascota, Veterinario veterinarian, String fecha, String diagnostico, Tratamiento tratamiento, double costo) {
        this.mascota = mascota;
        this.veterinarian = veterinarian;
        this.fecha = fecha;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.costo = costo;
    }

    /**
     * Funcion para obtener la mascota
     * @return devuelve la mascota de la consulta
     */
    public Mascota getMascota() {
        return mascota;
    }

    /**
     * Funcion para obtener su veterinario
     * @return devuelve el veterinario de la consulta
     */
    public Veterinario getVeterinario() {
        return veterinarian;
    }

    /**
     * Funcion para obtener la fecha
     * @return recibe la fecha de la consulta
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Funcion para obtener su diagnostico
     * @return devuelve el diagnostico de la consulta
     */
    public String getDiagnostico() {
        return diagnostico;
    }

    /**
     * Funcion para devolver su tratamiento
     * @return devuelve el tratamiento de la consulta
     */
    public Tratamiento getTratamiento() {
        return tratamiento;
    }

    /**
     * Funcion para recibirr el costo
     * @return devuelve el costo de la consulta
     */
    public double getCosto() {
        return costo;
    }

    /**
     * Funcion que muestra la informacion
     * @return devuelve la nformacion de la consulta
     */
    public String mostrarConsulta() {
        return "Fecha: " + fecha + " | Mascota: " + mascota.getNombre() + " | Vet: " + veterinarian.getNombre()
                + "\nDiag: " + diagnostico + "\nTratamiento: " + tratamiento + " | Costo: $" + costo;
    }

}
