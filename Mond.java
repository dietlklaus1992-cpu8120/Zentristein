import java.math.BigDecimal;

public enum Mond implements Himmelskoerper{
    ERDMOND("","73420000000000000000000","1022","384400000","215.15","4.62","3340","21958000000000000","0","","","","","","","","",""),
    ERDE("","5972000000000000000000000","29780","149600000000","288.15","465.1","5514","1083200000000000","0","","","","","","","","",""),
    APOPHIS("","27000000000","30730","137000000000","270","0","2700","20000000","0","","","","","","","","",""),
    BENNU("","73290000000","12800","168500000000","250","0","1190","62000000","0","","","","","","","","",""),
    CHICXULUB("","1000000000000000","20000","149600000000","288","0","2500","1000000000000","0","","","","","","","","","");
    public final BigDecimal Zefo2;
    public final BigDecimal GeKra2;
    public final BigDecimal BaGe2;
    public final BigDecimal KreBa2;
    public BigDecimal Temp2;
    public final BigDecimal Speed2;
    public final BigDecimal Dichte2;
    public final BigDecimal Volumen2;
    public final BigDecimal Strahlungsdruck2;
    public BigDecimal MidTemp2;
    public BigDecimal MidKreBa2;
    public BigDecimal MidBaGe2;
    public BigDecimal MaxTemp2;
    public BigDecimal MaxKreBa2;
    public BigDecimal MaxBaGe2;
    public BigDecimal MinTemp2;
    public BigDecimal MinKreBa2;
    public BigDecimal MinBaGe2;
    Mond(String n, String kg, String ms, String r, String c, String rot, String rho, String qm, String g, String Kelvin, String m, String mps,String MaxT, String MaxK, String MaxGe,String MinT, String MinK, String MinG){
        this.Zefo2 = (n == null || n.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(n);
        this.GeKra2 = (kg == null || kg.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(kg);
        this.BaGe2 = (ms == null || ms.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(ms);
        this.KreBa2 = (r == null || r.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(r);
        this.Temp2 = (c == null || c.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(c);
        this.Speed2 = (rot == null || rot.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(rot);
        this.Dichte2= (rho == null || rho.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(rho);
        this.Volumen2= (rho == null || rho.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(qm);
        this.Strahlungsdruck2 = (g == null || g.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(g);
        this.MidTemp2 = (Kelvin == null || Kelvin.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(Kelvin);
        this.MidKreBa2 = (m == null || m.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(m);
        this.MidBaGe2 = (mps == null || mps.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(mps);
        this.MaxTemp2 = (MaxT == null || MaxT.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(MaxT);
        this.MaxKreBa2 = (MaxK == null || MaxK.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(MaxK);
        this.MaxBaGe2 = (MaxGe == null || MaxGe.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(MaxGe);
        this.MinTemp2 = (MinT == null || MinT.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(MinT);
        this.MinKreBa2 = (MinK == null || MinK.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(MinK);
        this.MinBaGe2 = (MinG == null || MinG.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(MinG);

    }
    @Override public BigDecimal getZefo() { return Zefo2; }
    @Override public BigDecimal getGeKra() { return GeKra2; }
    @Override public BigDecimal getBaGe() { return BaGe2; }
    @Override public BigDecimal getKreBa() { return KreBa2; }
    @Override public BigDecimal getTemp() { return Temp2; }
    @Override public BigDecimal getSpeed() { return Speed2; }
    @Override public BigDecimal getDichte() { return Dichte2; }
    @Override public BigDecimal getVolumen() { return Volumen2; }
    @Override public BigDecimal getStrahlungsdruck() {return Strahlungsdruck2;}
    @Override public BigDecimal getMidTemp() {return MidTemp2;}
    @Override public BigDecimal getMidKreBa() {return MidKreBa2;}
    @Override public BigDecimal getMidBaGe() {return MidBaGe2;}
    @Override public void setMidTemp(BigDecimal MidTemp2){this.MidTemp2 = MidTemp2;}
    @Override public void setMidKreBa(BigDecimal MidKreBa2){this.MidKreBa2 = MidKreBa2;}
    @Override public void setMidBaGe(BigDecimal MidBaGe2){this.MidBaGe2 = MidBaGe2;}
    @Override public void setMaxTemp(BigDecimal MaxTemp2){this.MaxTemp2 = MaxTemp2;}
    @Override public void setMaxKreBa(BigDecimal MaxKreBa2){this.MaxKreBa2 = MaxKreBa2;}
    @Override public void setMaxBaGe(BigDecimal MaxBaGe2){this.MaxBaGe2 = MaxBaGe2;}
    @Override public BigDecimal getMaxTemp() {return MaxTemp2;}
    @Override public BigDecimal getMaxKreBa() {return MaxKreBa2;}
    @Override public BigDecimal getMaxBaGe() {return MaxBaGe2;}
    @Override public void setMinTemp(BigDecimal MinTemp2){this.MinTemp2 = MinTemp2;}
    @Override public void setMinKreBa(BigDecimal MinKreBa2){this.MinKreBa2 = MinKreBa2;}
    @Override public void setMinBaGe(BigDecimal MinBaGe2){this.MinBaGe2 = MinBaGe2;}
    @Override
    public void setTemp(BigDecimal temp2) {this.Temp2 = temp2;}
    @Override public BigDecimal getMinTemp() {return MinTemp2;}
    @Override public BigDecimal getMinKreBa() {return MinKreBa2;}
    @Override public BigDecimal getMinBaGe() {return MinBaGe2;}
}