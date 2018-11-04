package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto tyhjaVarasto;
    Varasto tyhjaVarastoTyhjallaSaldolla;
    Varasto tyhjaVarastoSaldolla;
    Varasto varastoSaldolla;
    Varasto varastoTyhjallaSaldolla;
    Varasto varastoNegatiivisellaSaldolla;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        this.tyhjaVarasto = new Varasto(0);
        this.tyhjaVarastoTyhjallaSaldolla = new Varasto(0,0);
        this.tyhjaVarastoSaldolla = new Varasto(0,1);
        this.varastoSaldolla = new Varasto(1,1);
        this.varastoTyhjallaSaldolla = new Varasto(1,0);
        this.varastoNegatiivisellaSaldolla = new Varasto(1,-1);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriLuoTyhjanVarastonTyhjallaSaldolla() {
        assertEquals(0, this.tyhjaVarastoTyhjallaSaldolla.getSaldo(), vertailuTarkkuus);
        assertEquals(0, this.tyhjaVarastoTyhjallaSaldolla.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriLuoTyhjanVarastonSaldolla() {
        assertEquals(0, this.tyhjaVarastoSaldolla.getSaldo(), vertailuTarkkuus);
        assertEquals(0, this.tyhjaVarastoSaldolla.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriLuoVarastonTyhjallaSaldolla() {
        assertEquals(0, this.varastoTyhjallaSaldolla.getSaldo(), vertailuTarkkuus);
        assertEquals(1, this.varastoTyhjallaSaldolla.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriLuoVarastonSaldolla() {
        assertEquals(1, this.varastoSaldolla.getSaldo(), vertailuTarkkuus);
        assertEquals(1, this.varastoSaldolla.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriLuoVarastonOikeinNegatiivisellaSaldolla() {
        assertEquals(0, this.varastoNegatiivisellaSaldolla.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriOsaaHoitaaKayttokevottomanArvon() {
        assertEquals(0, tyhjaVarasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä.
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void toStringToimii() {
        varasto.lisaaVarastoon(8);
        assertEquals("saldo = 8.0, vielä tilaa 2.0", varasto.toString());
    }
    
    @Test
    public void lisaaVarastostaEiVoiLisataNegatiivistaArvoa() {
        varasto.lisaaVarastoon(-1);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void eiVoidaLisataLiikaa() {
        varasto.lisaaVarastoon(11);
        assertEquals(varasto.getTilavuus(), varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }
    
    @Test
    public void eiVoiOttaaNegatiivistaArvoa() {
        double saatuMaara = varasto.otaVarastosta(-2);
        assertEquals(0, saatuMaara, vertailuTarkkuus);
    }
    
    @Test
    public void eiVoiOttaaLiikaa() {
    	varasto.lisaaVarastoon(5);
        double saatuMaara = varasto.otaVarastosta(6);
        assertEquals(5, saatuMaara, vertailuTarkkuus);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

}