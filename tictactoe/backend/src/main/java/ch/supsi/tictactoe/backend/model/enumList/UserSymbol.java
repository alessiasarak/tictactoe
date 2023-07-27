package ch.supsi.tictactoe.backend.model.enumList;

public enum UserSymbol {
    X('X'), O('O'),
    A('A'), B('B'),
    D('D'), S('S');
    private final char symbol;
    UserSymbol(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public static UserSymbol getSymbolFromChar(char c){
        for (UserSymbol s : UserSymbol.values()){
            if(s.getSymbol() == c) return s;
        }
        return null;
    }

    public static UserSymbol[] getSymbols(){
        UserSymbol[] userSymbols = new UserSymbol[UserSymbol.values().length];

        int i = 0;
        for (UserSymbol us : UserSymbol.values()) {
            userSymbols[i] = us;
            i++;
        }
        return userSymbols;
    }
}
