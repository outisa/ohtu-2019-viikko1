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
    Varasto negaVarasto;
    Varasto varastoSaldolla;
    Varasto varastoNegSaldolla;
    Varasto negaTilavuusPosSaldo;
    Varasto negaTilavuusNegaSaldo;
    Varasto ylimaaraSaldoVarasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        negaVarasto = new Varasto(-122);
        varastoSaldolla = new Varasto(10, 8);
        varastoNegSaldolla = new Varasto(10, -12);
        negaTilavuusPosSaldo = new Varasto(-1, 1);
        negaTilavuusNegaSaldo = new Varasto(-1, -1);
        ylimaaraSaldoVarasto = new Varasto(12,13);
        
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }
    @Test
    public void negatiivinenTilavuusHylataan() {
        assertEquals(0, negaVarasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void uudellaVarastollaOikeaTilavuusJaSaldo() {
        assertEquals(10, varastoSaldolla.getTilavuus(), vertailuTarkkuus);
        assertEquals(8, varastoSaldolla.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void ylijaamaKasitellaanOikein() {
        assertEquals(12, ylimaaraSaldoVarasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(12, ylimaaraSaldoVarasto.getSaldo(), vertailuTarkkuus);
    }
    @Test
    public void negatiivisellaSaldollaArvoNolla() {
        assertEquals(10, varastoNegSaldolla.getTilavuus(), vertailuTarkkuus);
        assertEquals(0,  varastoNegSaldolla.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenTilavuusJaSaldoNolla() {
        assertEquals(0, negaTilavuusPosSaldo.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, negaTilavuusPosSaldo.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenSaldoNolla() {
        assertEquals(10, varastoNegSaldolla.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, varastoNegSaldolla.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenLisausEiOnnistu() {
        varasto.lisaaVarastoon(-2);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void saldoEiKasvaYliTilavuuden() {
        varasto.lisaaVarastoon(12);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenOttaminenEiOnnistu() {
        varasto.lisaaVarastoon(2);
        double saatuMaara = varasto.otaVarastosta(-2);
        assertEquals(0, saatuMaara, vertailuTarkkuus);
    }
    
    @Test
    public void ylimaarainenOttaminenEiOnnistu() {
        varasto.lisaaVarastoon(2);
        double saatuMaara = varasto.otaVarastosta(13);
        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }
   
    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    @Test
    public void tulosteOikein() {
        assertEquals("saldo = 8.0, vielä tilaa 2.0", varastoSaldolla.toString());
    }
}