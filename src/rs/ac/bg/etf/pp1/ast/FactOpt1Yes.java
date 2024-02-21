// generated with ast extension for cup
// version 0.8
// 15/0/2024 22:57:55


package rs.ac.bg.etf.pp1.ast;

public class FactOpt1Yes extends FactOpt1 {

    private APmaybe APmaybe;

    public FactOpt1Yes (APmaybe APmaybe) {
        this.APmaybe=APmaybe;
        if(APmaybe!=null) APmaybe.setParent(this);
    }

    public APmaybe getAPmaybe() {
        return APmaybe;
    }

    public void setAPmaybe(APmaybe APmaybe) {
        this.APmaybe=APmaybe;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(APmaybe!=null) APmaybe.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(APmaybe!=null) APmaybe.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(APmaybe!=null) APmaybe.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FactOpt1Yes(\n");

        if(APmaybe!=null)
            buffer.append(APmaybe.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FactOpt1Yes]");
        return buffer.toString();
    }
}
