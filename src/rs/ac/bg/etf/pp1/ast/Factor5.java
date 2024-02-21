// generated with ast extension for cup
// version 0.8
// 15/0/2024 22:57:55


package rs.ac.bg.etf.pp1.ast;

public class Factor5 extends Factor {

    private Type Type;
    private FactorOr1 FactorOr1;

    public Factor5 (Type Type, FactorOr1 FactorOr1) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.FactorOr1=FactorOr1;
        if(FactorOr1!=null) FactorOr1.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public FactorOr1 getFactorOr1() {
        return FactorOr1;
    }

    public void setFactorOr1(FactorOr1 FactorOr1) {
        this.FactorOr1=FactorOr1;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(FactorOr1!=null) FactorOr1.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(FactorOr1!=null) FactorOr1.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(FactorOr1!=null) FactorOr1.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Factor5(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FactorOr1!=null)
            buffer.append(FactorOr1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Factor5]");
        return buffer.toString();
    }
}
