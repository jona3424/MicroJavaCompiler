// generated with ast extension for cup
// version 0.8
// 7/1/2024 9:9:30


package rs.ac.bg.etf.pp1.ast;

public class VarDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private VarDeclType VarDeclType;
    private String varName;
    private SquareBraces SquareBraces;
    private SquareBracesLoop SquareBracesLoop;

    public VarDecl (VarDeclType VarDeclType, String varName, SquareBraces SquareBraces, SquareBracesLoop SquareBracesLoop) {
        this.VarDeclType=VarDeclType;
        if(VarDeclType!=null) VarDeclType.setParent(this);
        this.varName=varName;
        this.SquareBraces=SquareBraces;
        if(SquareBraces!=null) SquareBraces.setParent(this);
        this.SquareBracesLoop=SquareBracesLoop;
        if(SquareBracesLoop!=null) SquareBracesLoop.setParent(this);
    }

    public VarDeclType getVarDeclType() {
        return VarDeclType;
    }

    public void setVarDeclType(VarDeclType VarDeclType) {
        this.VarDeclType=VarDeclType;
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName=varName;
    }

    public SquareBraces getSquareBraces() {
        return SquareBraces;
    }

    public void setSquareBraces(SquareBraces SquareBraces) {
        this.SquareBraces=SquareBraces;
    }

    public SquareBracesLoop getSquareBracesLoop() {
        return SquareBracesLoop;
    }

    public void setSquareBracesLoop(SquareBracesLoop SquareBracesLoop) {
        this.SquareBracesLoop=SquareBracesLoop;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarDeclType!=null) VarDeclType.accept(visitor);
        if(SquareBraces!=null) SquareBraces.accept(visitor);
        if(SquareBracesLoop!=null) SquareBracesLoop.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclType!=null) VarDeclType.traverseTopDown(visitor);
        if(SquareBraces!=null) SquareBraces.traverseTopDown(visitor);
        if(SquareBracesLoop!=null) SquareBracesLoop.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclType!=null) VarDeclType.traverseBottomUp(visitor);
        if(SquareBraces!=null) SquareBraces.traverseBottomUp(visitor);
        if(SquareBracesLoop!=null) SquareBracesLoop.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDecl(\n");

        if(VarDeclType!=null)
            buffer.append(VarDeclType.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+varName);
        buffer.append("\n");

        if(SquareBraces!=null)
            buffer.append(SquareBraces.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(SquareBracesLoop!=null)
            buffer.append(SquareBracesLoop.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDecl]");
        return buffer.toString();
    }
}
