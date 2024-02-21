// generated with ast extension for cup
// version 0.8
// 15/0/2024 22:57:55


package rs.ac.bg.etf.pp1.ast;

public class DesigRepYes extends DesigRep {

    private DesigRep DesigRep;
    private String I2;

    public DesigRepYes (DesigRep DesigRep, String I2) {
        this.DesigRep=DesigRep;
        if(DesigRep!=null) DesigRep.setParent(this);
        this.I2=I2;
    }

    public DesigRep getDesigRep() {
        return DesigRep;
    }

    public void setDesigRep(DesigRep DesigRep) {
        this.DesigRep=DesigRep;
    }

    public String getI2() {
        return I2;
    }

    public void setI2(String I2) {
        this.I2=I2;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesigRep!=null) DesigRep.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesigRep!=null) DesigRep.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesigRep!=null) DesigRep.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesigRepYes(\n");

        if(DesigRep!=null)
            buffer.append(DesigRep.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+I2);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesigRepYes]");
        return buffer.toString();
    }
}
