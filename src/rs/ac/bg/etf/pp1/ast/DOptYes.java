// generated with ast extension for cup
// version 0.8
// 15/0/2024 22:57:55


package rs.ac.bg.etf.pp1.ast;

public class DOptYes extends DOpt {

    private DOpt DOpt;
    private DDO DDO;

    public DOptYes (DOpt DOpt, DDO DDO) {
        this.DOpt=DOpt;
        if(DOpt!=null) DOpt.setParent(this);
        this.DDO=DDO;
        if(DDO!=null) DDO.setParent(this);
    }

    public DOpt getDOpt() {
        return DOpt;
    }

    public void setDOpt(DOpt DOpt) {
        this.DOpt=DOpt;
    }

    public DDO getDDO() {
        return DDO;
    }

    public void setDDO(DDO DDO) {
        this.DDO=DDO;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DOpt!=null) DOpt.accept(visitor);
        if(DDO!=null) DDO.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DOpt!=null) DOpt.traverseTopDown(visitor);
        if(DDO!=null) DDO.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DOpt!=null) DOpt.traverseBottomUp(visitor);
        if(DDO!=null) DDO.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DOptYes(\n");

        if(DOpt!=null)
            buffer.append(DOpt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DDO!=null)
            buffer.append(DDO.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DOptYes]");
        return buffer.toString();
    }
}
