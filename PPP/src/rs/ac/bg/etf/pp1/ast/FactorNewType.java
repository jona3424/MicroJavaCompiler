// generated with ast extension for cup
// version 0.8
// 7/1/2024 9:9:30


package rs.ac.bg.etf.pp1.ast;

public class FactorNewType extends Factor {

    private Type Type;
    private ArrayIndexDsingerica ArrayIndexDsingerica;

    public FactorNewType (Type Type, ArrayIndexDsingerica ArrayIndexDsingerica) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.ArrayIndexDsingerica=ArrayIndexDsingerica;
        if(ArrayIndexDsingerica!=null) ArrayIndexDsingerica.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public ArrayIndexDsingerica getArrayIndexDsingerica() {
        return ArrayIndexDsingerica;
    }

    public void setArrayIndexDsingerica(ArrayIndexDsingerica ArrayIndexDsingerica) {
        this.ArrayIndexDsingerica=ArrayIndexDsingerica;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(ArrayIndexDsingerica!=null) ArrayIndexDsingerica.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(ArrayIndexDsingerica!=null) ArrayIndexDsingerica.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(ArrayIndexDsingerica!=null) ArrayIndexDsingerica.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FactorNewType(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ArrayIndexDsingerica!=null)
            buffer.append(ArrayIndexDsingerica.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FactorNewType]");
        return buffer.toString();
    }
}
