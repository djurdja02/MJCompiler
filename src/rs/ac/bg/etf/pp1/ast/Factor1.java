// generated with ast extension for cup
// version 0.8
// 15/0/2024 22:57:55


package rs.ac.bg.etf.pp1.ast;

public class Factor1 extends Factor {

    private Designator Designator;
    private FactOpt1 FactOpt1;

    public Factor1 (Designator Designator, FactOpt1 FactOpt1) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.FactOpt1=FactOpt1;
        if(FactOpt1!=null) FactOpt1.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public FactOpt1 getFactOpt1() {
        return FactOpt1;
    }

    public void setFactOpt1(FactOpt1 FactOpt1) {
        this.FactOpt1=FactOpt1;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(FactOpt1!=null) FactOpt1.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(FactOpt1!=null) FactOpt1.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(FactOpt1!=null) FactOpt1.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Factor1(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FactOpt1!=null)
            buffer.append(FactOpt1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Factor1]");
        return buffer.toString();
    }
}
