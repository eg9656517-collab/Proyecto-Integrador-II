/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package inventariofarmacia;

import Modelo.DatosCompartidos;
import Modelo.Producto;
import java.util.ArrayList; //Libreria a utilizar que se van agregando
import javax.swing.JOptionPane;
import javax.swing.JDialog;//Libreria para ventana de dialogo
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;
import java.beans.PropertyVetoException;
import java.util.List;

/**
 *
 * @author lapto
 */
public class JInternalFrameProducto extends javax.swing.JInternalFrame {

    /**
     * Arreglo de objetos productos
     */
    ArrayList<Producto> listaProducto = new ArrayList<>();
    int indice = -1; // Variable utilizada para para Editar/Actualizar

    DefaultTableModel model;
    List<Object[]> originalOrder = new ArrayList<>();
    // Esta variable guardará la instancia de este formulario
    public static JInternalFrameProducto instanciaAbierta;

    /**
     * Creates new form JInternalFrameProducto
     */
    public JInternalFrameProducto() {  // Constructor del formulario
        initComponents();
        model = (DefaultTableModel) jTableProductos.getModel();

        // Le decimos a la variable estática 
        instanciaAbierta = this;
        // Usamos el metodo para cargar la tabla
        cargarTabla();

    }

    private void limpiarCampos() { //Metodo para limpiar cajas de texto
        //se asigna un valor vacio a todas la cajas de texto
        //Se utiñiza el nombre de variable asignado
        this.jTextFieldID.setText(""); // Set para asignar un valor
        this.jTextFieldNombreP.setText("");
        this.jTextFieldCodigo.setText("");
        this.jTextFieldDescripcion.setText("");
        this.jTextFieldUnidadM.setText("");
        this.jTextFieldPrecioC.setText("");
        this.jTextFieldPorsentajeG.setText("");
        this.jTextFieldStockM.setText("");
        this.jTextFieldLote.setText("");
        this.FechaV.setText("");
        this.jTextFieldCategoria.setText("");
        this.jTextFieldPresentacion.setText("");
        this.jTextFieldPrecioV.setText("");
        this.jTextFieldStockI.setText("");

    }

    public void cargarTabla() {
        model = new DefaultTableModel();
        String[] columnas = {"ID", "Nombres", "Unidad Medida", "Fecha Vencimiento",
            "Presentacion", "Precio Venta", "Stock Incial"};
        model.setColumnIdentifiers(columnas);
        for (Producto pro : DatosCompartidos.listaProducto) {
            // Agrega cada elemento de la lista a un arreglo
            // Utiliza un objeto pro del tipo clase producto para obtener
            // datos de cada atributo, atraves del metodo get
            String[] renglon = {Integer.toString(pro.getID()), pro.getNombre(),
                pro.getUnidadM(), pro.getFechaV().toString(), pro.getPresentacion(),
                Double.toString(pro.getPrecioV()), Integer.toString(pro.getStockI())};
            model.addRow(renglon);
        }

        jTableProductos.setModel(model);
    }

    // Se crea el metodo que guarda el orden original
    private void guardarOrdenOriginal(DefaultTableModel modelo) {
        if (modelo == null) {
            return;
        }
        originalOrder.clear(); // Limpias la lista que guarda el orden original
        for (int i = 0; i < modelo.getRowCount(); i++) {
            Object[] fila = new Object[modelo.getColumnCount()];
            for (int j = 0; j < modelo.getColumnCount(); j++) {
                fila[j] = modelo.getValueAt(i, j);
            }
            originalOrder.add(fila);
        }
    }

    // se Crea el metodo para buscar el producto y ponerlo en la primera fila
    public void buscarProducto(String nombreProducto) {
        if (jTableProductos == null) {
            JOptionPane.showMessageDialog(null, "La tabla no está inicializada.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (model == null) {
            model = (DefaultTableModel) jTableProductos.getModel();
        }

        if (model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "No hay productos en la tabla.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (nombreProducto == null || nombreProducto.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese un nombre de producto válido.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int filaEncontrada = -1;
        nombreProducto = nombreProducto.toLowerCase().trim();

        // Buscar coincidencia parcial en la columna del nombre (columna 1)
        for (int i = 0; i < model.getRowCount(); i++) {
            Object celda = model.getValueAt(i, 1);
            if (celda != null) {
                String valorCelda = celda.toString().toLowerCase();
                if (valorCelda.contains(nombreProducto)) {
                    filaEncontrada = i;
                    break;
                }
            }
        }

        if (filaEncontrada == -1) {
            JOptionPane.showMessageDialog(null, "No se encontró el producto.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Guardar los datos de la fila encontrada
        Object[] filaBuscada = new Object[model.getColumnCount()];
        for (int j = 0; j < model.getColumnCount(); j++) {
            filaBuscada[j] = model.getValueAt(filaEncontrada, j);
        }

        // Mover fila al inicio
        model.removeRow(filaEncontrada);
        model.insertRow(0, filaBuscada);

        // Selecciona y enfoca la primera fila
        jTableProductos.setModel(model); // Fuerza a que JTable reconozca el cambio
        jTableProductos.requestFocus();
        jTableProductos.changeSelection(0, 0, false, false); // <-- Selección real
    }

    // Crea el método para restaurar el orden original:
    public void restaurarOrden() {
        if (model == null || originalOrder.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay un orden guardado para restaurar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        model.setRowCount(0); // Limpiar tabla
        for (Object[] row : originalOrder) {
            model.addRow(row);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldCategoria = new javax.swing.JTextField();
        jTextFieldNombreP = new javax.swing.JTextField();
        jTextFieldDescripcion = new javax.swing.JTextField();
        jTextFieldUnidadM = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldPorsentajeG = new javax.swing.JTextField();
        jTextFieldPrecioC = new javax.swing.JTextField();
        jTextFieldStockM = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextFieldID = new javax.swing.JTextField();
        FechaV = new javax.swing.JFormattedTextField();
        jTextFieldLote = new javax.swing.JTextField();
        jTextFieldPresentacion = new javax.swing.JTextField();
        jTextFieldPrecioV = new javax.swing.JTextField();
        jTextFieldStockI = new javax.swing.JTextField();
        jButtonGuardar = new javax.swing.JButton();
        jButtonEditar = new javax.swing.JButton();
        jButtonActualizar = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
        jButtonLimpiar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableProductos = new javax.swing.JTable();
        jTextFieldCodigo = new javax.swing.JTextField();
        jTextFieldFiltrar = new javax.swing.JTextField();
        jButtonBuscar = new javax.swing.JButton();
        jLabelMinimizar = new javax.swing.JLabel();
        jLabelMaximizar = new javax.swing.JLabel();
        jLabelCerrar = new javax.swing.JLabel();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de Productos", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setText("ID:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 60, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 255));
        jLabel2.setText("Nombre:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 92, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 255));
        jLabel3.setText("Codigo: ");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 124, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 255));
        jLabel4.setText("Descripción:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 156, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 255));
        jLabel5.setText("Unidad de medida:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 188, -1, -1));

        jTextFieldCategoria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldCategoriaKeyTyped(evt);
            }
        });
        jPanel1.add(jTextFieldCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(564, 160, 113, -1));

        jTextFieldNombreP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldNombrePKeyTyped(evt);
            }
        });
        jPanel1.add(jTextFieldNombreP, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 96, 112, -1));

        jTextFieldDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldDescripcionKeyTyped(evt);
            }
        });
        jPanel1.add(jTextFieldDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 160, 112, -1));

        jTextFieldUnidadM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldUnidadMKeyTyped(evt);
            }
        });
        jPanel1.add(jTextFieldUnidadM, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 192, 112, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 255));
        jLabel6.setText("Precio de compra:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 220, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 255));
        jLabel7.setText("Porcentaje de ganacia:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 252, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 255));
        jLabel8.setText("Stock minimo:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 284, -1, -1));

        jTextFieldPorsentajeG.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldPorsentajeGKeyTyped(evt);
            }
        });
        jPanel1.add(jTextFieldPorsentajeG, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 256, 112, -1));

        jTextFieldPrecioC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldPrecioCKeyTyped(evt);
            }
        });
        jPanel1.add(jTextFieldPrecioC, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 224, 112, -1));

        jTextFieldStockM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldStockMKeyTyped(evt);
            }
        });
        jPanel1.add(jTextFieldStockM, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 288, 112, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 255));
        jLabel9.setText("Lote:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(345, 92, -1, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 255));
        jLabel10.setText("Fecha de vencimiento:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(345, 124, -1, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 255));
        jLabel11.setText("Categoría:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(345, 156, -1, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 255));
        jLabel12.setText("Presentación:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(345, 188, -1, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 255));
        jLabel13.setText("Precio de venta:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(345, 220, -1, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 255));
        jLabel14.setText("Stock inicial:");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(345, 272, -1, -1));

        jTextFieldID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldIDKeyTyped(evt);
            }
        });
        jPanel1.add(jTextFieldID, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 64, 112, -1));

        try {
            FechaV.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jPanel1.add(FechaV, new org.netbeans.lib.awtextra.AbsoluteConstraints(564, 128, 113, -1));

        jTextFieldLote.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldLoteKeyTyped(evt);
            }
        });
        jPanel1.add(jTextFieldLote, new org.netbeans.lib.awtextra.AbsoluteConstraints(564, 96, 113, -1));

        jTextFieldPresentacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldPresentacionKeyTyped(evt);
            }
        });
        jPanel1.add(jTextFieldPresentacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(564, 192, 113, -1));

        jTextFieldPrecioV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldPrecioVKeyTyped(evt);
            }
        });
        jPanel1.add(jTextFieldPrecioV, new org.netbeans.lib.awtextra.AbsoluteConstraints(564, 224, 113, -1));

        jTextFieldStockI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldStockIKeyTyped(evt);
            }
        });
        jPanel1.add(jTextFieldStockI, new org.netbeans.lib.awtextra.AbsoluteConstraints(564, 276, 113, -1));

        jButtonGuardar.setBackground(new java.awt.Color(0, 153, 0));
        jButtonGuardar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonGuardar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonGuardar.setText("Guardar");
        jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 328, -1, -1));

        jButtonEditar.setBackground(new java.awt.Color(0, 0, 153));
        jButtonEditar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonEditar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonEditar.setText("Editar");
        jButtonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 328, -1, -1));

        jButtonActualizar.setBackground(new java.awt.Color(0, 102, 102));
        jButtonActualizar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonActualizar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonActualizar.setText("Actualizar");
        jButtonActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActualizarActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(292, 328, -1, -1));

        jButtonEliminar.setBackground(new java.awt.Color(153, 0, 0));
        jButtonEliminar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonEliminar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonEliminar.setText("Eliminar");
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(451, 328, -1, -1));

        jButtonLimpiar.setBackground(new java.awt.Color(102, 102, 102));
        jButtonLimpiar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonLimpiar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonLimpiar.setText("Limpiar");
        jButtonLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimpiarActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(605, 328, -1, -1));

        jTableProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "", "", "", "", ""
            }
        ));
        jScrollPane1.setViewportView(jTableProductos);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 661, 170));

        jTextFieldCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldCodigoKeyTyped(evt);
            }
        });
        jPanel1.add(jTextFieldCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 128, 112, -1));
        jPanel1.add(jTextFieldFiltrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 260, 30));

        jButtonBuscar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonBuscar.setText("Buscar");
        jButtonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 370, -1, 30));

        jLabelMinimizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventariofarmacia/maximize (2).png"))); // NOI18N
        jLabelMinimizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelMinimizarMouseClicked(evt);
            }
        });

        jLabelMaximizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventariofarmacia/checkbox-blank-line (1).png"))); // NOI18N
        jLabelMaximizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelMaximizarMouseClicked(evt);
            }
        });

        jLabelCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventariofarmacia/close-fill (1) (1).png"))); // NOI18N
        jLabelCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelCerrarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabelMinimizar)
                        .addGap(25, 25, 25)
                        .addComponent(jLabelMaximizar)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelCerrar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 711, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 8, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelMinimizar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabelCerrar)
                        .addComponent(jLabelMaximizar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimpiarActionPerformed
        //Llama al metodo limpiar camos
        limpiarCampos();
    }//GEN-LAST:event_jButtonLimpiarActionPerformed

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        //validar que todos los campos de texto no esten vacios
        if ((jTextFieldID.getText().contentEquals(""))
                || (jTextFieldNombreP.getText().contentEquals(""))
                || (jTextFieldCodigo.getText().contentEquals(""))
                || (jTextFieldDescripcion.getText().contentEquals(""))
                || (jTextFieldUnidadM.getText().contentEquals(""))
                || (jTextFieldPrecioC.getText().contentEquals(""))
                || (jTextFieldPorsentajeG.getText().contentEquals(""))
                || (jTextFieldStockM.getText().contentEquals(""))
                || (jTextFieldLote.getText().contentEquals(""))
                || (FechaV.getText().contentEquals(""))
                || (jTextFieldCategoria.getText().contentEquals(""))
                || (jTextFieldPresentacion.getText().contentEquals(""))
                || (jTextFieldPrecioV.getText().contentEquals(""))
                || (jTextFieldStockI.getText().contentEquals(""))) {
            //Mensaje para informar si algun campo esta vacio // Agrego import
            JOptionPane.showMessageDialog(null,
                    "Algunos campos estan vacios",
                    "!Revise todos los campos!",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            //Get para obtener un valor
            //obtiene lo que esta en la caja de texto y lo convierte a tipo de dato
            int idProducto = Integer.parseInt(this.jTextFieldID.getText());
            String nombreProducto = this.jTextFieldNombreP.getText();
            String codigo = this.jTextFieldCodigo.getText();
            String descripcion = this.jTextFieldDescripcion.getText();
            String unidadMedida = this.jTextFieldUnidadM.getText().trim();
            Double precioCompra = Double.parseDouble(this.jTextFieldPrecioC.getText());
            Double porsentajeGanancia = Double.parseDouble(this.jTextFieldPorsentajeG.getText());
            int stockMinimo = Integer.parseInt(this.jTextFieldStockM.getText());
            String fechaVencimientos = this.FechaV.getText();
            String nombreCategoria = this.jTextFieldCategoria.getText();
            String presentacion = this.jTextFieldPresentacion.getText();
            Double precioVenta = Double.parseDouble(this.jTextFieldPrecioV.getText());
            int stockInicial = Integer.parseInt(this.jTextFieldStockI.getText());
            String loteProducto = this.jTextFieldLote.getText();

            try { //Bloque try (intentar) - catch (capturar) para capturar errores
                //Declarando un objeto de la clase producto,
                //crea un objeto de la clase producto
                //llama al metodo constructor de la clase producto
                Producto prod = new Producto(idProducto, nombreProducto, codigo, unidadMedida, precioCompra, porsentajeGanancia,
                        stockMinimo, fechaVencimientos, nombreCategoria, presentacion, precioVenta, stockInicial, descripcion, loteProducto);
                //Agrega al arreglo de objetos los datos del objeto prod
                // this.listaProducto.add(prod);
                DatosCompartidos.agregarProducto(prod);

                this.limpiarCampos(); //llama al metodo limpiar campos

                cargarTabla(); // Llama al metodo llenarTabla

                JOptionPane.showMessageDialog(null,
                        "Datos agregados correctamente");

            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
                JOptionPane.showMessageDialog(null,
                        "Verifique que los camos númericos sean validos.",
                        "Error de formato",
                        JOptionPane.ERROR_MESSAGE);

            } catch (Exception e) { //capturar el error
                e.printStackTrace();
                JOptionPane.showMessageDialog(null,
                        "Datos no agregados:",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        int fila = this.jTableProductos.getSelectedRow(); //Se obtiene #fila seleccionada
        // Se verifica que este seleccionada una fila de la tabla
        if (fila == -1) {
            JOptionPane.showMessageDialog(rootPane,
                    "Seleccione un registro de la tabla");
        } else {
            // Ventana de dialogo para preguntar si desea eliminar
            JDialog.setDefaultLookAndFeelDecorated(true);
            int resp = JOptionPane.showConfirmDialog(null,
                    "¿Esta seguro de eliminar?", "Eliminado...",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            // Si la respuesta es si se elimina
            if (resp == JOptionPane.YES_OPTION) {
                // Metodo remove para quitar de la lista de la fila seleccionada
                // Se le debe indicar la posicion del elemento de la lista
                // la numeracion de filas de la tabla es igual a las posiciones de la lista
                DatosCompartidos.listaProducto.remove(fila);
                // Se llama al metodo llenarTabla para mostrar los datos actuales de la lista
                cargarTabla();
            }
        }
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        // Se verifica que este seleccionada una fila de la tabla
        int fila = this.jTableProductos.getSelectedRow(); //se obtiene #fila selecionada
        if (fila == -1) {
            JOptionPane.showMessageDialog(rootPane,
                    "Seleccione un registro de la tabla");
            return;
        }
        // Se asigna a la variable global 
        indice = fila; // Se actualiza la variable gloval 

        // Accedemos al objeto completo desde la lista
        Producto prod = DatosCompartidos.listaProducto.get(indice);
        //se rellena los camos directamente des de el objeto

        jTextFieldID.setText(String.valueOf(prod.getID()));
        jTextFieldNombreP.setText(prod.getNombre());
        jTextFieldCodigo.setText(prod.getCodigo());
        jTextFieldUnidadM.setText(prod.getUnidadM());
        jTextFieldPrecioC.setText(String.valueOf(prod.getPrecioC()));
        jTextFieldPorsentajeG.setText(String.valueOf(prod.getPorsentajeG()));
        jTextFieldStockM.setText(String.valueOf(prod.getStockM()));
        FechaV.setText(prod.getFechaV().toString());
        jTextFieldCategoria.setText(prod.getCategoria());
        jTextFieldPresentacion.setText(prod.getPresentacion());
        jTextFieldPrecioV.setText(String.valueOf(prod.getPrecioV()));
        jTextFieldStockI.setText(String.valueOf(prod.getStockI()));
        jTextFieldDescripcion.setText(prod.getDescripcion());
        jTextFieldLote.setText(prod.getLote());


    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jButtonActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActualizarActionPerformed
        // Validamos si selecciono un producto para editar que los campos no esten vacios
        if (jTextFieldID.getText().trim().isEmpty()
                || jTextFieldNombreP.getText().trim().isEmpty()
                || jTextFieldCodigo.getText().trim().isEmpty()
                || jTextFieldUnidadM.getText().trim().isEmpty()
                || jTextFieldPrecioC.getText().trim().isEmpty()
                || jTextFieldPorsentajeG.getText().trim().isEmpty()
                || jTextFieldStockM.getText().trim().isEmpty()
                || FechaV.getText().trim().isEmpty()
                || jTextFieldCategoria.getText().trim().isEmpty()
                || jTextFieldPresentacion.getText().trim().isEmpty()
                || jTextFieldPrecioV.getText().trim().isEmpty()
                || jTextFieldStockI.getText().trim().isEmpty()
                || jTextFieldDescripcion.getText().trim().isEmpty()
                || jTextFieldLote.getText().trim().isEmpty()
                || indice == -1) {
            // mensaje para informar si algunos campos estan vacios
            JOptionPane.showMessageDialog(null,
                    "Algunos campos están vacíos o no ha seleccionado un producto",
                    "Actualizar...",
                    JOptionPane.WARNING_MESSAGE);
            limpiarCampos();
        }

        try {
            // Obtenemos el producto de la lista usando el índice
            Producto prod = DatosCompartidos.listaProducto.get(indice);

            // Actualizamos todos los campos
            prod.setID(Integer.parseInt(jTextFieldID.getText().trim()));
            prod.setNombre(jTextFieldNombreP.getText().trim());
            prod.setCodigo(jTextFieldCodigo.getText().trim());
            prod.setUnidadM(jTextFieldUnidadM.getText().trim());
            prod.setPrecioC(Double.parseDouble(jTextFieldPrecioC.getText().trim()));
            prod.setPorsentajeG(Double.parseDouble(jTextFieldPorsentajeG.getText().trim()));
            prod.SetStockM(Integer.parseInt(jTextFieldStockM.getText().trim()));
            prod.setFechaV(FechaV.getText().trim());
            prod.setCategoria(jTextFieldCategoria.getText().trim());
            prod.setPresentacion(jTextFieldPresentacion.getText().trim());
            prod.setPrecioV(Double.parseDouble(jTextFieldPrecioV.getText().trim()));
            prod.setStockI(Integer.parseInt(jTextFieldStockI.getText().trim()));
            prod.setDescripcion(jTextFieldDescripcion.getText().trim());
            prod.setLote(jTextFieldLote.getText().trim());

            // Sellama al metodo llenar tabla para mostrar los datos actuales de la lista
            cargarTabla();

            // llama al metodo limpiamoCampos
            limpiarCampos();

            // Reiniciamos el índice
            indice = -1;

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Error en formato numérico. Revise los valores de ID, precios y stock.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (IndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null,
                    "Error: Producto no encontrado en la lista.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButtonActualizarActionPerformed

    private void jTextFieldIDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldIDKeyTyped
        char car = evt.getKeyChar();
        // Solo permite números del 0 al 9
        if (car < '0' || car > '9') {
            evt.consume(); // Ignora el carácter si no es un número
        }


    }//GEN-LAST:event_jTextFieldIDKeyTyped

    private void jTextFieldNombrePKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNombrePKeyTyped
        char c = evt.getKeyChar();

        // Permitimos letras (mayúsculas y minúsculas), espacios, ñ, Ñ y vocales acentuadas
        if (!Character.isLetter(c)
                && c != ' '
                && c != 'ñ' && c != 'Ñ'
                && c != 'á' && c != 'é' && c != 'í' && c != 'ó' && c != 'ú'
                && c != 'Á' && c != 'É' && c != 'Í' && c != 'Ó' && c != 'Ú') {
            evt.consume(); // Ignora cualquier otro carácter
        }


    }//GEN-LAST:event_jTextFieldNombrePKeyTyped

    private void jTextFieldCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCodigoKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isLetterOrDigit(c)) {
            evt.consume(); // No permite caracteres que no sean letras o números
        }


    }//GEN-LAST:event_jTextFieldCodigoKeyTyped

    private void jTextFieldDescripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldDescripcionKeyTyped
        char car = evt.getKeyChar();

        // Permite letras y espacios únicamente
        if (!Character.isLetter(car) && car != KeyEvent.VK_SPACE) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldDescripcionKeyTyped

    private void jTextFieldUnidadMKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldUnidadMKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isLetterOrDigit(c)) {
            evt.consume(); // No permite caracteres que no sean letras o números
        }
    }//GEN-LAST:event_jTextFieldUnidadMKeyTyped

    private void jTextFieldPrecioCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPrecioCKeyTyped
        char car = evt.getKeyChar();
        // Solo permite números del 0 al 9
        if (car < '0' || car > '9') {
            evt.consume(); // Ignora el carácter si no es un número
        }
    }//GEN-LAST:event_jTextFieldPrecioCKeyTyped

    private void jTextFieldPorsentajeGKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPorsentajeGKeyTyped
        char c = evt.getKeyChar();
        JTextField field = (JTextField) evt.getSource();
        String texto = field.getText();

        if (Character.isDigit(c)) {
            if (texto.contains("%")) {
                // No permitir números después del %
                evt.consume();
            }
        } else if (c == '%') {
            if (texto.contains("%") || texto.isEmpty()) {
                // No permitir más de un % o si se escribe como primer carácter
                evt.consume();
            }
        } else {
            // Bloquear cualquier otro carácter
            evt.consume();
        }

    }//GEN-LAST:event_jTextFieldPorsentajeGKeyTyped

    private void jTextFieldStockMKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldStockMKeyTyped
        char car = evt.getKeyChar();
        // Solo permite números del 0 al 9
        if (car < '0' || car > '9') {
            evt.consume(); // Ignora el carácter si no es un número
        }
    }//GEN-LAST:event_jTextFieldStockMKeyTyped

    private void jTextFieldLoteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldLoteKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isLetterOrDigit(c)) {
            evt.consume(); // No permite caracteres que no sean letras o números
        }
    }//GEN-LAST:event_jTextFieldLoteKeyTyped

    private void jTextFieldCategoriaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCategoriaKeyTyped
        char car = evt.getKeyChar();

        // Permite letras y espacios únicamente
        if (!Character.isLetter(car) && car != KeyEvent.VK_SPACE) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldCategoriaKeyTyped

    private void jTextFieldPresentacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPresentacionKeyTyped
        char c = evt.getKeyChar();
        // permite letras (incluye ñ y acentos), numeros y espacio
        if (!Character.isLetterOrDigit(c)
                && c != ' '
                && c != 'ñ' && c != 'Ñ'
                && c != 'á' && c != 'é' && c != 'í' && c != 'ó' && c != 'ú'
                && c != 'Á' && c != 'É' && c != 'Í' && c != 'Ó' && c != 'Ú') {
            evt.consume(); // Ignora cualquier otro carácter
        }

    }//GEN-LAST:event_jTextFieldPresentacionKeyTyped

    private void jTextFieldPrecioVKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPrecioVKeyTyped
        char car = evt.getKeyChar();
        // Solo permite números del 0 al 9
        if (car < '0' || car > '9') {
            evt.consume(); // Ignora el carácter si no es un número
        }
    }//GEN-LAST:event_jTextFieldPrecioVKeyTyped

    private void jTextFieldStockIKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldStockIKeyTyped
        char car = evt.getKeyChar();
        // Solo permite números del 0 al 9
        if (car < '0' || car > '9') {
            evt.consume(); // Ignora el carácter si no es un número
        }
    }//GEN-LAST:event_jTextFieldStockIKeyTyped

    private void jLabelMinimizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMinimizarMouseClicked
        try {
            this.setIcon(true);

        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
        }

    }//GEN-LAST:event_jLabelMinimizarMouseClicked

    private void jLabelMaximizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMaximizarMouseClicked
        try {
            this.setMaximum(true);

        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jLabelMaximizarMouseClicked

    private void jLabelCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCerrarMouseClicked
        try {
            this.setClosed(true);

        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jLabelCerrarMouseClicked

    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed
        try {
            // Verifica que la tabla esté inicializada
            if (jTableProductos == null) {
                JOptionPane.showMessageDialog(this, "La tabla no está inicializada.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obtiene el modelo de la tabla
            if (model == null) {
                model = (DefaultTableModel) jTableProductos.getModel();
            }

            // Verifica si la tabla tiene filas
            if (model.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "No hay productos en la tabla.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Guarda el orden original solo la primera vez
            if (originalOrder.isEmpty()) {
                guardarOrdenOriginal(model);
            }

            // Obtiene el texto ingresado por el usuario
            String nombreProducto = jTextFieldFiltrar.getText().trim();

            if (nombreProducto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese un nombre de producto para buscar.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Llama al método que realiza la búsqueda del producto
            buscarProducto(nombreProducto);
            // Limpia la caja de texto despues de buscar
            jTextFieldFiltrar.setText("");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al buscar el producto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }


    }//GEN-LAST:event_jButtonBuscarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField FechaV;
    private javax.swing.JButton jButtonActualizar;
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonLimpiar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelCerrar;
    private javax.swing.JLabel jLabelMaximizar;
    private javax.swing.JLabel jLabelMinimizar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableProductos;
    private javax.swing.JTextField jTextFieldCategoria;
    private javax.swing.JTextField jTextFieldCodigo;
    private javax.swing.JTextField jTextFieldDescripcion;
    private javax.swing.JTextField jTextFieldFiltrar;
    private javax.swing.JTextField jTextFieldID;
    private javax.swing.JTextField jTextFieldLote;
    private javax.swing.JTextField jTextFieldNombreP;
    private javax.swing.JTextField jTextFieldPorsentajeG;
    private javax.swing.JTextField jTextFieldPrecioC;
    private javax.swing.JTextField jTextFieldPrecioV;
    private javax.swing.JTextField jTextFieldPresentacion;
    private javax.swing.JTextField jTextFieldStockI;
    private javax.swing.JTextField jTextFieldStockM;
    private javax.swing.JTextField jTextFieldUnidadM;
    // End of variables declaration//GEN-END:variables
}
