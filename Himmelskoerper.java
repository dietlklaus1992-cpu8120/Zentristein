import java.math.BigDecimal;

public interface Himmelskoerper {
    BigDecimal getZefo();
    BigDecimal getGeKra();
    BigDecimal getBaGe();
    BigDecimal getKreBa();
    BigDecimal getTemp();
    BigDecimal getSpeed();
    BigDecimal getDichte();
    BigDecimal getVolumen();
    BigDecimal getStrahlungsdruck();
    BigDecimal getMidTemp();
    BigDecimal getMidBaGe();
    BigDecimal getMidKreBa();
    void setMidTemp(BigDecimal MidTemp);
    void setMidKreBa(BigDecimal MidKreBa);
    void setMidBaGe(BigDecimal MidBaGe);
    BigDecimal getMaxTemp();
    BigDecimal getMaxBaGe();
    BigDecimal getMaxKreBa();
    void setMaxTemp(BigDecimal MaxTemp);
    void setMaxKreBa(BigDecimal MaxKreBa);
    void setMaxBaGe(BigDecimal MaxBaGe);
    BigDecimal getMinTemp();
    BigDecimal getMinBaGe();
    BigDecimal getMinKreBa();
    void setMinTemp(BigDecimal MinTemp);
    void setMinKreBa(BigDecimal MinKreBa);
    void setMinBaGe(BigDecimal MinBaGe);
    void setTemp(BigDecimal Temp);



    // Dichte
}
