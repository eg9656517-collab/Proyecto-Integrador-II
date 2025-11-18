package Modelo; // Nombre del paquete

import java.time.LocalDate;
/**
 *
 * @author lapto
 */
public class Producto { // Nombre de la clase/inicio
    int ID;
    String Nombre;      // Nombre del producto tipo cadena
    String Codigo;      // Codigo del Producto tipo cadena
    String UnidadM;     // Unidad de medida tipo cadena
    Double PrecioC;     // Precio compra del producto tipo real
    Double PorsentajeG;  // Porsentaje de ganancia tipo real
    int StockM;         // Alerta de cantidad minima de tipo entero
    String FechaV;         // fecha de vencimiento de tipo entero
    String Categoria;   // Categoria de tipo cadena
    String Presentacion; // Presentacion de tipo cadena
    Double PrecioV;      // Precio de venta tipo real
    int StockI;          // cantidad existente de tipo entero
    String Descripcion;  // Descripcion de tipo cadena
    String Lote;         // Lote de tipo cadena

    public Producto() {
    }

    public Producto(int ID, String Nombre, String Codigo, String UnidadM, Double PrecioC, Double PorsentajeG, int StockM, String FechaV, String Categoria, String Presentacion, Double PrecioV, int StockI, String Descripcion, String Lote) {
        this.ID = ID;
        this.Nombre = Nombre;
        this.Codigo = Codigo;
        this.UnidadM = UnidadM;
        this.PrecioC = PrecioC;
        this.PorsentajeG = PorsentajeG;
        this.StockM = StockM;
        this.FechaV = FechaV;
        this.Categoria = Categoria;
        this.Presentacion = Presentacion;
        this.PrecioV = PrecioV;
        this.StockI = StockI;
        this.Descripcion = Descripcion;
        this.Lote = Lote;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getUnidadM() {
        return UnidadM;
    }

    public void setUnidadM(String UnidadM) {
        this.UnidadM = UnidadM;
    }

    public Double getPrecioC() {
        return PrecioC;
    }

    public void setPrecioC(Double PrecioC) {
        this.PrecioC = PrecioC;
    }

    public Double getPorsentajeG() {
        return PorsentajeG;
    }

    public void setPorsentajeG(Double PorsentajeG) {
        this.PorsentajeG = PorsentajeG;
    }

    public int getStockM() {
        return StockM;
    }

    public void SetStockM(int StockM) {
        this.StockM = StockM;
    }

    public String getFechaV() {
        return FechaV;
    }

    public void setFechaV(String FechaV) {
        this.FechaV = FechaV;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }

    public String getPresentacion() {
        return Presentacion;
    }

    public void setPresentacion(String Presentacion) {
        this.Presentacion = Presentacion;
    }

    public Double getPrecioV() {
        return PrecioV;
    }

    public void setPrecioV(Double PrecioV) {
        this.PrecioV = PrecioV;
    }

    public int getStockI() {
        return StockI;
    }

    public void setStockI(int StockI) {
        this.StockI = StockI;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getLote() {
        return Lote;
    }

    public void setLote(String Lote) {
        this.Lote = Lote;
    }
    
}
