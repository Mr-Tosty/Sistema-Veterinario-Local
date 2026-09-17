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
package com.aronixcompany.veteninaria.Vista;

import com.aronixcompany.veteninaria.Controlador.Veterinaria;
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
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * ----------------------------------------------------------------------------
 * [ DESCRIPCIÓN TÉCNICA ]
 * ----------------------------------------------------------------------------
 * Descripción : Clase principal que contiene toda la interfaz Módulos : JFrame
 * Dependencias: swing, awt, moledo, excepcio, controlador
 *
 * @author Aaron Cisneros (VA Developer)
 * @version 1.0
 * @since 9 jul 2026
 * ----------------------------------------------------------------------------
 */
public class VentanaPrincipal extends JFrame {

    private final Veterinaria negocio = new Veterinaria();
    
    private DefaultTableModel modeloPropietarios;
    private DefaultTableModel modeloMascotas;
    private DefaultTableModel modeloConsultas;
    private DefaultTableModel modeloVeterinarios;
    
    private JTextField txtPropNombre, txtPropTel, txtPropDir, txtPropCorreo;
    private JTextField txtMascNombre, txtMascEdad, txtMascPeso, txtMascRaza, txtMascPropTel;
    private JComboBox<String> cmbMascEspecie;
    private JTextField txtVetNombre, txtVetEsp, txtVetCedula, txtVetTel;
    private JTextField txtConsMascNombre, txtConsMascTel, txtConsVetNombre, txtConsFecha, txtConsCosto;
    private JTextArea txtConsDiag, txtConsMed, txtConsDosis, txtConsDuracion, txtConsObs;
    private JTextArea txtAreaReportes;

    /**
     * Constructor para inciar la aplicacion
     */
    public VentanaPrincipal() {
        super("Clínica Veterinaria - Panel Operativo Java 25");
        inicializarBaseDatos();
        configurarVentana();
        inicializarComponentes();
    }

    /**
     * Metodo para inicializar la base de datos local
     */
    private void inicializarBaseDatos() {
        try {
            negocio.cargarDatosDesdeArchivo();
        } catch (Exception e) {
            System.out.println("Aviso: No se pudieron cargar datos iniciales: " + e.getMessage());
        }
    }

    /**
     * Metodo para configurar lla ventana principal
     */
    private void configurarVentana() {
        setSize(950, 680);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }

    /**
     * Metodo para inicializar los componentes
     */
    private void inicializarComponentes() {
        var pestañas = new JTabbedPane();

        pestañas.addTab("Propietarios", crearPanelPropietarios());
        pestañas.addTab("Mascotas", crearPanelMascotas());
        pestañas.addTab("Veterinarios", crearPanelVeterinarios());
        pestañas.addTab("Consultas Médicas", crearPanelConsultas());
        pestañas.addTab("Reportes y Agenda", crearPanelReportes());

        add(pestañas, BorderLayout.CENTER);

        // Barra Inferior de Estado / Persistencia rápida
        var panelPersistencia = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        var btnGuardar = new JButton("Guardar Datos en Disco");
        btnGuardar.addActionListener(e -> {
            try {
                negocio.guardarDatosEnArchivo();
                JOptionPane.showMessageDialog(this, "Datos persistidos exitosamente en el archivo local.", "Guardado Correcto", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error de E/S al escribir en archivo: " + ex.getMessage(), "IOException", JOptionPane.ERROR_MESSAGE);
            }
        });
        panelPersistencia.add(btnGuardar);
        add(panelPersistencia, BorderLayout.SOUTH);
    }

    /**
     * Funcio para crear los paneles de propietarios
     *
     * @return devuelve la UX de la tabla
     */
    private JPanel crearPanelPropietarios() {
        var panel = new JPanel(new BorderLayout(10, 10));
        var panelForm = new JPanel(new GridLayout(5, 2, 5, 5));

        panelForm.setBorder(BorderFactory.createTitledBorder("Datos del Propietario"));
        panelForm.add(new JLabel(" Nombre Completo: *"));
        txtPropNombre = new JTextField();
        panelForm.add(txtPropNombre);
        panelForm.add(new JLabel(" Teléfono Celular: *"));
        txtPropTel = new JTextField();
        panelForm.add(txtPropTel);
        panelForm.add(new JLabel(" Dirección Habitacional: *"));
        txtPropDir = new JTextField();
        panelForm.add(txtPropDir);
        panelForm.add(new JLabel(" Correo Electrónico: *"));
        txtPropCorreo = new JTextField();
        panelForm.add(txtPropCorreo);

        var btnRegistrar = new JButton("Registrar Propietario");
        btnRegistrar.addActionListener(e -> procesarRegistroPropietario());
        panelForm.add(btnRegistrar);

        modeloPropietarios = new DefaultTableModel(new String[]{"Nombre", "Teléfono", "Dirección", "Email"}, 0);
        var tabla = new JTable(modeloPropietarios);
        actualizarTablaPropietarios();

        panel.add(panelForm, BorderLayout.NORTH);
        panel.add(new JScrollPane(tabla), BorderLayout.CENTER);
        return panel;
    }

    /**
     * Funcion para crear el panel de las mascotas
     *
     * @return devuelve la UX en tabla
     */
    private JPanel crearPanelMascotas() {
        var panel = new JPanel(new BorderLayout(10, 10));
        var panelForm = new JPanel(new GridLayout(7, 2, 5, 5));

        panelForm.setBorder(BorderFactory.createTitledBorder("Alta de Mascota (Asociación Directa)"));
        panelForm.add(new JLabel(" Nombre Mascota: *"));
        txtMascNombre = new JTextField();
        panelForm.add(txtMascNombre);
        panelForm.add(new JLabel(" Especie: *"));
        cmbMascEspecie = new JComboBox<>(new String[]{"Perro", "Gato", "Ave", "Otro"});
        panelForm.add(cmbMascEspecie);
        panelForm.add(new JLabel(" Raza: *"));
        txtMascRaza = new JTextField();
        panelForm.add(txtMascRaza);
        panelForm.add(new JLabel(" Edad (Años): *"));
        txtMascEdad = new JTextField();
        panelForm.add(txtMascEdad);
        panelForm.add(new JLabel(" Peso (kg): *"));
        txtMascPeso = new JTextField();
        panelForm.add(txtMascPeso);
        panelForm.add(new JLabel(" Teléfono del Propietario: *"));
        txtMascPropTel = new JTextField();
        panelForm.add(txtMascPropTel);

        var btnRegistrar = new JButton("Registrar Mascota");
        btnRegistrar.addActionListener(e -> procesarRegistroMascota());
        panelForm.add(btnRegistrar);

        modeloMascotas = new DefaultTableModel(new String[]{"Nombre", "Especie", "Raza", "Edad", "Peso", "Propietario"}, 0);
        var tabla = new JTable(modeloMascotas);

        panel.add(panelForm, BorderLayout.NORTH);
        panel.add(new JScrollPane(tabla), BorderLayout.CENTER);
        return panel;
    }

    /**
     * Funcion para crear el panel de control de veterinario
     *
     * @return debuelve la UX de la tabla de veterinario
     */
    private JPanel crearPanelVeterinarios() {
        var panel = new JPanel(new BorderLayout(10, 10));
        var panelForm = new JPanel(new GridLayout(5, 2, 5, 5));

        panelForm.setBorder(BorderFactory.createTitledBorder("Registro de Nuevo Veterinario"));

        panelForm.add(new JLabel(" Nombre Completo: *"));
        txtVetNombre = new JTextField();
        panelForm.add(txtVetNombre);

        panelForm.add(new JLabel(" Especialidad: *"));
        txtVetEsp = new JTextField();
        panelForm.add(txtVetEsp);

        panelForm.add(new JLabel(" Cédula Profesional: *"));
        txtVetCedula = new JTextField();
        panelForm.add(txtVetCedula);

        panelForm.add(new JLabel(" Teléfono de Contacto: *"));
        txtVetTel = new JTextField();
        panelForm.add(txtVetTel);

        var btnRegistrar = new JButton("Registrar Veterinario");
        btnRegistrar.addActionListener(e -> procesarRegistroVeterinario());
        panelForm.add(btnRegistrar);
        panelForm.add(new JLabel("")); // Espacio vacío para alinear el botón

        modeloVeterinarios = new DefaultTableModel(new String[]{"Nombre", "Especialidad", "Cédula", "Teléfono"}, 0);
        var tabla = new JTable(modeloVeterinarios);
        actualizarTablaVeterinarios(); // Llenamos la tabla con datos iniciales

        panel.add(panelForm, BorderLayout.NORTH);
        panel.add(new JScrollPane(tabla), BorderLayout.CENTER);
        return panel;
    }

    /**
     * Funcion para crear el panel de consultas
     *
     * @return devuelve la UX en tabla de consultas
     */
    private JPanel crearPanelConsultas() {
        var panel = new JPanel(new BorderLayout(10, 10));

        // Creamos un panel principal dividido en dos columnas
        var panelFormPrincipal = new JPanel(new GridLayout(1, 2, 15, 5));
        panelFormPrincipal.setBorder(BorderFactory.createTitledBorder("Apertura de Consulta y Tratamiento"));

        // Columna Izquierda: Datos de la Consulta
        var panelIzquierdo = new JPanel(new GridLayout(6, 2, 5, 5));
        panelIzquierdo.setBorder(BorderFactory.createTitledBorder("Datos Generales"));
        panelIzquierdo.add(new JLabel(" Nombre Mascota:"));
        txtConsMascNombre = new JTextField();
        panelIzquierdo.add(txtConsMascNombre);
        panelIzquierdo.add(new JLabel(" Tel. Propietario:"));
        txtConsMascTel = new JTextField();
        panelIzquierdo.add(txtConsMascTel);
        panelIzquierdo.add(new JLabel(" Nombre Veterinario:"));
        txtConsVetNombre = new JTextField();
        panelIzquierdo.add(txtConsVetNombre);
        panelIzquierdo.add(new JLabel(" Fecha (AAAA-MM-DD):"));
        txtConsFecha = new JTextField();
        panelIzquierdo.add(txtConsFecha);
        panelIzquierdo.add(new JLabel(" Costo Consulta ($):"));
        txtConsCosto = new JTextField();
        panelIzquierdo.add(txtConsCosto);
        panelIzquierdo.add(new JLabel(" Diagnóstico Clínico:"));
        txtConsDiag = new JTextArea(2, 10);
        panelIzquierdo.add(new JScrollPane(txtConsDiag));

        // Columna Derecha: Datos del Tratamiento
        var panelDerecho = new JPanel(new GridLayout(6, 2, 5, 5));
        panelDerecho.setBorder(BorderFactory.createTitledBorder("Receta / Tratamiento"));
        panelDerecho.add(new JLabel(" Medicamento:"));
        txtConsMed = new JTextArea(2, 10);
        panelDerecho.add(new JScrollPane(txtConsMed));
        panelDerecho.add(new JLabel(" Dosis:"));
        txtConsDosis = new JTextArea(2, 10);
        panelDerecho.add(new JScrollPane(txtConsDosis));
        panelDerecho.add(new JLabel(" Duración:"));
        txtConsDuracion = new JTextArea(2, 10);
        panelDerecho.add(new JScrollPane(txtConsDuracion));
        panelDerecho.add(new JLabel(" Observaciones:"));
        txtConsObs = new JTextArea(2, 10);
        panelDerecho.add(new JScrollPane(txtConsObs));

        // Botón de registro en la parte inferior de la columna derecha
        panelDerecho.add(new JLabel("")); // Espacio vacío
        var btnRegistrar = new JButton("Registrar Consulta");
        btnRegistrar.setBackground(new Color(46, 204, 113)); // Un color verde suave para destacar
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.addActionListener(e -> procesarRegistroConsulta());
        panelDerecho.add(btnRegistrar);

        // Agregamos ambas columnas al panel principal
        panelFormPrincipal.add(panelIzquierdo);
        panelFormPrincipal.add(panelDerecho);

        // Configuramos la tabla
        modeloConsultas = new DefaultTableModel(new String[]{"Fecha", "Mascota", "Veterinario", "Diagnóstico", "Costo"}, 0);
        var tabla = new JTable(modeloConsultas);

        panel.add(panelFormPrincipal, BorderLayout.NORTH);
        panel.add(new JScrollPane(tabla), BorderLayout.CENTER);
        return panel;
    }

    /**
     * Funcion para crear el reporte de las ganancias
     *
     * @return evuelve los datos en UX
     */
    private JPanel crearPanelReportes() {
        var panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Inteligencia del Negocio y Agendas"));

        txtAreaReportes = new JTextArea();
        txtAreaReportes.setFont(new Font("Monospaced", Font.PLAIN, 12));
        txtAreaReportes.setEditable(false);

        var btnCalcular = new JButton("Ejecutar Auditoría e Informes Estadísticos");
        btnCalcular.addActionListener(e -> txtAreaReportes.setText(negocio.generarReportesYEstadisticas()));

        var panelAgenda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        var txtBuscarVet = new JTextField(15);
        var btnBuscarAgenda = new JButton("Ver Agenda del Médico");

        btnBuscarAgenda.addActionListener(e -> {
            var consults = negocio.obtenerAgendaVeterinario(txtBuscarVet.getText().strip());
            var sb = new StringBuilder("=== AGENDA PARA: " + txtBuscarVet.getText() + " ===\n");
            for (var c : consults) {
                sb.append("[%s] Mascota: %s | Diagnóstico: %s\n".formatted(c.getFecha(), c.getMascota().getNombre(), c.getDiagnostico()));
            }
            if (consults.isEmpty()) {
                sb.append("No registra citas agendadas.\n");
            }
            txtAreaReportes.setText(sb.toString());
        });

        panelAgenda.add(new JLabel("Filtrar Médico:"));
        panelAgenda.add(txtBuscarVet);
        panelAgenda.add(btnBuscarAgenda);

        var norte = new JPanel(new GridLayout(2, 1));
        norte.add(btnCalcular);
        norte.add(panelAgenda);

        panel.add(norte, BorderLayout.NORTH);
        panel.add(new JScrollPane(txtAreaReportes), BorderLayout.CENTER);
        return panel;
    }

    /**
     * Metodo para procesar el registro del propietario
     */
    private void procesarRegistroPropietario() {
        try {
            var p = new Propietario(
                    txtPropNombre.getText(),
                    txtPropTel.getText(),
                    txtPropDir.getText(),
                    txtPropCorreo.getText()
            );
            negocio.agregarPropietario(p);
            actualizarTablaPropietarios();
            limpiarCampos(txtPropNombre, txtPropTel, txtPropDir, txtPropCorreo);
            JOptionPane.showMessageDialog(this, "Propietario añadido correctamente.");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "IllegalArgumentException", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Metodo para procesaro los datos de las mascotas
     */
    private void procesarRegistroMascota() {
        try {
            var prop = negocio.buscarPropietarioPorTelefono(txtMascPropTel.getText().strip());
            int edad = Integer.parseInt(txtMascEdad.getText().strip());
                double peso = Double.parseDouble(txtMascPeso.getText().strip());
            var especie = (String) cmbMascEspecie.getSelectedItem();
            var raza = txtMascRaza.getText();
            var nombre = txtMascNombre.getText();

            Mascota m = switch (especie) {
                case "Perro" ->
                    new Perro(nombre, edad, peso, raza, prop, "Alto");
                case "Gato" ->
                    new Gato(nombre, edad, peso, raza, prop, true);
                case "Ave" ->
                    new Ave(nombre, edad, peso, raza, prop, true);
                default ->
                    new Mascota(nombre, edad, peso, especie, raza, prop);
            };

            negocio.agregarMascota(m);
            actualizarTablaMascotas();
            limpiarCampos(txtMascNombre, txtMascRaza, txtMascEdad, txtMascPeso, txtMascPropTel);
            JOptionPane.showMessageDialog(this, "Mascota asociada al cliente de forma exitosa.");
        } catch (PropietarioNoEncontradoException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Propietario Ausente", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "La edad y el peso deben traducirse a valores numéricos válidos.", "NumberFormatException", JOptionPane.ERROR_MESSAGE);
        } catch (MascotaDuplicadaException | IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error en Datos", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Metodo para registrar la consulta
     */
    private void procesarRegistroConsulta() {
        try {
            var tknMascota = txtConsMascNombre.getText().strip();
            var tknTel = txtConsMascTel.getText().strip();

            if (tknMascota.isEmpty() || tknTel.isEmpty()) {
                throw new NullPointerException("Debe ingresar la clave de la mascota y el teléfono para buscar.");
            }

            var mascotaOpt = negocio.getListaMascotas().stream()
                    .filter(m -> m.getNombre().equalsIgnoreCase(tknMascota) && m.getPropietario().getTelefono().equals(tknTel))
                    .findFirst();

            if (mascotaOpt.isEmpty()) {
                throw new IllegalArgumentException("No se encuentra ninguna mascota registrada con ese nombre para el teléfono indicado.");
            }

            var vetNombre = txtConsVetNombre.getText().strip();
            var vet = negocio.getListaVeterinarios().stream()
                    .filter(v -> v.getNombre().equalsIgnoreCase(vetNombre))
                    .findFirst()
                    .orElseGet(() -> new Veterinario(vetNombre, "General", "CED-GENERIC", "0000"));

            double costo = Double.parseDouble(txtConsCosto.getText().strip());

            var tratamiento = new Tratamiento(txtConsMed.getText(), txtConsDosis.getText(), txtConsDuracion.getText(), txtConsObs.getText());
            var consulta = new Consulta(mascotaOpt.get(), vet, txtConsFecha.getText(), txtConsDiag.getText(), tratamiento, costo);

            negocio.registrarConsulta(consulta);
            actualizarTablaConsultas();
            limpiarCampos(txtConsMascNombre, txtConsMascTel, txtConsVetNombre, txtConsFecha, txtConsCosto, txtConsDiag, txtConsMed, txtConsDosis, txtConsDuracion, txtConsObs);
            JOptionPane.showMessageDialog(this, "Consulta grabada correctamente.");
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "NullPointerException", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El costo monetario de la consulta debe ser numérico.", "NumberFormatException", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Campos incorrectos", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Metodo para actualizar la tabla de propietarios
     */
    private void actualizarTablaPropietarios() {
        modeloPropietarios.setRowCount(0);
        for (var p : negocio.getListaPropietarios()) {
            modeloPropietarios.addRow(new Object[]{p.getNombre(), p.getTelefono(), p.getDireccion(), p.getCorreoElectronico()});
        }
    }

    /**
     * Metodo para actualizar la tabla de mascotas
     */
    private void actualizarTablaMascotas() {
        modeloMascotas.setRowCount(0);
        for (var m : negocio.getListaMascotas()) {
            modeloMascotas.addRow(new Object[]{m.getNombre(), m.getEspecie(), m.getRaza(), m.getEdad(), m.getPeso(), m.getPropietario().getNombre()});
        }
    }

    /**
     * Metodo para actualizar la tabla de consultas
     */
    private void actualizarTablaConsultas() {
        modeloConsultas.setRowCount(0);
        for (var c : negocio.getListaConsultas()) {
            modeloConsultas.addRow(new Object[]{c.getFecha(), c.getMascota().getNombre(), c.getVeterinario().getNombre(), c.getDiagnostico(), c.getCosto()});
        }
    }

    /**
     * Funcion para limpiar los componentes de la tabla
     *
     * @param componentes Recibe los componentes a actualizar
     */
    private void limpiarCampos(Component... componentes) {
        for (var c : componentes) {
            if (c instanceof JTextField tf) {
                tf.setText("");
            }
            if (c instanceof JTextArea ta) {
                ta.setText("");
            }
        }
    }

    /**
     * Metodo para porcesar los registros
     */
    private void procesarRegistroVeterinario() {
        try {
            var v = new Veterinario(
                    txtVetNombre.getText(),
                    txtVetEsp.getText(),
                    txtVetCedula.getText(),
                    txtVetTel.getText()
            );
            negocio.agregarVeterinario(v);
            actualizarTablaVeterinarios();
            limpiarCampos(txtVetNombre, txtVetEsp, txtVetCedula, txtVetTel);
            JOptionPane.showMessageDialog(this, "Veterinario añadido correctamente.");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Campos incompletos", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Metodo para poder actualizar los datos del veterinario
     */
    private void actualizarTablaVeterinarios() {
        if (modeloVeterinarios != null) {
            modeloVeterinarios.setRowCount(0);
            for (var v : negocio.getListaVeterinarios()) {
                modeloVeterinarios.addRow(new Object[]{v.getNombre(), v.getEspecialidad(), v.getCedulaProfesional(), v.getTelefono()});
            }
        }
    }

}
