/*
 *  __      __  _______ 
 *  \ \    / / |  ___  |
 *   \ \  / /  | |___| |
 *    \ \/ /   |  ___  |   VORTEX
 *     \__/    |_|   |_|   ARONIX
 * ----------------------------------------------------------------------------
 *  @Copyright: (c) 2026 Vortex Aronix.
 *  @License  : Privativa - Todos los derechos reservados.
 *  @Terms    : La copia, distribución o ingeniería inversa de este código 
 *              fuente está estrictamente prohibida bajo los términos de VA.
 *              Para mas informacion en https://vortexaronix.com/FAQs/App/Escritorio/Modificaciones.
 * ----------------------------------------------------------------------------
 */
package com.aronixcompany.veteninaria.Controlador;

import com.aronixcompany.veteninaria.Excepciones.MascotaDuplicadaException;
import com.aronixcompany.veteninaria.Excepciones.PropietarioNoEncontradoException;
import com.aronixcompany.veteninaria.Modelo.Ave;
import com.aronixcompany.veteninaria.Modelo.Consulta;
import com.aronixcompany.veteninaria.Modelo.Gato;
import com.aronixcompany.veteninaria.Modelo.Mascota;
import com.aronixcompany.veteninaria.Modelo.Perro;
import com.aronixcompany.veteninaria.Modelo.Propietario;
import com.aronixcompany.veteninaria.Modelo.Tratamiento;
import com.aronixcompany.veteninaria.Modelo.Veterinario;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ----------------------------------------------------------------------------
 * [ DESCRIPCIÓN TÉCNICA ]
 * ----------------------------------------------------------------------------
 * Descripción : Clase base para guardar los datos de la veterinaria
 * Módulos : Ninguno
 * Dependencias: Util, IO, Modelo, Excepciones
 *
 * @author solda (VA Developer)
 * @version 1.0
 * @since 9 jul 2026
 * ----------------------------------------------------------------------------
 */
public class Veterinaria {
    
    /**
     * Constructor de la clase inicio
     * No tiene ningun uso
     */
    public Veterinaria(){}

    private final List<Propietario> listaPropietarios = new ArrayList<>();
    private final List<Mascota> listaMascotas = new ArrayList<>();
    private final List<Veterinario> listaVeterinarios = new ArrayList<>();
    private final List<Consulta> listaConsultas = new ArrayList<>();

    /**
     * Metodo para agregar nuevos propietarios
     * @param p recibe el propietario para guardar
     * @throws IllegalArgumentException Lanza excepcion si el propietario ya
     * existe con el mismo numero
     */
    public void agregarPropietario(Propietario p) throws IllegalArgumentException {
        validarCamposObligatorios(p.getNombre(), p.getTelefono(), p.getDireccion(), p.getCorreoElectronico());
        for (var prop : listaPropietarios) {
            if (prop.getTelefono().equals(p.getTelefono())) {
                throw new IllegalArgumentException("Error: Ya existe un propietario con el mismo número telefónico.");
            }
        }
        listaPropietarios.add(p);
    }

    /**
     * Metodo para asignar a la mascota
     * @param m recibe la mascota para agregar
     * @throws MascotaDuplicadaException aroja error si la mascota la edad es
     * negativa, peso negativo, porpietario unico o no tiene asignado a un
     * propietario
     */
    public void agregarMascota(Mascota m) throws MascotaDuplicadaException, IllegalArgumentException {
        validarCamposObligatorios(m.getNombre(), m.getEspecie(), m.getRaza());
        if (m.getEdad() < 0) {
            throw new IllegalArgumentException("La edad no puede ser un número negativo.");
        }
        if (m.getPeso() <= 0) {
            throw new IllegalArgumentException("El peso debe ser una cantidad estrictamente mayor a cero.");
        }
        if (m.getPropietario() == null) {
            throw new NullPointerException("Debe asignar un propietario válido a la mascota.");
        }

        for (var masc : listaMascotas) {
            if (masc.getNombre().equalsIgnoreCase(m.getNombre())
                    && masc.getPropietario().getTelefono().equals(m.getPropietario().getTelefono())) {
                throw new MascotaDuplicadaException("Esta mascota ya se encuentra registrada para este propietario.");
            }
        }
        listaMascotas.add(m);
    }

    /**
     * Metodo para agregar un vetenirario
     * @param v recibe el venerirario nuevo
     * @throws IllegalArgumentException Arroja error si no se cumple los datos requeridos
     */
    public void agregarVeterinario(Veterinario v) throws IllegalArgumentException {
        validarCamposObligatorios(v.getNombre(), v.getEspecialidad(), v.getCedulaProfesional());
        listaVeterinarios.add(v);
    }

    /**
     * Metod para registrar una consulta
     * @param c recibe la consulta para registrar
     * @throws IllegalArgumentException Arroja error si el nombre, vetenirario
     * cumple sus campos
     * @throws NullPointerException Arroja error si una no se agrego la mascota o
     * propietario
     */
    public void registrarConsulta(Consulta c) throws IllegalArgumentException, NullPointerException {
        if (c.getMascota() == null) {
            throw new NullPointerException("No se puede registrar una consulta sin seleccionar una mascota.");
        }
        if (c.getVeterinario() == null) {
            throw new NullPointerException("Se requiere asociar un veterinario a la consulta.");
        }
        if (c.getCosto() <= 0) {
            throw new IllegalArgumentException("El costo de la consulta debe ser estrictamente mayor a cero.");
        }
        validarCamposObligatorios(c.getFecha(), c.getDiagnostico());

        listaConsultas.add(c);
    }

    //Metodo para validar los caampos
    private void validarCamposObligatorios(String... campos) {
        for (String campo : campos) {
            if (campo == null || campo.strip().isEmpty()) {
                throw new IllegalArgumentException("Todos los campos obligatorios deben ser llenados.");
            }
        }
    }

    /**
     * Metodo para obtener el hisorial de la consulta
     * @param nombreMascota recibe el nombre de la mascota para buscar
     * @return devuelve el objeto encontrado
     */
    public List<Consulta> mostrarHistorial(String nombreMascota) {
        return listaConsultas.stream()
                .filter(c -> c.getMascota().getNombre().equalsIgnoreCase(nombreMascota))
                .collect(Collectors.toList());
    }

    /**
     * Metodo privado para buscar la mascota por su nombre
     * @param nombre recibe el nombre de la mascota a buscar
     * @return devuelve la mascota encontrada
    */
    public List<Mascota> buscarMascotaPorNombre(String nombre) {
        return listaMascotas.stream().filter(m -> m.getNombre().equalsIgnoreCase(nombre)).toList();
    }

    /**
     * Funcion para buscar el propietario por telefono
     * @param telefono recibe su numero de elefono
     * @return devuelve el propietario encontrado
     * @throws PropietarioNoEncontradoException Arroja erroor si no se a encontrado
     * su propietario por telefono
     */
    public Propietario buscarPropietarioPorTelefono(String telefono) throws PropietarioNoEncontradoException {
        return listaPropietarios.stream()
                .filter(p -> p.getTelefono().equals(telefono))
                .findFirst()
                .orElseThrow(() -> new PropietarioNoEncontradoException("No existe un propietario asociado al teléfono: " + telefono));
    }

    /**
     * Funcion para buscar el vetenirario por especialidad
     * @param esp recibe la especialidad a buscar
     * @return Devuelve el vetenirario encontrado
     */
    public List<Veterinario> buscarVeterinarioPorEspecialidad(String esp) {
        return listaVeterinarios.stream().filter(v -> v.getEspecialidad().equalsIgnoreCase(esp)).toList();
    }

    /**
     * Funcion para buscar consulta por fecha
     * @param fecha recibe como parametro la fecha
     * @return devuelve la consulta
     */
    public List<Consulta> buscarConsultasPorFecha(String fecha) {
        return listaConsultas.stream().filter(c -> c.getFecha().equals(fecha)).toList();
    }

    /**
     * Funcion para obtener el vetenirario
     * @param nombreVet obtiene el vetenirario
     * @return devuelve la agenda
     */
    public List<Consulta> obtenerAgendaVeterinario(String nombreVet) {
        return listaConsultas.stream()
                .filter(c -> c.getVeterinario().getNombre().equalsIgnoreCase(nombreVet))
                .toList();
    }

    /**
     * Funcion para obtener los reportes del vetenirario
     * @return devuelve sus estadisticaas e informes
     */
    public String generarReportesYEstadisticas() {
        if (listaConsultas.isEmpty() && listaMascotas.isEmpty()) {
            return "No hay datos suficientes registrados para calcular estadísticas globales.";
        }

        long totalMascotas = listaMascotas.size();
        long totalConsultas = listaConsultas.size();

        double costoPromedio = listaConsultas.stream().mapToDouble(Consulta::getCosto).average().orElse(0.0);
        double totalIngresos = listaConsultas.stream().mapToDouble(Consulta::getCosto).sum();
        double promedioEdad = listaMascotas.stream().mapToInt(Mascota::getEdad).average().orElse(0.0);

        long perros = listaMascotas.stream().filter(m -> m instanceof Perro).count();
        long gatos = listaMascotas.stream().filter(m -> m instanceof Gato).count();
        long aves = listaMascotas.stream().filter(m -> m instanceof Ave).count();

        String topVeterinario = listaConsultas.stream()
                .collect(Collectors.groupingBy(c -> c.getVeterinario().getNombre(), Collectors.counting()))
                .entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse("N/A");

        String topEspecie = listaMascotas.stream()
                .collect(Collectors.groupingBy(Mascota::getEspecie, Collectors.counting()))
                .entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse("N/A");

        String topPropietario = listaMascotas.stream()
                .collect(Collectors.groupingBy(m -> m.getPropietario().getNombre(), Collectors.counting()))
                .entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse("N/A");

        return """
        ==================================================
                  REPORTES Y ESTADÍSTICAS GENERALES       
        ==================================================
        * Cantidad Total de Mascotas: %d
        * Número de Consultas Realizadas: %d
        * Veterinario con mayor volumen de consultas: %s
        * Especie con mayor número de pacientes: %s
        * Cliente con más mascotas registradas: %s
        --------------------------------------------------
        * Costo promedio por consulta médica: $%.3f
        * Total global de ingresos percibidos: $%.2f
        * Edad promedio de las mascotas activas: %.1f años
        --------------------------------------------------
        * Desglose - Perros: %d | Gatos: %d | Aves: %d
        ==================================================
        """.formatted(totalMascotas, totalConsultas, topVeterinario, topEspecie, topPropietario,
                costoPromedio, totalIngresos, promedioEdad, perros, gatos, aves);
    }

    /**
     * Metodo par aguardar los archivos
     * @throws IOException Arroja error si no se guardo correctamente los datos
     */
    public void guardarDatosEnArchivo() throws IOException {
        try (var writer = new BufferedWriter(new FileWriter("datos_veterinaria.txt"))) {
            
            writer.write("[PROPIETARIOS]\n");
            for (var p : listaPropietarios) {
                writer.write(p.getNombre() + "," + p.getTelefono() + "," + p.getDireccion() + "," + p.getCorreoElectronico() + "\n");
            }
            
            writer.write("[MASCOTAS]\n");
            for (var m : listaMascotas) {
                writer.write(m.getNombre() + "," + m.getEdad() + "," + m.getPeso() + "," + m.getEspecie() + "," + m.getRaza() + "," + m.getPropietario().getTelefono() + "\n");
            }
            
            writer.write("[VETERINARIOS]\n");
            for (var v : listaVeterinarios) {
                writer.write(v.getNombre() + "," + v.getEspecialidad() + "," + v.getCedulaProfesional() + "," + v.getTelefono() + "\n");
            }
            
            writer.write("[CONSULTAS]\n");
            for (var c : listaConsultas) {
                var t = c.getTratamiento();
                
                writer.write(c.getMascota().getNombre() + "," + c.getMascota().getPropietario().getTelefono() + "," +
                             c.getVeterinario().getNombre() + "," + c.getFecha() + "," + c.getDiagnostico() + "," +
                             t.getMedicamento() + ";" + t.getDosis() + ";" + t.getDuracion() + ";" + t.getObservaciones() + "," +
                             c.getCosto() + "\n");
            }
        }
    }

    /**
     * Metodo para cargar los datos
     * @throws IOException Arroja error si no cargo los datoss
     * @throws PropietarioNoEncontradoException Arroja error si el propietario no esta
     * @throws MascotaDuplicadaException Arroja error si la mascota esta duplicada
     */
    public void cargarDatosDesdeArchivo() throws IOException, PropietarioNoEncontradoException, MascotaDuplicadaException {
        var file = new File("datos_veterinaria.txt");
        if (!file.exists()) return;

        try (var reader = new BufferedReader(new FileReader(file))) {
            String linea;
            String seccionActual = "";
            
            while ((linea = reader.readLine()) != null) {
                if (linea.startsWith("[")) {
                    seccionActual = linea;
                    continue;
                }
                if (linea.strip().isEmpty()) continue;

                var tokens = linea.split(",");
                switch (seccionActual) {
                    
                    case "[PROPIETARIOS]" -> {
                        if (tokens.length >= 4) {
                            
                            if (listaPropietarios.stream().noneMatch(p -> p.getTelefono().equals(tokens[1]))) {
                                agregarPropietario(new Propietario(tokens[0], tokens[1], tokens[2], tokens[3]));
                            }
                        }
                    }
                    
                    case "[MASCOTAS]" -> {
                        if (tokens.length >= 6) {
                            var prop = buscarPropietarioPorTelefono(tokens[5]);
                            
                            boolean existe = listaMascotas.stream().anyMatch(m -> 
                                m.getNombre().equalsIgnoreCase(tokens[0]) && 
                                m.getPropietario().getTelefono().equals(tokens[5])
                            );
                            
                            if (!existe) {
                                int edad = Integer.parseInt(tokens[1]);
                                double peso = Double.parseDouble(tokens[2]);
                                switch (tokens[3].toLowerCase()) {
                                    case "perro" -> agregarMascota(new Perro(tokens[0], edad, peso, tokens[4], prop, "Moderado"));
                                    case "gato" -> agregarMascota(new Gato(tokens[0], edad, peso, tokens[4], prop, true));
                                    case "ave" -> agregarMascota(new Ave(tokens[0], edad, peso, tokens[4], prop, true));
                                    default -> agregarMascota(new Mascota(tokens[0], edad, peso, tokens[3], tokens[4], prop));
                                }
                            }
                        }
                    }
                    
                    case "[VETERINARIOS]" -> {
                        if (tokens.length >= 4) {
                             
                            boolean existe = listaVeterinarios.stream().anyMatch(v -> v.getNombre().equalsIgnoreCase(tokens[0]));
                            if (!existe) {
                                agregarVeterinario(new Veterinario(tokens[0], tokens[1], tokens[2], tokens[3]));
                            }
                        }
                    }
                    
                    case "[CONSULTAS]" -> {
                        if (tokens.length >= 7) {
                            var tknMascota = tokens[0];
                            var tknTel = tokens[1];
                            var tknVet = tokens[2];
                            
                            
                            var mascotaOpt = listaMascotas.stream()
                                    .filter(m -> m.getNombre().equalsIgnoreCase(tknMascota) && m.getPropietario().getTelefono().equals(tknTel))
                                    .findFirst();
                            
                            
                            var vetOpt = listaVeterinarios.stream()
                                    .filter(v -> v.getNombre().equalsIgnoreCase(tknVet))
                                    .findFirst();
                            
                            
                            var tTokens = tokens[5].split(";");
                            var obs = (tTokens.length > 3) ? tTokens[3] : "";
                            var tratamiento = new Tratamiento(tTokens[0], tTokens[1], tTokens[2], obs);
                            
                            if (mascotaOpt.isPresent() && vetOpt.isPresent()) {
                                
                                boolean existeConsulta = listaConsultas.stream().anyMatch(c -> 
                                    c.getFecha().equals(tokens[3]) && 
                                    c.getMascota().getNombre().equalsIgnoreCase(tknMascota) &&
                                    c.getVeterinario().getNombre().equalsIgnoreCase(tknVet)
                                );
                                
                                if(!existeConsulta) {
                                    registrarConsulta(new Consulta(mascotaOpt.get(), vetOpt.get(), tokens[3], tokens[4], tratamiento, Double.parseDouble(tokens[6])));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Funcion para obtener la lista de propietarios
     * @return devuelve la lista
     */
    public List<Propietario> getListaPropietarios() {
        return listaPropietarios;
    }

    /**
     * Funcion para obtener la lista de las mascotas
     * @return devuelve la lista
     */
    public List<Mascota> getListaMascotas() {
        return listaMascotas;
    }

    /**
     * Funcion para obtener la lista de vetenirarios
     * @return devuelve la lista
     */
    public List<Veterinario> getListaVeterinarios() {
        return listaVeterinarios;
    }

    /**
     * Funcion para devolver la lista de consultas
     * @return devuelve la lista
     */
    public List<Consulta> getListaConsultas() {
        return listaConsultas;
    }

}
