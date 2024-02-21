// generated with ast extension for cup
// version 0.8
// 15/0/2024 22:57:55


package rs.ac.bg.etf.pp1.ast;

public class DclConstClass extends Decl {

    private ConstD ConstD;

    public DclConstClass (ConstD ConstD) {
        this.ConstD=ConstD;
        if(ConstD!=null) ConstD.setParent(this);
    }

    public ConstD getConstD() {
        return ConstD;
    }

    public void setConstD(ConstD ConstD) {
        this.ConstD=ConstD;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstD!=null) ConstD.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstD!=null) ConstD.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstD!=null) ConstD.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DclConstClass(\n");

        if(ConstD!=null)
            buffer.append(ConstD.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DclConstClass]");
        return buffer.toString();
    }
}
