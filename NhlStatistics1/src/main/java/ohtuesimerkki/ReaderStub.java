package ohtuesimerkki;

import java.util.List;

public class ReaderStub implements Reader {
    
    private List<Player> players;
    
    public void ReaderSub(List<Player> list) {
        this.players = list;
    }

    @Override
    public List<Player> getPlayers() {
        return this.players;
    }
    
}
