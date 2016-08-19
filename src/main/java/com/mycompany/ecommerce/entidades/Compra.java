package com.mycompany.ecommerce.entidades;

import com.mycompany.ecommerce.interfaces.MongoDBObject;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

public class Compra implements MongoDBObject<Compra>{

    private int id;
    private Usuario usuario;
    private List<ItemCompra> itens;

    public Compra() {
        itens = new ArrayList<>();
    }

    public Compra(int id, Usuario usuario) {
        this.id = id;
        this.usuario = usuario;
        this.itens = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean addItem(ItemCompra item){
        return itens.add(item);
    }
    
    public List<ItemCompra> getItens() {
        return itens;
    }
    
    public double getTotal(){
        double total = 0;
        for(ItemCompra item : itens){
            total += item.getSubtotal();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Compra{" + "id=" + id + ", usuario=" + usuario + ", itens=" + itens + '}';
    }

    public Document toDocument(){
        Document doc = new Document();
        doc.append("_id", id);
        doc.append("usuario", usuario.toDocument());
        
        List<Document> itensDocument = new ArrayList<>();
        for(ItemCompra i : itens){
            itensDocument.add(i.toDocument());
        }
        
        doc.append("itens", itensDocument);
        
        return doc;
    }
    
    public Compra fromDocument(Document doc){
        
        id = doc.getInteger("_id");
        usuario = new Usuario().fromDocument(doc.get("usuario", Document.class));
        
        List<Document> itensDocument = doc.get("itens",List.class);
        
        for(Document document : itensDocument){
            itens.add(new ItemCompra().fromDocument(document));
        }
        
        return this;
    }
    
}
