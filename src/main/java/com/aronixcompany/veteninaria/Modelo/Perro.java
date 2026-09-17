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
 * Descripción : Objeto para guardar en memoria los datos del perro
 * Módulos : Mascota
 * Dependencias: Ninguno
 *
 * @author Aaron Cisneros (VA Developer)
 * @version 1.0
 * @since 9 jul 2026
 * ----------------------------------------------------------------------------
 */
public class Perro extends Mascota {

    private final String nivelLadrito;

    /**
     * Constructor para crear el objeto perro
     * @param nombre recibe el nombre
     * @param edad recibe la edad
     * @param peso recibe su peso
     * @param raza recibe su raza
     * @param propietario recibe el objeto propietario
     * @param nivelLadrito recive su nivel de ladrido
     */
    public Perro(String nombre, int edad, double peso, String raza, Propietario propietario, String nivelLadrito) {
        super(nombre, edad, peso, "Perro", raza, propietario);
        this.nivelLadrito = nivelLadrito;
    }

    /**
     * Funcion para mostrar la informacion del perro
     * @return devuelvve los datos de su informacion
     */
    @Override
    public String mostrarInformacion() {
        return super.mostrarInformacion() + " | Atributo Perro [Intensidad Ladrido: " + nivelLadrito + "]";
    }

}
