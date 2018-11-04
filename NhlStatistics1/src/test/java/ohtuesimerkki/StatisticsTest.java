package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatisticsTest {
    
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() { 
            ArrayList<Player> players = new ArrayList<Player>();
            
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        stats = new Statistics(readerStub);
    }  
    
    @Test
    public void konstruktorWorks() {
        assertEquals(this.stats.getPlayers().toString(), this.readerStub.getPlayers().toString());
    }
    
    @Test
    public void searchWorks() {
        assertEquals(new Player("Gretzky", "EDM", 35, 89).toString(), this.stats.search("Gretzky").toString());
    }
    
    @Test
    public void searchWorksIfNull() {
        assertEquals(null, this.stats.search("ABC"));
    }
    
    @Test
    public void teamWorks() {
        assertEquals(this.stats.team("EDM").size(), 3);
    }
    
    @Test
    public void topScorersWorks() {
        assertEquals(this.stats.topScorers(1).get(0).getName(), "Gretzky");
    }
}