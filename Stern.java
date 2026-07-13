import java.math.BigDecimal;

public enum Stern implements Himmelskoerper{
    Sonne("","1988470000000000000000000000000","225000","9460730472580800","15699726.85","1997","1.408","1412200000000000000000000000","0.0000736", "","","","","","","","","");

    public BigDecimal Zefo3;
    public BigDecimal GeKra3;
    public BigDecimal BaGe3;
    public BigDecimal KreBa3;
    public BigDecimal Temp3;
    public BigDecimal Speed3;
    public BigDecimal Dichte3;
    public BigDecimal Volumen3;
    public BigDecimal Strahlungsdruck3;
    public BigDecimal MidTemp3;
    public BigDecimal MidKreBa3;
    public BigDecimal MidBaGe3;
    public BigDecimal MaxTemp3;
    public BigDecimal MaxKreBa3;
    public BigDecimal MaxBaGe3;
    public BigDecimal MinTemp3;
    public BigDecimal MinKreBa3;
    public BigDecimal MinBaGe3;

    Stern(String n, String kg, String ms, String r, String c, String rot, String rho,String qm, String g, String Kelvin, String m, String mps, String MaxT, String MaxK, String MaxGe,String MinT, String MinK, String MinG){
        this.Zefo3 = (n == null || n.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(n);
        this.GeKra3 = (kg == null || kg.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(kg);
        this.BaGe3= (ms == null || ms.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(ms);
        this.KreBa3 = (r == null || r.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(r);
        this.Temp3 = (c == null || c.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(c);
        this.Speed3 = (rot == null || rot.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(rot);
        this.Dichte3= (rho == null || rho.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(rho);
        this.Volumen3= (rho == null || rho.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(qm);
        this.Strahlungsdruck3 =  (g == null || g.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(g);
        this.MidTemp3 = (Kelvin == null || Kelvin.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(Kelvin);
        this.MidKreBa3 = (m == null || m.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(m);
        this.MidBaGe3 = (mps == null || mps.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(mps);
        this.MaxTemp3 = (MaxT == null || MaxT.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(MaxT);
        this.MaxKreBa3 = (MaxK == null || MaxK.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(MaxK);
        this.MaxBaGe3 = (MaxGe == null || MaxGe.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(MaxGe);
        this.MinTemp3 = (MinT == null || MinT.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(MinT);
        this.MinKreBa3 = (MinK == null || MinK.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(MinK);
        this.MinBaGe3 = (MinG == null || MinG.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(MinG);
    }

    public void Sonne(String n, String kg, String ms, String r, String c, String rot) {
        this.Zefo3 = (n == null || n.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(n);
        this.GeKra3 = (kg == null || kg.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(kg);
        this.BaGe3= (ms == null || ms.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(ms);
        this.KreBa3 = (r == null || r.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(r);
        this.Temp3 = (c == null || c.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(c);
        this.Speed3 = (rot == null || rot.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(rot);
    }

    @Override public BigDecimal getZefo() { return Zefo3; }
    @Override public BigDecimal getGeKra() { return GeKra3; }
    @Override public BigDecimal getBaGe() { return BaGe3; }
    @Override public BigDecimal getKreBa() { return KreBa3; }
    @Override public BigDecimal getTemp() { return Temp3; }
    @Override public BigDecimal getSpeed() { return Speed3; }
    @Override public BigDecimal getDichte() { return Dichte3; }
    @Override public BigDecimal getVolumen() { return Volumen3; }
    @Override public BigDecimal getStrahlungsdruck() {return Strahlungsdruck3;}
    @Override public BigDecimal getMidTemp() {return MidTemp3;}
    @Override public BigDecimal getMidKreBa() {return MidKreBa3;}
    @Override public BigDecimal getMidBaGe() {return MidBaGe3;}
    @Override public void setMidTemp(BigDecimal MidTemp3){this.MidTemp3 = MidTemp3;}
    @Override public void setMidKreBa(BigDecimal MidKreBa3){this.MidKreBa3 = MidKreBa3;}
    @Override public void setMidBaGe(BigDecimal MidBaGe3){this.MidBaGe3 = MidBaGe3;}
    @Override public void setMaxTemp(BigDecimal MaxTemp3){this.MaxTemp3 = MaxTemp3;}
    @Override public void setMaxKreBa(BigDecimal MaxKreBa3){this.MaxKreBa3 = MaxKreBa3;}
    @Override public void setMaxBaGe(BigDecimal MaxBaGe3){this.MaxBaGe3 = MaxBaGe3;}
    @Override public BigDecimal getMaxTemp() {return MaxTemp3;}
    @Override public BigDecimal getMaxKreBa() {return MaxKreBa3;}
    @Override public BigDecimal getMaxBaGe() {return MaxBaGe3;}
    @Override public void setMinTemp(BigDecimal MinTemp3){this.MinTemp3 = MinTemp3;}
    @Override public void setMinKreBa(BigDecimal MinKreBa3){this.MinKreBa3 = MinKreBa3;}
    @Override public void setMinBaGe(BigDecimal MinBaGe3){this.MinBaGe3 = MinBaGe3;}

    @Override
    public void setTemp(BigDecimal temp3) {
        this.Temp3 = temp3;
    }

    @Override public BigDecimal getMinTemp() {return MinTemp3;}
    @Override public BigDecimal getMinKreBa() {return MinKreBa3;}
    @Override public BigDecimal getMinBaGe() {return MinBaGe3;}
}