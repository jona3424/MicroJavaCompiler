// generated with ast extension for cup
// version 0.8
// 7/1/2024 9:9:30


package rs.ac.bg.etf.pp1.ast;

public class ConstDeclLoopList extends ConstDeclLoop {

    private ConstDeclLoop ConstDeclLoop;
    private String constName;
    private Consts Consts;

    public ConstDeclLoopList (ConstDeclLoop ConstDeclLoop, String constName, Consts Consts) {
        this.ConstDeclLoop=ConstDeclLoop;
        if(ConstDeclLoop!=null) ConstDeclLoop.setParent(this);
        this.constName=constName;
        this.Consts=Consts;
        if(Consts!=null) Consts.setParent(this);
    }

    public ConstDeclLoop getConstDeclLoop() {
        return ConstDeclLoop;
    }

    public void setConstDeclLoop(ConstDeclLoop ConstDeclLoop) {
        this.ConstDeclLoop=ConstDeclLoop;
    }

    public String getConstName() {
        return constName;
    }

    public void setConstName(String constName) {
        this.constName=constName;
    }

    public Consts getConsts() {
        return Consts;
    }

    public void setConsts(Consts Consts) {
        this.Consts=Consts;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstDeclLoop!=null) ConstDeclLoop.accept(visitor);
        if(Consts!=null) Consts.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstDeclLoop!=null) ConstDeclLoop.traverseTopDown(visitor);
        if(Consts!=null) Consts.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstDeclLoop!=null) ConstDeclLoop.traverseBottomUp(visitor);
        if(Consts!=null) Consts.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDeclLoopList(\n");

        if(ConstDeclLoop!=null)
            buffer.append(ConstDeclLoop.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+constName);
        buffer.append("\n");

        if(Consts!=null)
            buffer.append(Consts.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDeclLoopList]");
        return buffer.toString();
    }
}
