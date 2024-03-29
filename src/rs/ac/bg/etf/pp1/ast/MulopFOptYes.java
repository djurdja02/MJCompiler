// generated with ast extension for cup
// version 0.8
// 15/0/2024 22:57:55


package rs.ac.bg.etf.pp1.ast;

public class MulopFOptYes extends MulopFOpt {

    private MulopFOpt MulopFOpt;
    private Mulop Mulop;
    private Factor Factor;

    public MulopFOptYes (MulopFOpt MulopFOpt, Mulop Mulop, Factor Factor) {
        this.MulopFOpt=MulopFOpt;
        if(MulopFOpt!=null) MulopFOpt.setParent(this);
        this.Mulop=Mulop;
        if(Mulop!=null) Mulop.setParent(this);
        this.Factor=Factor;
        if(Factor!=null) Factor.setParent(this);
    }

    public MulopFOpt getMulopFOpt() {
        return MulopFOpt;
    }

    public void setMulopFOpt(MulopFOpt MulopFOpt) {
        this.MulopFOpt=MulopFOpt;
    }

    public Mulop getMulop() {
        return Mulop;
    }

    public void setMulop(Mulop Mulop) {
        this.Mulop=Mulop;
    }

    public Factor getFactor() {
        return Factor;
    }

    public void setFactor(Factor Factor) {
        this.Factor=Factor;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MulopFOpt!=null) MulopFOpt.accept(visitor);
        if(Mulop!=null) Mulop.accept(visitor);
        if(Factor!=null) Factor.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MulopFOpt!=null) MulopFOpt.traverseTopDown(visitor);
        if(Mulop!=null) Mulop.traverseTopDown(visitor);
        if(Factor!=null) Factor.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MulopFOpt!=null) MulopFOpt.traverseBottomUp(visitor);
        if(Mulop!=null) Mulop.traverseBottomUp(visitor);
        if(Factor!=null) Factor.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MulopFOptYes(\n");

        if(MulopFOpt!=null)
            buffer.append(MulopFOpt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Mulop!=null)
            buffer.append(Mulop.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Factor!=null)
            buffer.append(Factor.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MulopFOptYes]");
        return buffer.toString();
    }
}
