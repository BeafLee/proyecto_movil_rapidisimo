package pe.usat.moviles.rapidisimoapp.model;

public class Solicitud {

    private int id;
    private String descripcionCarga;
    private String claseCarga;
    private String tipoCarga;
    private String categoriaCarga;
    private double pesoKg;
    private String fechaHoraPartida;
    private String fechaHoraLlegada;
    private String direccionOrigen;
    private String direccionDestino;
    private double montoPagar;
    private double distanciaKm;
    private int TARIFAid;
    private int CLIENTEid;
    private int PAGO_SOLICITUDid;
    private int CONDUCTORid;
    private int VEHICULOid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcionCarga() {
        return descripcionCarga;
    }

    public void setDescripcionCarga(String descripcionCarga) {
        this.descripcionCarga = descripcionCarga;
    }

    public String getClaseCarga() {
        return claseCarga;
    }

    public void setClaseCarga(String claseCarga) {
        this.claseCarga = claseCarga;
    }

    public String getTipoCarga() {
        return tipoCarga;
    }

    public void setTipoCarga(String tipoCarga) {
        this.tipoCarga = tipoCarga;
    }

    public String getCategoriaCarga() {
        return categoriaCarga;
    }

    public void setCategoriaCarga(String categoriaCarga) {
        this.categoriaCarga = categoriaCarga;
    }

    public double getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(double pesoKg) {
        this.pesoKg = pesoKg;
    }

    public String getFechaHoraPartida() {
        return fechaHoraPartida;
    }

    public void setFechaHoraPartida(String fechaHoraPartida) {
        this.fechaHoraPartida = fechaHoraPartida;
    }

    public String getFechaHoraLlegada() {
        return fechaHoraLlegada;
    }

    public void setFechaHoraLlegada(String fechaHoraLlegada) {
        this.fechaHoraLlegada = fechaHoraLlegada;
    }

    public String getDireccionOrigen() {
        return direccionOrigen;
    }

    public void setDireccionOrigen(String direccionOrigen) {
        this.direccionOrigen = direccionOrigen;
    }

    public String getDireccionDestino() {
        return direccionDestino;
    }

    public void setDireccionDestino(String direccionDestino) {
        this.direccionDestino = direccionDestino;
    }

    public double getMontoPagar() {
        return montoPagar;
    }

    public void setMontoPagar(double montoPagar) {
        this.montoPagar = montoPagar;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public void setDistanciaKm(double distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    public int getTARIFAid() {
        return TARIFAid;
    }

    public void setTARIFAid(int TARIFAid) {
        this.TARIFAid = TARIFAid;
    }

    public int getCLIENTEid() {
        return CLIENTEid;
    }

    public void setCLIENTEid(int CLIENTEid) {
        this.CLIENTEid = CLIENTEid;
    }

    public int getPAGO_SOLICITUDid() {
        return PAGO_SOLICITUDid;
    }

    public void setPAGO_SOLICITUDid(int PAGO_SOLICITUDid) {
        this.PAGO_SOLICITUDid = PAGO_SOLICITUDid;
    }

    public int getCONDUCTORid() {
        return CONDUCTORid;
    }

    public void setCONDUCTORid(int CONDUCTORid) {
        this.CONDUCTORid = CONDUCTORid;
    }

    public int getVEHICULOid() {
        return VEHICULOid;
    }

    public void setVEHICULOid(int VEHICULOid) {
        this.VEHICULOid = VEHICULOid;
    }
}
