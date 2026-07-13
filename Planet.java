import java.math.BigDecimal;

public enum Planet implements Himmelskoerper {
    MERKUR("","330100000000000000000000","47360","57910000000","440.15","3.03","5427","60830000000000","0","","","","","","","","",""),// dichte in Kg/m³
    VENUS("","4867000000000000000000000","35020","108200000000","737.15","1.81","5243","928400000000000","0","","","","","","","","",""),
    ERDE("","5972000000000000000000000","29780","149600000000","288.15","465.1","5514","1083200000000000","0","","","","","","","","",""),
    MARS("","641700000000000000000000","24070","227900000000","208.15","241.1","3933","163180000000000","0","","","","","","","","",""),
    JUPITER("","1898000000000000000000000000","13070","778500000000","163.15","12600","1326","1431280000000000000","0","","","","","","","","",""),
    SATURN("","568300000000000000000000000","9680","1433000000000","133.15","9870","687","827130000000000000","0","","","","","","","","",""),
    URANUS("","86810000000000000000000000","6800","2871000000000","78","2590","1271","68330000000000000","0","","","","","","","","",""),
    NEPTUN("","102400000000000000000000000","5430","4495000000000","72.15","2680","1638","62540000000000000","0","","","","","","","","",""),
    PLUTO("","13030000000000000000000","4740","5906000000000","48.15","13.1","1860","7000000000000","0","","","","","","","","","");

    public final BigDecimal Zefo;
    public final BigDecimal GeKra;
    public final BigDecimal BaGe;
    public final BigDecimal KreBa;
    public BigDecimal Temp;
    public final BigDecimal Speed;
    public final BigDecimal Dichte;
    public final BigDecimal Volumen;
    public final BigDecimal Strahlungsdruck;
    public BigDecimal MidTemp;
    public BigDecimal MidKreBa;
    public BigDecimal MidBaGe;
    public BigDecimal MaxTemp;
    public BigDecimal MaxKreBa;
    public BigDecimal MaxBaGe;
    public BigDecimal MinTemp;
    public BigDecimal MinKreBa;
    public BigDecimal MinBaGe;

    Planet(String n, String kg, String ms, String r, String c, String rot, String rho, String qm, String g, String Kelvin, String m,String mps, String MaxT, String MaxK, String MaxGe,String MinT, String MinK, String MinG){
        this.Zefo = (n == null || n.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(n);
        this.GeKra = (kg == null || kg.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(kg);
        this.BaGe= (ms == null || ms.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(ms);
        this.KreBa = (r == null || r.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(r);
        this.Temp = (c == null || c.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(c);
        this.Speed = (rot == null || rot.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(rot);
        this.Dichte= (rho == null || rho.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(rho);
        this.Volumen= (qm == null || qm.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(qm);
        this.Strahlungsdruck =  (g == null || g.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(g);
        this.MidTemp = (Kelvin == null || Kelvin.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(Kelvin);
        this.MidKreBa = (m == null || m.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(m);
        this.MidBaGe = (mps == null || mps.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(mps);
        this.MaxTemp = (MaxT == null || MaxT.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(MaxT);
        this.MaxKreBa = (MaxK == null || MaxK.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(MaxK);
        this.MaxBaGe = (MaxGe == null || MaxGe.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(MaxGe);
        this.MinTemp = (MinT == null || MinT.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(MinT);
        this.MinKreBa = (MinK == null || MinK.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(MinK);
        this.MinBaGe = (MinG == null || MinG.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(MinG);
    }
    @Override public BigDecimal getZefo() { return Zefo; }
    @Override public BigDecimal getGeKra() { return GeKra; }
    @Override public BigDecimal getBaGe() { return BaGe; }
    @Override public BigDecimal getKreBa() { return KreBa; }
    @Override public BigDecimal getTemp() { return Temp; }
    @Override public BigDecimal getSpeed() { return Speed; }
    @Override public BigDecimal getDichte() { return Dichte; }
    @Override public BigDecimal getVolumen() { return Volumen; }
    @Override public BigDecimal getStrahlungsdruck() {return Strahlungsdruck;}
    @Override public BigDecimal getMidTemp() {return MidTemp;}
    @Override public BigDecimal getMidKreBa() {return MidKreBa;}
    @Override public BigDecimal getMidBaGe() {return MidBaGe;}
    @Override public void setMidTemp(BigDecimal MidTemp){this.MidTemp = MidTemp;}
    @Override public void setMidKreBa(BigDecimal MidKreBa){this.MidKreBa = MidKreBa;}
    @Override public void setMidBaGe(BigDecimal MidBaGe){this.MidBaGe = MidBaGe;}
    @Override public void setMaxTemp(BigDecimal MaxTemp){this.MaxTemp = MaxTemp;}
    @Override public void setMaxKreBa(BigDecimal MaxKreBa){this.MaxKreBa = MaxKreBa;}
    @Override public void setMaxBaGe(BigDecimal MaxBaGe){this.MaxBaGe = MaxBaGe;}
    @Override public BigDecimal getMaxTemp() {return MaxTemp;}
    @Override public BigDecimal getMaxKreBa() {return MaxKreBa;}
    @Override public BigDecimal getMaxBaGe() {return MaxBaGe;}
    @Override public void setMinTemp(BigDecimal MinTemp){this.MinTemp = MinTemp;}
    @Override public void setMinKreBa(BigDecimal MinKreBa){this.MinKreBa = MinKreBa;}
    @Override public void setMinBaGe(BigDecimal MinBaGe){this.MinBaGe = MinBaGe;}

    @Override
    public void setTemp(BigDecimal temp) {
        this.Temp = temp;
    }

    @Override public BigDecimal getMinTemp() {return MinTemp;}
    @Override public BigDecimal getMinKreBa() {return MinKreBa;}
    @Override public BigDecimal getMinBaGe() {return MinBaGe;}


}
