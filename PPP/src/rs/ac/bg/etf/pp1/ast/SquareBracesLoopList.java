// generated with ast extension for cup
// version 0.8
// 7/1/2024 9:9:30


package rs.ac.bg.etf.pp1.ast;

public class SquareBracesLoopList extends SquareBracesLoop {

    private SquareBracesLoop SquareBracesLoop;
    private String varName;
    private SquareBracesInnerLoop SquareBracesInnerLoop;

    public SquareBracesLoopList (SquareBracesLoop SquareBracesLoop, String varName, SquareBracesInnerLoop SquareBracesInnerLoop) {
        this.SquareBracesLoop=SquareBracesLoop;
        if(SquareBracesLoop!=null) SquareBracesLoop.setParent(this);
        this.varName=varName;
        this.SquareBracesInnerLoop=SquareBracesInnerLoop;
        if(SquareBracesInnerLoop!=null) SquareBracesInnerLoop.setParent(this);
    }

    public SquareBracesLoop getSquareBracesLoop() {
        return SquareBracesLoop;
    }

    public void setSquareBracesLoop(SquareBracesLoop SquareBracesLoop) {
        this.SquareBracesLoop=SquareBracesLoop;
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName=varName;
    }

    public SquareBracesInnerLoop getSquareBracesInnerLoop() {
        return SquareBracesInnerLoop;
    }

    public void setSquareBracesInnerLoop(SquareBracesInnerLoop SquareBracesInnerLoop) {
        this.SquareBracesInnerLoop=SquareBracesInnerLoop;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(SquareBracesLoop!=null) SquareBracesLoop.accept(visitor);
        if(SquareBracesInnerLoop!=null) SquareBracesInnerLoop.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(SquareBracesLoop!=null) SquareBracesLoop.traverseTopDown(visitor);
        if(SquareBracesInnerLoop!=null) SquareBracesInnerLoop.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(SquareBracesLoop!=null) SquareBracesLoop.traverseBottomUp(visitor);
        if(SquareBracesInnerLoop!=null) SquareBracesInnerLoop.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SquareBracesLoopList(\n");

        if(SquareBracesLoop!=null)
            buffer.append(SquareBracesLoop.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+varName);
        buffer.append("\n");

        if(SquareBracesInnerLoop!=null)
            buffer.append(SquareBracesInnerLoop.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SquareBracesLoopList]");
        return buffer.toString();
    }
}
