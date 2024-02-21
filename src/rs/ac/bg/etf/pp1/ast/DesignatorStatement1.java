// generated with ast extension for cup
// version 0.8
// 15/0/2024 22:57:55


package rs.ac.bg.etf.pp1.ast;

public class DesignatorStatement1 extends DesignatorStatement {

    private Designator Designator;
    private DSChoose DSChoose;

    public DesignatorStatement1 (Designator Designator, DSChoose DSChoose) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.DSChoose=DSChoose;
        if(DSChoose!=null) DSChoose.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public DSChoose getDSChoose() {
        return DSChoose;
    }

    public void setDSChoose(DSChoose DSChoose) {
        this.DSChoose=DSChoose;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(DSChoose!=null) DSChoose.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(DSChoose!=null) DSChoose.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(DSChoose!=null) DSChoose.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorStatement1(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DSChoose!=null)
            buffer.append(DSChoose.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorStatement1]");
        return buffer.toString();
    }
}
