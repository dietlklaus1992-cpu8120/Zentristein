import java.math.BigDecimal;

public enum Puffer implements Himmelskoerper{
    Datasheet1("","","","","","","","","","","","","","","","","",""),
    Datasheet2("","","","","","","","","","","","","","","","","","");

    public final BigDecimal Zefo4;
    public final BigDecimal GeKra4;
    public final BigDecimal BaGe4;
    public final BigDecimal KreBa4;
    public BigDecimal Temp4;
    public final BigDecimal Speed4;
    public final BigDecimal Dichte4;
    public final BigDecimal Volumen4;
    public final BigDecimal Strahlungsdruck4;
    public BigDecimal MidTemp4;
    public BigDecimal MidKreBa4;
    public BigDecimal MidBaGe4;
    public BigDecimal MaxTemp4;
    public BigDecimal MaxKreBa4;
    public BigDecimal MaxBaGe4;
    public BigDecimal MinTemp4;
    public BigDecimal MinKreBa4;
    public BigDecimal MinBaGe4;

    Puffer(String n, String kg, String ms, String r, String c, String rot, String rho,String qm, String g, String Kelvin, String m, String mps, String MaxT, String MaxK, String MaxGe,String MinT, String MinK, String MinG){
        this.Zefo4 = (n == null || n.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(n);
        this.GeKra4 = (kg == null || kg.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(kg);
        this.BaGe4= (ms == null || ms.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(ms);
        this.KreBa4 = (r == null || r.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(r);
        this.Temp4 = (c == null || c.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(c);
        this.Speed4 = (rot == null || rot.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(rot);
        this.Dichte4= (rho == null || rho.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(rho);
        this.Volumen4= (qm == null || qm.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(qm);
        this.Strahlungsdruck4 =  (g == null || g.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(g);
        this.MidTemp4 = (Kelvin == null || Kelvin.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(Kelvin);
        this.MidKreBa4 = (m == null || m.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(m);
        this.MidBaGe4 = (mps == null || mps.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(mps);
        this.MaxTemp4 = (MaxT == null || MaxT.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(MaxT);
        this.MaxKreBa4 = (MaxK == null || MaxK.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(MaxK);
        this.MaxBaGe4 = (MaxGe == null || MaxGe.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(MaxGe);
        this.MinTemp4 = (MinT == null || MinT.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(MinT);
        this.MinKreBa4 = (MinK == null || MinK.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(MinK);
        this.MinBaGe4 = (MinG == null || MinG.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(MinG);
    }


    @Override public BigDecimal getZefo() { return Zefo4; }
    @Override public BigDecimal getGeKra() { return GeKra4; }
    @Override public BigDecimal getBaGe() { return BaGe4; }
    @Override public BigDecimal getKreBa() { return KreBa4; }
    @Override public BigDecimal getTemp() { return Temp4; }
    @Override public BigDecimal getSpeed() { return Speed4; }
    @Override public BigDecimal getDichte() { return Dichte4; }
    @Override public BigDecimal getVolumen() { return Volumen4; }
    @Override public BigDecimal getStrahlungsdruck() {return Strahlungsdruck4;}
    @Override public BigDecimal getMidTemp() {return MidTemp4;}
    @Override public BigDecimal getMidKreBa() {return MidKreBa4;}
    @Override public BigDecimal getMidBaGe() {return MidBaGe4;}
    @Override public void setMidTemp(BigDecimal midtemp4){this.MidTemp4 = midtemp4;}
    @Override public void setMidKreBa(BigDecimal midkreba4){this.MidKreBa4 = midkreba4;}
    @Override public void setMidBaGe(BigDecimal midbage4){this.MidBaGe4 = midbage4;}
    @Override public void setMaxTemp(BigDecimal maxtemp4){this.MaxTemp4 = maxtemp4;}
    @Override public void setMaxKreBa(BigDecimal maxkreba4){this.MaxKreBa4 = maxkreba4;}
    @Override public void setMaxBaGe(BigDecimal maxbage4){this.MaxBaGe4 = maxbage4;}
    @Override public BigDecimal getMaxTemp() {return MaxTemp4;}
    @Override public BigDecimal getMaxKreBa() {return MaxKreBa4;}
    @Override public BigDecimal getMaxBaGe() {return MaxBaGe4;}
    @Override public void setMinTemp(BigDecimal mintemp4){this.MinTemp4 = mintemp4;}
    @Override public void setMinKreBa(BigDecimal minkreba4){this.MinKreBa4 = minkreba4;}
    @Override public void setMinBaGe(BigDecimal minbage4){this.MinBaGe4 = minbage4;}
    @Override public void setTemp(BigDecimal temp4) {this.Temp4 = temp4;}
    @Override public BigDecimal getMinTemp() {return MinTemp4;}
    @Override public BigDecimal getMinKreBa() {return MinKreBa4;}
    @Override public BigDecimal getMinBaGe() {return MinBaGe4;}
}
