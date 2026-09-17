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
 * Descripción : Clase para guardar los datos del propietario en memoria
 * Módulos : Ninguno
 * Dependencias: Ninguno
 *
 * @author Aaron Cisneros (VA Developer)
 * @version 1.0
 * @since 9 jul 2026
 * ----------------------------------------------------------------------------
 */
public class Propietario {

    private String nombre;
    private String telefono;
    private String direccion;
    private String correoElectronico;

    /**
     * Constructor para crear el objeto del propietario
     * @param nombre recibe el nombre
     * @param telefono recibe su numero de contacto
     * @param direccion recibe la direccion
     * @param correoElectronico recibe su direccion de correo
     */
    public Propietario(String nombre, String telefono, String direccion, String correoElectronico) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.correoElectronico = correoElectronico;
    }

    /**
     * Funcion para obtener su nombre del propietario
     * @return devuelve el nombre del propietario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo para cambiar el nombre
     * @param nombre recibe el nombre para asignar al propietario
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Funcion para obtener el numero de contacto del telefono
     * @return devuelve el telefono del propetiario
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Metodo para asignar el uevo numero
     * @param telefono obtiene el numero de telefono del propietario
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Funcion para obtener la direccion
     * @return devuelve la direccion del propietario
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Metodo para asignar un nuevo direccion al propietario
     * @param direccion recibe la direccion
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Funcion para obtener su coreo electronico
     * @return  devuelve el correo del propietario
     */
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    /**
     * Metodo para asignar el nuevo correo 
     * @param correoElectronico recibe el correo asignado al propietario
     */
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    /**
     * Funcion para mostrar la informacion del propietario
     * @return devuelve la informacion 
     */
    public String mostrarInformacion() {
        return "Propietario: " + nombre + " | Tel: " + telefono + " | Email: " + correoElectronico;
    }

}
