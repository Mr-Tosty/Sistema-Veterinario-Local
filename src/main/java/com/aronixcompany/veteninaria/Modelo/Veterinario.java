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
 * Descripción :Clase que guarda como objeto el veterinario
 * Módulos : Ninguno
 * Dependencias: Ninguno
 *
 * @author Aaron Cisneros (VA Developer)
 * @version 1.0
 * @since 9 jul 2026
 * ----------------------------------------------------------------------------
 */
public class Veterinario {

    private String nombre;
    private String especialidad;
    private String cedulaProfesional;
    private String telefono;

    /**
     * Constructor para iniciar la creacion del objeto en memoria
     * @param nombre recibe el nombre del veterinario
     * @param especialidad recibe su especialidad
     * @param cedulaProfesional recibe su cedula
     * @param telefono  recibe su numero de contacto
     */
    public Veterinario(String nombre, String especialidad, String cedulaProfesional, String telefono) {
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.cedulaProfesional = cedulaProfesional;
        this.telefono = telefono;
    }

    /**
     * Funcion para obtener el nombre
     * @return devuelve el nombre del verterinario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo para asignar el nuevo nombre
     * @param nombre recibe el nuevo nombre para el veterinario
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Funcion para obtener la especialidad
     * @return devuelve el especialidad del verterinario
     */
    public String getEspecialidad() {
        return especialidad;
    }

    /**
     * Metodo para asignar su especialidad
     * @param especialidad recibe el nuevo especialidad para el veterinario
     */
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    /**
     * Funcion para obtener su cedula
     * @return devuelve la cedula del verterinario
     */
    public String getCedulaProfesional() {
        return cedulaProfesional;
    }

    /**
     * Metodo para cambiar su cedula
     * @param cedulaProfesional recibe el nuevo cedula para el veterinario
     */
    public void setCedulaProfesional(String cedulaProfesional) {
        this.cedulaProfesional = cedulaProfesional;
    }

    /**
     * Funcion para obtener su numero
     * @return devuelve el numero de contacto del verterinario
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Metodo para cambiar el numero
     * @param telefono recibe el nuevo numero para el veterinario
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
