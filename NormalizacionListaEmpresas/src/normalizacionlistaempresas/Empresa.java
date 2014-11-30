package normalizacionlistaempresas;

public class Empresa {

    String nombreEmpresa = "";
    long ingresosOperacionales, variacionIngresos, utilidadOperacional, utilidadNeta, activoTotal;
    long pasivoTotal, patrimonioTotal;

    public Empresa() {
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public long getIngresosOperacionales() {
        return ingresosOperacionales;
    }

    public void setIngresosOperacionales(long ingresosOperacionales) {
        this.ingresosOperacionales = ingresosOperacionales;
    }

    public long getVariacionIngresos() {
        return variacionIngresos;
    }

    public void setVariacionIngresos(long variacionIngresos) {
        this.variacionIngresos = variacionIngresos;
    }

    public long getUtilidadOperacional() {
        return utilidadOperacional;
    }

    public void setUtilidadOperacional(long utilidadOperacional) {
        this.utilidadOperacional = utilidadOperacional;
    }

    public long getUtilidadNeta() {
        return utilidadNeta;
    }

    public void setUtilidadNeta(long utilidadNeta) {
        this.utilidadNeta = utilidadNeta;
    }

    public long getActivoTotal() {
        return activoTotal;
    }

    public void setActivoTotal(long activoTotal) {
        this.activoTotal = activoTotal;
    }

    public long getPasivoTotal() {
        return pasivoTotal;
    }

    public void setPasivoTotal(long pasivoTotal) {
        this.pasivoTotal = pasivoTotal;
    }

    public long getPatrimonioTotal() {
        return patrimonioTotal;
    }

    public void setPatrimonioTotal(long patrimonioTotal) {
        this.patrimonioTotal = patrimonioTotal;
    }
}
